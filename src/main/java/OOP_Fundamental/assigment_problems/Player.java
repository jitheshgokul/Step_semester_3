import java.util.Arrays;

public class Player implements Comparable<Player> {

    private String name;
    private int matchesPlayed;
    private double battingAverage;
    private boolean injured;

    public Player(String name, int matchesPlayed,
                  double battingAverage, boolean injured) {
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.battingAverage = battingAverage;
        this.injured = injured;
    }

    static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10;
    }

    static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= 5 && !injured;
    }

    @Override
    public int compareTo(Player other) {
        int result = Double.compare(
                other.battingAverage, this.battingAverage);

        if (result == 0) {
            return this.name.compareTo(other.name);
        }

        return result;
    }

    public String getName() {
        return name;
    }

    static String draftAndRank(Player[] players) {
        Player[] draftable = new Player[players.length];
        int count = 0;

        for (Player player : players) {
            if (isDraftable(player.matchesPlayed)
                    || isDraftable(player.matchesPlayed,
                                   player.injured)) {
                draftable[count] = player;
                count++;
            }
        }

        Player[] ranked = Arrays.copyOf(draftable, count);
        Arrays.sort(ranked);

        String result = "";

        for (int i = 0; i < ranked.length; i++) {
            if (i > 0) {
                result += " | ";
            }

            result += (i + 1) + ". " + ranked[i].getName();
        }

        return result;
    }

    public static void main(String[] args) {
        Player[] players = {
            new Player("Rahul", 7, 55.0, false),
            new Player("Virat", 15, 48.0, false),
            new Player("Dev", 12, 20.0, true),
            new Player("Sameer", 3, 60.0, false)
        };

        System.out.println(draftAndRank(players));
    }
}
