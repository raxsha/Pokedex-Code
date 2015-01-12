/**
 * Represents a Pokedex - basically a Pokemon encyclopedia that adds new entries
 * when you encounter a Pokemon for the first time.
 * It also provides methods for organizing its information in useful ways.
 *
 * @author Raksha Muthukumar
 * @version 1.1
 */
public class Pokedex {

    private MySortedSet<Pokemon> pokedex;
    /**
     * Constructs a Pokedex object by setting up the sorted set of Pokemon
     */
    public Pokedex() {
        pokedex = new MySortedSet<Pokemon>((Pokemon a, Pokemon b) -> {
                return a.getNumber() - b.getNumber();
            });
    }

    /**
      * makes the collection into a string version
      *
      * @return the string of objects
      */
    @Override
    public String toString() {
        return pokedex.toString();
    }

    /**
     * Adds a Pokemon to the sorted set
     *
     * @param p the Pokemon to be added
     * @return true if the pokemon was not already in the set, false otherwise
     */
    public boolean add(Pokemon p) {
        if (pokedex.contains(p)) {
            return false;
        }
        pokedex.add(p);
        return true;
    }

    /**
     * Returns the number of Pokemon in the Pokedex
     *
     * @return  the number of Pokemon in the Pokedex
     */
    public int countPokemon() {
        return pokedex.size();
    }

    /**
     * Clear the Pokedex and start over
     */
    public void clear() {
        pokedex.clear();
    }

    /**
     * Returns a set of alphabetized Pokemon, using a lambda expression
     *
     * @return  the alphabetized set
     */
    public MySortedSet<Pokemon> listAlphabetically() {
        return pokedex.sort((Pokemon a, Pokemon b) -> {
                return a.getName().compareTo(b.getName());
            });
    }

    /**
     * Returns a set of Pokemon grouped by type, using a lambda expression
     *
     * @return  the grouped by primary type set
     */
    public MySortedSet<Pokemon> groupByPrimaryType() {
        return pokedex.sort((Pokemon a, Pokemon b) -> {
                if (a.getPrimaryType().hashCode()
                    > b.getPrimaryType().hashCode()) {
                    return 1;
                } else if (b.getPrimaryType().hashCode()
                    < a.getPrimaryType().hashCode()) {
                    return -1;
                } else {
                    return 0;
                }
            });
    }

    /**
     * Returns a set of all Pokemon of type t
     *
     * @param t the type we want listed
     * @return  the set of all Pokemon in the Pokedex of type t
     */
    public MySortedSet<Pokemon> listByType(PokemonType t) {
        MySortedSet<Pokemon> ret = new MySortedSet<Pokemon>();
        ret.addAll(pokedex);
        ret.filter(e -> (e.getPrimaryType() != t && e.getSecondaryType() != t))
            .forEach(x -> ret.remove(x));
        return ret;
    }

    /**
     * Returns a set of Pokemon with numbers in the range [start, end]
     *
     * @param start the first number in the new set
     * @param end   the last number in the new set
     * @return  the set containing all Pokemon in the Pokedex from [start, end]
     */
    public MySortedSet<Pokemon> listRange(int start, int end) {
        MySortedSet<Pokemon> ret = new MySortedSet<Pokemon>();
        ret.addAll(pokedex);
        ret.filter(e -> (e.getNumber() < start || e.getNumber() > end))
            .forEach(x -> ret.remove(x));
        return ret;
    }
}
