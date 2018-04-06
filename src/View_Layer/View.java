package View_Layer;

import java.awt.EventQueue;
import javax.swing.JFrame;

import Model_Layer.Basket;
import Model_Layer.Daytrip;
import javax.swing.JTextField;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;

import Controller_Layer.Root;
import Controller_Layer.TripSearch;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.ScrollPane;
import java.awt.Panel;
import java.awt.List;
import java.awt.Scrollbar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class View {
	
	private ArrayList<Daytrip> trips;
	private TripSearch tripsearch;
	private Basket basket;
	private Root root;
	
	private JFrame frame;
	private JTextField searchBoxTextField;
	private JList tripDisplayList;
	private DefaultListModel dm;
	private JComboBox comboBoxLocation;
	private JComboBox comboBoxActivity;
	
	public String searchString;
	public String Location = "Reykjavík";
	public String Activity = "";
	public int index;
	
	
	
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
		
		trips = new ArrayList<Daytrip>();
		tripsearch = new TripSearch(trips, "", "", 0, 10000000, -1, -1);
		root = new Root(basket, tripsearch);
		
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.menu);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tripDisplayList = new JList();
		dm = new DefaultListModel();
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchString = searchBoxTextField.getText();
				trips = root.search(searchString);
				addToList();
				System.out.println(searchString);
				
			}
		});
		
		
		//Search Button
		//------------------------------------------------
		searchButton.setBounds(341, 11, 83, 23);
		frame.getContentPane().add(searchButton);
		
		searchBoxTextField = new JTextField();
		searchBoxTextField.setBounds(22, 12, 299, 20);
		frame.getContentPane().add(searchBoxTextField);
		searchBoxTextField.setColumns(10);
		
		
		//ComboboxLocation
		//---------------------------------------------------------
		comboBoxLocation = new JComboBox();
		comboBoxLocation.addItem(null);
		comboBoxLocation.addItem("Akureyri");
		comboBoxLocation.addItem("Heimaey");
		comboBoxLocation.addItem("Ísafjörður");
		comboBoxLocation.addItem("Reykjavík");
		comboBoxLocation.addItem("Vatnajökull");

		comboBoxLocation.setToolTipText("");
		comboBoxLocation.setBounds(341, 58, 83, 22);
		frame.getContentPane().add(comboBoxLocation);
		
		comboBoxLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateTrips();
			}

		});
		
		
		
		//Scrollpane
		//------------------------------------------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 42, 299, 167);
		frame.getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(tripDisplayList);
		
		
		//comboBoxActivity
		//-------------------------------------------------------
		comboBoxActivity = new JComboBox();
		comboBoxActivity.addItem(null);
		comboBoxActivity.addItem("Boat tours");
		comboBoxActivity.addItem("Museums");
		comboBoxActivity.addItem("Nature and parks");
		comboBoxActivity.addItem("Outdoor activities");
		comboBoxActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTrips();
			}
		});
		comboBoxActivity.setBounds(341, 90, 83, 23);
		frame.getContentPane().add(comboBoxActivity);
		
		
		
		//Listener fyrir search takka
		//----------------------------------------------------------------
		tripDisplayList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				index = tripDisplayList.getSelectedIndex();
				String nafn = (String)tripDisplayList.getSelectedValue();
				System.out.println(nafn);
			}

		});
		
		
		
	//update trips fall
	//------------------------------------------------------------
	}
	private void updateTrips() {
		Location = (String)comboBoxLocation.getSelectedItem();
		Activity = (String)comboBoxActivity.getSelectedItem();
		int[] priceRange = new int[2];
		priceRange[0] = 0;
		priceRange[1] = 10000000;
		
			try {
				trips = root.findTrips(Location, Activity, priceRange, -1, -1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		addToList();
	}
	
	
	//addtolist fall 
	//------------------------------------------------
	private void addToList() {
		dm.removeAllElements();
		for(int i = 0; i<trips.size(); i++) {
			dm.addElement(trips.get(i).getName());
			tripDisplayList.setModel(dm);
		}	
	}
}
