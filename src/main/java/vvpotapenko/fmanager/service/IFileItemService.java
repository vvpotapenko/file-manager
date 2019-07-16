package vvpotapenko.fmanager.service;

import vvpotapenko.fmanager.model.IFileItem;

import java.util.List;

public interface IFileItemService {

    List<IFileItem> getChildren(IFileItem parent) throws Exception;
}
