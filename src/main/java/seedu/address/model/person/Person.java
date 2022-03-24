package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Memo memo;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Memo memo, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.memo = memo;
        this.tags.addAll(tags);
    }

    /**
     * Returns name.
     *
     * @return name.
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns phone.
     *
     * @return phone/
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Returns email.
     *
     * @return email.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Returns address.
     *
     * @return address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns memo.
     *
     * @return memo.
     */
    public Memo getMemo() {
        return memo;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns if memo is empty.
     *
     * @return If memo is empty true; otherwise false.
     */
    public boolean isMemoEmpty() {
        return memo.isEmpty();
    }

    /**
     * Returns true if both persons have same phone number or email.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && (otherPerson.getPhone().equals(getPhone()) || otherPerson.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     *
     * @return true if both persons have the same identity and data fields; otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getMemo().equals(getMemo())
                && otherPerson.getTags().equals(getTags());
    }

    /**
     * Returns hashcode of Person.
     *
     * @return hashcode of Person.
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, memo, tags);
    }

    /**
     * Returns string representation of Person.
     *
     * @return string representation of Person.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        if (!memo.isEmpty()) {
            builder.append(" Memo: ").append(getMemo());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
