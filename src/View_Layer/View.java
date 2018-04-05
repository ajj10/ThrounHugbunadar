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
	private TripSearch tripsearch = new TripSearch(trips, "", "", 0, 10000000, -1, -1);
	private Basket basket;
	private Root root = new Root(basket, tripsearch);
	
	private JFrame frame;
	private JTextField searchBoxTextField;
	
	public String searchString;
	public String Location = "Reykjavík";
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
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.menu);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchString = searchBoxTextField.getText();
				System.out.println(searchString);
				
			}
		});
		
		
		searchButton.setBounds(341, 11, 83, 23);
		frame.getContentPane().add(searchButton);
		
		searchBoxTextField = new JTextField();
		searchBoxTextField.setBounds(22, 12, 299, 20);
		frame.getContentPane().add(searchBoxTextField);
		searchBoxTextField.setColumns(10);
		
		JComboBox comboBoxLocation = new JComboBox();
		comboBoxLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Location = (String)comboBoxLocation.getSelectedItem();
				System.out.println(Location);
				int[] priceRange = new int[2];
				priceRange[0] = 0;
				priceRange[1] = 10000000;
				
					try {
						trips = root.findTrips(Location, null, priceRange, -1, -1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				System.out.println(trips.get(0).getName());
				//tripDisplayList.setModel(dm);
			}
		});
		comboBoxLocation.addItem("Reykjavík");
		comboBoxLocation.addItem("Akureyri");
		comboBoxLocation.addItem("Selfoss");
		comboBoxLocation.addItem("Ísafjörður");
		comboBoxLocation.addItem("Egilsstaðir");
		//comboBoxLocation.setSelectedItem(null);
		comboBoxLocation.setToolTipText("");
		comboBoxLocation.setBounds(341, 58, 83, 22);
		frame.getContentPane().add(comboBoxLocation);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 42, 299, 167);
		frame.getContentPane().add(scrollPane);
		
		JList tripDisplayList = new JList();
		tripDisplayList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				index = tripDisplayList.getSelectedIndex();
				String nafn = (String)tripDisplayList.getSelectedValue();
				System.out.println(nafn);
			}
		});
		scrollPane.setViewportView(tripDisplayList);
		DefaultListModel dm = new DefaultListModel();
		
		
		for(int i = 0; i<trips.size(); i++) {
			dm.addElement(trips.get(i).getName());
		}
	
		dm.addElement("palli");
		dm.addElement("halli");
		dm.addElement("salli");
		dm.addElement("dalli");
		dm.addElement("ralli");
		dm.addElement("talli");
		dm.addElement("lalli");
		dm.addElement("jalli");
		dm.addElement("galli");
		dm.addElement("falli");
		dm.addElement("salli");
		dm.addElement("valli");
		dm.addElement("balli");
		dm.addElement("nalli");
		dm.addElement("malli");
		dm.addElement("zalli");
		dm.addElement("walli");
		
		tripDisplayList.setModel(dm);
		
		
	}
}
