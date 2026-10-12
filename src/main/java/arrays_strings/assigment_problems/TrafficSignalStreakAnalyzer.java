public class TrafficSignalStreakAnalyzer {

public static void findLongestStreak(String signalLog) {
    if (signalLog.isEmpty()) {
        System.out.println("No Signal Data");
        return;
    }

    char longestSignal = signalLog.charAt(0);
    int longestCount = 1;

    char currentSignal = signalLog.charAt(0);
    int currentCount = 1;

    for (int i = 1; i < signalLog.length(); i++) {
        if (signalLog.charAt(i) == currentSignal) {
            currentCount++;
        } else {
            currentSignal = signalLog.charAt(i);
            currentCount = 1;
        }

        if (currentCount > longestCount) {
            longestCount = currentCount;
            longestSignal = currentSignal;
        }
    }

    System.out.println(
        "Longest Streak: '" + longestSignal
        + "' repeated " + longestCount + " times"
    );
}

public static void main(String[] args) {
    findLongestStreak("RRGGGYRR");
    findLongestStreak("RRRRYYGG");
}


}
