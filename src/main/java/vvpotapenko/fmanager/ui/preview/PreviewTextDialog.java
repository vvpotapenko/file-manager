package vvpotapenko.fmanager.ui.preview;

import javax.swing.*;
import java.awt.*;

public class PreviewTextDialog extends JDialog {

    public PreviewTextDialog(Frame owner, String title, String text) {
        super(owner, title, true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 300);

        JTextPane textPane = new JTextPane();
        textPane.setText(text);
        textPane.setEditable(false);
        JScrollPane panel = new JScrollPane(textPane);

        setContentPane(panel);
    }
}
