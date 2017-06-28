import java.util.Map;

/**
 * This is the class has the main method
 */
public class InputFormatterApp {
    public static void main(String[] args) {
        InputFormatter formatter = new InputFormatter("./input.txt");
        formatter.parseAndFormat();
        System.out.println("The original feed is: " + formatter.getOriginalFeed());
        System.out.println("Formatted output is: " + formatter.getFormattedOutput());
    }
}