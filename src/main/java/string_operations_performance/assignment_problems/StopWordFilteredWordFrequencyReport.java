import java.util.HashMap;
import java.util.Map;

public class StopWordFilteredWordFrequencyReport {

    public static void printFilteredWordFrequency(String feedback) {
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        feedback = feedback.toLowerCase()
                           .replace(".", "")
                           .replace(",", "");

        String[] words = feedback.split("\\s+");
        Map<String, Integer> frequency = new HashMap<>();

        for (String word : words) {
            boolean isStopWord = false;

            for (String stop : stopWords) {
                if (word.equals(stop)) {
                    isStopWord = true;
                    break;
                }
            }

            if (!isStopWord && !word.isEmpty()) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
        }

        frequency.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(entry ->
                    System.out.println(entry.getKey() + ": " + entry.getValue())
                );
    }

    public static void main(String[] args) {
        printFilteredWordFrequency(
            "The mentor was great, the session was great and clear."
        );
    }
}