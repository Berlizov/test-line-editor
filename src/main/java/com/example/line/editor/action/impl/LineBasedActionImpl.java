package com.example.line.editor.action.impl;

import com.example.line.editor.FileController;
import com.example.line.editor.action.ActionResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class for {@link Action} for line edit.
 */
public abstract class LineBasedActionImpl extends BaseActionImpl {
    private static final ActionResult INCORRECT_LINE_RANGE
        = new ActionResultImpl("Wrong line number entered.", true);
    private final FileController mFileController;

    public LineBasedActionImpl(FileController fileController) {
        mFileController = fileController;
    }

    @Override
    protected final ActionResult processWithCorrectArgumentSize(String[] arguments) {
        try {
            int lineNumber = Integer.parseInt(arguments[0]);
            int lineIndex = lineNumber - 1;
            List<String> lines = new ArrayList<>(mFileController.getLines());
            ActionResult result = processLineEdit(lines, lineIndex);
            if (!result.isError()) {
                mFileController.setLines(lines);
            }
            return result;
        } catch (NumberFormatException e) {
            return INCORRECT_ARGS_RESULT;
        } catch (IndexOutOfBoundsException e) {
            return INCORRECT_LINE_RANGE;
        }
    }

    /**
     * Process line edit.
     *
     * @param lines file lines
     * @param lineIndex line index
     * @return {@link ActionResult} of execution
     */
    protected abstract ActionResult processLineEdit(List<String> lines, int lineIndex);

    @Override
    protected int getExpectedArgumentCount() {
        return 1;
    }
}
