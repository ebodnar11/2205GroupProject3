import java.util.ArrayList;
import java.util.Scanner;

public class GenericTreeTest {
    public static void main(String[] args){

        GenericTree<String> gt = new GenericTree<>();

        //The following was just general testing to if the insert function worked
        gt.addRoot("A");
        gt.insert("A", "B");
        gt.insert("A", "C");
        gt.insert("A", "D");
        gt.insert("A", "E");
        gt.insert("B", "1");
        gt.insert("C", "2");
        gt.insert("C", "3");
        gt.insert("1", "a");

        //Testing the preorder method
        System.out.println("Testing Sequence: ");
        ArrayList preOrder =  gt.preOrder(gt.root());
        System.out.print("Preorder: ");
        for(Object elem : preOrder){
            System.out.print(elem + " ");
        }
        gt.clearList();

        //Testing the postorder method
        System.out.println("\n-----------------------------");
        System.out.print("Postorder: ");
        ArrayList postOrder =  gt.postOrder(gt.root());
        for(Object elem : postOrder){
            System.out.print(elem + " ");
        }
        gt.clearList();
        System.out.println("\n-----------------------------");

        //This is the bonus - to take a directory path from the user and build the tree based on given elements
        System.out.println("Input Sequence: ");
        Scanner reader = new Scanner(System.in);

        //Start the process by requesting the name of the main file.
        //This becomes the root and we execute fillByNode, beginning at the root
        System.out.println("Enter the name of the main file: ");
        String temp = reader.nextLine();
        gt.addRoot(temp);
        gt.fillByNode(gt.root());

        //Calling preorder traversal method on the new tree
        ArrayList tester = gt.preOrder(gt.root());
        System.out.print("Tree has been created. \nResults: ");
        for(Object item : tester){
            System.out.print(item + ", ");
        }
        System.out.print("\b\b\n");
    }
}
