package readability;

public class FkScore implements ReadabilityScore {
    @Override
    public double calculate(ReadabilityAnalyzer a) {
        double words = a.getWordCount();
        double sentences = a.getSentenceCount();
        double syllables = a.getSyllableCount();
        return 0.39 * (words / sentences) + 11.8 * (syllables / words) - 15.59;
    }
}
