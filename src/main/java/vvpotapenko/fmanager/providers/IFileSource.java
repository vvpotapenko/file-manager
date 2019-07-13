package vvpotapenko.fmanager.providers;

import javax.swing.*;

public interface IFileSource {

    Icon getSystemIcon();

    void destroy();

    String getDisplaySize();
}
