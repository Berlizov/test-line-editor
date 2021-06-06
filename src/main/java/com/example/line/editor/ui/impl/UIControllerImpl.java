package com.example.line.editor.ui.impl;

import com.example.line.editor.FileController;
import com.example.line.editor.action.ActionController;
import com.example.line.editor.action.ActionResult;
import com.example.line.editor.ui.UIController;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Implementation of {@link UIController}.
 */
public class UIControllerImpl implements UIController {
    private final FileController mFileController;
    private final ActionController mActionController;

    /**
     * Constructor for UIControllerImpl.
     *
     * @param fileController the file controller
     * @param actionController the artion controller
     */
    public UIControllerImpl(
        FileController fileController,
        ActionController actionController) {
        mFileController = fileController;
        mActionController = actionController;
    }

    @Override
    public void update() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFileLines());
        sb.append(System.lineSeparator());
        sb.append(getLastActionResult());
        sb.append(System.lineSeparator());
        sb.append(getActionDescriptions());
        sb.append(System.lineSeparator());
        System.out.println(sb);
    }

    private String getFileLines() {
        final List<String> lines = mFileController.getLines();
        return IntStream.range(0, lines.size())
            .mapToObj(index -> formatLine(lines, index))
            .collect(Collectors.joining(System.lineSeparator()));
    }

    private String formatLine(List<String> lines, int index) {
        int lineNumber = index + 1;
        return String.format("%d : %s", lineNumber, lines.get(index));
    }

    private String getLastActionResult() {
        ActionResult result = mActionController.getLastActionResult();
        return result != null ? result.getMessage() : "";
    }

    private String getActionDescriptions() {
        return mActionController.getActionsDescriptions();
    }

}
