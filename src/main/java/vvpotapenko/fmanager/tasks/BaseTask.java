package vvpotapenko.fmanager.tasks;

import vvpotapenko.fmanager.Application;

import javax.swing.*;

abstract class BaseTask<T, V> extends SwingWorker<T, V> {

    final Application app;

    BaseTask(Application app) {
        this.app = app;
    }

    private void handleException(Exception e) {
        app.handleException(e);
    }

    @Override
    protected void done() {
        try {
            T t = get();
            afterJob(t);
        } catch (Exception e) {
            handleException(e);
        }
    }

    void afterJob(T result) {
        // do nothing by default
    }
}
