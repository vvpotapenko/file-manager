package vvpotapenko.fmanager.providers;

import javax.swing.*;
import java.io.InputStream;

public interface IFileSource {

    Icon getSystemIcon();

    void destroy();

    String getDisplaySize();

    InputStream createInputStream() throws Exception;
}
