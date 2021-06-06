package com.example.line.editor;

import java.io.IOException;
import java.util.List;

/**
 * Interface for easy interaction with a specific text file.
 * The readed file is interpreted as a list of lines.
 */
public interface FileController {
    /**
     * Returns the list of lines read from the file or changed in memory since
     * the last read of the file.
     *
     * @return none null list of text lines
     */
    List<String> getLines();

    /**
     * Replaces the readed list of lines for the file in memory.
     * But it doesn't save it to the file.
     *
     * @param newLines list of text lines to set as file lines
     * @throws NullPointerException if <code>newLines</code> argument is null
     */
    void setLines(List<String> newLines) throws NullPointerException;

    /**
     * Saves current state to the file.
     *
     * @throws IOException if an I/O error occurs opening the file
     */
    void save() throws IOException;
}
