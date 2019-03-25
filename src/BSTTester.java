public class BSTTester {

    public static void main(String args[]) {
        BetterBST<Integer> bst = new BetterBST<Integer>();
        bst.insert(6);
        bst.insert(2);
        bst.insert(8);
        bst.insert(1);
        bst.insert(4);
        bst.insert(5);
        bst.insert(9);
        bst.insert(10);
        bst.insert(12);
        bst.insert(7);
        bst.insert(13);
        bst.prettyPrint();
        //System.out.println(bst.height());
    }
}
