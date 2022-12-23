import java.io.FileWriter;
import java.io.IOException;

class consumer extends Thread {
    Buffer buffer;
    int item = 0;
    String fileName;

    public consumer(Buffer buffer, String fileName) {
        this.buffer = buffer;
        this.fileName = fileName;
    }

    public void run() {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            while (true) {
                if (buffer.counter == 0 && buffer.process == true) {
                    myWriter.close();
                    System.out.println("closed");
                    break;
                } else {
                    item = buffer.consume();
                    myWriter.write("'" + item + "'");
                    System.out.println(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

