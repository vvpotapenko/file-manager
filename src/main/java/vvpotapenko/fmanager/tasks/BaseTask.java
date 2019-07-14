package vvpotapenko.fmanager.tasks;

import vvpotapenko.fmanager.Application;

import javax.swing.*;

abstract class BaseTask<T, V> extends SwingWorker<T, V> {

    final Application app;

    BaseTask(Application app) {
        this.app = app;
    }

    private void handleException(Exception e) {
        e.printStackTrace();
        // TODO handle exception
    }

    @Override
    protected void done() {
        try {
            T t = get();
            handleResult(t);
        } catch (Exception e) {
            handleException(e);
        }
    }

    abstract void handleResult(T result);
}
