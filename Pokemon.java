/**
 * Represents a Pokemon object. Each has a number, a name, and two elemental
 * types, chosen from the PokemonType enumeration.
 *
 * @author  Raksha Muthukumar
 * @version 1.1
 */
public class Pokemon implements Comparable<Pokemon> {

    private int num;
    private String name;
    private PokemonType p;
    private PokemonType s;

    /**
     * Constructs a Pokemon object
     *
     * @param num   this Pokemon's unique number
     * @param name  this Pokemon's name
     * @param p this Pokemon's primary type
     * @param s this Pokemon's secondary type
     */
    public Pokemon(int num, String name, PokemonType p, PokemonType s) {
        this.num = num;
        this.name = name;
        this.p = p;
        this.s = s;
    }

    /**
      * compared the objects
      *
      * @return the int based on greater/less/equal to object
      */
    @Override
    public int compareTo(Pokemon o) {
        return this.num - o.num;
    }

    /**
      * mchecks if the objects are the same (by parts)
      *
      * @return whether the objects are equal
      */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (o instanceof Pokemon) {
            Pokemon po = (Pokemon) o;
            if (this.num == po.num) {
                if (this.name.equals(po.name)) {
                    if (this.p == po.p) {
                        if (this.s == po.s) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
      * makes a unique number for each object
      *
      * @return the int of that object
      */
    @Override
    public int hashCode() {
        return num;
    }

    /**
      * makes the collection into an string version based on assignment
      *
      * @return the string of objects
      */
    @Override
    public String toString() {
        return String.format("#%5s: %-12s Primary Type: %-10s Secondary Type:"
            + " %-10s", num, name, p, s);
    }

    /**
     * @return  the name of this Pokemon
     */
    public String getName() {
        return name;
    }

    /**
     * @return  the unique number of this Pokemon
     */
    public int getNumber() {
        return num;
    }

    /**
     * @return  the primary type of this Pokemon
     */
    public PokemonType getPrimaryType() {
        return p;
    }

    /**
     * @return  the secondary type of this Pokemon
     */
    public PokemonType getSecondaryType() {
        return s;
    }
}
