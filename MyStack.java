/**
 * Created by yizhiw on 8/3/2017.
 */
/**
 * This implements basic stack operations using array
 */

public class MyStack<T> {
    T[] array;
    int size;
    final int DEFAULT_SIZE = 5;

    // constructor is not generic
    public MyStack() {
        array = (T[])new Object[DEFAULT_SIZE];
        size = 0;
    }

    public void push(T t) {
        if (size >= DEFAULT_SIZE) {
            // exceed the default size, need to reallocate
            System.out.println("Reallocate memory...");
            T[] newArray = (T[])new Object[DEFAULT_SIZE * 2];
            int tSize = size;
            while (tSize >= 1) {
                newArray[tSize - 1] = array[tSize - 1];
                tSize--;
            }
            array = newArray;
        }

        array[size++] = t;
    }

    public T pop() {
        if (size >= 1) {
            T top = array[size - 1];
            size--;
            return top;
        } else {
            System.out.println("Stack is empty");
            return null;
        }
    }

    public T peek() {
        return array[size - 1];
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<Integer>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        System.out.println("peek(): " + stack.peek());
        System.out.println("pop(): " + stack.pop());
        System.out.println("pop(): " + stack.pop());
        System.out.println("pop(): " + stack.pop());
        System.out.println("pop(): " + stack.pop());
        System.out.println("pop(): " + stack.pop());
        System.out.println("pop(): " + stack.pop());
        System.out.println("pop(): " + stack.pop());
        System.out.println("pop(): " + stack.pop());
    }
}
