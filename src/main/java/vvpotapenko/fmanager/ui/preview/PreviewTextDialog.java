package vvpotapenko.fmanager.ui.preview;

import vvpotapenko.fmanager.Resources;

import javax.swing.*;
import java.awt.*;

public class PreviewTextDialog extends JDialog {

    public PreviewTextDialog(Frame owner, String text) {
        super(owner, Resources.getString("text.preview"), true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 300);

        JTextPane textPane = new JTextPane();
        textPane.setText(text);
        textPane.setEditable(false);
        JScrollPane panel = new JScrollPane(textPane);

        setContentPane(panel);
    }
}
