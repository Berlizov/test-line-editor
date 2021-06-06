package com.example.line.editor.action;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import com.example.line.editor.action.impl.SaveAction;
import com.example.line.editor.action.mock.BaseFileControllerMock;

import org.junit.jupiter.api.Test;

public class SaveActionTest {
    
    @Test
    void incorrectArgumentCount() {
        FakeFileController fakeFileController = new FakeFileController();
        SaveAction saveAction = new SaveAction(fakeFileController);
        ActionResult result = saveAction.process("1");
        assertEquals(result.isError(), true, result.getMessage());
    }

    @Test
    void simpleSave() {
        FakeFileController fakeFileController = new FakeFileController();
        SaveAction saveAction = new SaveAction(fakeFileController);

        ActionResult result = saveAction.process();

        assertEquals(result.isError(), false, result.getMessage());
        assertEquals(fakeFileController.saveFlag, true);
    }

    @Test
    void saveWithIOException() {
        FakeIOExceptionFileController fakeFileController = new FakeIOExceptionFileController();
        SaveAction saveAction = new SaveAction(fakeFileController);

        ActionResult result = saveAction.process();

        assertEquals(result.isError(), true, result.getMessage());
    }

    class FakeFileController extends BaseFileControllerMock {
        boolean saveFlag = false;
        
        @Override
        public void save() throws IOException {
            saveFlag = true;
        }
    }

    class FakeIOExceptionFileController extends BaseFileControllerMock {
        @Override
        public void save() throws IOException{
            throw new IOException("Fake IOException");
        }
    }
}
