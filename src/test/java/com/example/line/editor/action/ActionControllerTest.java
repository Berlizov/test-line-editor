package com.example.line.editor.action;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collections;

import com.example.line.editor.action.impl.ActionControllerImpl;
import com.example.line.editor.action.impl.ActionResultImpl;

import org.junit.jupiter.api.Test;

public class ActionControllerTest {
    @Test
    public void cannotFindAction() throws IOException {
        Reader reader = new StringReader("fake action");
        BufferedReader bufferedReader = new BufferedReader(reader);
        ActionController actionController = new ActionControllerImpl(
            bufferedReader,
            Collections.emptyList());
        actionController.process();
        assertEquals(actionController.getLastActionResult().isError(), true);
    }

    @Test
    public void canFindAction() throws IOException {
        FakeAction fakeAction= new FakeAction();
        Reader reader = new StringReader("fake action");
        BufferedReader bufferedReader = new BufferedReader(reader);
        ActionController actionController = new ActionControllerImpl(
            bufferedReader,
            Arrays.asList(fakeAction));
        actionController.process();
        assertEquals(actionController.getLastActionResult().isError(), false);
        assertEquals(fakeAction.processFlag, true);
    }

    @Test
    public void testDescriptions() throws IOException {
        FakeAction fakeAction= new FakeAction();
        ActionController actionController = new ActionControllerImpl(
            null,
            Arrays.asList(fakeAction, fakeAction));
        assertEquals(actionController.getActionsDescriptions(), "fake - this is fake action\nfake - this is fake action");

        ActionController emptyController= new ActionControllerImpl(
            null,
            Collections.emptyList());
        assertEquals(emptyController.getActionsDescriptions(), "");
    }

    class FakeAction implements Action {
        boolean processFlag = false;
        @Override
        public ActionResult process(String... arguments) {
            processFlag = true;
            return new ActionResultImpl("Fake Result");
        }

        @Override
        public String getKey() {
            return "fake";
        }

        @Override
        public String getDescription() {
            return "fake - this is fake action";
        }
    }
}
