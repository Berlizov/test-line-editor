package com.example.line.editor.action.impl;

import com.example.line.editor.FileController;
import com.example.line.editor.action.ActionResult;
import java.io.IOException;

/**
 * Action to save current file.
 */
public class SaveAction extends BaseActionImpl {
    private static final String KEY = "save";
    private static final String DESCRIPTION
        = "save - saves to disk";
    private static final String IO_ERROR_TEMPLATE = "I/O Problem - ";

    private static final ActionResult SUCCESS_RESULT
        = new ActionResultImpl("File saved.");

    private final FileController mFileController;

    public SaveAction(FileController fileController) {
        mFileController = fileController;
    }

    @Override
    protected ActionResult processWithCorrectArgumentSize(String[] arguments) {
        try {
            mFileController.save();
            return SUCCESS_RESULT;
        } catch (IOException e) {
            return new ActionResultImpl(IO_ERROR_TEMPLATE + e.getMessage(), true);
        }
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
