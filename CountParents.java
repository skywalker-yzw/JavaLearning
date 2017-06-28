/*
Your previous Markdown content is preserved below:

int[][] parentChildPairs = new int[][] {
    {1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7},
    {4, 5}, {4, 8}, {8, 9}
};

For example, in this diagram, 3 is a child of 1 and 2, and 5 is a child of 4:

1   2   4
 \ /   / \
  3   5   8
   \ / \   \
    6   7   9

Write a function that takes this data as input and outputs two collections: one containing all individuals with zero known parents, and one containing all individuals with exactly one known parent.

Sample output:
Zero parents: 1, 2, 4
One parent: 5, 7, 8, 9


 */

import java.io.*;
import java.util.*;

class CountParents {
    static int[][] parentChildPairs = new int[][] {
            {1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7},
            {4, 5}, {4, 8}, {8, 9}
    };

    // class to save all related information for each number
    public class Node {
        int val;
        List<Node> parentList;
        List<Node> childList;

        public Node(int val) {
            this.val = val;
            parentList = new LinkedList<Node>();
            childList = new LinkedList<Node>();
        }
    }

    public  void countParents(int[][] array, Map<Integer, Node> map) {
        if ((array == null) || (array.length == 0)) {
            return;
        }

        int row = array.length;
        int col = 2;

        // scan the array, create the cooresponding node objects and update the parent/child list
        for(int i = 0; i < row; i++) {
            // if it's a new node, create it and put it into the hashmap
            Node parent = map.get(array[i][0]);
            if (parent == null) {
                parent = new Node(array[i][0]);

                map.put(parent.val, parent);
            }

            // if it's a new node, create it and put it into the hashmap
            Node child = map.get(array[i][1]);
            if (child == null) {
                child = new Node(array[i][1]);

                map.put(child.val, child);
            }

            // update the parent/child list for parent/child node
            parent.childList.add(child);
            child.parentList.add(parent);
        }
    }

    // Scan the map to output
    public  void output(Map<Integer, Node> map) {
        List<Integer> zeroParent = new LinkedList<Integer>();
        List<Integer> oneParent = new LinkedList<Integer>();

        Set<Integer> keySet = map.keySet();
        for (Integer key : keySet) {
            Node node = map.get(key);

            if (node.parentList.size() == 0) {
                zeroParent.add(node.val);
            }

            if (node.parentList.size() == 1) {
                oneParent.add(node.val);
            }
        }

        System.out.print("zero parents: ");
        for (int i = 0; i < zeroParent.size(); i++) {
            if (i != zeroParent.size() - 1) {
                System.out.print(zeroParent.get(i) + ", ");
            } else {
                System.out.print(zeroParent.get(i));
            }
        }

        System.out.print("\none parents: ");
        for (int i = 0; i < oneParent.size(); i++) {
            if (i != oneParent.size() - 1) {
                System.out.print(oneParent.get(i) + ", ");
            } else {
                System.out.print(oneParent.get(i));
            }
        }
    }


    // call and print here
    public static void main(String[] args) {
        Map<Integer, Node> map = new HashMap<Integer, Node>();

        CountParents sol = new CountParents();

        sol.countParents(parentChildPairs, map);

        sol.output(map);
    }
}
