/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.menu;

import eg.exceptions.AccessDenied;
import eg.exceptions.CandidateNotFound;
import eg.exceptions.HistoryNotFound;
import eg.menu.addFrames.*;
import eg.exceptions.ListIsEmpty;
import eg.exceptions.UserNotFound;
import eg.exceptions.VotingNotFound;
import eg.models.User;
import eg.models.enums.Models;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.*;

public class AdminMenu extends BaseMenuFrame {

    private String title;
    private String votingTitle;
    private String candidateName;
    private static final String noCandidatesInVoting = "No candidates in this voting!";

    private javax.swing.JButton logOffButton;
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton resultsButton;
    private javax.swing.JComboBox votingComboBox;
    private javax.swing.JComboBox UCVComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;


    public AdminMenu(User currentUser) throws SQLException, ListIsEmpty {
        title = "Users";
        this.currentUser = currentUser;
        initComponents();
    }

    private void resultsButtonActionPerformed(java.awt.event.ActionEvent evt) {

        if (title.equals("Votions") && votingTitle.equals("*All*")) {
            try {
                new ResultFrame(votingService.getResultArray(getSelectedIndex()), votingService.getById(getSelectedIndex()).getTitle()).setVisible(true);
            } catch (NullPointerException ex) {
                showMessage(noCandidatesInVoting, messageDialogTitle);
            } catch (SQLException | UserNotFound | VotingNotFound | CandidateNotFound | AccessDenied e) {
                showMessage(e.getMessage(), messageDialogTitle);
                e.printStackTrace();
            }
        }
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        switch (title) {
            case "Users":
                new UserAddFrame();
                break;
            case "Candidates":
                new CandidateAddFrame();
                break;
            case "History":
                new HistoryAddFrame().setVisible(true);
                break;
            case "Votions":
                if (votingTitle.equals("*All*")) {
                    new VotingAddFrame().setVisible(true);
                    break;
                } else
                    try {
                        new CandidateToVotingAddFrame(votingService.getByName(votingTitle).getId());
                    } catch (VotingNotFound | UserNotFound | SQLException ex) {
                        showMessage(ex.getMessage(), messageDialogTitle);
                    }
                break;
            default:
                System.exit(1);
        }
    }

