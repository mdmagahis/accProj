package simpleAccount.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import simpleAccount.controller.AccountEditController;

public class AccountModel extends AbstractModel {
	private int index = 0;
	UserList list = new UserList();
	ArrayList<Integer> accounts = new ArrayList<Integer>(5);
	String[] dismiss = {"Dismiss"};
	
	public void readInput(String file) throws FileNotFoundException {
		String status = list.readInput(file);
		if (status.equals("Success!")) {
			//-- sort the list
			list.sort();
		}
		else {
			//-- "Error 404: File not found!"
			JOptionPane.showOptionDialog(new JDialog(), "Error 404: File not found!", 
					"File Not Found", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
					null, null, dismiss);
		}
	}
	
	/**
	 * Method to create contents for Drop Down list
	 * @return concatList
	 */
	public String[] createConcatListB() {
		// TODO Auto-generated method stub
		String[] concatList = new String[list.getSize()];
		
		for (int i = 0; i < list.getSize(); i++) {
			concatList[i] = list.getListItemA(i);
		}
		return concatList;
	}

	public void edit(String exchange){
		new AccountEditController(exchange, index, list);
	}
	
	public void save() throws FileNotFoundException {
		list.save();
	}
	
	public void updateSelect(int index) {
		this.index = index;
	}

}
