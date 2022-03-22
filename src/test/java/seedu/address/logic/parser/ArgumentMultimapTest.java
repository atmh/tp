package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ArgumentMultimapTest {
    private final Prefix emptyPrefix = new Prefix("");
    private final Prefix validPrefix = new Prefix("t/");

    @Test
    void put_validArguments_isPresent() {
        ArgumentMultimap emptyMultiMap = new ArgumentMultimap();
        String emptyPutMessage = "Empty String inserted.";
        emptyMultiMap.put(emptyPrefix, emptyPutMessage);
        assertTrue(emptyMultiMap.getValue(emptyPrefix).isPresent());

        ArgumentMultimap validMultiMap = new ArgumentMultimap();
        String dummyMessage = "Some message inserted.";
        validMultiMap.put(validPrefix, dummyMessage);
        assertTrue(validMultiMap.getValue(validPrefix).isPresent());
    }

    @Test
    void removePreamble() {
        ArgumentMultimap mapWithEmptyKeys = new ArgumentMultimap();
        String dummyString = "This string won't be used.";
        mapWithEmptyKeys.put(emptyPrefix, dummyString);
        assertTrue(mapWithEmptyKeys.getValue(emptyPrefix).isPresent());
        mapWithEmptyKeys.removePreamble();
        assertFalse(mapWithEmptyKeys.getValue(emptyPrefix).isPresent());
    }

    @Test
    void getValueOrEmpty_validArguments() {
        // When value is null -> should return ""
        ArgumentMultimap testMap = new ArgumentMultimap();
        assertEquals(testMap.getValueOrEmpty(validPrefix), "");
        assertEquals(testMap.getValueOrEmpty(emptyPrefix), "");
        String testString = "This is a test";
        testMap.put(validPrefix, testString);
        assertEquals(testMap.getValueOrEmpty(validPrefix), testString);
    }

    @Test
    void getAllValues_validInputs() {
        ArgumentMultimap testMap = new ArgumentMultimap();
        // When value is not null -> should return value
        List<String> listOfTestStrings = new LinkedList<>();
        String firstTestString = "This is a test string number 1.";
        String secondTestString = "This is a test string number 2.";
        listOfTestStrings.add(firstTestString);
        listOfTestStrings.add(secondTestString);

        testMap.put(validPrefix, firstTestString);
        testMap.put(validPrefix, secondTestString);
        assertEquals(testMap.getAllValues(validPrefix), listOfTestStrings);
    }

    @Test
    void getAllKeys() {
        ArgumentMultimap testMap = new ArgumentMultimap();
        List<Prefix> listOfKeys = new LinkedList<>();
        listOfKeys.add(emptyPrefix);
        listOfKeys.add(validPrefix);

        testMap.put(emptyPrefix, "");
        testMap.put(validPrefix, "");
        assertEquals(testMap.getAllKeys(), listOfKeys);
    }

    @Test
    void isEmpty() {
        ArgumentMultimap testMap = new ArgumentMultimap();
        assertTrue(testMap.isEmpty());
        testMap.put(validPrefix, "");
        assertFalse(testMap.isEmpty());
    }

    @Test
    void getPreamble() {
        String preambleString = "preamble text here";
        String validPrefixString = " a/";
        ArgumentMultimap testMap = ArgumentTokenizer.tokenize(preambleString + validPrefixString, PREFIX_NAME,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_MEMO);
        assertEquals(testMap.getPreamble(), preambleString);
    }

}