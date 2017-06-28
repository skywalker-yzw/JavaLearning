/**
 * This is the class for Entity data type
 */
public class Entity extends DataType {
    String tag;

    public Entity(String type, String content) {
        super(type, content);
        tag = "strong";
    }

    public String wrap() {
        String str = "<" + tag + ">" + content + "</" + tag + ">";

        return str;
    }
}
