package View_Layer;

import java.awt.EventQueue;
import javax.swing.JFrame;
import Model_Layer.Daytrip;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class View {
	
	private Daytrip[] trips;
	
	private JFrame frame;
	private JTextField searchBoxTextField;
	
	public String searchString;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.menu);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchString = searchBoxTextField.getText();
				System.out.println(searchString);
			}
		});
		
		
		btnNewButton.setBounds(341, 11, 83, 23);
		frame.getContentPane().add(btnNewButton);
		
		searchBoxTextField = new JTextField();
		searchBoxTextField.setBounds(22, 12, 299, 20);
		frame.getContentPane().add(searchBoxTextField);
		searchBoxTextField.setColumns(10);
	}
}
