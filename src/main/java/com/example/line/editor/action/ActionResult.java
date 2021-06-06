package com.example.line.editor.action;

/**
 * Result of {@link Action} execution.
 * It can be successful or with error.
 */
public interface ActionResult {
    /**
     * Checking the success of the {@link Action}.
     *
     * @return <code>true</code> if this is result with error.
     *  <code>false</code> if this is successful result.
     */
    boolean isError();

    /**
     * Returns a message to the user describing the completed {@link Action}.
     *
     * @return user-focused message string
     */
    String getMessage();
}
