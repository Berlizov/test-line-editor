package com.example.line.editor.action.impl;

import com.example.line.editor.FileController;
import com.example.line.editor.action.ActionResult;
import java.util.List;

/**
 * Action to delete a line in the file.
 */
public class DeleteLineAction extends LineBasedActionImpl {
    private static final String KEY = "del";
    private static final String DESCRIPTION = "del n - delete line at n";

    private static final ActionResultImpl SUCCESS_RESULT
        = new ActionResultImpl("Line deleted.");

    public DeleteLineAction(FileController fileController) {
        super(fileController);
    }

    @Override
    protected ActionResult processLineEdit(List<String> lines, int lineIndex) {
        lines.remove(lineIndex);
        return SUCCESS_RESULT;
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