    private int getSelectedIndex() {
        candidateName = jList1.getSelectedValue().toString();
        int selectedIndex = candidateName.indexOf(" ");
        candidateName = candidateName.substring(0, selectedIndex);
        selectedIndex = Integer.parseInt(candidateName);
        return selectedIndex;
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {

        switch (title) {
            case "Users": {
                try {
                    if (getSelectedIndex() == 1) throw new UserNotFound("Don't delete admin");
                    userService.deleteById(getSelectedIndex());
                } catch (SQLException | UserNotFound ex) {
                    showMessage(ex.getMessage(), messageDialogTitle);
                } catch (NullPointerException e) {
//                    e.printStackTrace();
                }
                break;
            }
            case "Candidates": {
                try {
//                    candidateName = jList1.getSelectedValue().toString();
//                    int t = candidateName.indexOf(" ");
//                    candidateName = candidateName.substring(0, t);
                    userService.deleteCandidateById(getSelectedIndex());
                } catch (SQLException ex) {
                    showMessage(ex.getMessage(), messageDialogTitle);
                } catch (NullPointerException e) {
//                    e.printStackTrace();
                }
                break;
            }
            case "History": {
                try {
//                    candidateName = jList1.getSelectedValue().toString();
//                    int t = candidateName.indexOf(" ");
//                    candidateName = candidateName.substring(0, t);
                    historyService.deleteById(getSelectedIndex());
                } catch (SQLException | HistoryNotFound ex) {
                    showMessage(ex.getMessage(), messageDialogTitle);
                } catch (NullPointerException e) {
//                    e.printStackTrace();
                }
                break;
            }
            case "Votions": {
                if (votingTitle.equals("*All*")) {
                    try {
//                        candidateName = jList1.getSelectedValue().toString();
//                        int t = candidateName.indexOf(" ");
//                        candidateName = candidateName.substring(0, t);
                        votingService.deleteById(getSelectedIndex());
                        updateVotingComboBox();
                    } catch (ListIsEmpty lie) {
                        votingComboBox.setModel(
                                new DefaultComboBoxModel(
                                        new String[]{"*All*"}
                                )
                        );
                    } catch (SQLException | VotingNotFound | UserNotFound ex) {
                        showMessage(ex.getMessage(), messageDialogTitle);

                    } catch (NullPointerException e) {
//                        e.printStackTrace();
                    }
                    break;
                } else {
                    try {
                        candidateName = jList1.getSelectedValue().toString();
                        votingService.deleteCandidateFromVoting(userService.getCandidateByName(candidateName).getId(), votingService.getByName(votingTitle).getId());
                        updateVotingComboBox();
                    } catch (SQLException | ListIsEmpty | VotingNotFound | UserNotFound | AccessDenied | CandidateNotFound ex) {
                        showMessage(ex.getMessage(), messageDialogTitle);
                    } catch (NullPointerException e) {
//                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        if (votingComboBox.isEnabled()) {
            updateVoting();
        } else update();
    }

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {

    }

    //TODO AFTER DELETE VOTING


    private void UCHVComboBoxActionPerformed(ActionEvent evt) {
        votingComboBox.setVisible(false);
        votingComboBox.setEnabled(false);
        votingComboBox.setModel(new DefaultComboBoxModel());

        title = (String) UCVComboBox.getModel().getSelectedItem();
        switch (title) {
            case "Users": {

                try {
                    refreshJListAbstractListModel(userService.getUserArray());
                    break;
                } catch (NullPointerException | ListIsEmpty | SQLException e) {
                    jList1.setModel(new DefaultListModel());
                    break;
                }
            }
            case "Candidates": {
                try {
                    refreshJListAbstractListModel(userService.getCandidateArray());
                    break;
                } catch (NullPointerException | ListIsEmpty | SQLException e) {
                    setDefaultJListModel();
                    break;
                }
            }
            case "History": {
                try {
                    refreshJListAbstractListModel(historyService.getHistoryArray());
                    break;
                } catch (NullPointerException | ListIsEmpty | SQLException e) {
                    setDefaultJListModel();
                    break;
                }
            }
            case "Votions": {
                votingComboBox.setVisible(true);
                votingComboBox.setEnabled(true);
                votingTitle = "*All*";
                try {
                    refreshJListAbstractListModel(votingService.getVotingArray());
                    updateVotingComboBox();
                } catch (SQLException | ListIsEmpty e) {
                    setDefaultJListModel();
                    votingComboBox.setModel(
                            new DefaultComboBoxModel(
                                    new String[]{"*All*"}
                            )
                    );

                }
                break;
            }
            default:
                System.exit(1);
        }
    }


    private void votingComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            votingTitle = (String) votingComboBox.getModel().getSelectedItem();
            if (votingTitle.equals("*All*")) {
                refreshJListAbstractListModel(votingService.getVotingArray());
            } else {
                refreshJListAbstractListModel(
                        userService.getCandidateNameArray(
                                votingService.getVotingCandidateList(
                                        votingService.getCandidateByName(votingTitle).getId())));
            }
        } catch (NullPointerException | ListIsEmpty e) {
            setDefaultJListModel();
        } catch (SQLException | UserNotFound | VotingNotFound ex) {
            showMessage(ex.getMessage(), messageDialogTitle);
        }

    }

    public void update() {
        this.UCHVComboBoxActionPerformed(new java.awt.event.ActionEvent(new Object(), 0, title));
    }

    public void updateVoting() {
        this.votingComboBoxActionPerformed(new java.awt.event.ActionEvent(new Object(), 0, votingTitle));
    }

    public void updateVotingComboBox() throws ListIsEmpty, SQLException {
        votingComboBox.setModel(
                new javax.swing.DefaultComboBoxModel(
                        votingService.getVotingTitleArrayWithNull()
                )
        );
    }

    private void refreshJListAbstractListModel(final String[] array) throws ListIsEmpty, SQLException {
        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = array;

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });
    }

    private void setDefaultJListModel() {
        jList1.setModel(new DefaultListModel());
    }

    @SuppressWarnings("unchecked")
    private void initComponents() throws SQLException, ListIsEmpty {

        this.setBounds(400, 150, 500, 300);
        jLabel1 = new javax.swing.JLabel();
        logOffButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        UCVComboBox = new javax.swing.JComboBox();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        resultsButton = new javax.swing.JButton();
        votingComboBox = new javax.swing.JComboBox();
        editButton.setEnabled(false);

        votingComboBox.setEnabled(false);
        votingComboBox.setVisible(false);

        logOffButton.setText("Log off");
        addButton.setText("ADD");
        deleteButton.setText("DELETE");
        editButton.setText("EDIT");
        resultsButton.setText("RESULTS");


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("You are logged as : " + currentUser.getName());

        logOffButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOffButtonActionPerformed(evt);
            }

        });

        refreshJListAbstractListModel(userService.getUserArray());
        jScrollPane1.setViewportView(jList1);

        UCVComboBox.setModel(new javax.swing.DefaultComboBoxModel(
            new String[]{

                    Models.Users.name(),
                    Models.Candidates.name(),
                    Models.History.name(),
                    Models.Votions.name()
            })
        );
        votingComboBox.setModel(new javax.swing.DefaultComboBoxModel());


        UCVComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UCHVComboBoxActionPerformed(evt);
            }
        });
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        resultsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultsButtonActionPerformed(evt);
            }
        });
        votingComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                votingComboBoxActionPerformed(evt);
            }
        });

// <editor-fold defaultstate="collapsed" desc="Generated Code">

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                                .addComponent(logOffButton)
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(UCVComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(votingComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(resultsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(logOffButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(UCVComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(votingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(24, 24, 24)
                                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(resultsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1))
                                .addContainerGap())
        );

        pack();
        this.setVisible(true);
    }

}
