package com.example.line.editor.action;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.line.editor.AppExitPoint;
import com.example.line.editor.action.impl.ExitAction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExitActionTest {
    Action mExitAction;
    AppExitPointMock mAppExitPointMock;

    @BeforeEach
    void init() {
        mAppExitPointMock = new AppExitPointMock();
        mExitAction = new ExitAction(mAppExitPointMock);
    }
    @Test
    void incorrectArgumentCount() {
        ActionResult result = mExitAction.process("1");
        assertEquals(result.isError(), true, result.getMessage());
    }

    @Test
    void simpleExit() {
        ActionResult result = mExitAction.process();

        assertEquals(result.isError(), false, result.getMessage());
        assertEquals(mAppExitPointMock.exitFlag, true);
    }

    class AppExitPointMock implements AppExitPoint {
        boolean exitFlag = false;
        
        @Override
        public void exit() {
            exitFlag = true;
        }
    }
}
