/**
 * Created by yizhiw on 4/30/2017.
 */
// Java program to implement solution of producer
// consumer problem.
import java.util.*;

public class ConsumerProducer {
    static class NumberQueue {
        final int CAPACITY = 10;
        int counter;
        Queue<Integer> queue;
        boolean workDone;

        NumberQueue() {
            counter = 0;
            queue = new LinkedList<Integer>();
            workDone = false;
        }

        synchronized void pushItem(Integer num) {
            // if queue is full
            while (counter == CAPACITY) {
                try {
                    wait();
                } catch (InterruptedException e) {

                }
            }

            queue.offer(num);
            counter++;
            notifyAll();
        }

        synchronized Integer pullItem() {
            // if queue is empty
            while (counter == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {

                }
            }

            Integer ret = queue.poll();
            counter--;
            notifyAll();
            return ret;
        }

        synchronized Integer peekItem() {

            return queue.peek();
        }
    }

    static class Consumer extends Thread {
        NumberQueue itemQueue;
        int id;

        Consumer(NumberQueue queue, int id) {
            this.itemQueue = queue;
            this.id = id;
        }

        public void run() {
            // to exit when all numbers have been printed out and nothing left in the queue
            while ((itemQueue.workDone == false) || (itemQueue.counter != 0)) {
                Integer num = itemQueue.peekItem();

                // if it's odd consumer
                if ((num != null) && (num%2 != 0) && (id == 1)) {
                    System.out.println("odd consumer: " + itemQueue.pullItem());
                } else if ((num != null) && (num%2 == 0) && (id == 2)) {
                    System.out.println("even consumer: " + itemQueue.pullItem());
                }
            }
        }
    }

    static class Producer extends Thread {
        NumberQueue itemQueue;

        Producer(NumberQueue queue) {
            this.itemQueue = queue;
        }

        public void run() {
            for (int i = 1; i <= 100; i++) {
                itemQueue.pushItem(i);
                System.out.println("producer: " + i);
            }

            itemQueue.workDone = true;
        }
    }

    public static void main(String[] args)
            throws InterruptedException
    {
        NumberQueue queue = new NumberQueue();

        Producer producer = new Producer(queue);
        Consumer oddConsumer = new Consumer(queue,1);
        Consumer evenConsumer = new Consumer(queue, 2);

        producer.start();
        oddConsumer.start();
        evenConsumer.start();
    }
}
