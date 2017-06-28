/**
 * This is a factory class created to easily create objects for all data types, following the factory design pattern
 * You will need to extend the creation API when new data types are added
 */
public class DataTypeFactory {
    public static DataType createDataObj(String type, String content) {

        type = type.toLowerCase();
        switch(type) {
            case "entity":
                return new Entity(type, content);

            case "twitter username":
                return new TwitterUser(type, content);

            case "link":
                return new Link(type, content);

            default:
                System.out.println("Unknown data type!");
        }

        return null;
    }
}
