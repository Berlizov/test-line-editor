package com.example.line.editor.action;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.example.line.editor.FileController;
import com.example.line.editor.action.impl.AddLineAction;
import com.example.line.editor.action.mock.ListBasedFileControllerMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddLineActionTest {
    AddLineAction mAddLineAction;
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
        mAddLineAction = new AddLineAction(mFakeFileController);
    }

    @Test
    void incorrectArgument() {
        ActionResult result = mAddLineAction.process("a");
        assertEquals(result.isError(), true, result.getMessage());
    }
    
    @Test
    void incorrectArgumentCount() {
        ActionResult result = mAddLineAction.process("1", "1");
        assertEquals(result.isError(), true, result.getMessage());
    }
    
    @Test
    void insertLineInMiddle() {
        mFakeFileController.setLines(mLorem);

        ActionResult result = mAddLineAction.process("3");

        assertEquals(result.isError(), false, result.getMessage());
        assertEquals(
            mFakeFileController.getLines(), 
            Arrays.asList(
                /* 1 */ "Lorem ipsum dolor sit amet, consectetur ", 
                /* 2 */ "adipiscing elit. Aliquam ullamcorper faucibus nisl, ", 
                /* 3  - inserted */ "",
                /* 4 */ "et iaculis tellus bibendum sit amet. Nunc pharetra ", 
                /* 5 */ "ligula ut dignissim semper. "
            )
        );
    }

    @Test
    void insertLineInBeginning() {
        mFakeFileController.setLines(mLorem);

        ActionResult result = mAddLineAction.process("1");

        assertEquals(result.isError(), false, result.getMessage());
        assertEquals(
            mFakeFileController.getLines(), 
            Arrays.asList(
                /* 1  - inserted */ "",
                /* 2 */ "Lorem ipsum dolor sit amet, consectetur ", 
                /* 3 */ "adipiscing elit. Aliquam ullamcorper faucibus nisl, ", 
                /* 4 */ "et iaculis tellus bibendum sit amet. Nunc pharetra ", 
                /* 5 */ "ligula ut dignissim semper. "
            )
        );
    }

    @Test
    void insertLineInEnd() {
        mFakeFileController.setLines(mLorem);

        ActionResult result = mAddLineAction.process("5");

        assertEquals(result.isError(), false, result.getMessage());
        assertEquals(
            mFakeFileController.getLines(), 
            Arrays.asList(
                /* 1 */ "Lorem ipsum dolor sit amet, consectetur ", 
                /* 2 */ "adipiscing elit. Aliquam ullamcorper faucibus nisl, ", 
                /* 3 */ "et iaculis tellus bibendum sit amet. Nunc pharetra ", 
                /* 4 */ "ligula ut dignissim semper. ",
                /* 5  - inserted */ ""
            )
        );
    }

    @Test
    void insertLineOutOfRange() {
        mFakeFileController.setLines(mLorem);

        ActionResult result = mAddLineAction.process("7");

        assertEquals(result.isError(), true, result.getMessage());
        assertEquals(mFakeFileController.getLines(), mLorem);

        result = mAddLineAction.process("-1");

        assertEquals(result.isError(), true, result.getMessage());
        assertEquals(mFakeFileController.getLines(), mLorem);

        result = mAddLineAction.process("0");

        assertEquals(result.isError(), true, result.getMessage());
        assertEquals(mFakeFileController.getLines(), mLorem);
    }

    @Test
    void insertLineInEmptyFile() {
        ActionResult result = mAddLineAction.process("1");

        assertEquals(result.isError(), false, result.getMessage());
        assertEquals(
            mFakeFileController.getLines(), 
            Arrays.asList(
                /* 1  - inserted */ ""
            )
        );
    }
}
