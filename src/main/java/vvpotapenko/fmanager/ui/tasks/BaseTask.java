package vvpotapenko.fmanager.ui.tasks;

import javax.swing.*;

abstract class BaseTask<T, V> extends SwingWorker<T, V> {

    void handleException(Exception e) {
        // TODO handle exception
    }
}
