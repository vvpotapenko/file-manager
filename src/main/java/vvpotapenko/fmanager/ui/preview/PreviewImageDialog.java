package vvpotapenko.fmanager.ui.preview;

import javax.swing.*;
import java.awt.*;

public class PreviewImageDialog extends JDialog {

    public PreviewImageDialog(Frame owner, String title, Image img) {
        super(owner, title, true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 300);

        ImageIcon icon = new ImageIcon(img);
        JLabel label = new JLabel(icon);
        JScrollPane panel = new JScrollPane(label);

        setContentPane(panel);
    }
}
