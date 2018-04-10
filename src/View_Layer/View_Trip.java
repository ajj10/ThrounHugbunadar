package View_Layer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller_Layer.TripSearch;
import Model_Layer.Daytrip;
import Model_Layer.Review;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Label;

public class View_Trip extends JFrame {

	// breyting
	private TripSearch tripsearch;
	
	private JPanel contentPane;
	private JTextField Activity_Text;
	private JTextField Location_Text;
	private JTextField Price_Text;
	private JTextField Rating_Text;
	private JTextField Duration_Text;
	private Daytrip trip;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Trip frame = new View_Trip();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public View_Trip(Daytrip myTrip) {
		trip = myTrip;
		
		setTitle(trip.getName());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(148, 11, 262, 92);
		contentPane.add(scrollPane);
		
		JTextArea descriptionText = new JTextArea();
		scrollPane.setViewportView(descriptionText);
		descriptionText.setWrapStyleWord(true);
		descriptionText.setLineWrap(true);
		descriptionText.setEditable(false);
		descriptionText.setText(trip.getDescription());
		
		JLabel lblNewLabel = new JLabel("Activity:");
		lblNewLabel.setBounds(10, 131, 62, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Location:");
		lblNewLabel_1.setBounds(10, 156, 62, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price:");
		lblNewLabel_2.setBounds(10, 181, 54, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Rating:");
		lblNewLabel_3.setBounds(10, 206, 54, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Duration:");
		lblNewLabel_4.setBounds(10, 231, 62, 14);
		contentPane.add(lblNewLabel_4);
		
		Activity_Text = new JTextField();
		Activity_Text.setEditable(false);
		Activity_Text.setBounds(84, 125, 130, 26);
		contentPane.add(Activity_Text);
		Activity_Text.setColumns(10);
		Activity_Text.setText(trip.getActivity());
		
		Location_Text = new JTextField();
		Location_Text.setEditable(false);
		Location_Text.setColumns(10);
		Location_Text.setBounds(84, 150, 130, 26);
		contentPane.add(Location_Text);
		Location_Text.setText(trip.getLocation());
		
		Price_Text = new JTextField();
		Price_Text.setEditable(false);
		Price_Text.setColumns(10);
		Price_Text.setBounds(84, 175, 130, 26);
		contentPane.add(Price_Text);
		Price_Text.setText(trip.getPrice() + " kr.");
		
		Rating_Text = new JTextField();
		Rating_Text.setEditable(false);
		Rating_Text.setColumns(10);
		Rating_Text.setBounds(84, 200, 130, 26);
		contentPane.add(Rating_Text);
		Rating_Text.setText(trip.getAverageRating() + "");
		
		Duration_Text = new JTextField();
		Duration_Text.setEditable(false);
		Duration_Text.setColumns(10);
		Duration_Text.setBounds(84, 225, 130, 26);
		contentPane.add(Duration_Text);
		Duration_Text.setText(trip.getDuration() + " m�n");
		
		JLabel PhotoLabel = new JLabel("");
		PhotoLabel.setBounds(10, 11, 130, 92);
		contentPane.add(PhotoLabel);
	
		String path = "Images/" + trip.getName() + ".PNG";
		ImageIcon myImage = new ImageIcon(path);
		Image img = myImage.getImage();
		Image newImg = img.getScaledInstance(PhotoLabel.getWidth(), PhotoLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		PhotoLabel.setIcon(image);
	}
}