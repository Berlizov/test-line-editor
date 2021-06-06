package com.example.line.editor.action.impl;

import com.example.line.editor.action.Action;
import com.example.line.editor.action.ActionResult;

/**
 * Base class for {@link Action} with arguments cout check.
 */
public abstract class BaseActionImpl implements Action {
    /**
     * ActionResult for error with incorrect arrguments.
     */
    protected static final ActionResult INCORRECT_ARGS_RESULT
        = new ActionResultImpl("The action arguments could not be read, "
        + "check the command is correct.", true);

    private static final ActionResult INCORRECT_ARG_COUNT_RESULT
        = new ActionResultImpl("The action takes a different number of "
        + "arguments.", true);

    @Override
    public final ActionResult process(String... arguments) {
        if (arguments.length != getExpectedArgumentCount()) {
            return INCORRECT_ARG_COUNT_RESULT;
        }
        return processWithCorrectArgumentSize(arguments);
    }

    protected int getExpectedArgumentCount() {
        return 0;
    }

    protected abstract ActionResult processWithCorrectArgumentSize(String[] arguments);
}
