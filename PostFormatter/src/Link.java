/**
 * This is the class for Link data type
 */
public class Link extends DataType {
    String tag;

    public Link(String type, String content) {
        super(type, content);
        tag = "a href";
    }

    public String wrap() {
        String str = "<" + tag + "=" + "\"" + content + "\"" + ">" + content + "</a>";

        return str;
    }
}
