package simpleAccount.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import simpleAccount.controller.AccountEditController;
import simpleAccount.model.AccountEditModel;
import simpleAccount.model.ModelEvent;

/**
 * 
 * @author markmagahis
 *
 */
@SuppressWarnings("serial")
public class AccountEditView extends JFrameView {
	public static final String Deposit = "Deposit";
	public static final String Withdraw = "Withdraw";
	public static final String Dismiss = "Dismiss";
	public static final String Available = "Available funds: ";
	public static final String Operations = "Operations in ";
	public static final String Edit = "Enter amount in ";
	private JTextField amountField;
	private JTextField availableField;

	/**
	 * Constructor
	 * <p>
	 * Used to create a sub-window for after clicking "Edit in _________"
	 * @param model
	 * @param controller
	 * @param exchange
	 * @param index
	 */
	public AccountEditView(AccountEditModel model, AccountEditController controller, String exchange, int index) {
		// TODO Auto-generated constructor stub
		super(model, controller);
		
		this.setTitle(((AccountEditController)getController()).getTitleA() + " - " + Operations + exchange);
		
		JPanel buttonPanel = new JPanel();
		JPanel availPanel = new JPanel();
		JPanel amountPanel = new JPanel();
		
		Handler handler = new Handler();
		
		JLabel availLabel = new JLabel(Available);
		JLabel amtLabel = new JLabel(Edit + exchange + ": ");
		
		availableField = new JTextField(20);
		availableField.setEnabled(false);
		availableField.setText(((AccountEditController)getController()).getAmountA());
		
		amountField = new JTextField(20);
		amountField.setEnabled(true);
		amountField.addActionListener(handler);
		amountField.setText("0.0");
		
		JButton depositButton = new JButton(Deposit);
		depositButton.addActionListener(handler);
		
		JButton withdrawButton = new JButton(Withdraw);
		withdrawButton.addActionListener(handler);
		
		JButton dismissButton = new JButton(Dismiss);
		dismissButton.addActionListener(handler);
		
		buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
		this.getContentPane().add(availPanel, BorderLayout.NORTH);
		this.getContentPane().add(amountPanel, BorderLayout.CENTER);
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		availPanel.add(availLabel, 0);
		availPanel.add(availableField, 1);
		
		amountPanel.add(amtLabel, 0);
		amountPanel.add(amountField, 1);
		
		buttonPanel.add(depositButton, null);
		buttonPanel.add(withdrawButton, null);
		buttonPanel.add(dismissButton, null);
		
		pack();
	}

	@Override
	public void modelChanged(ModelEvent event) {
		// TODO Auto-generated method stub
		String msg = event.getAmount() + "";
		availableField.setText(msg);
		amountField.setText("0.00");
	}
	
	// Inner classes for Event Handling 
	class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			try {
				((AccountEditController)getController()).operation(e.getActionCommand(), amountField.getText());
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

}
