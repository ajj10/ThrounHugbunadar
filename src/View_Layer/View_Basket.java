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

public class View_Basket extends JFrame {

	private JPanel contentPane;
	private Basket basket;
	private DefaultTableModel dm;
	private JTable table;


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
	public View_Basket(Basket myBasket) {
		
		basket = myBasket;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JTable(new DefaultTableModel(new Object[]{"Trip", "Price", "Duration", "Rating"},0){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
		contentPane.add(table, BorderLayout.CENTER);
		
		
		
		dm = (DefaultTableModel) table.getModel();
		
		
		
		
		addToList();
	}
	
	private void addToList() {
		dm.setNumRows(0);
		for(int i = 0; i<basket.getTrips().size(); i++) {
			Daytrip trip = basket.getTrips().get(i);
			
			if(trip.getDuration() == -1) {
				dm.addRow(new Object[]{trip.getName(), trip.getPrice() + " kr.",  "??? min", trip.getAverageRating()});
			}else {
				dm.addRow(new Object[]{trip.getName(), trip.getPrice() + " kr.", trip.getDuration() + " min", trip.getAverageRating()});
			}
		}	
	}

}
