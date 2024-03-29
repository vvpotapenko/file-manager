package vvpotapenko.fmanager.service;

import vvpotapenko.fmanager.model.IFileItem;

import java.io.InputStream;
import java.util.List;

public interface IFileItemProvider {

    boolean canHandle(IFileItem fileItem);

    List<IFileItem> listFiles(IFileItem directory) throws Exception;

    InputStream createInputStream(IFileItem fileItem) throws Exception;
}
