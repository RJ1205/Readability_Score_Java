package readability;

public class SmogScore implements ReadabilityScore {
    @Override
    public double calculate(ReadabilityAnalyzer a) {
        double polys = a.getPolysyllableCount();
        double sentences = a.getSentenceCount();
        return 1.043 * Math.sqrt(polys * (30.0 / sentences)) + 3.1291;
    }
}
