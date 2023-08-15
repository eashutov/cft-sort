package ru.shutov.cft;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shutov.cft.arguments.Arguments;
import ru.shutov.cft.arguments.Type;
import ru.shutov.cft.exceptions.CmdException;
import ru.shutov.cft.exceptions.EmptyListException;
import ru.shutov.cft.utils.ArgsParser;
import ru.shutov.cft.utils.FileHandler;
import ru.shutov.cft.utils.MergeSorter;

import java.io.IOException;
import java.util.*;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ArgsParser argsParser = ArgsParser.getInstance();
        FileHandler fileHandler = new FileHandler();
        try {
            Arguments arguments = argsParser.parseArgs(args);
            String[] input = argsParser.getInputFileNames(args);
            String output = argsParser.getOutputFileName(args);
            if(arguments.getType() == Type.INTEGER) {
                List<Integer> data = fileHandler.getIntData(input);
                MergeSorter.sortIntegers(data, 0, data.size() - 1, arguments.getOrder());
                LOGGER.info("Data has been sorted");
                fileHandler.saveToOutputFile(output, data.stream().map(Objects::toString).toList());
            } else if(arguments.getType() == Type.STRING) {
                List<String> data = fileHandler.getStringData(input);
                MergeSorter.sortStrings(data, 0, data.size() - 1, arguments.getOrder());
                LOGGER.info("Data has been sorted");
                fileHandler.saveToOutputFile(output, data);
            }
        } catch (ParseException | CmdException | IOException e) {
            LOGGER.error(e.getMessage());
            argsParser.printUsage();
            System.exit(1);
        } catch (RuntimeException re) {
            LOGGER.error(re.getMessage());
            System.exit(2);
        } catch (EmptyListException e) {
            LOGGER.warn(e.getMessage());
            System.exit(3);
        }
        LOGGER.info("Completed successfully");
    }
}
