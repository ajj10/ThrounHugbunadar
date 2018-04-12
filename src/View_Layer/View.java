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
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeEvent;

public class View {
	
	private ArrayList<Daytrip> trips;
	private TripSearch tripsearch;
	private Basket basket;
	private Root root;
	
	private JFrame frame;
	private JTextField searchBoxTextField;
	private JTable tripDisplayList;
	private DefaultTableModel dm;
	private JComboBox comboBoxLocation;
	private JComboBox comboBoxActivity;
	private JSlider priceSlider;
	
	private View_Trip view_trip;
	private View_Basket view_basket;
	private View_Checkout view_checkout;
	
	public String searchString;
	public String Location = "Reykjavík";
	public String Activity = "";
	public int Price;
	public int index;
	
	private View view;
	
	
	
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
		basket = new Basket();
		root = new Root(basket, tripsearch);
		view = this;
		
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.menu);
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		tripDisplayList = new JTable(new DefaultTableModel(new Object[]{"Trip", "Price", "Duration", "Rating"},0){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
		
		tripDisplayList.getTableHeader().setReorderingAllowed(false);
		tripDisplayList.getColumnModel().getColumn(0).setPreferredWidth(100);
		tripDisplayList.getColumnModel().getColumn(1).setPreferredWidth(50);
		tripDisplayList.getColumnModel().getColumn(2).setPreferredWidth(50);
		tripDisplayList.getColumnModel().getColumn(3).setPreferredWidth(30);
		tripDisplayList.getTableHeader().setResizingAllowed(false);
		
		dm = (DefaultTableModel) tripDisplayList.getModel();
		
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchString = searchBoxTextField.getText();
				trips = root.search(searchString);
				addToList();
				System.out.println(searchString);
				searchBoxTextField.setText("");
				
			}
		});
		
		
		//Search Button
		//------------------------------------------------
		searchButton.setBounds(536, 66, 100, 33);
		frame.getContentPane().add(searchButton);
		
		searchBoxTextField = new JTextField();
		searchBoxTextField.setBounds(64, 65, 443, 33);
		frame.getContentPane().add(searchBoxTextField);
		searchBoxTextField.setColumns(10);
		
		
		//ComboboxLocation
		//---------------------------------------------------------
		comboBoxLocation = new JComboBox();
		comboBoxLocation.addItem("Location:");
		comboBoxLocation.addItem("Akureyri");
		comboBoxLocation.addItem("Heimaey");
		comboBoxLocation.addItem("Ísafjörður");
		comboBoxLocation.addItem("Keflavík");
		comboBoxLocation.addItem("London");
		comboBoxLocation.addItem("Paris");
		comboBoxLocation.addItem("Reykjavík");
		comboBoxLocation.addItem("Vatnajökull");
		comboBoxLocation.setSelectedIndex(7);
		

		comboBoxLocation.setToolTipText("");
		comboBoxLocation.setBounds(648, 109, 147, 22);
		frame.getContentPane().add(comboBoxLocation);
		
		comboBoxLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateTrips();
				searchBoxTextField.setText("");
			}

		});
		
		
		
		//Scrollpane
		//------------------------------------------------------
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 110, 572, 297);
		frame.getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(tripDisplayList);
		
		
		//comboBoxActivity
		//-------------------------------------------------------
		
		comboBoxActivity = new JComboBox();
		comboBoxActivity.addItem("Activity:");
		comboBoxActivity.addItem("Boat tours");
		comboBoxActivity.addItem("Games");
		comboBoxActivity.addItem("Museums");
		comboBoxActivity.addItem("Nature and parks");
		comboBoxActivity.addItem("Outdoor activities");
		comboBoxActivity.setSelectedIndex(0);
		
		comboBoxActivity.setBounds(648, 143, 147, 23);
		frame.getContentPane().add(comboBoxActivity);
		
		comboBoxActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTrips();
				searchBoxTextField.setText("");
			}
		});
		
		
		
		//Listener fyrir priceSlider
		//--------------------------------------------------
		priceSlider = new JSlider();
		priceSlider.setMinimum(0);
		priceSlider.setMaximum(50000);
		priceSlider.setValue(50000);
		updateTrips();
		
		priceSlider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				updateTrips();
			}
		});
		
		priceSlider.setBounds(648, 178, 147, 26);
		frame.getContentPane().add(priceSlider);
		
		JLabel lblKr = new JLabel("0 kr.");
		lblKr.setBounds(646, 216, 44, 14);
		frame.getContentPane().add(lblKr);
		
		JLabel lblKr_1 = new JLabel("50.000 kr.");
		lblKr_1.setBounds(759, 216, 63, 14);
		frame.getContentPane().add(lblKr_1);
		
		JButton btnBasket = new JButton("Basket");
		btnBasket.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				view_basket = new View_Basket(root.getBasket(), view);
				view_basket.setVisible(true);
			}
		});
		btnBasket.setBounds(661, 297, 117, 29);
		frame.getContentPane().add(btnBasket);
		
		
		
		
		
		//Listener fyrir töflu
		//----------------------------------------------------------------
		tripDisplayList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int rowIndex = tripDisplayList.getSelectedRow();
				System.out.println(trips.get(rowIndex).getName());
				view_trip = new View_Trip(view, trips.get(rowIndex));
				view_trip.setVisible(true);
			}

		});
		
		tripDisplayList.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = tripDisplayList.columnAtPoint(e.getPoint());
		        String name = tripDisplayList.getColumnName(col);
		        if(name.equals("Price")) trips = root.sortByPrice();
		        else if(name.equals("Rating")) trips = root.sortByRating();
		        else if(name.equals("Duration")) trips = root.sortByDuration();
		        addToList();
		        
			}

		});
		
		
		
	//update trips fall
	//------------------------------------------------------------
	}
	private void updateTrips() {
	
		Location = (String)comboBoxLocation.getSelectedItem();
		if(Location == "Location:") Location = null;
		
		Activity = (String)comboBoxActivity.getSelectedItem();
		if(Activity == "Activity:") Activity = null;
		
		int[] priceRange = new int[2];
		priceRange[0] = 0;
		priceRange[1] = priceSlider.getValue();
		
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
		dm.setNumRows(0);
		for(int i = 0; i<trips.size(); i++) {
			Daytrip trip = trips.get(i);
			
			if(trip.getDuration() == -1) {
				dm.addRow(new Object[]{trip.getName(), trip.getPrice() + " kr.",  "??? min", trip.getAverageRating()});
			}else {
				dm.addRow(new Object[]{trip.getName(), trip.getPrice() + " kr.", trip.getDuration() + " min", trip.getAverageRating()});
			}
		}	
	}
	
	
	
	//bætir við ferð í basket
	public void addToBasket(Daytrip trip, String day, int seats) {
		root.addBooking(trip, day, seats);
	}
	
	
	public void checkout() {
		view_checkout = new View_Checkout(root.getBasket(), this);
		view_checkout.setVisible(true);
	}

	public void bookBasket() {
		root.bookBasket();
		
	}
}
