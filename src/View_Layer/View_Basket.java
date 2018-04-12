package View_Layer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model_Layer.Basket;
import Model_Layer.Daytrip;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View_Basket extends JFrame {

	private JPanel contentPane;
	private Basket basket;
	private DefaultTableModel dm;
	private JTable table;
	private View parentView;


	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Basket frame = new View_Basket();
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
	public View_Basket(Basket myBasket, View view) {
		
		basket = myBasket;
		parentView = view;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 81, 468, 273);
		contentPane.add(scrollPane);
		
		table = new JTable(new DefaultTableModel(new Object[]{"Trip", "Day", "Persons", "Price (total)"},0){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parentView.checkout();
				dispose();
			}
		});
		btnCheckout.setBounds(477, 324, 117, 29);
		contentPane.add(btnCheckout);
		
		
		
		dm = (DefaultTableModel) table.getModel();
		
		
		
		
		addToList();
	}
	
	private void addToList() {
		dm.setNumRows(0);
		for(int i = 0; i < basket.getBookings().size(); i++) {
			String tripName = basket.getBookings().get(i).getTrip().getName();
			int numSeats = basket.getBookings().get(i).getSeats();
			int totalTrip = numSeats * basket.getBookings().get(i).getTrip().getPrice();
			String day = basket.getBookings().get(i).getDay();
			dm.addRow(new Object[]{tripName, day, numSeats, totalTrip});
		}	
	}
}
