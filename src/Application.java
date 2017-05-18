import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Application {
    
    
    public Tree tree;
    public int numOfTeams;
    public List<String> teams;
    
    public Application() {
        askForSize();
        tree = new Tree(numOfTeams);
        teams = getTeams();
        Collections.shuffle(teams);
        
        Queue<Node> buffer = tree.bfsTraversal(n -> n.isLeaf());
        
        teams.stream().forEach(name -> {
            buffer.poll().setTeam(name);
        });
        
        
        
        int logSquezze = numOfTeams;
        int round = 1;
        while (logSquezze > 1) {
            System.out.println("------------ Round " + round++ + "------------------");
            tree.bfsTraversal(n -> n.isParentOfLeafs())
            .stream().forEach(n -> {
                n.setScores();
                n.pullWinner();
            });
            
            logSquezze /= 2;
        }
        System.out.println("*******************************");
        System.out.println("Winner of tour: " + tree.root.getTeam());
        System.out.println("*******************************");
        
    }
    
    
    public void askForSize() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter a number (power of two): ");
            numOfTeams = sc.nextInt();
        } while (!isPowerOfTwo(numOfTeams));
    }
    
    
    
    
    public boolean isPowerOfTwo(int n) {
        return Arrays.asList(2, 4, 8, 16, 32, 64).contains(n);
    }
    
    
    public List<String> getTeams() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> teams = new ArrayList<String>();
        do {
            teams.clear();
            System.out.println("Insert (" + numOfTeams + ") comma separated team names: ");
            String t = sc.nextLine();
            String[] names = t.split(",");
            if (names.length != numOfTeams) continue;
            for (String name : names) {
                if (name.matches("\\s*")) {
                    System.out.println("No empty name permitted");
                    continue;
                }
                teams.add(name.replaceAll("\\s+", ""));
            }
            
        } while (teams.size() != numOfTeams);
        return teams;
    }
    
    
    
}
