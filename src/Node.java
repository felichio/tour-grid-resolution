import java.util.Random;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

public class Node implements Comparable<Node> {
    
    
    
    public Node parent;
    public Node left;
    public Node right;
    
    private String team = "";
    private int score = 0;
    
    public Node() {
        
    }
    
    public Node(String team, int score) {
        this.team = team;
        this.score = score;
    }
    
    public void pullWinner() {
        if (!isLeaf()) {
            this.team = Node.getWinner(left, right).team;
            System.out.println(left.team + " vs " + right.team + " = " + left.score + " - " + right.score + "  | --> " + "Winner: " + this.team);
            
            this.left = null;
            this.right = null;
        }    
    }
    
    public void setScores() {
        if (!isLeaf()) {
            Scanner sc = new Scanner(System.in);
            String result;
            do {
                System.out.print(left.team + " vs " + right.team + " = ");
                result = sc.nextLine();
            } while (!result.matches("\\d+-\\d+"));
            
            String[] scores = result.split("-");
            left.score = Integer.parseInt(scores[0]);
            right.score = Integer.parseInt(scores[1]);
        }
    }

    
    
    
    
    public boolean isLeaf() {
        return left == null && right == null;
    }
    
    public boolean isParentOfLeafs() {
        return !isLeaf() && left.isLeaf() && right.isLeaf();
    }
    
    
    public int compareTo(Node other) {
        return this.score - other.score;
    }
    
    public static Node getWinner(Node a, Node b) {
        if (a.compareTo(b) > 0) return a;
        else if (a.compareTo(b) < 0) return b;
        
        if (new Random().nextBoolean()) {
            return a;
        }
 
        return b;
    }
    
    public static int getDepth(Node n) {
        if (n.parent == null) return 0;
        return 1 + getDepth(n.parent);
    }
    
    public String getTeam() {
        return this.team;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void setTeam(String team) {
        this.team = team;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
}
