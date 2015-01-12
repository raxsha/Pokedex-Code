import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * An implementation of the FunctionalSortedSet interface that uses an ArrayList
 * as the backing data structure.
 *
 * @author Raksha
 * @version 1.1
 * @param <E> A comparable object that is contained within this sorted set.
 */
public class MySortedSet<E extends Comparable<? super E>>
    implements FunctionalSortedSet<E> {

    private ArrayList<E> list;
    private Comparator<E> c;

    /**
     * Creates a MySortedSet using the Comparable's compareTo as Comparator
     */
    public MySortedSet() {
        this(null);
    }

    /**
     * Creates a MySortedSet using a specific Comparator
     *
     * @param c a Comparator that either "has" or "is" one int valued method
     */
    public MySortedSet(Comparator<E> c) {
        this.c = c;
        list = new ArrayList<E>();
    }

    //-----------FunctionalSortedSet METHODS - ONLY MODIFY filter!!------------
    /**
     * filters the collection based on the passed in predicate
     *
     * @param the predicate to sort by
     * @return the filtered sorted set
     */
    @Override
    public MySortedSet<E> filter(Predicate<E> p) {
        MySortedSet<E> copy = new MySortedSet<E>(this.c);
        list.forEach(copy::add);
        copy.removeIf(p.negate());
        return copy;
    }

    /**
     * sorts a set by a specific criteria
     *
     * @param c a Comparator that either "has" or "is" one int valued method
     * @return a sorted set
     */
    @Override
    public MySortedSet<E> sort(Comparator<E> comparator) {
        MySortedSet<E> ret = new MySortedSet<E>(comparator);
        ret.addAll(this.list);
        return ret;
    }

    //------SortedSet METHODS - ONLY MODIFY subSet and tailSet!!---------------

    /**
     * Creates a specific Comparator
     *
     * @return a comparator c
     */
    @Override
    public Comparator<? super E> comparator() {
        return c;
    }

    /**
     * gets the first item in the list
     *
     * @preturn the first object in the collection
     */
    @Override
    public E first() {
        return list.get(0);
    }

    /**
     * Returns a MySortedSet object with all elements [first, toElement) using a
     * functional programming expression.
     *
     * @param toElement The element the returned set should stop before.
     * @return A sorted set containing elements strictly less than toElement.
     */
    @Override
    public MySortedSet<E> headSet(E toElement) {
        return list.subList(0, list.indexOf(toElement)).stream().collect(
                        Collectors.toCollection(() -> new MySortedSet<>(c)));
    }

    /**
     * Return a MySortedSet object with all elements [fromElement, toElement)
     * using a functional programming expression.
     *
     * @param fromElement The first element the returned set should contain.
     * @param toElement The element the returned set should stop before.
     * @return A sorted set containing elements fromElement to toElement.
     */
    @Override
    public MySortedSet<E> subSet(E fromElement, E toElement) {
        return list.subList(list.indexOf(fromElement),
            list.indexOf(toElement)).stream().collect(
            Collectors.toCollection(() -> new MySortedSet<>(c)));
    }

    /**
     * Return a MySortedSet object with all elements [fromElement, last]
     * using a functional programming expression.
     *
     * @param fromElement The first element the returned set should contain.
     * @return A sorted set containing elements fromElement and onwards.
     */
    @Override
    public MySortedSet<E> tailSet(E fromElement) {
        return list.subList(list.indexOf(fromElement),
            list.size()).stream().collect(
            Collectors.toCollection(() -> new MySortedSet<>(c)));
    }

    /**
     * gets last object in collection
     *
     * @return the final object in the collection
     */
    @Override
    public E last() {
        return list.get(list.size() - 1);
    }

    //-----------Set METHODS---------------------------------------------

    /**
     * adds an object in the spot it belongs (in order)
     *
     * @param the thing to be added
     * @return whether it was added
     */
    @Override
    public boolean add(E e) {
        if (c != null) {
            int i = 0;
            while (i < list.size() && c.compare(e, list.get(i)) > 0) {
                i++;
            }
            list.add(i, e);
            return true;
        } else {
            return list.add(e);
        }
    }

    /**
     * removes an object in the collection
     *
     * @param the thing to be removed
     * @return whether it was removed
     */
    @Override
    public boolean remove(Object e) {
        return list.remove(e);
    }

    /**
     * determines if the collection has this object
     *
     * @param the object to be searched for
     * @return whether it was found or not
     */
    @Override
    public boolean contains(Object e) {
        return list.contains(e);
    }

    /**
     * determines if the collection has these objects
     *
     * @param the objects to be searched for
     * @return whether they were found or not
     */
    @Override
    public boolean containsAll(Collection<?> col) {
        return list.containsAll(col);
    }

    /**
     * determines if the collection is empty
     *
     * @return whether it was empty or not
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * determines the size of the collection
     *
     * @return the size of the collection
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
      * adds the multiple objects
      *
      * @param the collection to be added
      * @return whether they were added or not
      */
    @Override
    public boolean addAll(Collection<? extends E> col) {
        for (E e : col) {
            this.add(e);
        }
        return true;
    }

    /**
      * removes the multiple objects
      *
      * @param the collection to be removed
      * @return whether they were removed or not
      */
    @Override
    public boolean removeAll(Collection<?> col) {
        return list.removeAll(col);
    }

    /**
      * retains the objects that aren't included here
      *
      * @param the collection to not be removed
      * @return whether they were removed or not
      */
    @Override
    public boolean retainAll(Collection<?> col) {
        return list.retainAll(col);
    }

    /**
      * returns an iterator of the elements in the arraylist
      *
      * @return an iterator
      */
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    /**
      * empties collection
      *
      */
    @Override
    public void clear() {
        list.clear();
    }

    /**
      * makes the collection into an array version
      *
      * @return the array of objects
      */
    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    /**
      * makes the collection into an array version
      *
      * @param the array of objects in proper order
      * @return the array of objects
      */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    /**
      * makes the collection into a string version requested in assignment
      *
      * @return the string for the objects
      */
    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < list.size(); i++) {
            string += list.get(i).toString() + "\n";
        }
        return string;
    }
}
