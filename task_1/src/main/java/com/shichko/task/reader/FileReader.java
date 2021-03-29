package com.shichko.task.reader;

import com.shichko.task.exception.FileReadException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    static Logger logger = LogManager.getLogger();

    public List<String> readLines(File file) throws FileReadException {
        if (file.exists() && file.isFile()) {
            try {
                return Files.lines(file.toPath()).collect(Collectors.toList());
            } catch (IOException e) {
                logger.log(Level.ERROR, "Error with file reading", e);
                throw new FileReadException("Error with file reading", e);
            }
        } else {
            throw new FileReadException("Invalid file: " + file.getAbsolutePath());
        }
    }
}