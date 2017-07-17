import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by yizhiw on 7/12/2017.
 */
public class Tracker {
    Map<String, List<Integer>> hostPool;

    public Tracker() {
        hostPool = new HashMap<String,List<Integer>>();
    }

    public String allocate(String hostType) {
        List<Integer> list = hostPool.get(hostType);

        if (list == null) {
            // new type
            list = new LinkedList<Integer>();
            list.add(1);
            // update the host pool
            hostPool.put(hostType, list);
            return (hostType + "1");
        } else {
            // existing type, find the next number, insert into the list
            ServerCounter counter = new ServerCounter();
            int next = counter.nextServerNumber(list);
            // add into the list
            list.add(next);

            return (hostType + next);
        }
    }

    public void deallocate(String server) {
        if (server == null) {
            return;
        }

        // get the host type and server number from the passing server name
        String hostType = "";
        int serverID = 0;
        for (int i = 0; i < server.length(); i++) {
            char ch = server.charAt(i);
            if ((ch >= '0') && (ch <= '9')) {
                hostType = server.substring(0, i);
                serverID = Integer.parseInt(server.substring(i, server.length()));
            }
        }

        List<Integer> list = hostPool.get(hostType);
        if (list == null) {
            return;
        } else {
            // remove serverID from the list
            for (int i = 0; i < list.size(); i++) {
                if (serverID == list.get(i)) {
                    list.remove(i);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Tracker tacker = new Tracker();
        System.out.println(tacker.allocate("apibox"));
        System.out.println(tacker.allocate("apibox"));
        System.out.println(tacker.allocate("apibox"));
        System.out.println(tacker.allocate("sitebox"));
        System.out.println(tacker.allocate("sitebox"));
        System.out.println(tacker.allocate("sitebox"));
        System.out.println(tacker.allocate("sitebox"));
        tacker.deallocate("apibox1");
        System.out.println(tacker.allocate("apibox"));
        tacker.deallocate("sitebox3");
        tacker.deallocate("sitebox2");
        System.out.println(tacker.allocate("sitebox"));
    }
}
