package ru.shutov.cft.utils;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shutov.cft.arguments.Arguments;
import ru.shutov.cft.arguments.Order;
import ru.shutov.cft.arguments.Type;
import ru.shutov.cft.exceptions.CmdException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArgsParser {

    private static ArgsParser parser;
    private final Options options;
    private final HelpFormatter helpFormatter;
    private final Logger LOGGER = LoggerFactory.getLogger(ArgsParser.class);

    private ArgsParser() {
        options = new Options();

        OptionGroup typeGroup = new OptionGroup();
        typeGroup.addOption(new Option(Type.INTEGER.getName(), false, "Integer type"));
        typeGroup.addOption(new Option(Type.STRING.getName(), false, "String type"));
        typeGroup.setRequired(true);
        options.addOptionGroup(typeGroup);

        OptionGroup orderGroup = new OptionGroup();
        orderGroup.addOption(new Option(Order.ASCEND.getName(), false, "Ascending order"));
        orderGroup.addOption(new Option(Order.DESCEND.getName(), false, "Descending order"));
        orderGroup.setRequired(false);
        options.addOptionGroup(orderGroup);

        Option outputOption = new Option(null, "output", true, "Output file");
        outputOption.setRequired(true);
        outputOption.setArgs(1);
        options.addOption(outputOption);

        Option inputOption = new Option(null,"input", true, "Input files (at least 1)");
        inputOption.setRequired(true);
        inputOption.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(inputOption);

        helpFormatter = new HelpFormatter();
    }

    public static synchronized ArgsParser getInstance() {
        if(parser == null)
            parser = new ArgsParser();
        return parser;
    }

    public Arguments parseArgs(String[] args) throws ParseException, CmdException {
        validate(Arrays.stream(args).toList());
        LOGGER.info("Validated");

        CommandLineParser cmdParser = new DefaultParser();
        CommandLine cmd = cmdParser.parse(options, args);

        Arguments arguments = new Arguments();
        if(cmd.hasOption("i"))
            arguments.setType(Type.INTEGER);
        if(cmd.hasOption("s"))
            arguments.setType(Type.STRING);
        if(cmd.hasOption("d"))
            arguments.setOrder(Order.DESCEND);

        LOGGER.info("Args has been parsed");
        return arguments;
    }

    public static void validate(List<String> args) throws CmdException {
        if(Collections.frequency(args, "-i") > 1 || Collections.frequency(args, "-s") > 1
                || Collections.frequency(args, "-d") > 1 || Collections.frequency(args, "-a") > 1
                || Collections.frequency(args, "--input") > 1 || Collections.frequency(args, "--output") > 1)
            throw new CmdException("The same options cannot be processed");
    }

    public String[] getInputFileNames(String[] args) throws ParseException {
        CommandLineParser cmdParser = new DefaultParser();
        CommandLine cmd = cmdParser.parse(options, args);
        return cmd.getOptionValues("--input");
    }

    public String getOutputFileName(String[] args) throws ParseException {
        CommandLineParser cmdParser = new DefaultParser();
        CommandLine cmd = cmdParser.parse(options, args);
        return cmd.getOptionValue("--output");
    }

    public void printUsage() {
        helpFormatter.printHelp("-jar cft-maven.jar", options, true);
    }
}
