package View_Layer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller_Layer.Root;
import Controller_Layer.TripSearch;
import Model_Layer.Basket;
import Model_Layer.Daytrip;
import Model_Layer.Review;
import View_Layer.View;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Label;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;

import java.time.Year;
import java.time.MonthDay;

public class View_Trip extends JFrame {

	// breyting
	
	private JPanel contentPane;
	private JTextField Activity_Text;
	private JTextField Location_Text;
	private JTextField Price_Text;
	private JTextField Rating_Text;
	private JTextField Duration_Text;
	private Daytrip trip;
	private JTextField numberOfSeats;
	private JTextField tripDate;
	
	public Calendar cal = null;
	// private 
	
	public String day;
	public int seats;

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
	
	
	// function that takes in a string and returns true if it is numeric, returns false if not
	public static boolean isNumeric(String inputData) {
		  return inputData.matches("[-+]?\\d+(\\.\\d+)?");
		}
	
	
	/**
	 * Create the frame.
	 */
	public View_Trip(View parent, Daytrip myTrip) {
		trip = myTrip;
		
		setTitle(trip.getName());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//scrollpane fyrir t�flu og tafla fyrir description fer�ar
		//---------------------------------------------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(486, 33, 296, 226);
		contentPane.add(scrollPane);
		
		JTextArea descriptionText = new JTextArea();
		descriptionText.setLineWrap(true);
		scrollPane.setViewportView(descriptionText);
		descriptionText.setWrapStyleWord(true);
		descriptionText.setEditable(false);
		descriptionText.setText(trip.getDescription());
		descriptionText.setSelectionStart(0);
		descriptionText.setSelectionEnd(0);
		
		
		
		//labels
		//----------------------------------------------------
		JLabel lblNewLabel = new JLabel("Activity:");
		lblNewLabel.setBounds(42, 288, 62, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Location:");
		lblNewLabel_1.setBounds(42, 313, 62, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price:");
		lblNewLabel_2.setBounds(42, 338, 54, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Rating:");
		lblNewLabel_3.setBounds(42, 363, 54, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Duration:");
		lblNewLabel_4.setBounds(42, 388, 62, 14);
		contentPane.add(lblNewLabel_4);
		
		
		//n�r � activity fer�ar og setur � vi�eigandi textfield
		//-----------------------------------------------------
		Activity_Text = new JTextField();
		Activity_Text.setEditable(false);
		Activity_Text.setBounds(116, 282, 130, 26);
		contentPane.add(Activity_Text);
		Activity_Text.setColumns(10);
		Activity_Text.setText(trip.getActivity());
		
		//n�r � location fer�ar og setur � vi�eigandi textfield
		//-----------------------------------------------------
		Location_Text = new JTextField();
		Location_Text.setEditable(false);
		Location_Text.setColumns(10);
		Location_Text.setBounds(116, 307, 130, 26);
		contentPane.add(Location_Text);
		Location_Text.setText(trip.getLocation());
		
		
		//n�r � ver� fer�ar og setur � vi�eigandi textfield
		//-----------------------------------------------------
		Price_Text = new JTextField();
		Price_Text.setEditable(false);
		Price_Text.setColumns(10);
		Price_Text.setBounds(116, 332, 130, 26);
		contentPane.add(Price_Text);
		Price_Text.setText(trip.getPrice() + " kr.");
		
		
		//n�r � rating fer�ar og setur � vi�eigandi textfield
		//-----------------------------------------------------
		Rating_Text = new JTextField();
		Rating_Text.setEditable(false);
		Rating_Text.setColumns(10);
		Rating_Text.setBounds(116, 357, 130, 26);
		contentPane.add(Rating_Text);
		Rating_Text.setText(trip.getAverageRating() + "");
		
		
		//N�r � duration fer�ar og setur � vi�eigandi textfield
		//--------------------------------------------------------
		Duration_Text = new JTextField();
		Duration_Text.setEditable(false);
		Duration_Text.setColumns(10);
		Duration_Text.setBounds(116, 382, 130, 26);
		contentPane.add(Duration_Text);
		if(trip.getDuration() == -1) {
			Duration_Text.setText("margar min");
		}else {
			Duration_Text.setText(trip.getDuration() + " min");
		}
		

		//tekur myndir, gerir ��r a� r�ttri st�r� og setur sem icon fyrir label
		//----------------------------------------------------------
		JLabel PhotoLabel = new JLabel("");
		PhotoLabel.setBounds(22, 33, 435, 226);
		contentPane.add(PhotoLabel);
		String path = "Images/" + trip.getName() + ".jpeg";
		ImageIcon myImage = new ImageIcon(path);
		Image img = myImage.getImage();
		Image newImg = img.getScaledInstance(PhotoLabel.getWidth(), PhotoLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		PhotoLabel.setIcon(image);
		
		
		
		//label og textagluggi fyrir fj�lda s�ta panta�, teki� inn fr� vi�m�ti
		//----------------------------------------------------------------------
		JLabel lblNewLabel_5 = new JLabel("How many persons?");
		lblNewLabel_5.setBounds(516, 330, 158, 20);
		contentPane.add(lblNewLabel_5);
		numberOfSeats = new JTextField();
		numberOfSeats.setBounds(684, 330, 54, 20);
		contentPane.add(numberOfSeats);
		numberOfSeats.setColumns(10);
		numberOfSeats.setText("0");
		
		
		
		//Listener fyrir add to basket takkan
		//---------------------------------------------------------
		JButton btnNewButton = new JButton("Add to Basket");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				day = tripDate.getText();
				if(numberOfSeats.getText().matches("\\d+")) {
					seats = Integer.parseInt(numberOfSeats.getText());
				}else {
					seats = 0;
				}
				
				boolean boo = validSeatDay(seats, false);
				if(boo == true) {
					parent.addToBasket(trip, day, seats);
					dispose();
				}
		}});
		btnNewButton.setBounds(616, 354, 122, 23);
		contentPane.add(btnNewButton);
		
		
		
		//dags. label og gluggi sem tekur inn dags. fr� vi�m�ti
		//----------------------------------------------------------
		JLabel lblDateddmmyy = new JLabel("Date (DDMMYY)");
		lblDateddmmyy.setBounds(516, 283, 130, 14);
		contentPane.add(lblDateddmmyy);
		tripDate = new JTextField();
		tripDate.setBounds(652, 280, 86, 20);
		contentPane.add(tripDate);
		tripDate.setColumns(10);
		
		
		//Listener fyrir availability takkann
		//---------------------------------------------------------
		JButton btnNewButton_1 = new JButton("Check availability");
		btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				validDay();
			}});
		
