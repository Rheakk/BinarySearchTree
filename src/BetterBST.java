import java.util.ArrayList;
public class BetterBST<T extends Comparable<? super T>> extends BinarySearchTree<T> {


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
    private int depth(BinaryNode<T> t) {
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

    public void printNode(BinaryNode<T> t){
        System.out.println(t.data);
        System.out.println(t.left.data);
        System.out.println(t.right.data);
    }
}
