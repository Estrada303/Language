import java.util.*;

/**
 * Represents a finite language.
 *
 * @author Dr. Jody Paul
 * @author Ernesto Estrada
 * @version 1.3.8.26
 */
public final class Language implements Iterable<String>, java.io.Serializable {
    /** The empty string. */
    private static final String EMPTY_STRING = "";
    /** The empty set. */
    private static final Set<String> EMPTY_SET = new TreeSet<String>();

    /** The set of strings in this language, initially empty. */
    private Set<String> strings;

    /**
     * Create a language with no strings.
     */
    public Language() {
        strings = new TreeSet<String>();
    }

    /**
     * Indicates if this language has no strings.
     * @return true if the language is equivalent to the empty set;
     *         false otherwise
     */
    public boolean isEmpty() {
        return strings.isEmpty();
    }

    /**
     * Accesses the number of strings (cardinality) in this language.
     * @return the cardinality of the language
     */
    public int cardinality() {
        return strings.size();
    }

    /**
     * Determines if a specified string is in this language.
     * @param candidate the string to check
     * @return true if the string is in the language,
     *         false if not in the language or the parameter is null
     */
    public boolean includes(final String candidate) {
        return strings.contains(candidate);
    }

    /**
     * Ensures that this language includes the given string
     * (adds it if necessary).
     * @param memberString the string to be included in the language
     * @return true if this language changed as a result of the call
     */
    public boolean addString(final String memberString) {
        return strings.add(memberString);
    }

    /**
     * Ensures that this language includes all of the strings
     * in the given parameter (adds any as necessary).
     * @param memberStrings the strings to be included in the language
     * @return true if this language changed as a result of the call
     */
    public boolean addAllStrings(final Collection<String> memberStrings) {
        return strings.addAll(memberStrings);
    }

    /**
     * Provides an iterator over the strings in this language.
     * @return an iterator over the strings in this language in ascending order
     */
    public Iterator<String> iterator() {
        return strings.iterator();
    }


    /**
     * Creates a language that is the concatenation of this language
     * with another language.
     * @param language the language to be concatenated to this language
     * @return the concatenation of this language with the parameter language
     */
    public Language concatenate(final Language language) {
        Language returnLanguage;
        if (language.isEmpty() || this.isEmpty()) {
            return new Language();
        }
        else {
            returnLanguage = new Language();
            Iterator<String> thisIterator = this.iterator();
            while(thisIterator.hasNext()) {
                String x = thisIterator.next();
                Iterator<String> langIterator = language.iterator();
                while(langIterator.hasNext()) {
                    String y = langIterator.next();
                    returnLanguage.addString(x + y);
                }
            }
        }
        return returnLanguage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Language strings1 = (Language) o;
        return Objects.equals(strings, strings1.strings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strings);
    }
}
