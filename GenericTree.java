import java.util.Iterator;
import java.util.ArrayList;
import java.util.Scanner;

public class GenericTree<E> extends AbstractTree<E> {

    private Node<E> root;
    private ArrayList<E> list = new ArrayList<>();

    //Finds whether an element exists, calling the find element within the Node class to check
    public Node<E> find(E element){
        return root == null ? null: root.find(element);
    }

    //Takes an element and adds it as a root in the tree
    public void addRoot(E element){
        root = new Node<>(element, null, null);
    }

    //Inserts a parent-child node pair into the tree
    public void insertNode(Node<E> parent, Node<E> child){
        child.setParent(parent);
        ArrayList<Node<E>> children = parent.getChildren();
        children.add(child);
        parent.setChildren(children);
    }

    //Inserts two elements into the tree, one as a parent and one as a child
    public boolean insert(E parentElem, E elem){
        Node<E> found = find(parentElem);
        if (found == null) {
            return false;
        }
        Node<E> child = new Node<>(elem, found, null);
        found.getChildren().add(child);
        return true;
    }

    //Simply returns the root
    public Node<E> root() {
        return root;
    }

    //Most of these methods were unused, I don't know if we should include them or not
    public Node<E> parent(Node<E> child) throws IllegalArgumentException {
        return child.getParent();
    }

    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        return null;
    }

    public int numChildren(ArrayList<Node<E>> children) throws IllegalArgumentException {
        return children.size();
    }

    public Iterator<E> iterator() {
        return null;
    }

    public Iterable<Position<E>> positions() {
        return null;
    }

    //Preorder traversal - we add each element to an ArrayList, which this method returns
    //This could have returned void and used println instead
    public ArrayList<E> preOrder(Node<E> current){
        if(current == null){
            return list;
        }
        list.add(current.getElement());
        for(Node<E> node : current.getChildren()){
            preOrder(node);
        }
        return list;
    }

    //Postorder traversal - we add each element to an ArrayList, which this method returns
    //This could have returned void and used println instead
    public ArrayList<E> postOrder(Node<E> current){
        if(current == null){
            return list;
        }
        for(Node<E> node : current.getChildren()){
            postOrder(node);
        }
        list.add(current.getElement());
        return list;
    }

    //This method is to reset the list - the list (at the top of this class) is used to create
    //an ordered (postorder or preorder) representation of the tree for printing
    public void clearList(){
        list = new ArrayList<>();
    }

    /*
    This method is to fill the tree based on user input. The user is prompted to enter the name of a file that is
    "within" the "current" file, meaning they are required to enter a child of the current parent.
    The user has the option to type "BACK" to "exit the current file", which would be to move up a level in the tree
        - in this case, the new current node is the parent of the previous current node
    The user also has the option to type "ESCAPE" when finished. This simply terminates the tree building process
    If the user does not input "BACK", then we insert the value we read from the user as a child to the "current" node
    However, there are two cases here:
    1. The user inputted value will be an external node
        - external nodes in this file system are NOT concluded with a "/", so when this is the case of the user input,
          we do not try to branch off of this node as we know it is external. In this case, we call the fillByNode method
          recursively with the same "current" node as before
          - It is important to note that all internal nodes must end with "/" for this to work!!!
    2. The user inputted value will be an internal node
        - this is the "else" case, and in this case we call the fillByNode method recursively with the child node as the
          new "current" node so as to move downwards in the tree
    If the user inputs "BACK", then we call the fillByNode method recursively with the parent of the previous "current" node
    as the new "current" node so as to move back upwards in the tree
        - This will allow us to move one level upwards in the tree (eg. homeworks/ to cs016/)
    Sorry for writing an essay
     */
    public void fillByNode(Node<E> current){
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the name of a file that is within " + current.getElement() + " " +
                ". Type \"BACK\" to exit the current file. Type \"ESCAPE\" when finished.");
        String temp = reader.nextLine();
        Node<E> child = new Node<E>((E) temp);
        if(temp.equals("ESCAPE")){
            return;
        }
        if(temp.equals("BACK") && current == root()){
            return;
        }
        else if(!temp.equals("BACK")) {
            insertNode(current, child);
            if(!internalNode(temp)) {
                fillByNode(current);
            }
            else{
                fillByNode(child);
            }
        }
        else{
            fillByNode(current.getParent());
        }
        return;
    }

    //This method simply detects whether or not a node ends with "/", which would make it internal
    public boolean internalNode(String input){
        if(input.charAt(input.length() - 1) == '/'){
            return true;
        }
    return false;
    }

}
