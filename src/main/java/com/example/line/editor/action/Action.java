package com.example.line.editor.action;

/**
 * Interface for user actions.
 */
public interface Action {
    /**
     * Handle user interaction.
     *
     * @param arguments the arguments of action
     * @return {@link ActionResult} of execution
     */
    ActionResult process(String... arguments);

    /**
     * Returns keyword.
     * Used to find the given action.
     *
     * @see ActionController#process()
     */
    String getKey();

    /**
     * Returns user-focused description.
     */
    String getDescription();
}
