package vvpotapenko.fmanager.ui.preview;

import vvpotapenko.fmanager.Resources;

import javax.swing.*;
import java.awt.*;

public class PreviewPanel extends JPanel {

    private JLabel label;
    private JScrollPane scrollPane;

    private PreviewImage previewImage;
    private PreviewText previewText;

    public PreviewPanel() {
        super(new BorderLayout());

        label = new JLabel();
        label.setText(Resources.getString("no.data"));
        add(label, BorderLayout.NORTH);

        scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);
    }

    public void clear() {
        scrollPane.setViewportView(null);
        label.setText(Resources.getString("no.data"));
    }

    public void showPreviewImage(Image image, String name) {
        if (previewImage == null) {
            previewImage = new PreviewImage();
        }
        previewImage.setImage(image);

        scrollPane.setViewportView(previewImage);
        label.setText(name);
    }

    public void showPreviewText(String text, String name) {
        if (previewText == null) {
            previewText = new PreviewText();
        }
        previewText.setText(text);

        scrollPane.setViewportView(previewText);
        label.setText(name);
    }
}
