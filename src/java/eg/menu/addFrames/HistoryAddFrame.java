package eg.menu.addFrames;

import eg.exceptions.*;
import eg.main.Main;

import java.sql.SQLException;

public class HistoryAddFrame extends BaseAddFrame {

    private static final String titleText = "History add menu";
    private static final String candidateLabelText = "Candidate id";
    private static final String votingLabelText = "Voting id";
    private static final String userLabelText = "User id";
    private javax.swing.JTextField candidateIdField;
    private javax.swing.JTextField votingIdField;

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {

        try {
            historyService.add(Integer.parseInt(candidateIdField.getText()), Integer.parseInt(votingIdField.getText()), Integer.parseInt(votingIdField.getText()));
        } catch (SQLException | AccessDenied | CandidateNotFound | UserNotFound | VotingNotFound | IncorrectInput ex) {
            showMessage(ex.getMessage(), messageDialogTitle);
        } catch (Exception ex) {
            showMessage(inputErrorText, messageDialogTitle);
        } finally {
            Main.getAdminMenu().update();
        }
    }


    public HistoryAddFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        this.setBounds(500, 250, 500, 300);
        javax.swing.JLabel titleMessage = new javax.swing.JLabel();
        javax.swing.JLabel votingIdLabel = new javax.swing.JLabel();
        javax.swing.JLabel userIdLabel = new javax.swing.JLabel();
        javax.swing.JLabel candidateIdLabel = new javax.swing.JLabel();
        javax.swing.JButton addButton = new javax.swing.JButton();
        javax.swing.JButton cancelButton = new javax.swing.JButton();
        candidateIdField = new javax.swing.JTextField();
        votingIdField = new javax.swing.JTextField();
        javax.swing.JTextField userIdField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titleMessage.setText(titleText);
        votingIdLabel.setText(votingLabelText);
        userIdLabel.setText(userLabelText);
        candidateIdLabel.setText(candidateLabelText);
        addButton.setText(addButtonText);
        cancelButton.setText(cancelButtonText);

        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        setVisible(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(candidateIdLabel)
                                                        .addComponent(userIdLabel)
                                                        .addComponent(votingIdLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(candidateIdField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                                        .addComponent(votingIdField, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(userIdField, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addGap(46, 46, 46))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(titleMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(votingIdLabel)
                                        .addComponent(candidateIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(votingIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(userIdLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(userIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(candidateIdLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                        .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        pack();
    }
}

