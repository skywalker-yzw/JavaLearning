/**
 * This is the class for twitter user data type
 */
public class TwitterUser extends DataType {
    String tag;

    public TwitterUser(String type, String content) {
        super(type, content);
        tag = "a href=" + "\"http://twitter.com/" + content.substring(1, content.length()) + "\"";
    }

    public String wrap() {

        String str = "@<" + tag + ">" + content.substring(1, content.length()) + "</a>";

        return str;
    }
}
