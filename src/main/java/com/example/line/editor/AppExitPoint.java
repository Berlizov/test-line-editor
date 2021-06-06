package com.example.line.editor;

/**
 * This interface for marking classes with the ability to close app.
 */
public interface AppExitPoint {

    /**
     * Request to exit.
     */
    void exit();
}
