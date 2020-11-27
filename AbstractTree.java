public abstract class AbstractTree<E> implements Tree<E>{

    public boolean isInternal(Node<E> p)
    {
        return numChildren(p.getChildren()) > 0;
    }

    public boolean isExternal(Node<E> p)
    {
        return numChildren(p.getChildren()) == 0;
    }

    public boolean isRoot(Node<E> p)
    {
        return p == root();
    }

    public boolean isEmpty()
    {
        return root() == null;
    }
}
