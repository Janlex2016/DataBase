package eg.menu.addFrames;

import eg.exceptions.IncorrectInput;
import eg.exceptions.ListIsEmpty;
import eg.main.Main;

import java.sql.SQLException;

public class VotingAddFrame extends BaseAddFrame {

    private static final String menuTitleLabelText = "Voting add menu";
    private static final String votingTitleLabelText = "Voting title";
    private javax.swing.JTextField titleTextField;


    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            votingService.add(titleTextField.getText());
            titleTextField.setText("");
        } catch (SQLException | IncorrectInput ex) {
            showMessage(ex.getMessage(), messageDialogTitle);
        }finally {
            Main.getAdminMenu().updateVoting();
            try {
                Main.getAdminMenu().updateVotingComboBox();
            } catch (ListIsEmpty listIsEmpty) {
                listIsEmpty.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();//TODO EXCEPTIONS
            }
        }
    }

    public VotingAddFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        this.setBounds(500, 250, 500, 300);
        javax.swing.JLabel menuTitleLabel = new javax.swing.JLabel();
        javax.swing.JLabel votingTitleLabel = new javax.swing.JLabel();
        javax.swing.JButton addButton = new javax.swing.JButton();
        titleTextField = new javax.swing.JTextField();
        javax.swing.JButton cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        menuTitleLabel.setText(menuTitleLabelText);
        votingTitleLabel.setText(votingTitleLabelText);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addComponent(votingTitleLabel)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(9, 9, 9)
                                                                .addComponent(menuTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap(61, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(menuTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(votingTitleLabel))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }
}

