import java.util.ArrayList;

public class Node<E> implements Position<E>
{
    private E element;
    private ArrayList<Node<E>> children;
    private Node<E> parent;

    //Empty constructor
    public Node(){

    }

    //Constructor to create node only based on the element
    public Node(E elem){
        this();
        setElement(elem);
    }

    //constructor to create node based on full information - element, parent, children
    public Node(E elem, Node<E> parent, ArrayList<Node<E>> children)
    {
        element = elem;
        this.parent = parent;
        this.children = children;
    }

    public void setElement(E element){
        this.element = element;
    }

    public E getElement() throws IllegalStateException {
        return element;
    }

    //If a node has no children, create an empty ArrayList
    //Then return the newly created or existing ArrayList
    public ArrayList<Node<E>> getChildren(){
        if(children == null){
            children = new ArrayList<>();
        }
        return children;
    }

    public void setChildren(ArrayList<Node<E>> children){
        this.children = children;
    }

    //Finds an element within the tree and returns its Node
    //the find and insert elements in GenericTree are dependent on this
    public Node<E> find(E element){
        if(this.element.equals(element)){
            return this;
        }
        for(Node<E> node : getChildren()){
            Node<E> found = node.find(element);
            if(found != null){
                return found;
            }
        }
        return null;
    }

    public void addChild(Node<E> child){
        children.add(child);
    }

    public void setParent(Node<E> parent){
        this.parent = parent;
    }

    public Node<E> getParent() {
        return parent;
    }
}