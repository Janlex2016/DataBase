/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.menu.addFrames;

import eg.exceptions.IncorrectInput;
import eg.main.Main;
import eg.models.enums.Access;

import javax.swing.*;
import java.sql.SQLException;

public class CandidateAddFrame extends BaseAddFrame {

    private static final String titleLabelText = "User add menu";
    private static final String nameLabelText = "Name";
    private static final String loginLabelText = "Login";
    private static final String passwordLabelText = "Password";
    private static final String accessLabelText = "Access";
    private String accessComboBoxSelectedItem;
    private JTextField nameTextField;
    private JTextField loginTextField;
    private JTextField passwordTextField;
    private JComboBox accessComboBox;


    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
                userService.addCandidate(
                        nameTextField.getText(),
                        accessComboBoxSelectedItem
                );
        } catch (IncorrectInput | SQLException e) {
            showMessage(e.getMessage(), messageDialogTitle);
        } finally {
            nameTextField.setText("");
            loginTextField.setText("");
            passwordTextField.setText("");
            Main.getAdminMenu().update();
        }
    }

    private void accessComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        accessComboBoxSelectedItem = (String) accessComboBox.getModel().getSelectedItem();
    }

    public CandidateAddFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {
        setBounds(530, 230, 230, 140);
        accessComboBoxSelectedItem = Access.CANDIDATE.name();
        JLabel titleLabel = new JLabel();
        JLabel nameLabel = new JLabel();
        JLabel loginLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        JLabel accessLabel = new JLabel();
        JButton addButton = new JButton();
        JButton cancelButton = new JButton();
        nameTextField = new JTextField();
        loginTextField = new JTextField();
        passwordTextField = new JTextField();
        accessComboBox = new JComboBox();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setText(titleLabelText);
        nameLabel.setText(nameLabelText);
        loginLabel.setText(loginLabelText);
        passwordLabel.setText(passwordLabelText);
        accessLabel.setText(accessLabelText);

        addButton.setText(addButtonText);
        cancelButton.setText(cancelButtonText);
        loginLabel.setEnabled(false);
        passwordLabel.setEnabled(false);
        setVisible(true);

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

        accessComboBox.setModel(
                new DefaultComboBoxModel(
                        new String[]{
                                Access.CANDIDATE.name(),
                        }));
        accessComboBox.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accessComboBoxActionPerformed(evt);
            }
        });

        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(cancelButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addButton)
                                .addGap(57, 57, 57))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(accessLabel)
                                        .addComponent(passwordLabel)
                                        .addComponent(loginLabel)
                                        .addComponent(nameLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(nameTextField, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                                .addComponent(loginTextField, GroupLayout.Alignment.TRAILING)
                                                .addComponent(passwordTextField, GroupLayout.Alignment.TRAILING)
                                                .addComponent(accessComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameLabel)
                                        .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(loginTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(loginLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordLabel))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(accessComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(accessLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(addButton)
                                        .addComponent(cancelButton))
                                .addGap(20, 20, 20))
        );
        pack();
    }
}

