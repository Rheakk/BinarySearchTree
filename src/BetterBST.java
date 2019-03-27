import java.util.ArrayList;
public class BetterBST<T extends Comparable<? super T>> extends BinarySearchTree<T> {


    public BetterBST(){
        root = null;
    }

    public BetterBST(BinaryNode<T> root){
        this.root = root;
    }

    public void prettyPrint(){
        prettyPrint(root);
    }

   private void prettyPrint(BinaryNode<T> t){
       int h = height();
       int maxLevel = h + 1;
       int level;
       ArrayList<BinaryNode<T>> current = new ArrayList<BinaryNode<T>>();
       current.add(t);
       for (int i = 0; i <= h; i++) {
           level = i + 1;
           printSpace(Math.pow(2, maxLevel - level) - 1);
           ArrayList<BinaryNode<T>> next = new ArrayList<BinaryNode<T>>();
           for (int j = 0; j < current.size(); j++) {
               BinaryNode<T> node = current.get(j);
               if (node == null){
                   System.out.print(" ");
                   next.add(null);
                   next.add(null);
               }
               else {
                    System.out.print(node.data);
                    next.add(node.left);
                    next.add(node.right);
               }
               printSpace(Math.pow(2, maxLevel - level + 1) - 1);
           }
           System.out.println();
           current = next;
       }

    }
    public void printSpace(double x){
        while (x>0){
            System.out.print(" ");
            x--;
        }
    }

    public int height(){

        return depth (root);
    }
    public int depth(BinaryNode<T> t) {
        int leftDepth, rightDepth;
        if (t.left != null){
            leftDepth = depth(t.left) + 1;
        }
        else {
            leftDepth = 0;
        }
        if (t.right != null){
            rightDepth = depth(t.right) + 1;
        }
        else {
            rightDepth = 0;
        }
        // https://www.tutorialspoint.com/java/lang/math_max_int.htm
        return java.lang.Math.max(rightDepth, leftDepth);
    }

    public T imbalance() { return imbalance(root); }

    private T imbalance(BinaryNode<T> t) {
        int rHeight, lHeight;
        if (t.left == null){
            lHeight = -1;
        }
        else{
            lHeight = depth(t.left);
        }
        if(t.right == null){
            rHeight = -1;
        }
        else{
            rHeight = depth(t.right);
        }
        if (java.lang.Math.abs(rHeight - lHeight) > 1) {
        //    System.out.println("Imbalance found at node: " + t.data);
        //    System.out.println("Height of left subtree:" + lHeight);
        //   System.out.println("Height of right subtree:" + rHeight);
        //   System.out.println("Difference in height of " + java.lang.Math.abs(rHeight - lHeight));
            return t.data;
        }
        else {
            if (t.left != null) {
                imbalance(t.left);
            }
            if (t.right != null) {
                imbalance(t.right);
            }
        }
        return null;
    }
    public void mirrorInsert(T x){
        root = mirrorInsert( x, root );

    }
    private BinaryNode<T> mirrorInsert(T x, BinaryNode<T> t) {
        if (t == null)
            return new BinaryNode<T>(x, null, null);

        int compareResult = x.compareTo(t.data);

        if (compareResult > 0)
            t.left = mirrorInsert(x, t.left);
        else if (compareResult < 0)
            t.right = mirrorInsert(x, t.right);
        return t;
    }
    private BinarySearchTree<T> mirror(BinaryNode<T> t, BetterBST<T> tree){
        tree.mirrorInsert(t.data);
        if (t.left != null){
            mirror(t.left, tree);
        }
        if (t.right != null){
            mirror(t.right, tree);
        }
        return tree;
    }
    public BinarySearchTree<T> mirror(){
        BetterBST<T> newTree = new BetterBST<T>();
        return mirror(root, newTree);
    }
    public T smallestGreaterThan(T t){
        BinaryNode<T> currSmallest = new BinaryNode<T>(root.data);
        T result = smallestGreaterThan(t, root, true);
        if (result != null){
            System.out.println("Smallest node greater than " + t + ": " + result);
            return result;
        }
        else {
            System.out.println("No node value found greater than " + t);
            return null;
        }
    }
    public T findMax( )
    {
        if(root == null)
            throw new NullPointerException( );
        return findMax( root ).data;
    }

    private BinaryNode<T> findMax( BinaryNode<T> t )
    {
        if( t == null )
            return null;
        else if( t.right == null )
            return t;
        return findMax( t.right );
    }

    // private instance variable so can be updated
    // throughout entire method
    private BinaryNode<T> currSmallest; // the current smallest node greater than
                                        // what we're looking for

    private T smallestGreaterThan(T x, BinaryNode<T> t, boolean notFoundGreater){
        int compareResult = x.compareTo(t.data);
        // immediately returning if x is greater than or equal to
        // the max node value
        if (x.compareTo(findMax()) > 0 || x.compareTo(findMax()) == 0){
            return null;
        }
        // moving to right child if node is less than
        // what we're looking for
        if (compareResult > 0 || compareResult == 0){
            if (t.right != null) {
                smallestGreaterThan(x, t.right, true);
            }
        }
        else if (compareResult < 0) {
            // setting currSmallest for the first time
            // a greater node is found
            if (notFoundGreater){
                currSmallest = t;
            }
            if (t.data.compareTo(currSmallest.data) < 0) {
                currSmallest = t;
            }
            if (t.left != null) {
                smallestGreaterThan(x, t.left, false);
            }
        }
        return currSmallest.data;
    }

    public void printNode(BinaryNode<T> t){
        System.out.println(t.data);
        System.out.println(t.left.data);
        System.out.println(t.right.data);
    }
}
