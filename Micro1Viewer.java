import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.nio.BufferOverflowException;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Micro1Viewer {
    
    public static void main(String[] args) {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(null);

        JLabel title = new JLabel("Micro-1 Viewer (Anastasia, Brandon, and Declan)");
        title.setBounds(350, 0, 1000, 100);
        panel.add(title);

                
        JTextField registers_textfield = new JTextField("registers go here!");
        registers_textfield.setBounds(100, 500, 700, 400);
        panel.add(registers_textfield);
        // currently cant do multiple lines

        JTextField load_textfield = new JTextField();
        load_textfield.setBounds(300, 150, 500, 50);
        panel.add(load_textfield);


        JButton load_button = new JButton("Load");
        load_button.setBounds(100, 150, 100, 40);
        load_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print(load_textfield.getText());
            }
        });
        panel.add(load_button);

        // this one should dump to command console
        JButton memory_button = new JButton("Memory");
        memory_button.setBounds(100, 300, 100, 40);
        memory_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("pressed");
            }
        });
        panel.add(memory_button);

        // this one should dump to jtextfield
        JButton registers_button = new JButton("Registers");
        registers_button.setBounds(300, 300, 100, 40);
        registers_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("pressed");
            }
        });
        panel.add(registers_button);

        // produce success or error message to jtextfield
        JButton step_button = new JButton("Step");
        step_button.setBounds(500, 300, 100, 40);
        step_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("pressed");
            }
        });
        panel.add(step_button);

    }

}
