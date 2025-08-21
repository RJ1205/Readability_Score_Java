package readability;

public class ClScore implements ReadabilityScore {
    @Override
    public double calculate(ReadabilityAnalyzer a) {
        double L = a.getCharacterCount() / (double) a.getWordCount() * 100;
        double S = a.getSentenceCount() / (double) a.getWordCount() * 100;
        return 0.0588 * L - 0.296 * S - 15.8;
    }
}
