import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Predicate;

public class Tree {
    
    public Node root;
     
    
    public Tree(int numOfLeafs) {
        root = new Node();
        insertNodes((numOfLeafs - 1) * 2);
    }
    
    
    public void insertNodes(int number) {
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        Node n;
        while (number > 0) {
            n = q.poll();
            
            n.left = new Node();
            n.right = new Node();
            
            n.left.parent = n;
            n.right.parent = n;
            
            q.add(n.left);
            q.add(n.right);
            number -= 2;
        }
        
    }
    
    public Queue<Node> bfsTraversal(Predicate<Node> t) {
        Queue<Node> q = new LinkedList<Node>();
        Queue<Node> buffer = new LinkedList<Node>();
        q.add(root);
        Node n;
        
        while((n = q.poll()) != null) {
            q.add(n.left);
            q.add(n.right);
            if (t.test(n)) {
                buffer.add(n);
            }
        }
        return buffer;
    }
    
    
    
}
