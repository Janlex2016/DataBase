/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.menu;

import eg.exceptions.AccessDenied;
import eg.exceptions.CandidateNotFound;
import eg.exceptions.HistoryNotFound;
import eg.exceptions.ListIsEmpty;
import eg.exceptions.UserNotFound;
import eg.exceptions.VotingNotFound;
import eg.models.User;

import java.sql.SQLException;
import javax.swing.*;

public class UserMenu extends BaseMenuFrame {

//    private User currentUser;

    String votingTitle;
    String candidateName;

    private javax.swing.JButton voteButton;
    private javax.swing.JComboBox votingComboBox;
    private javax.swing.JComboBox candidateComboBox;

    @SuppressWarnings("unchecked")
    private void votingComboBoxActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, ListIsEmpty, VotingNotFound, UserNotFound {
        candidateComboBox.setEnabled(false);
        voteButton.setEnabled(false);
        votingTitle = (String) votingComboBox.getModel().getSelectedItem();

        try {
            candidateComboBox.setModel(
                    new javax.swing.DefaultComboBoxModel(
                            userService.getCandidateNameArray(
                                    votingService.getVotingCandidateList(
                                            votingService.getByName(votingTitle).getId()))));

            candidateComboBox.setEnabled(true);
        } catch (NullPointerException e) {
            candidateComboBox.setModel(new javax.swing.DefaultComboBoxModel());
            candidateComboBox.setEnabled(false);
        } catch (SQLException | UserNotFound | VotingNotFound ex) {
            System.out.println(ex);
        }
    }

    private void candidateComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        candidateName = (String) candidateComboBox.getModel().getSelectedItem();
        voteButton.setEnabled(true);
    }

    private void voteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            userService.vote(
                    userService.getCandidateByName(candidateName).getId(),
                    votingService.getByName(votingTitle).getId(),
                    currentUser.getId()
            );
            JOptionPane.showMessageDialog(null, "Your vote has been counted!", "Output", JOptionPane.PLAIN_MESSAGE);
        } catch (AccessDenied ad) {
            JOptionPane.showMessageDialog(null, ad.getMessage(), "Output", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException | CandidateNotFound | VotingNotFound | UserNotFound | HistoryNotFound ex) {
            System.out.println(ex);
        }
    }

    public UserMenu(User currentUser) throws SQLException {
        this.currentUser = currentUser;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() throws SQLException {

        this.setBounds(400, 150, 500, 300);
        JLabel currentUserLabel = new JLabel();
        votingComboBox = new javax.swing.JComboBox();
        candidateComboBox = new javax.swing.JComboBox();
        JButton logOffButton = new JButton();
        voteButton = new javax.swing.JButton();
        setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        currentUserLabel.setText("You are logged as : " + currentUser.getName());

        votingComboBox.setModel(new javax.swing.DefaultComboBoxModel(votingService.getVotionTitleArray()));
        votingComboBox.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    votingComboBoxActionPerformed(evt);
                } catch (SQLException | ListIsEmpty | UserNotFound | VotingNotFound | NullPointerException ex) {
                }
            }
        });

        candidateComboBox.setModel(new javax.swing.DefaultComboBoxModel());
        candidateComboBox.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                candidateComboBoxActionPerformed(evt);
            }
        });

        voteButton.setText("VOTE");
        voteButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voteButtonActionPerformed(evt);
            }
        });

        logOffButton.setText("Log off");
        logOffButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOffButtonActionPerformed(evt);
            }
        });

        candidateComboBox.setEnabled(false);
        voteButton.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(votingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(candidateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(voteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                .addGap(44, 44, 44))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(currentUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(logOffButton)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(currentUserLabel)
                                        .addComponent(logOffButton))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(votingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(candidateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(voteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(356, Short.MAX_VALUE))
        );
        pack();
    }
}