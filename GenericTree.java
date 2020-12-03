import java.util.Iterator;
import java.util.ArrayList;
import java.io.File;

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
    giveFiles method to accomplish the bonus task:
    three parameters - an array of files, the current index to be analyzed, and the level (for root purposes)
    First if statement: termination condition - this stops the recursive loop once all of the sub-files/directories have been added to the tree
    Second if statement: if the current File is a directory
        insert the current File as the child of its parent file in the tree
        then recursively call the giveFiles method while moving up a level (in order to avoid adding the root again)
            this allows us to move to the first sub-file of the directory
    Else if statement: if the current File is a file - insert it as a child to its parent file in the tree
    The internal if statement of both the if and else if statement is to handle the adding of the root
        Both of them are there to add the root (parent of the current File) when level = index = 0 (which can only occur once)
        We need this within both statements in order to handle the case of there only being files or only being directories within the root file
    Finally, if the method is not terminated already, recursively call the giveFiles method again while moving up an index point
     */
    public void giveFiles(File[] fArray, int index, int level){
        if(index == fArray.length){
            return;
        }
        if(fArray[index].isDirectory()){
            if(index == 0 && level == 0){
                addRoot((E) fArray[index].getParentFile().getName());
            }
            insert((E) fArray[index].getParentFile().getName(), (E) fArray[index].getName());
            giveFiles(fArray[index].listFiles(), 0, level + 1);
        }
        else if(fArray[index].isFile()){
            if(index == 0 && level == 0){
                addRoot((E) fArray[index].getParentFile().getName());
            }
            insert((E) fArray[index].getParentFile().getName(), (E) fArray[index].getName());
        }
        giveFiles(fArray, ++index, level);
    }
}
