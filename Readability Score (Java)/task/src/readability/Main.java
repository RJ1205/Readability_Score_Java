package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    private static final String[] AGES = {
            "6", "7", "9", "10", "11", "12", "13", "14", "15", "16",
            "17", "18", "24", "24", "24" // Hyperskill Vorgabe
    };

    private static int getAge(double score) {
        int index = (int) Math.ceil(score);
        if (index < 1) index = 1;
        if (index > AGES.length) index = AGES.length;
        return Integer.parseInt(AGES[index - 1]);
    }

    public static void main(String[] args) throws IOException {
        String text = Files.readString(Paths.get(args[0]));
        ReadabilityAnalyzer analyzer = new ReadabilityAnalyzer(text);

        System.out.println("The text is:");
        System.out.println(text + "\n");
        System.out.println("Words: " + analyzer.getWordCount());
        System.out.println("Sentences: " + analyzer.getSentenceCount());
        System.out.println("Characters: " + analyzer.getCharacterCount());
        System.out.println("Syllables: " + analyzer.getSyllableCount());
        System.out.println("Polysyllables: " + analyzer.getPolysyllableCount());

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String choice = sc.nextLine();

        double totalAge = 0;
        int count = 0;

        if (choice.equals("ARI") || choice.equals("all")) {
            double score = new AriScore().calculate(analyzer);
            int age = getAge(score);
            System.out.printf("Automated Readability Index %.2f (about %d-year-olds).%n", score, age);
            totalAge += age; count++;
        }
        if (choice.equals("FK") || choice.equals("all")) {
            double score = new FkScore().calculate(analyzer);
            int age = getAge(score);
            System.out.printf("Flesch-Kincaid readability tests %.2f (about %d-year-olds).%n", score, age);
            totalAge += age; count++;
        }
        if (choice.equals("SMOG") || choice.equals("all")) {
            double score = new SmogScore().calculate(analyzer);
            int age = getAge(score);
            System.out.printf("Simple Measure of Gobbledygook %.2f (about %d-year-olds).%n", score, age);
            totalAge += age; count++;
        }
        if (choice.equals("CL") || choice.equals("all")) {
            double score = new ClScore().calculate(analyzer);
            int age = getAge(score);
            System.out.printf("Coleman-Liau index %.2f (about %d-year-olds).%n", score, age);
            totalAge += age; count++;
        }

        if (choice.equals("all")) {
            System.out.printf("%nThis text should be understood in average by %.2f-year-olds.%n",
                    totalAge / count);
        }
    }
}
