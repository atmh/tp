package seedu.address.logic.parser;

import java.util.Arrays;
import java.util.List;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_CONTACTED_DATE = new Prefix("c/");
    public static final Prefix PREFIX_MEMO = new Prefix("m/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    /** List containing prefix definitions */
    public static final List<Prefix> LIST_OF_PREFIX = Arrays.asList(PREFIX_NAME, PREFIX_PHONE,
            PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_CONTACTED_DATE, PREFIX_MEMO, PREFIX_TAG);
}
