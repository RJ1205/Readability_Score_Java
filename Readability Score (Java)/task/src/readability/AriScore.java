package readability;

public class AriScore implements ReadabilityScore {
    @Override
    public double calculate(ReadabilityAnalyzer a) {
        double chars = a.getCharacterCount();
        double words = a.getWordCount();
        double sentences = a.getSentenceCount();
        return 4.71 * (chars / words) + 0.5 * (words / sentences) - 21.43;
    }
}
