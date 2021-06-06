package com.example.line.editor.action;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.example.line.editor.FileController;
import com.example.line.editor.action.impl.DeleteLineAction;
import com.example.line.editor.action.mock.ListBasedFileControllerMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeleteLineActionTest {
    DeleteLineAction mDeleteLineAction;
    FileController mFakeFileController;
    final List<String> mLorem = Arrays.asList(
            /* 1 */ "Lorem ipsum dolor sit amet, consectetur ", 
            /* 2 */ "adipiscing elit. Aliquam ullamcorper faucibus nisl, ",
            /* 3 */ "et iaculis tellus bibendum sit amet. Nunc pharetra ", 
            /* 4 */ "ligula ut dignissim semper. "
        );

    @BeforeEach
    void init() {
        mFakeFileController = new ListBasedFileControllerMock();
        mDeleteLineAction = new DeleteLineAction(mFakeFileController);
    }

    @Test
    void incorrectArgument() {
        ActionResult result = mDeleteLineAction.process("a");
        assertEquals(result.isError(), true, result.getMessage());
    }
    
    @Test
    void incorrectArgumentCount() {
        ActionResult result = mDeleteLineAction.process("1", "1");
        assertEquals(result.isError(), true, result.getMessage());
    }
    
    @Test
    void deleteLineInMiddle() {
        mFakeFileController.setLines(mLorem);

        ActionResult result = mDeleteLineAction.process("3");

        assertEquals(result.isError(), false, result.getMessage());
        assertEquals(
            mFakeFileController.getLines(), 
            Arrays.asList(
                /* 1 */ "Lorem ipsum dolor sit amet, consectetur ", 
                /* 2 */ "adipiscing elit. Aliquam ullamcorper faucibus nisl, ", 
                /* deleted */
                /* 3 */ "ligula ut dignissim semper. "
            )
        );
    }

    @Test
    void deleteLineInBeginning() {
        mFakeFileController.setLines(mLorem);

        ActionResult result = mDeleteLineAction.process("1");

        assertEquals(result.isError(), false, result.getMessage());
        assertEquals(
            mFakeFileController.getLines(), 
            Arrays.asList(
                /* deleted */ 
                /* 1 */ "adipiscing elit. Aliquam ullamcorper faucibus nisl, ", 
                /* 2 */ "et iaculis tellus bibendum sit amet. Nunc pharetra ", 
                /* 3 */ "ligula ut dignissim semper. "
            )
        );
    }

    @Test
    void deleteLineInEnd() {
        mFakeFileController.setLines(mLorem);

        ActionResult result = mDeleteLineAction.process("4");

        assertEquals(result.isError(), false, result.getMessage());
        assertEquals(
            mFakeFileController.getLines(), 
            Arrays.asList(
                /* 1 */ "Lorem ipsum dolor sit amet, consectetur ", 
                /* 2 */ "adipiscing elit. Aliquam ullamcorper faucibus nisl, ", 
                /* 3 */ "et iaculis tellus bibendum sit amet. Nunc pharetra "
                /* deleted */ 
            )
        );
    }

    @Test
    void deleteLineOutOfRange() {
        mFakeFileController.setLines(mLorem);

        ActionResult result = mDeleteLineAction.process("7");

        assertEquals(result.isError(), true, result.getMessage());
        assertEquals(mFakeFileController.getLines(), mLorem);

        result = mDeleteLineAction.process("-1");

        assertEquals(result.isError(), true, result.getMessage());
        assertEquals(mFakeFileController.getLines(), mLorem);

        result = mDeleteLineAction.process("0");

        assertEquals(result.isError(), true, result.getMessage());
        assertEquals(mFakeFileController.getLines(), mLorem);
    }
}
