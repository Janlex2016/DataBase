package eg.menu;

import eg.exceptions.AccessDenied;
import eg.exceptions.CandidateNotFound;
import eg.exceptions.HistoryNotFound;
import eg.menu.addFrames.*;
import eg.exceptions.ListIsEmpty;
import eg.exceptions.UserNotFound;
import eg.exceptions.VotingNotFound;
import eg.models.User;

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
    private javax.swing.JLabel currentUserLabel;
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
        try {
            switch (title) {
                case "Users": {
                    if (getSelectedIndex() == 1) throw new UserNotFound("Don't delete admin");
                    userService.deleteById(getSelectedIndex());
                    break;
                }
                case "Candidates": {
                    userService.deleteCandidateById(getSelectedIndex());
                    break;
                }
                case "History": {
                    historyService.deleteById(getSelectedIndex());
                    break;
                }
                case "Votions": {
                    System.out.println(votingTitle);
                    if (votingTitle.equals("*All*")) {
                        try {
                            votingService.deleteById(getSelectedIndex());
                            updateVotingComboBox();
                        } catch (ListIsEmpty lie) {
                            setDefaultVotingComboBox();
                        }
                        break;
                    } else {
                        candidateName = jList1.getSelectedValue().toString();
                        System.out.println(candidateName);
                        votingService.deleteCandidateFromVoting(userService.getCandidateByName(candidateName).getId(), votingService.getByName(votingTitle).getId());
                        updateVotingComboBox();
                        break;
                    }
                }
            }
        } catch (SQLException | ListIsEmpty | HistoryNotFound | VotingNotFound | UserNotFound | AccessDenied | CandidateNotFound ex) {
            showMessage(ex.getMessage(), messageDialogTitle);
            ex.printStackTrace();
        } catch (NullPointerException e) {
        }

        if (votingComboBox.isVisible()) {
            System.out.println("updated"+votingTitle);
            updateVoting();
        } else {
            update();
        }
    }

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void UCHVComboBoxActionPerformed(ActionEvent evt) {
        votingComboBox.setVisible(false);
        votingComboBox.setEnabled(false);
        votingComboBox.setModel(new DefaultComboBoxModel());

        title = (String) UCVComboBox.getModel().getSelectedItem();

        try {
            switch (title) {
                case "Users": {
                    refreshJListAbstractListModel(userService.getUserArray());
                    break;
                }
                case "Candidates": {
                    refreshJListAbstractListModel(userService.getCandidateArray());
                    break;
                }
                case "History": {
                    refreshJListAbstractListModel(historyService.getHistoryArray());
                    break;
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
                        setDefaultVotingComboBox();
                    }
                    break;
                }
                default:
                    System.exit(1);
            }
        } catch (NullPointerException | ListIsEmpty | SQLException ex) {
            setDefaultJListModel();
        }
    }


    private void votingComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            votingTitle = (String) votingComboBox.getModel().getSelectedItem();
            if (votingTitle.equals("*All*")) {
                refreshJListAbstractListModel(votingService.getVotingArray());
            } else {
                refreshJListAbstractListModel(
                        votingService.getCandidatesDedicatedToVoting(votingTitle)
//                        userService.getCandidateNameArray(
//                                votingService.getVotingCandidateList(
//                                        votingService.getCandidateByName(votingTitle).getId()
//                                )
                );
            }
        } catch (NullPointerException | ListIsEmpty e) {
            setDefaultJListModel();
        } catch (SQLException ex) {
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
        String tempTitle = votingTitle;
        votingComboBox.setModel(
                new javax.swing.DefaultComboBoxModel(
                        votingService.getVotingTitleArrayWithNull()
                )
        );
        votingTitle = tempTitle;
        votingComboBox.getModel().setSelectedItem(tempTitle);
    }

    public void setDefaultVotingComboBox() {
        votingComboBox.setModel(
                new DefaultComboBoxModel(
                        new String[]{"*All*"}
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
        currentUserLabel = new javax.swing.JLabel();
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

        currentUserLabel.setText("You are logged as : " + currentUser.getName());

        logOffButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOffButtonActionPerformed(evt);
            }

        });

        refreshJListAbstractListModel(userService.getUserArray());
        jScrollPane1.setViewportView(jList1);

        UCVComboBox.setModel(new javax.swing.DefaultComboBoxModel(
                        new String[]{
                                "Users",
                                "Candidates",
                                "History",
                                "Votions"
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
                                                .addComponent(currentUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(currentUserLabel)
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
