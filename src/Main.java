
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame form1 = new JFrame("Prime numbers Calculator:)");
        JLabel l1, l2, l3;
        l1 = new JLabel("Enter the N number: ");
        l2 = new JLabel("Enter the Buffer Size: ");
        l3 = new JLabel("Enter the File Name: ");
        l1.setBounds(20, 20, 150, 30);
        l2.setBounds(20, 60, 150, 30);
        l3.setBounds(20, 100, 150, 30);
        final JTextField t1 = new JTextField();
        final JTextField t2 = new JTextField();
        final JTextField t3 = new JTextField();
        t1.setBounds(200, 20, 150, 20);
        t2.setBounds(200, 60, 150, 20);
        t3.setBounds(200, 100, 150, 20);
        JButton produce = new JButton("Produce");
        produce.setBounds(200, 140, 150, 20);
        produce.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                int bufferSize = Integer.valueOf(t2.getText());
                int n = Integer.valueOf(t1.getText());
                String fileName = String.valueOf(t3.getText());


                Buffer buf = new Buffer(bufferSize);
                long startTime = System.currentTimeMillis();
                producer P = new producer(buf, n, startTime);
                consumer C = new consumer(buf, fileName);
                P.start();
                C.start();
            }
        });
        form1.add(t1);
        form1.add(t2);
        form1.add(t3);
        form1.add(produce);
        form1.add(l1);
        form1.add(l2);
        form1.add(l3);
        form1.setSize(400, 240);
        form1.setLayout(null);
        form1.setVisible(true);
    }
}