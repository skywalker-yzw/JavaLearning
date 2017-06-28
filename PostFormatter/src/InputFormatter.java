import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * This class serves as a parser to parse the input file
 * It will store the original feed and also save all data objects into a hash map for fast look up later when do formatting
 */
public class InputFormatter {
    String fileName;
    String originalFeed;
    String output;

    public InputFormatter(String fileName) {
        this.fileName = fileName;
        this.originalFeed = "";
        this.output = "";
    }

    public void parseAndFormat() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.fileName))) {
            String line = in.readLine();

            while (line != null) {
                if (line.equals("")) {
                    line = in.readLine();
                    continue;
                } else if (line.contains("positions") == false) {
                    originalFeed += line;
                    output += line;
                } else {
                    //System.out.println(line);

                    // exact the start and end position for each data type
                    String[] array1 = line.split(" ");
                    int startPos = Integer.parseInt(array1[1]);
                    int endPos = Integer.parseInt(array1[3]);

                    String[] array2 = line.split("-> ");
                    String type = array2[1];

                    //System.out.println("startPos: " + startPos + " endPos: " + endPos + " data type: " + type);
                    String content = originalFeed.substring(startPos, endPos);
                    //System.out.println("type: " + type + " content: " + content);

                    // call to the factory class to create the cooresponding data object based on the data type
                    DataType data = DataTypeFactory.createDataObj(type, content);

                    if (data != null) {
                        // replace the part with formatted string
                        output = output.replace(content, data.wrap());

                        //System.out.println(output);
                    } else {
                        return;
                    }
                }

                line = in.readLine();
            }

            //System.out.println(originalFeed);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String getOriginalFeed() {
        return this.originalFeed;
    }

    public String getFormattedOutput() {
        return this.output;
    }
}
