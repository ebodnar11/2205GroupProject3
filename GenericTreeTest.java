import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

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

        //Everything below this relates to the bonus

        //Here we take the name of a directory from the user in order to add the sub-directories and sub-files to a tree
        //If I had a file named 'test' on my desktop, I would enter /Users/username/Desktop/test when prompted for the name of the directory path (on a mac)
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the name of the directory path: ");
        String directoryPath = reader.nextLine();

        //Create a File object named mainDir as well as a new Tree object (as gt has been filled already)
        File mainDir = new File(directoryPath);
        GenericTree<String> newTree = new GenericTree<>();

        //Check to see if mainDir exists and has sub-files/directories (so see if it is a directory)
        //If the conditions are true, we call the giveFiles method
        if(mainDir.exists() && mainDir.isDirectory()){
            File[] fArray = mainDir.listFiles();
            System.out.println("Reading files from directory: " + mainDir);
            newTree.giveFiles(fArray, 0, 0);
        }

        //Preorder traversal of the directory/file tree
        ArrayList pre = newTree.preOrder(newTree.root());
        System.out.print("Preorder traversal of directory: ");
        for(Object elem : pre){
            System.out.print(elem + ", ");
        }
        System.out.println("\b\b");
        newTree.clearList();

        //Postorder traversal of the directory/file tree
        ArrayList post = newTree.postOrder(newTree.root());
        System.out.print("Postorder traversal of directory: ");
        for(Object elem : post){
            System.out.print(elem + ", ");
        }
        System.out.println("\b\b");
    }
}