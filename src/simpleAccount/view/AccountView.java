package simpleAccount.view;
import javax.swing.*;

import simpleAccount.controller.AccountController;
import simpleAccount.model.AccountModel;
import simpleAccount.model.ModelEvent;

import java.awt.*; 
import java.awt.event.*;
import java.io.FileNotFoundException;

@SuppressWarnings("serial")
public class AccountView extends JFrameView {
	public static final String Save		= "Save";
	public static final String Exit 	= "Exit";
	public static final String USD		= "Edit in USD ($)";
	public static final String Euros	= "Edit in Euros (€)";
	public static final String Yuan		= "Edit in Yuan (¥)";
	private JComboBox<String> accounts;

	public AccountView(AccountModel model, AccountController controller) {
		super(model, controller);
		this.setTitle("Simple Account Manager");

		//-- Panel
		JPanel panel = new JPanel();
		Handler handler = new Handler();

		//-- Constraints
		GridBagConstraints cEditDollar = new GridBagConstraints();
		GridBagConstraints cEditEuro = new GridBagConstraints();
		GridBagConstraints cEditYuan = new GridBagConstraints();
		GridBagConstraints cDrop = new GridBagConstraints();
		GridBagConstraints cSave = new GridBagConstraints();
		GridBagConstraints cExit = new GridBagConstraints();
		GridBagConstraints cSpace1 = new GridBagConstraints();
		GridBagConstraints cSpace2 = new GridBagConstraints();


		//-- Create drop down list and buttons
		String[] concatList = ((AccountController)getController()).createConcatListA();
		accounts = new JComboBox<String>(concatList);
		accounts.addActionListener(handler);
		cDrop.gridwidth = 4;
		cDrop.gridx = 2;
		cDrop.gridy = 0;

		JButton saveButton = new JButton(Save); 
		saveButton.addActionListener(handler);
		cSave.gridwidth = 1;
		cSave.gridx = 4;
		cSave.gridy = 2;

		JButton exitButton = new JButton(Exit);
		exitButton.addActionListener(handler);
		cExit.gridwidth = 1;
		cExit.gridx = 5;
		cExit.gridy = 2;

		JButton usdButton = new JButton(USD);
		usdButton.addActionListener(handler);
		cEditDollar.gridwidth = 2;
		cEditDollar.gridx = 0;
		cEditDollar.gridy = 0;

		JButton eurosButton = new JButton(Euros);
		eurosButton.addActionListener(handler);
		cEditEuro.gridwidth = 2;
		cEditEuro.gridx = 0;
		cEditEuro.gridy = 1;

		JButton yuanButton = new JButton(Yuan);
		yuanButton.addActionListener(handler);
		cEditYuan.gridwidth = 2;
		cEditYuan.gridx = 0;
		cEditYuan.gridy = 2;
		
		//-- cSpace1 and cSpace2
		cSpace1.gridwidth = 4;
		cSpace1.gridx = 2;
		cSpace1.gridy = 1;
		cSpace2.gridwidth = 2;
		cSpace2.gridx = 2;
		cSpace2.gridy = 2;

		//-- Panel Formatting
		//-- Grid is 3 rows by 6 columns
		panel.setLayout(new GridBagLayout());
//		buttonPanel.setLayout(new GridLayout(3, 6, 5, 5));
		this.getContentPane().add(panel, BorderLayout.CENTER);
		
		//-- Adding drop down list and buttons to panel
		panel.add(accounts, cDrop);

		panel.add(usdButton, cEditDollar);
		panel.add(eurosButton, cEditEuro);
		panel.add(yuanButton, cEditYuan);

		panel.add(Box.createHorizontalBox(), cSpace1);
		panel.add(Box.createHorizontalBox(), cSpace2);
		panel.add(saveButton, cSave);
		panel.add(exitButton, cExit);
		pack();
	}
	
	// Now implement the necessary event handling code 
	public void modelChanged(ModelEvent event) {
//		String msg = event.getAmount() + "";
//		textField.setText(msg);

	}
	// Inner classes for Event Handling 
	class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == accounts) {
				((AccountController)getController()).updateSelect(accounts.getSelectedIndex());
			}
			try {
				((AccountController)getController()).operation(e.getActionCommand());
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		try {
			new AccountController(args[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			String[] dismiss = {"Dismiss"};
			JOptionPane.showOptionDialog(new JDialog(), "Error 404: File not found!", 
					"File not found", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
					null, dismiss, dismiss);
			System.exit(0);
		} 
	}
}
