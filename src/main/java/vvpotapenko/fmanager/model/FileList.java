package vvpotapenko.fmanager.model;

import java.util.ArrayList;
import java.util.List;

public class FileList {

    private final IFileItem loadingFileItem = new LoadingFileItem();

    private IFileItem currentDirectory;
    private List<IFileItem> items = new ArrayList<>();

    public IFileItem getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(IFileItem currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public List<IFileItem> getItems() {
        return items;
    }

    public void setItems(List<IFileItem> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    public void showLoading() {
        this.items.clear();
        this.items.add(loadingFileItem);
    }
}
