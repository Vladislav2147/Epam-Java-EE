package com.shichko.firsttask.reader;

import com.shichko.firsttask.exception.FileReadException;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class FileReaderTest {

    private FileReader fileReader;

    private static final String VALID_FILE_NAME = "valid.txt";
    private static final String NOT_EXISTING_FILE_NAME = "not_exist.txt";
    private static final String DIRECTORY_PATH = "src/test/resources";
    private static final String VALID_FILE_DATA = "1gfdskjal\n2 3x 4.\n1 7 8 3\n5 6 7 2";

    private File directory;
    private File validFile;
    private File notExistingFile;

    @BeforeTest
    public void init() throws IOException {
        fileReader = new FileReader();

        directory = new File(DIRECTORY_PATH);
        if (directory.exists()) {
            FileUtils.deleteDirectory(new File(DIRECTORY_PATH));
        }
        directory.mkdir();

        validFile = new File(DIRECTORY_PATH, VALID_FILE_NAME);
        validFile.createNewFile();
        Files.writeFile(VALID_FILE_DATA, validFile);

        notExistingFile = new File(DIRECTORY_PATH, NOT_EXISTING_FILE_NAME);
    }

    @Test
    public void testReadLinesOnValidFile() throws FileReadException {
        List<String> expected = Arrays.stream(VALID_FILE_DATA.split("\n")).collect(Collectors.toList());
        List<String> actual = fileReader.readLines(validFile);
        assertEquals(actual, expected);
    }
    @Test(expectedExceptions = FileReadException.class)
    public void testReadLinesThrowsOnDirectory() throws FileReadException {
        fileReader.readLines(directory);
    }

    @Test(expectedExceptions = FileReadException.class)
    public void testReadLinesThrowsOnNotExistingFile() throws FileReadException {
        fileReader.readLines(notExistingFile);
    }

}