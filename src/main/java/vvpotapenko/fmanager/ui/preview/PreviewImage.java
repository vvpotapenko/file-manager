package vvpotapenko.fmanager.ui.preview;

import javax.swing.*;
import java.awt.*;

class PreviewImage extends JLabel {

    void setImage(Image img) {
        ImageIcon icon = new ImageIcon(img);
        setIcon(icon);
    }
}
