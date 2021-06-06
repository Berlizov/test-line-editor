package com.example.line.editor.action.impl;

import com.example.line.editor.FileController;
import com.example.line.editor.action.ActionResult;
import java.util.List;

/**
 * Action to add a new line in the file.
 */
public class AddLineAction extends LineBasedActionImpl {
    private static final String EMPTY_STRING = "";
    private static final String KEY = "ins";
    private static final String DESCRIPTION = "ins n - insert a line at n";

    private static final ActionResultImpl SUCCESS_RESULT
        = new ActionResultImpl("New line added.");

    public AddLineAction(FileController fileController) {
        super(fileController);
    }

    @Override
    protected ActionResult processLineEdit(List<String> lines, int lineIndex) {
        lines.add(lineIndex, EMPTY_STRING);
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
