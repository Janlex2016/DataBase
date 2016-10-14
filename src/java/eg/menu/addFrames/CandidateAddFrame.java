/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.menu.addFrames;

import eg.exceptions.AccessDenied;
import eg.exceptions.CandidateNotFound;
import eg.exceptions.UserNotFound;
import eg.exceptions.VotingNotFound;
import eg.main.Main;
import eg.service.UserService;
import eg.service.VotingService;
import eg.serviceImpl.ServiceFactory;

import java.sql.SQLException;

public class CandidateAddFrame extends BaseAddFrame {

    private int votingId;
    private static final String titleText = "Add candidate to voting";
    private javax.swing.JList candidateList;


    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (candidateList.getSelectedValue() != null) {
            try {
                votingService.addCandidateToVotion(
                        userService.getCandidateByName(
                                candidateList.getSelectedValue().toString()).getId(), votingId);
                refreshCandidateList();
            } catch (AccessDenied | CandidateNotFound | SQLException | UserNotFound | VotingNotFound ex) {
                showMessage(ex.getMessage(), messageDialogTitle);
            } finally {
                Main.getAdminMenu().updateVotion();
            }
        }
    }

    private void refreshCandidateList(){
        try {
            String[] candidateArray = userService.getCandidateNameArray(
                    votingService.getPossibleCandidateList(votingId));
            candidateList.setModel(createModel(candidateArray));
        } catch (SQLException | UserNotFound e) {
            showMessage(e.getMessage(), messageDialogTitle);
        }
    }

    public CandidateAddFrame(int votingId){
        this.votingId = votingId;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents(){

        setBounds(500, 250, 500, 300);
        javax.swing.JLabel titleMessage = new javax.swing.JLabel();
        javax.swing.JButton addButton = new javax.swing.JButton();
        javax.swing.JButton cancelButton = new javax.swing.JButton();
        javax.swing.JScrollPane candidatePane = new javax.swing.JScrollPane();
        candidateList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titleMessage.setText(titleText);
        addButton.setText(addButtonText);
        cancelButton.setText(cancelButtonText);
        candidatePane.setViewportView(candidateList);


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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(titleMessage)
                                .addGap(69, 69, 69))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(36, 52, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(candidatePane, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(candidatePane, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                        .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        refreshCandidateList();
        setVisible(true);
    }
}
