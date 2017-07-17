import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yizhiw on 7/12/2017.
 */
public class ServerCounter{
    public int nextServerNumber(List<Integer> list) {
        if ((list == null) || (list.size() == 0)) {
            return 1;
        }

        // sort the list
        Collections.sort(list);

        // scan the list and return the first available server number
        int prev = 0;
        for (int i = 0; i < list.size(); i++) {
            int cur = list.get(i);

            if (cur - prev > 1) {
                return (prev + 1);
            } else {
                prev = cur;
            }
        }

        return (list.size() + 1);
    }

    public static void main(String[] args) {
        Integer[] array = {5,4,1};
        List<Integer> list = Arrays.asList(array);
        ServerCounter sol = new ServerCounter();

        System.out.println("The next available server # is: " + sol.nextServerNumber(list));
    }
}
