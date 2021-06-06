package com.example.line.editor.action.mock;

import java.io.IOException;
import java.util.List;

import com.example.line.editor.FileController;

public abstract class BaseFileControllerMock implements FileController {

    @Override
    public List<String> getLines() {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public void setLines(List<String> newLines) {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public void save() throws IOException {
        throw new UnsupportedOperationException("Method is not implemented");
    }
    
}
