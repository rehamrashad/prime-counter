import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class producer extends Thread {
    Buffer buffer;

    int number;
    long startTime;
    int item = 0, primeCounter = 0;


    public producer(Buffer buffer, int number, long startTime) {
        this.buffer = buffer;
        this.number = number;
        this.startTime = startTime;
    }

    public void run() {
        JLabel l1, l2, l3, l4, l5, l6;
        l4 = new JLabel();
        l5 = new JLabel();
        l6 = new JLabel();
        long time = 0;
        int maximumPrime = 0;

        if (primeCounter == 0) {
            JFrame form2 = new JFrame("The Answer:)");
            l1 = new JLabel("The Largest Prime:");
            l2 = new JLabel("# of elements (Prime numbers) generated: ");
            l3 = new JLabel("Time Elapsed since the start of processing: ");
            if (maximumPrime == 0) {
                l4 = new JLabel("Not Found!");
            } else l4 = new JLabel(Integer.toString(maximumPrime));
            l5 = new JLabel(Integer.toString(primeCounter));
            l6 = new JLabel(Long.toString(time) + "ms");
            l1.setBounds(20, 20, 250, 30);
            l2.setBounds(20, 60, 250, 30);
            l3.setBounds(20, 100, 250, 30);
            l4.setBounds(300, 25, 150, 20);
            l5.setBounds(300, 65, 150, 20);
            l6.setBounds(300, 105, 150, 20);
            JButton button2 = new JButton("Close!");
            button2.setBounds(200, 140, 150, 20);
            button2.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    buffer.process = true;
                    System.exit(0);
                }
            });
            form2.add(l1);
            form2.add(l2);
            form2.add(l3);
            form2.add(l4);
            form2.add(l5);
            form2.add(l6);
            form2.add(button2);
            form2.setSize(400, 240);
            form2.setLayout(null);
            form2.setVisible(true);

        }
        if (number == 0)
            buffer.process = true;
        else {
            for (int i = 1; i <= number; i++) {

                boolean flag = true;
                for (int j = 2; j <= Math.sqrt(i); j++) {
                    if (i != 1) {
                        if (i % j == 0) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {

                    long endTime = System.currentTimeMillis();
                    buffer.produce(i);
                    item = i;
                    primeCounter++;
                    time = endTime - startTime;
                    maximumPrime = item;
                    l4.setText(Integer.toString(maximumPrime));
                    l5.setText(Integer.toString(primeCounter));
                    l6.setText(Long.toString(time) + " ms");
                }
                if (i == number) {
                    buffer.process = true;
                }
            }
        }
    }
}
