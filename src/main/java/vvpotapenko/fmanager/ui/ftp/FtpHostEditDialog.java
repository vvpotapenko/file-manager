package vvpotapenko.fmanager.ui.ftp;

import vvpotapenko.fmanager.Resources;
import vvpotapenko.fmanager.service.ftp.storage.FtpHost;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FtpHostEditDialog extends JDialog {

    private final FtpHost ftpHost;

    private JTextField hostnameEdit;
    private JTextField usernameEdit;
    private JTextField passwordEdit;
    private JCheckBox anonCheck;
    private JCheckBox passiveMode;

    private IDialogListener listener;

    public FtpHostEditDialog(Frame owner, FtpHost ftpHost, String title) {
        super(owner, title, true);
        this.ftpHost = ftpHost;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);

        initializeLayout();
        initializeControlValues();
    }

    private void initializeControlValues() {
        hostnameEdit.setText(ftpHost.getHostname());
        if (ftpHost.isAnonymous()) {
            anonCheck.setSelected(true);
        } else {
            usernameEdit.setText(ftpHost.getUsername());
            passwordEdit.setText(ftpHost.getPassword());
        }
        passiveMode.setSelected(ftpHost.isPassiveMode());
    }

    private void initializeLayout() {
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel(layout);
        JLabel hostnameLabel = new JLabel(Resources.getString("hostname"));
        hostnameLabel.setPreferredSize(new Dimension(70, 20));
        hostnameEdit = new JTextField(20);

        JLabel usernameLabel = new JLabel(Resources.getString("username"));
        usernameLabel.setPreferredSize(new Dimension(70, 20));
        usernameEdit = new JTextField(20);

        JLabel passwordLabel = new JLabel(Resources.getString("password"));
        passwordLabel.setPreferredSize(new Dimension(70, 20));
        passwordEdit = new JTextField(20);

        anonCheck = new JCheckBox(Resources.getString("anonymous"));
        anonCheck.addChangeListener(e -> {
            passwordEdit.setEnabled(!anonCheck.isSelected());
            usernameEdit.setEnabled(!anonCheck.isSelected());
        });
        passiveMode = new JCheckBox(Resources.getString("passive.mode"));

        panel.add(hostnameLabel);
        panel.add(hostnameEdit);
        panel.add(usernameLabel);
        panel.add(usernameEdit);
        panel.add(passwordLabel);
        panel.add(passwordEdit);
        panel.add(anonCheck);
        panel.add(passiveMode);

        layout.putConstraint(SpringLayout.WEST, hostnameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, hostnameLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.BASELINE, hostnameLabel, 0, SpringLayout.BASELINE, hostnameEdit);

        layout.putConstraint(SpringLayout.WEST, hostnameEdit, 10, SpringLayout.EAST, hostnameLabel);
        layout.putConstraint(SpringLayout.NORTH, hostnameEdit, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, usernameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, usernameLabel, 10, SpringLayout.SOUTH, hostnameLabel);
        layout.putConstraint(SpringLayout.BASELINE, usernameLabel, 0, SpringLayout.BASELINE, usernameEdit);

        layout.putConstraint(SpringLayout.WEST, usernameEdit, 10, SpringLayout.EAST, usernameLabel);
        layout.putConstraint(SpringLayout.NORTH, usernameEdit, 10, SpringLayout.SOUTH, hostnameEdit);

        layout.putConstraint(SpringLayout.WEST, passwordLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, passwordLabel, 10, SpringLayout.SOUTH, usernameLabel);
        layout.putConstraint(SpringLayout.BASELINE, passwordLabel, 0, SpringLayout.BASELINE, passwordEdit);

        layout.putConstraint(SpringLayout.WEST, passwordEdit, 10, SpringLayout.EAST, passwordLabel);
        layout.putConstraint(SpringLayout.NORTH, passwordEdit, 10, SpringLayout.SOUTH, usernameEdit);

        layout.putConstraint(SpringLayout.WEST, anonCheck, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, anonCheck, 10, SpringLayout.SOUTH, passwordLabel);

        layout.putConstraint(SpringLayout.WEST, passiveMode, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, passiveMode, 10, SpringLayout.SOUTH, anonCheck);

        JPanel buttons = new JPanel();
        JButton button = new JButton(Resources.getString("save"));
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveClicked();
            }
        });
        buttons.add(button);
        button = new JButton(Resources.getString("cancel"));
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttons.add(button);

        panel.add(buttons);

        layout.putConstraint(SpringLayout.WEST, buttons, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, buttons, 10, SpringLayout.SOUTH, passiveMode);

        setContentPane(panel);
    }

    public void setListener(IDialogListener listener) {
        this.listener = listener;
    }

    private void saveClicked() {
        updateHostFields();

        if (this.listener != null) {
            this.listener.saveClicked();
        }
        dispose();
    }

    private void updateHostFields() {
        ftpHost.setHostname(hostnameEdit.getText());
        if (anonCheck.isSelected()) {
            ftpHost.setUsername(null);
            ftpHost.setPassword(null);
            ftpHost.setAnonymous(true);
        } else {
            ftpHost.setUsername(usernameEdit.getText());
            ftpHost.setPassword(passwordEdit.getText());
            ftpHost.setAnonymous(false);
        }
        ftpHost.setPassiveMode(passiveMode.isSelected());
    }

    public interface IDialogListener {

        void saveClicked();
    }
}
