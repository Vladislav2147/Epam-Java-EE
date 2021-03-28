package com.shichko.first_task.reader;

import com.shichko.first_task.exception.FileReadException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    static Logger logger = LogManager.getLogger();

    public List<String> readLines(String filename) throws FileReadException {
        Path path = Paths.get(filename);
        if (Files.exists(path) && !Files.isDirectory(path) && Files.isReadable(path)) {
            try {
                return Files.lines(path).collect(Collectors.toList());
            } catch (IOException e) {
                logger.log(Level.ERROR, "Error with file reading", e);
                throw new FileReadException("Error with file reading", e);
            }
        } else {
            throw new FileReadException("Invalid file: " + filename);
        }
    }
}