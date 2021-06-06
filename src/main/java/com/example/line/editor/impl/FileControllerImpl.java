package com.example.line.editor.impl;

import com.example.line.editor.FileController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of {@link FileController}.
 */
public class FileControllerImpl implements FileController {
    private final Path mPath;
    private List<String> mLines;

    /**
     * Constructor with file reading.
     *
     * @param filePath file path to read
     * @throws IOException if an I/O error occurs opening the file
     */
    FileControllerImpl(String filePath) throws IOException {
        mPath = Paths.get(filePath);
        mLines = Files.lines(mPath).collect(Collectors.toList());
    }

    @Override
    public List<String> getLines() {
        return new ArrayList<>(mLines);
    }

    @Override
    public void setLines(List<String> newLines) throws NullPointerException {
        Objects.requireNonNull(newLines);
        mLines = new ArrayList<>(newLines);
    }

    @Override
    public void save() throws IOException {
        Files.write(mPath, mLines);
    }
}
