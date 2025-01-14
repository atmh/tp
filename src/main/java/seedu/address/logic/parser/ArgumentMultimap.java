package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.ARRAY_OF_PREFIX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Stores mapping of prefixes to their respective arguments.
 * Each key may be associated with multiple argument values.
 * Values for a given key are stored in a list, and the insertion ordering is maintained.
 * Keys are unique, but the list of argument values may contain duplicate argument values, i.e. the same argument value
 * can be inserted multiple times for the same prefix.
 */
public class ArgumentMultimap {

    /**
     * Prefixes mapped to their respective arguments
     **/
    private final Map<Prefix, List<String>> argMultimap = new HashMap<>();

    /**
     * Associates the specified argument value with {@code prefix} key in this map.
     * If the map previously contained a mapping for the key, the new value is appended to the list of existing values.
     *
     * @param prefix   Prefix key with which the specified argument value is to be associated
     * @param argValue Argument value to be associated with the specified prefix key
     */
    public void put(Prefix prefix, String argValue) {
        List<String> argValues = getAllValues(prefix);
        argValues.add(argValue);
        argMultimap.put(prefix, argValues);
    }

    /**
     * Returns the last value of {@code prefix}.
     */
    public Optional<String> getValue(Prefix prefix) {
        List<String> values = getAllValues(prefix);
        return values.isEmpty() ? Optional.empty() : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Returns the list of prefixes in the {@link #argMultimap}.
     * Modifying the returned list will not affect the underlying data structure of the ArgumentMultimap.
     *
     * @return Prefixes that are in the user input.
     */
    public List<Prefix> getAllAvailablePrefix() {
        Set<Prefix> setOfKeys = new HashSet<>(argMultimap.keySet());
        setOfKeys.remove(new Prefix("")); //removes preamble
        return new ArrayList<>(setOfKeys);
    }

    /**
     * Checks if there is an argument for the specified prefix.
     *
     * @param prefix Prefix to be checked against.
     * @return True if there is an argument for that prefix, false otherwise.
     */
    public boolean contains(Prefix prefix) {
        return argMultimap.containsKey(prefix);
    }


    /**
     * Returns all values of {@code prefix}.
     * If the prefix does not exist or has no values, this will return an empty list.
     * Modifying the returned list will not affect the underlying data structure of the ArgumentMultimap.
     */
    public List<String> getAllValues(Prefix prefix) {
        if (!argMultimap.containsKey(prefix)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(argMultimap.get(prefix));
    }

    /**
     * Checks if {@link #argMultimap} is empty.
     * @return true if {@link #argMultimap} is empty. False otherwise.
     */
    public boolean isEmpty() {
        return argMultimap.isEmpty();
    }

    /**
     * Checks if {@link #argMultimap} contains no valid prefix.
     *
     * @return True if no valid prefix is in the argument map. False otherwise.
     */
    public boolean hasNoValidPrefix() {
        return Arrays.stream(ARRAY_OF_PREFIX).noneMatch(argMultimap::containsKey);
    }

    /**
     * Returns the preamble (text before the first valid prefix). Trims any leading/trailing spaces.
     */
    public String getPreamble() {
        return getValue(new Prefix("")).orElse("");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArgumentMultimap // instanceof handles nulls
                && argMultimap.equals(((ArgumentMultimap) other).argMultimap)); // state check
    }

    @Override
    public String toString() {
        return argMultimap.toString();
    }
}
