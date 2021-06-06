package com.example.line.editor.action.impl;

import com.example.line.editor.AppExitPoint;
import com.example.line.editor.FileController;
import com.example.line.editor.action.Action;
import com.example.line.editor.action.ActionController;
import com.example.line.editor.action.ActionResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of {@link ActionController}.
 */
public class ActionControllerImpl implements ActionController {
    private static final ActionResult CANNOT_FIND_ACTION_RESULT
        = new ActionResultImpl("Action not found.", true);

    private final List<Action> mActions;
    private final BufferedReader mCMDReader;

    private ActionResult mLastResult;

    /**
     * Constructor for ActionControllerImpl.
     *
     * @param fileController the file controller
     * @param appExitPoint the application exit point
     */
    public ActionControllerImpl(
        FileController fileController,
        AppExitPoint appExitPoint) {
        mCMDReader = new BufferedReader(new InputStreamReader(System.in));
        mActions = Arrays.asList(
            new AddLineAction(fileController),
            new DeleteLineAction(fileController),
            new SaveAction(fileController),
            new ExitAction(appExitPoint)
        );
    }

    /**
     * Constructor of ActionControllerImpl for testing.
     *
     * @param reader the commands reader
     * @param actions list of available actions
     */
    public ActionControllerImpl(BufferedReader reader, List<Action> actions) {
        mCMDReader = reader;
        mActions = actions;
    }

    @Override
    public String getActionsDescriptions() {
        return mActions.stream()
            .map(action -> action.getDescription())
            .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public void process() throws IOException {
        String commandLine = mCMDReader.readLine();
        String[] commandArgs = commandLine.trim().split(" ");
        ActionResult result = null;
        if (commandArgs.length > 0) {
            result = process(commandArgs);
        }
        mLastResult = result != null ? result : CANNOT_FIND_ACTION_RESULT;
    }

    private ActionResult process(String[] commandArgs) {
        String key = commandArgs[0];
        Optional<Action> optional = mActions.stream()
            .filter(action -> Objects.equals(action.getKey(), key))
            .findAny();
        if (optional.isPresent()) {
            return optional.get().process(getActionArgs(commandArgs));
        }
        return null;
    }

    private String[] getActionArgs(String[] commandArgs) {
        if (commandArgs.length <= 1) {
            return new String[0];
        } else {
            return Arrays.copyOfRange(commandArgs, 1, commandArgs.length);
        }
    }

    @Override
    public ActionResult getLastActionResult() {
        return mLastResult;
    }
}
