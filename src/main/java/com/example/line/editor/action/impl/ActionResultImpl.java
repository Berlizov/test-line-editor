package com.example.line.editor.action.impl;

import com.example.line.editor.action.ActionResult;

/**
 * Implementation for ActionResult.
 */
public class ActionResultImpl implements ActionResult {
    private final boolean mErrorFlag;
    private final String mMessage;

    public ActionResultImpl(String message) {
        this(message, false);
    }

    public ActionResultImpl(String message, boolean error) {
        mErrorFlag = error;
        mMessage = message;
    }

    @Override
    public boolean isError() {
        return mErrorFlag;
    }

    @Override
    public String getMessage() {
        return mMessage;
    }
}
