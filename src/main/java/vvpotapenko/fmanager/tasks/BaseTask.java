package vvpotapenko.fmanager.tasks;

import javax.swing.*;

public abstract class BaseTask<T, V> extends SwingWorker<T, V> {

    void handleException(Exception e) {
        // TODO handle exception
    }
}
