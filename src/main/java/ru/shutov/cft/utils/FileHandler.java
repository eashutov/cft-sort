package ru.shutov.cft.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shutov.cft.exceptions.EmptyListException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(FileHandler.class);

    public List<Integer> getIntData(String[] input) throws IOException, EmptyListException {
        LOGGER.info("Input files: " + Arrays.toString(input));
        List<String> stringList = new ArrayList<>();
        for(String in : input) {
            readFromInputFile(in, stringList);
        }
        List<Integer> integerList = new ArrayList<>(stringList.stream().map(s -> {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                LOGGER.warn("Cannot parse integer " + s);
                return null;
            }
        }).toList());
        boolean isRemoved = integerList.removeAll(Collections.singleton(null));
        if(isRemoved) LOGGER.info("Lines that cannot be parsed have been deleted");

        isListEmpty(integerList);

        LOGGER.info("Data from files received");
        return integerList;
    }

    public List<String> getStringData(String[] input) throws IOException, EmptyListException {
        List<String> stringList = new ArrayList<>();
        for(String in : input) {
            readFromInputFile(in, stringList);
        }

        isListEmpty(stringList);

        LOGGER.info("Data from files received");
        return stringList;
    }

   private void readFromInputFile(String inputPath, List<String> actualList) throws IOException {
        List<String> strings;
        try {
            strings = Files.readAllLines(Paths.get(inputPath));
        } catch (NoSuchFileException e) {
            throw new RuntimeException("Specified file doesn't exist: " + inputPath);
        }
        LOGGER.info("Scanning: " + inputPath);
        boolean isNullOrEmptyRemoved = removeIfNullOrEmpty(strings);
        if (isNullOrEmptyRemoved) LOGGER.info("Empty or null lines detected and removed");
        boolean isSpacesRemoved = removeSpaces(strings);
        if (isSpacesRemoved) LOGGER.info("Spaces detected and removed");
        actualList.addAll(strings);
   }

    public void saveToOutputFile(String outputPath, List<String> output) throws IOException {
        Files.write(Paths.get(outputPath), output, Charset.defaultCharset());
        LOGGER.info("Result was saved in " + outputPath);
    }

    private boolean removeSpaces(List<String> list) {
        return list.removeIf(s -> s.contains(" "));
    }

    private boolean removeIfNullOrEmpty(List<String> list) {
        return list.removeAll(Arrays.asList("", null));
    }

    private void isListEmpty(List list) throws EmptyListException {
        if(list.isEmpty()) {
            throw new EmptyListException("Resulting list is empty, further work is unreasonable");
        }
    }
}
