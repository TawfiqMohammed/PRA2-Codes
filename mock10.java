import java.util.*;

class Player {
    private long playerID;
    private String playerName;
    private int levelCompleted;
    private int badgesEarned;
    
    public Player (long playerID, String playerName, int levelCompleted, int badgesEarned){
        this.playerID = playerID;
        this.playerName = playerName;
        this.badgesEarned = badgesEarned;
        this.levelCompleted = levelCompleted;
    }
    
    public long getPlayerID(){
        return playerID;
    }
    
    public String getPlayerName () {
        return playerName;
    }
    
    public int getLevelCompleted() {
        return levelCompleted;
    }
    
    public int getBadgesEarned() {
        return badgesEarned;
    }
    
    public void setLevelCompleted(int levelCompleted){
        this.levelCompleted = levelCompleted;
    }
    
    public void setBadgesEarned(int badgesEarned){
        this.badgesEarned = badgesEarned;
    }
}


class AchievementTracker {

    // Method to increment level completed and update badges earned
    public void completeLevel(Player player) {
        int b = player.getBadgesEarned();
        int l = player.getLevelCompleted();
        
        l += 1;
        if (l%3==0){
            b += 1;
        }    
        
        player.setLevelCompleted(l);
        player.setBadgesEarned(b);
    }

    // Method to display player's stats
    public void displayPlayerStats(Player player) {
        System.out.println("Player ID: " + player.getPlayerID());
        System.out.println("Name: " + player.getPlayerName());
        System.out.println("Levels Completed: " + player.getLevelCompleted());
        System.out.println("Badges Earned: " + player.getBadgesEarned());
        System.out.println();
    }

    // Method to simulate gameplay where a player completes a given number of levels
    public void simulateGameplay(ArrayList<Player> playerList, long playerID, int levelsToUpdate) {
        for (Player P : playerList){
            if (P.getPlayerID() == playerID){
                for (int i=0; i<levelsToUpdate; i++){
                    completeLevel(P);
                }
            }
        }
    }
}


public class mock10 {
    public static void main(String[] args) {
        ArrayList<Player> playerList = new ArrayList<>();
        
        Scanner sc = new Scanner (System.in);
        
        boolean flag = true;
        
        while (flag){
            long pid = Long.parseLong(sc.nextLine().trim());
            String pname =  sc.nextLine().trim();
            int levels = Integer.parseInt(sc.nextLine().trim());
            int badges = Integer.parseInt(sc.nextLine().trim());
            
            playerList.add(new Player(pid, pname, levels, badges));
            
            flag = Boolean.parseBoolean(sc.nextLine().trim());
        }
        
        // Create AchievementTracker object
        AchievementTracker tracker = new AchievementTracker();

        int n = Integer.parseInt(sc.nextLine().trim());

        // Simulate gameplay
        int[] levelsToSimulate = new int[n];

        for (int in=0; in<levelsToSimulate.length; in++){
            levelsToSimulate[in] = Integer.parseInt(sc.nextLine().trim());
        }
  
        // Simulate gameplay and display stats for each player
        for (int i = 0; i < playerList.size(); i++) {
            tracker.simulateGameplay(playerList, playerList.get(i).getPlayerID(), levelsToSimulate[i]);
            tracker.displayPlayerStats(playerList.get(i));
        }
        sc.close();
    }
}