package readability;

public class ReadabilityAnalyzer {
    private final String text;

    public ReadabilityAnalyzer(String text) {
        this.text = text;
    }

    public int getWordCount() {
        return text.split("\\s+").length;
    }

    public int getSentenceCount() {
        return text.split("[.!?]").length;
    }

    public int getCharacterCount() {
        return text.replaceAll("\\s", "").length();
    }

    public int getSyllableCount() {
        int syllables = 0;
        for (String word : text.split("\\s+")) {
            syllables += countSyllables(word.toLowerCase());
        }
        return syllables;
    }

    public int getPolysyllableCount() {
        int polys = 0;
        for (String word : text.split("\\s+")) {
            if (countSyllables(word.toLowerCase()) > 2) {
                polys++;
            }
        }
        return polys;
    }

    private int countSyllables(String word) {
        int count = 0;
        boolean prevVowel = false;
        String vowels = "aeiouy";

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (vowels.indexOf(c) >= 0) {
                if (!prevVowel) {
                    count++;
                }
                prevVowel = true;
            } else {
                prevVowel = false;
            }
        }

        if (word.endsWith("e")) {
            count--;
        }
        return Math.max(1, count);
    }
}
