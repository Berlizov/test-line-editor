package com.example.line.editor.action;

import java.io.IOException;

/**
 * Controller to manage set of {@link Action}.
 */
public interface ActionController {

    /**
     * Returns action descriptions.
     *
     * @return the list of user-focused action descriptions
     */
    String getActionsDescriptions();

    /**
     * Handle user interaction.
     *
     * @throws IOException If an I/O error occurs
     */
    void process() throws IOException;

    /**
     * Returns a result of a last action execution.
     *
     * @return the result of the last action,
     * or <code>null</code> if no action was taken
     */
    ActionResult getLastActionResult();
}
