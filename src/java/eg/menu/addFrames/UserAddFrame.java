/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.menu.addFrames;

import eg.exceptions.IncorrectInput;
import eg.main.Main;
import eg.models.enums.Access;

import java.sql.SQLException;
import javax.swing.*;

public class UserAddFrame extends BaseAddFrame {

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
            userService.addUser(
                    nameTextField.getText(),
                    loginTextField.getText(),
                    passwordTextField.getText(),
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

    public UserAddFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {
        setBounds(530, 230, 230, 140);
        accessComboBoxSelectedItem = Access.USER.name();
        JLabel titleLabel = new JLabel();
        JLabel nameLabel = new JLabel();
        JLabel loginLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        JLabel accessLabel = new JLabel();
        JButton addButton = new JButton();
        JButton cancelButton = new JButton();
        nameTextField = new javax.swing.JTextField();
        loginTextField = new javax.swing.JTextField();
        passwordTextField = new javax.swing.JTextField();
        accessComboBox = new JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setText(titleLabelText);
        nameLabel.setText(nameLabelText);
        loginLabel.setText(loginLabelText);
        passwordLabel.setText(passwordLabelText);
        accessLabel.setText(accessLabelText);

        addButton.setText(addButtonText);
        cancelButton.setText(cancelButtonText);
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
                new javax.swing.DefaultComboBoxModel(
                        new String[]{
                                Access.USER.name(),
                                Access.ADMIN.name(),
                        }));
        accessComboBox.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accessComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(cancelButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addButton)
                                .addGap(57, 57, 57))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(accessLabel)
                                        .addComponent(passwordLabel)
                                        .addComponent(loginLabel)
                                        .addComponent(nameLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(nameTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                                .addComponent(loginTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(passwordTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(accessComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameLabel)
                                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(loginLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordLabel))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(accessComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(accessLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addButton)
                                        .addComponent(cancelButton))
                                .addGap(20, 20, 20))
        );
        pack();
    }
}

