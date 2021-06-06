package com.example.line.editor.impl;

import com.example.line.editor.AppExitPoint;
import com.example.line.editor.FileController;
import com.example.line.editor.action.ActionController;
import com.example.line.editor.action.impl.ActionControllerImpl;
import com.example.line.editor.ui.UIController;
import com.example.line.editor.ui.impl.UIControllerImpl;
import java.io.File;
import java.io.IOException;

/**
 * Main application class controlling its life cycle.
 */
public class AppControllerImpl implements AppExitPoint {
    private FileController mFileController;
    private UIController mUIController;
    private ActionController mActionController;
    private boolean mExitFlag;

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public void run(String[] args) {
        try {
            String file = getFilePath(args);
            init(file);
            appCircle();
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getFilePath(String[] args) throws IllegalArgumentException {
        if (args.length != 1) {
            throw new IllegalArgumentException("One argument expected - file path.");
        }
        File file = new File(args[0]);
        if (!file.isFile()) {
            throw new IllegalArgumentException("Invalid file path.");
        }
        return file.getPath();
    }

    private void init(String filePath) throws IOException {
        mFileController = new FileControllerImpl(filePath);
        mActionController = new ActionControllerImpl(mFileController, this);
        mUIController
            = new UIControllerImpl(mFileController, mActionController);
    }

    private void appCircle() throws IOException {
        while (!mExitFlag) {
            mUIController.update();
            mActionController.process();
        }
    }

    @Override
    public void exit() {
        mExitFlag = true;
    }
}
