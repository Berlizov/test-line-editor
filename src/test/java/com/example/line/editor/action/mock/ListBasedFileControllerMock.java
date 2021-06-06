package com.example.line.editor.action.mock;

import java.util.Collections;
import java.util.List;

public class ListBasedFileControllerMock extends BaseFileControllerMock {
    List<String> mLines = Collections.emptyList();

    @Override
    public List<String> getLines() {
        return mLines;
    }

    @Override
    public void setLines(List<String> newLines) {
        mLines = Collections.unmodifiableList(newLines);
    }
}
