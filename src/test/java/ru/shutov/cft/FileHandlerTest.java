package ru.shutov.cft;

import org.junit.Assert;
import org.junit.Test;
import ru.shutov.cft.exceptions.EmptyListException;
import ru.shutov.cft.utils.FileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandlerTest {

    private final Path test1 = Paths.get("test1.txt");
    private final Path test2 = Paths.get("test2.txt");
    private final Path out = Paths.get("out.txt");

    @Test
    public void shouldReturnListOfInputIntegers() {
        try {
            Files.createFile(test1);
            Files.createFile(test2);
            Files.write(test1, Arrays.asList("1","7","3","6"));
            Files.write(test2, Arrays.asList("7","2","9","6"));
            FileHandler fileHandler = new FileHandler();
            String[] input = {test1.toString(), test2.toString()};
            List<Integer> expected = new ArrayList<>(Arrays.asList(1, 7, 3, 6, 7, 2, 9, 6));
            List<Integer> actual = fileHandler.getIntData(input);
            Assert.assertArrayEquals(expected.toArray(new Integer[0]), actual.toArray(new Integer[0]));
            Files.delete(test1);
            Files.delete(test2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (EmptyListException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void shouldReturnListOfInputStrings() {
        try {
            Files.createFile(test1);
            Files.createFile(test2);
            Files.write(test1, Arrays.asList("Lorem","ipsum","dolor","sit","amet"));
            Files.write(test2, Arrays.asList("consectetur","adipiscing","elit","sed","do"));
            FileHandler fileHandler = new FileHandler();
            String[] input = {test1.toString(), test2.toString()};
            List<String> expected = new ArrayList<>(Arrays.asList("Lorem","ipsum","dolor","sit","amet","consectetur","adipiscing","elit","sed","do"));
            List<String> actual = fileHandler.getStringData(input);
            Assert.assertArrayEquals(expected.toArray(new String[0]), actual.toArray(new String[0]));
            Files.delete(test1);
            Files.delete(test2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (EmptyListException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void shouldThrowEmptyListException() {
        try {
            Files.createFile(test1);
            FileHandler fileHandler = new FileHandler();
            String[] input = {test1.toString()};
            Throwable ex = Assert.assertThrows(EmptyListException.class, () -> {
                fileHandler.getIntData(input);
            });
            Assert.assertEquals(EmptyListException.class, ex.getClass());
            Files.delete(test1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldThrowRuntimeExceptionBecauseFileDoesNotExists() {
        FileHandler fileHandler = new FileHandler();
        String[] input = {"test.txt"};
        Throwable ex = Assert.assertThrows(RuntimeException.class, () -> {
            fileHandler.getIntData(input);
        });
        Assert.assertEquals(RuntimeException.class, ex.getClass());
    }

    @Test
    public void shouldSaveSomeDataInOutFile() {
        try {
            Files.createFile(out);
            FileHandler fileHandler = new FileHandler();
            List<String> expected = new ArrayList<>(Arrays.asList("test","test","test"));
            fileHandler.saveToOutputFile(out.toString(), expected);
            List<String> actual = Files.readAllLines(out);
            Assert.assertArrayEquals(expected.toArray(new String[0]), actual.toArray(new String[0]));
            Files.delete(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
