package com.example.line.editor.action.impl;

import com.example.line.editor.AppExitPoint;
import com.example.line.editor.action.ActionResult;

/**
 * Action to close current application.
 */
public class ExitAction extends BaseActionImpl {
    private static final String KEY = "quit";
    private static final String DESCRIPTION
        = "quit - quits the editor and returns to the command line";

    private static final ActionResult SUCCESS_RESULT
        = new ActionResultImpl("Close");

    private final AppExitPoint mAppExitPoint;

    public ExitAction(AppExitPoint appExitPoint) {
        mAppExitPoint = appExitPoint;
    }

    @Override
    protected ActionResult processWithCorrectArgumentSize(String[] arguments) {
        mAppExitPoint.exit();
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
