package View_Layer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model_Layer.Basket;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View_Checkout extends JFrame {

	private JPanel contentPane;
	private JTextField BookingtextField;
	private JTextField NametextField;
	private JTextField EmailtextField;
	private JTextField CredittextField;
	
	private View parentView;
	private Basket myBasket;
	private JLabel totalLabel;
	private JTextField totaltextField;
	private JButton btnBook;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Checkout frame = new View_Checkout();
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
	public View_Checkout(Basket basket, View view) {
		
		parentView = view;
		myBasket = basket;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel BookingLabel = new JLabel("Booking");
		BookingLabel.setBounds(78, 81, 61, 16);
		contentPane.add(BookingLabel);
		
		JLabel NameLabel = new JLabel("Name");
		NameLabel.setBounds(78, 109, 61, 16);
		contentPane.add(NameLabel);
		
		JLabel EmailLabel = new JLabel("Email");
		EmailLabel.setBounds(78, 137, 61, 16);
		contentPane.add(EmailLabel);
		
		JLabel CreditLabel = new JLabel("Credit Card");
		CreditLabel.setBounds(78, 165, 89, 16);
		contentPane.add(CreditLabel);
		
		BookingtextField = new JTextField();
		BookingtextField.setEditable(false);
		BookingtextField.setBounds(173, 76, 147, 26);
		contentPane.add(BookingtextField);
		BookingtextField.setColumns(10);
		BookingtextField.setText(myBasket.getCustomerInfo().getBookingID() + "");
		
		NametextField = new JTextField();
		NametextField.setBounds(173, 104, 147, 26);
		contentPane.add(NametextField);
		NametextField.setColumns(10);
		
		EmailtextField = new JTextField();
		EmailtextField.setBounds(173, 132, 147, 26);
		contentPane.add(EmailtextField);
		EmailtextField.setColumns(10);
		
		CredittextField = new JTextField();
		CredittextField.setBounds(173, 160, 147, 26);
		contentPane.add(CredittextField);
		CredittextField.setColumns(10);
		
		totalLabel = new JLabel("Total");
		totalLabel.setBounds(78, 245, 61, 16);
		contentPane.add(totalLabel);
		
		totaltextField = new JTextField();
		totaltextField.setEditable(false);
		totaltextField.setBounds(173, 240, 147, 26);
		contentPane.add(totaltextField);
		totaltextField.setColumns(10);
		totaltextField.setText(myBasket.getTotal() + " kr.");
		
		btnBook = new JButton("Book");
		btnBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				myBasket.getCustomerInfo().setFullName(NametextField.getText());
				myBasket.getCustomerInfo().setEmail(EmailtextField.getText());
				myBasket.getCustomerInfo().setCreditCard(CredittextField.getText());
				if(myBasket.getCustomerInfo().isValid()) {
					parentView.bookBasket();
					dispose();
				}else {
					JOptionPane.showMessageDialog(contentPane,
				            "Some or all information is incorrect",
				             "Error",
				            JOptionPane.INFORMATION_MESSAGE,
				            null);
				}
				
			}
		});
		btnBook.setBounds(426, 304, 117, 29);
		contentPane.add(btnBook);
	}
}