		btnNewButton_1.setBounds(616, 304, 122, 23);
		contentPane.add(btnNewButton_1);
	}
	
	
	//Fall sem kannar hvort allt s� � lagi �egar �tt er � add to bakset takkann
	//og ef ekki skilar �t vi�eigandi skilabo�i
	//----------------------------------------------------------------------------------
	public boolean validSeatDay(int seats, boolean book) {
		day = tripDate.getText();
		
		// if that returns true if the day is numeric
		if (isNumeric(day) && day.length() == 6) {
			int yearBooking = Integer.parseInt(day.substring(4,6));
			int monthBooking = Integer.parseInt(day.substring(2,4));
			int dayBooking = Integer.parseInt(day.substring(0,2));
			
			String bla =  "" + Year.now().getValue();
			int yearNow = Integer.parseInt(bla.substring(2,4));
			
			int monthNow = MonthDay.now().getMonthValue();
			int dayNow = MonthDay.now().getDayOfMonth();
			
			// if else sayings to check if the date is up to date
			if ((yearBooking > yearNow)&&(monthBooking<=12)&&(dayBooking<=31)&&(seats > 0)&&(seats <= trip.getSeatsAvailable(day))) {
					return true;
			}else if ((yearBooking == yearNow)&&(monthBooking > monthNow)&&(monthBooking <= 12)&&(dayBooking<=31)&&(seats > 0)&&(seats <= trip.getSeatsAvailable(day))) {
					return true;
				}else if ((yearBooking == yearNow)&&(monthBooking == monthNow)&&(dayBooking > dayNow)&&(dayBooking <= 31 )&&(seats > 0)&&(seats <= trip.getSeatsAvailable(day))) {
						return true;
					}
				else { if(seats == 0) {
						JOptionPane.showMessageDialog(contentPane,
				            	"Please choose how many seats you would like",
				             	"Error",
				            	JOptionPane.INFORMATION_MESSAGE,
				            	null);
						return false;
				}else if(seats> trip.getSeatsAvailable(day)){
					JOptionPane.showMessageDialog(contentPane,
			            	"Not enough seats available on chosen day",
			             	"Error",
			            	JOptionPane.INFORMATION_MESSAGE,
			            	null);
					return false;
				}else {
					JOptionPane.showMessageDialog(contentPane,
			            	"This date is not up to date",
			             	"Error",
			            	JOptionPane.INFORMATION_MESSAGE,
			            	null);
					return false;
				}
				}
				}
			
			// if the date is not numeric
			else {
				JOptionPane.showMessageDialog(contentPane,
				"<html>This input is not on the correct format<br/> Please write a date with 6 numeric digits <br/><br/> For example: 010118",
				"Error",
				            	JOptionPane.INFORMATION_MESSAGE,
				            	null);
				return false;
			}
			
			
		}
	
	
	
	//Fall sem kannar hvort allt s� � lagi �egar �tt er � availability takkan
	//og ef ekki skilar �t vi�eigandi skilabo�i
	//------------------------------------------------------------------------
	public void validDay() {
		day = tripDate.getText();
		
		// if that returns true if the day is numeric
		if (isNumeric(day) && day.length() == 6) {
			int yearBooking = Integer.parseInt(day.substring(4,6));
			int monthBooking = Integer.parseInt(day.substring(2,4));
			int dayBooking = Integer.parseInt(day.substring(0,2));
			
			String bla =  "" + Year.now().getValue();
			int yearNow = Integer.parseInt(bla.substring(2,4));
			
			int monthNow = MonthDay.now().getMonthValue();
			int dayNow = MonthDay.now().getDayOfMonth();
			
			// if else sayings to check if the date is up to date
		if ((yearBooking > yearNow)&&(monthBooking<=12)&&(dayBooking<=31)) {
				seats = trip.getSeatsAvailable(day);
				JOptionPane.showMessageDialog(contentPane,
			            "Available seats for "
			             + day.substring(0, 2) + "/" + day.substring(2, 4) + "/20" + day.substring(4, 6)+ " are: " + seats,
			             "Seats",
			            JOptionPane.INFORMATION_MESSAGE,
			            null);
		}
			
		else {
			if ((yearBooking == yearNow)&&(monthBooking > monthNow)&&(monthBooking <= 12)&&(dayBooking<=31)) {
				seats = trip.getSeatsAvailable(day);
				JOptionPane.showMessageDialog(contentPane,
			            "Available seats for "
			             + day.substring(0, 2) + "/" + day.substring(2, 4) + "/20" + day.substring(4, 6) + " are: " + seats,
			             "Seats",
			            JOptionPane.INFORMATION_MESSAGE,
			            null);
			}
			else {
				if ((yearBooking == yearNow)&&(monthBooking == monthNow)&&(dayBooking > dayNow)&&(dayBooking <= 31 )) {
					seats = trip.getSeatsAvailable(day);
					JOptionPane.showMessageDialog(contentPane,
			            	"Available seats for "
			             	+ day.substring(0, 2) + "/" + day.substring(2, 4) + "/20" + day.substring(4, 6) + " are: " + seats,
			             	"Seats",
			            	JOptionPane.INFORMATION_MESSAGE,
			            	null);
				}
			else {
					JOptionPane.showMessageDialog(contentPane,
			            	"This date is not up to date",
			             	"Error",
			            	JOptionPane.INFORMATION_MESSAGE,
			            	null);
			}
			}
		}
		}

		// if the date is not numeric
		else {
			JOptionPane.showMessageDialog(contentPane,
			"<html>This input is not on the correct format<br/> Please write the input with 6 numeric digits <br/><br/> For example: 010118",
			"Error",
			            	JOptionPane.INFORMATION_MESSAGE,
			            	null);
		}
	}
}