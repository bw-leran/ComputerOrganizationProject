import java.util.*;
import java.io.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.nio.BufferOverflowException;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Micro1Viewer {

    /**
	  Main memory of the simulated computer
	*/
	private static Memory memory;
	/**
	  Processor of the simulated computer
	*/
	private static Processor cpu;

    /**
       Constructs a memory with specified number of cells,
       and constructs an associated processor.

       @param cap  the sepcified amount of memory
    */
	public Micro1Viewer(int cap) {
		memory = new Memory(cap);
		cpu = new Processor();
		cpu.setMemory(memory);
	}

    /**
      Constructs a processor and a memory with 256 cells
    */
	public Micro1Viewer() {
		this(256);
	}

    
    /**
      Loads hexadecimal numbers stored in fName into
      memory starting at address 0. Resets PC to 0.

      @param fName the name of a file containing hex numbers
    */
	public static void load(String fName) {
		try {
			File f = new File(fName);
			try (Scanner scan = new Scanner(f)) {
				int address = 0;
				while(scan.hasNext()) {
					memory.write(address++, scan.nextInt(16));
				}
			}
			cpu.setPC(0);
		} catch(Exception e) {
			System.out.println(e);
		}
    }
    
    public static void main(String[] args) {

        Micro1Viewer micro1Viewer = new Micro1Viewer();

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(null);
        panel.setBackground(Color.blue);

        JLabel title = new JLabel("Micro-1 Viewer (Anastasia, Brandon, and Declan)");
        title.setBounds(100, 0, 1000, 100);
        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        panel.add(title);

                
        JTextArea registers_textfield = new JTextArea();
        registers_textfield.setBounds(100, 500, 700, 400);
        Border border = BorderFactory.createLineBorder(null);
        registers_textfield.setBorder(BorderFactory.createCompoundBorder(border,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        panel.add(registers_textfield);

        JTextField load_textfield = new JTextField();
        load_textfield.setBounds(300, 150, 500, 50);
        panel.add(load_textfield);


        JButton load_button = new JButton("Load");
        load_button.setBounds(100, 150, 100, 40);
        load_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load(load_textfield.getText());
                System.out.println("done");
            }
        });
        panel.add(load_button);

        // this one should dump to command console
        JButton memory_button = new JButton("Memory");
        memory_button.setBounds(100, 300, 100, 40);
        memory_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memory.dump();
            }
        });
        panel.add(memory_button);

        // this one should dump to jtextfield
        JButton registers_button = new JButton("Registers");
        registers_button.setBounds(300, 300, 100, 40);
        registers_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cpu.dumpToJField(registers_textfield);
            }
        });
        panel.add(registers_button);

        JButton step_button = new JButton("Step");
        step_button.setBounds(500, 300, 100, 40);
        step_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = 1;
				boolean halt = false;
				for(int i = 0; i < num && !halt; i++) {
					if (!halt) halt = cpu.step();
					if (halt) {
						System.out.println("program terminated");
						break;
                    }
                }
            }
        });
        panel.add(step_button);
    }
}
