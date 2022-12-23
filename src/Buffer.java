import java.util.LinkedList;
import java.util.Queue;
public class Buffer {
    private int bufferSize;
    Queue<Integer> q1 = new LinkedList<Integer>();
    Boolean process = false;
    int counter = 0;

    public Buffer(int size) {
        bufferSize = size;
    }

    public synchronized void produce(int num) {
        while (q1.size() == bufferSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        q1.add(num);
        counter++;
        notify();
    }

    public synchronized int consume() {
        while (q1.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int number = q1.remove();
        counter--;
        notify();
        return number;
    }
}
