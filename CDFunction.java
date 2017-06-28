import java.io.*;
import java.util.*;

/**
 * Created by yizhiw on 6/26/2017.
 */
public class CDFunction {
    public String cdFunction(String curDir, String destDir) {
        if (curDir == null) {
            return destDir;
        }

        if (destDir == null) {
            return curDir;
        }

        String[] str = curDir.split("/");
        // stack to store the path
        Stack<String> stack = new Stack<String>();
        for(String s : str) {
            if (s.equals("") == true) {
                continue;
            }
            stack.push(s);
        }

        String result = "";
        // check the destDir
        String[] dest = destDir.split("/");
        if (dest[0].equals("") == true) {
            return destDir;
        } else {
            for (String s: dest) {
                if (s.equals("..") == true) {
                    stack.pop();
                } else {
                    stack.push(s);
                }
            }
        }

        while(stack.isEmpty() == false) {
            result = "/" + stack.pop() + result;
        }

        return result;
    }

    public static void main(String[] args) {
        String curDir = "/root/usr/yizhiw";
        String destDir = "../local/test";

        CDFunction sol =  new CDFunction();

        System.out.println(sol.cdFunction(curDir, destDir));
    }
}
