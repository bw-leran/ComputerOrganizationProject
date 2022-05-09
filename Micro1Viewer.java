import java.awt.FlowLayout;
import java.nio.BufferOverflowException;

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

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JLabel title = new JLabel("Micro-1 Viewer (Anastasia, Brandon, and Declan)");
        panel.add(title);

        JButton load_button = new JButton("Load");
        panel.add(load_button);

        // this one should dump to command console
        JButton memory_button = new JButton("Memory");
        panel.add(memory_button);

        // this one should dump to jtextfield
        JButton registers_button = new JButton("Registers");
        panel.add(registers_button);

        // produce success or error message to jtextfield
        JButton step_button = new JButton("Step");
        panel.add(step_button);

        JTextField registers_textfield = new JTextField("registers go here!");
        panel.add(registers_textfield);

        JTextField step_textfield = new JTextField("step success or fail message go here!");
        panel.add(step_textfield);

    }

}
