/**
 * This class is the base class for all data types that output form the second module
 * The wrapper API wrap() has defined as abstract, so that all the subclasses will need to implement it
 */
public abstract class DataType {
    String type;      //data type
    String content;    //actual content

    public DataType(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public abstract String wrap();
}
