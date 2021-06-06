package com.example.line.editor;

import com.example.line.editor.impl.AppControllerImpl;

/**
 * App entry point.
 */
public abstract class App {

    /**
     * App entry point.
     *
     * @param args the command line arguments of the program
     */
    public static void main(String[] args) {
        new AppControllerImpl().run(args);
    }
}
