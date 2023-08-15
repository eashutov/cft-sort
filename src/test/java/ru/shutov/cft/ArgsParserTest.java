package ru.shutov.cft;

import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;
import ru.shutov.cft.arguments.Arguments;
import ru.shutov.cft.arguments.Order;
import ru.shutov.cft.arguments.Type;
import ru.shutov.cft.exceptions.CmdException;
import ru.shutov.cft.utils.ArgsParser;

import java.util.Arrays;

public class ArgsParserTest {
    @Test
    public void validateShouldThrowCmdException() {
        String[] args = {"-i", "-a", "-i", "--input", "hello.txt", "hello2.txt", "--output", "bye.txt"};
        Throwable ex = Assert.assertThrows(CmdException.class, () -> {
            ArgsParser.validate(Arrays.stream(args).toList());
        });
        Assert.assertEquals(CmdException.class, ex.getClass());
    }

    @Test
    public void shouldReturnInputNamesOfFiles() {
        String[] args = {"-i", "-a", "--input", "hello.txt", "hello2.txt", "--output", "bye.txt"};
        String[] expected = {"hello.txt", "hello2.txt"};
        try {
            String[] actual = ArgsParser.getInstance().getInputFileNames(args);
            Assert.assertArrayEquals(expected, actual);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldReturnOutputFileName() {
        String[] args = {"-i", "-a", "--input", "hello.txt", "hello2.txt", "--output", "bye.txt"};
        String expected = "bye.txt";
        try {
            String actual = ArgsParser.getInstance().getOutputFileName(args);
            Assert.assertEquals(expected, actual);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldReturnCmdArguments() {
        String[] args = {"-i", "-a", "--input", "hello.txt", "hello2.txt", "--output", "bye.txt"};
        Arguments expected = new Arguments();
        expected.setType(Type.INTEGER);
        expected.setOrder(Order.ASCEND);
        try {
            Arguments actual = ArgsParser.getInstance().parseArgs(args);
            Assert.assertEquals(expected, actual);
        } catch (ParseException | CmdException e) {
            throw new RuntimeException(e);
        }
    }
}
