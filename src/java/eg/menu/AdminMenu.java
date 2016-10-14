/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.menu;

import eg.exceptions.AccessDenied;
import eg.exceptions.CandidateNotFound;
import eg.exceptions.HistoryNotFound;
import eg.menu.addFrames.UserAddFrame;
import eg.exceptions.ListIsEmpty;
import eg.exceptions.UserNotFound;
import eg.exceptions.VotingNotFound;
import eg.menu.addFrames.CandidateAddFrame;
import eg.menu.addFrames.HistoryAddFrame;
import eg.menu.addFrames.ResultFrame;
import eg.menu.addFrames.VotingAddFrame;
import eg.models.History;
import eg.models.User;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class AdminMenu extends BaseMenuFrame {

//    private User currentUser;
//
//    private UserService userService = ServiceFactory.getUserService();
//    private HistoryService historyService = ServiceFactory.getHistoryService();
//    private VotingService votingService = ServiceFactory.getVotingService();

    private String title;
    private String votionTitle;
    private String candidateName;

    public AdminMenu(User currentUser) throws SQLException, ListIsEmpty {
        title = "Users";
        this.currentUser = currentUser;
        initComponents();
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
        votionComboBox = new javax.swing.JComboBox();
        editButton.setEnabled(false);

        votionComboBox.setEnabled(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("You are logged as : " + currentUser.getName());

        logOffButton.setText("Log off");
        logOffButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOffButtonActionPerformed(evt);
            }

        });

        jList1.setModel(new javax.swing.AbstractListModel() {

            String[] strings = userService.getUserArray();

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane1.setViewportView(jList1);

        UCVComboBox.setModel(new javax.swing.DefaultComboBoxModel(
                        new String[]{"Users", "History", "Votions"})
        );
        UCVComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jComboBox3ActionPerformed(evt);
                } catch (SQLException | ListIsEmpty ex) {
                }
            }
        });

        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        editButton.setText("EDIT");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        resultsButton.setText("RESULTS");
        resultsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultsButtonActionPerformed(evt);
            }
        });

        votionComboBox.setModel(new javax.swing.DefaultComboBoxModel());
        votionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                votionComboBoxActionPerformed(evt);
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
                                                                .addComponent(votionComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                                        .addComponent(votionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    // </editor-fold>



    private void resultsButtonActionPerformed(java.awt.event.ActionEvent evt) {

        if (title.equals("Votions") && votionTitle.equals("*All*")) {
                try {


                    candidateName = jList1.getSelectedValue().toString();
                    int t = candidateName.indexOf(" ");
                    candidateName = candidateName.substring(0, t);
                    int votingId = Integer.parseInt(candidateName);
                    String votingTitle = votingService.getById(votingId).getTitle();
                    //--------------------------
                    List<History> historyList = historyService.countVoices(votingId);
                    String[] history = new String[historyList.size()];
                    for (int i = 0; i < historyList.size(); i++) {
                        history[i] = "Candidate : " + userService.getById(historyList.get(i).getCandidateId()).getName() + ", " + historyList.get(i).getVotionId() + " голосов";
                    }

                    //--------------------------
                    new ResultFrame(history, votingTitle).setVisible(true);
                }catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(null, "No candidates in this voting!", "Error", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException | UserNotFound | VotingNotFound e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
                }
        }
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        switch (title) {
            case "Users":
                userAddFrame = new UserAddFrame();
                break;
            case "History":
                new HistoryAddFrame().setVisible(true);
                break;
            case "Votions":
                if (votionTitle.equals("*All*")) {
                    new VotingAddFrame().setVisible(true);
                    break;
                } else
                    try {
                        new CandidateAddFrame(votingService.getByName(votionTitle).getId());
                    } catch (VotingNotFound | UserNotFound | SQLException ex) {
                        System.out.println(ex);
                        System.out.println(votionTitle);
                    }
                break;
            default:
                System.exit(1);
        }
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {

        switch (title) {

            case "Users": {
                try {
                    candidateName = jList1.getSelectedValue().toString();
                    int t = candidateName.indexOf(" ");
                    candidateName = candidateName.substring(0, t);
                    votingService.deleteByCandidateId(Integer.parseInt(candidateName));
                    userService.deleteById(Integer.parseInt(candidateName));
                } catch (SQLException | UserNotFound ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Output", JOptionPane.PLAIN_MESSAGE);
                    System.out.println(ex);

                } catch (NullPointerException e) {
                }
                break;
            }
            case "History": {
                try {
                    candidateName = jList1.getSelectedValue().toString();
                    int t = candidateName.indexOf(" ");
                    candidateName = candidateName.substring(0, t);
                    historyService.deleteById(Integer.parseInt(candidateName));
                    JOptionPane.showMessageDialog(null, "Done!", "Output", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException | HistoryNotFound ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Output", JOptionPane.PLAIN_MESSAGE);
                } catch (NullPointerException e) {
                }
                break;
            }
            case "Votions": {
                if (votionTitle.equals("*All*")) {
                    try {
                        candidateName = jList1.getSelectedValue().toString();
                        int t = candidateName.indexOf(" ");
                        candidateName = candidateName.substring(0, t);
                        votingService.deleteById(Integer.parseInt(candidateName));
                        JOptionPane.showMessageDialog(null, "Done!", "Output", JOptionPane.PLAIN_MESSAGE);
                    } catch (SQLException | VotingNotFound | UserNotFound ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Output", JOptionPane.PLAIN_MESSAGE);
                    } catch (NullPointerException e) {
                    }
                    break;
                } else {
                    try {
                        candidateName = jList1.getSelectedValue().toString();
                        votingService.deleteCandidateFromVotion(userService.getCandidateByName(candidateName).getId(), votingService.getByName(votionTitle).getId());
                        JOptionPane.showMessageDialog(null, "Done!", "Output", JOptionPane.PLAIN_MESSAGE);
                    } catch (SQLException | VotingNotFound | UserNotFound | AccessDenied | CandidateNotFound ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Output", JOptionPane.PLAIN_MESSAGE);
                    } catch (NullPointerException e) {
                    }
                    break;
                }
            }
        }
        if(votionComboBox.isEnabled()) updateVotion();
        else update();
    }

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, ListIsEmpty {

        title = (String) UCVComboBox.getModel().getSelectedItem();

        switch (title) {
            case "Users": {
                try {
                    votionComboBox.setEnabled(false);
                    jList1.setModel(new javax.swing.AbstractListModel() {
                        String[] strings = userService.getUserArray();

                        public int getSize() {
                            return strings.length;
                        }

                        public Object getElementAt(int i) {
                            return strings[i];
                        }
                    });
                    votionComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                    votionComboBox.setEnabled(false);
                    break;
                } catch (NullPointerException e) {
                    jList1.setModel(new javax.swing.AbstractListModel() {
                        String[] strings = {""};

                        public int getSize() {
                            return strings.length;
                        }

                        public Object getElementAt(int i) {
                            return strings[i];
                        }
                    });
                    break;
                }
            }
            case "History": {
                try {
                    votionComboBox.setEnabled(false);
                    jList1.setModel(new javax.swing.AbstractListModel() {
                        String[] strings = historyService.getHistoryArray();

                        public int getSize() {
                            return strings.length;
                        }

                        public Object getElementAt(int i) {
                            return strings[i];
                        }
                    });
                    votionComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                    votionComboBox.setEnabled(false);
                    break;
                } catch (NullPointerException e) {
                    jList1.setModel(new javax.swing.AbstractListModel() {
                        String[] strings = {""};

                        public int getSize() {
                            return strings.length;
                        }

                        public Object getElementAt(int i) {
                            return strings[i];
                        }
                    });
                    break;
                }
            }
            case "Votions": {
                votionTitle = "*All*";
                jList1.setModel(new javax.swing.AbstractListModel() {
                    String[] strings = votingService.getVotionArray();

                    //                    String[] strings = votionService.getAll().;
                    public int getSize() {
                        return strings.length;
                    }

                    public Object getElementAt(int i) {
                        return strings[i];
                    }
                });

                votionComboBox.setModel(
                        new javax.swing.DefaultComboBoxModel(
                                votingService.getVotionTitleArrayWithNull()
                        )
                );
                votionComboBox.setEnabled(true);
                break;
            }
            default:
                System.exit(1);
        }
    }

    private void votionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            votionTitle = (String)votionComboBox.getModel().getSelectedItem();
            if (votionTitle.equals("*All*")) {
                jList1.setModel(new javax.swing.AbstractListModel() {
                    String[] strings = votingService.getVotionArray();

                    public int getSize() {
                        return strings.length;
                    }

                    public Object getElementAt(int i) {
                        return strings[i];
                    }
                });
            } else {
                jList1.setModel(new javax.swing.AbstractListModel() {
                    String[] strings = userService.getCandidateNameArray(
                            votingService.getVotingCandidateList(
                                    votingService.getByName(votionTitle).getId()));

                    public int getSize() {
                        return strings.length;
                    }

                    public Object getElementAt(int i) {
                        return strings[i];
                    }
                });
            }
        } catch (NullPointerException e) {
            jList1.setModel(new javax.swing.AbstractListModel() {
                String[] strings = {""};

                public int getSize() {
                    return strings.length;
                }

                public Object getElementAt(int i) {
                    return strings[i];
                }
            });
        } catch (SQLException | UserNotFound | VotingNotFound ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Output", JOptionPane.PLAIN_MESSAGE);
        }

    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AdminMenu(new User(0, "name", "log", "pass", ("USER"))).setVisible(true);
                } catch (SQLException | ListIsEmpty ex) {
                }
            }
        });
    }

    private javax.swing.JButton logOffButton;
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton resultsButton;
    private javax.swing.JComboBox votionComboBox;
    private javax.swing.JComboBox UCVComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private UserAddFrame userAddFrame;

    public void update() {
        try {
            this.jComboBox3ActionPerformed(new java.awt.event.ActionEvent(new Object(), 0, title));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ListIsEmpty listIsEmpty) {
            listIsEmpty.printStackTrace();
        }
    }
    public void updateVotion() {
        System.out.println("voting update");
            this.votionComboBoxActionPerformed(new java.awt.event.ActionEvent(new Object(), 0, votionTitle));

    }
}
