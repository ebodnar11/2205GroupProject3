import java.util.Iterator;
import java.util.ArrayList;

public interface Tree<E> extends Iterable<E> {

    Node<E> root();
    Node<E> parent(Node<E> p) throws IllegalArgumentException;
    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;
    int numChildren(ArrayList<Node<E>> p) throws IllegalArgumentException;
    boolean isInternal(Node<E> p) throws IllegalArgumentException;
    boolean isExternal(Node<E> p) throws IllegalArgumentException;
    boolean isRoot(Node<E> p) throws IllegalArgumentException;
    boolean isEmpty();
    Iterator<E> iterator();
    Iterable<Position<E>> positions();
}
