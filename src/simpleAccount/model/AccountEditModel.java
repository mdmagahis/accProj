package simpleAccount.model;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class AccountEditModel extends AbstractModel{
	private String exchange;
	private int index;
	UserList list;
	
	private static final double euroRate = 0.94;
	private static final double yuanRate = 6.91;
	
	private String[] dismiss = {"Dismiss"};
	
	public AccountEditModel(String exchange, int index, UserList list) {
		// TODO Auto-generated constructor stub
		this.exchange = exchange;
		this.index = index;
		this.list = list;
	}
	
	public void deposit(String amountString) {
		Double amount;
		Double balance = list.getAmountFromIndex(index);
		
		try {
			amount = Double.parseDouble(amountString);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			JOptionPane.showOptionDialog(new JDialog(), "Invalid amount format!\n", 
					"Invalid amount", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, dismiss, dismiss);
			ModelEvent invalid = new ModelEvent(this, index, "", balance);
			notifyChanged(invalid);
			return;
		}
		
		if(amount < 1) {
			JOptionPane.showOptionDialog(new JDialog(), "Amount must exceed 1!\n", 
					"Invalid amount", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, dismiss, dismiss);
			ModelEvent invalid = new ModelEvent(this, index, "", balance);
			notifyChanged(invalid);
			return;
		}
		
		switch (this.exchange) {
		case "$":
			balance += amount;
			balance = (double)Math.round(balance * 100d) / 100d;
			list.updateIndexAmount(index, balance);
			ModelEvent usd = new ModelEvent(this, index, "", balance);
			notifyChanged(usd);
			break;
		case "€":
			amount = (amount/euroRate);
			balance += amount;
			balance = (double)Math.round(balance * 100d) / 100d;
			list.updateIndexAmount(index, balance);
			balance = (balance*euroRate);
			balance = (double)Math.round(balance * 100d) / 100d;
			ModelEvent euro = new ModelEvent(this, index, "", balance);
			notifyChanged(euro);
			break;
		case "¥":
			amount = (amount/yuanRate);
			balance += amount;
			balance = (double)Math.round(balance * 100d) / 100d;
			list.updateIndexAmount(index, balance);
			balance = (balance*yuanRate);
			balance = (double)Math.round(balance * 100d) / 100d;
			ModelEvent yuan = new ModelEvent(this, index, "", balance);
			notifyChanged(yuan);
			break;
		default:
			break;
		}
	}
	
	public void withdraw(String amountString) {
		Double amount;
		Double balance = list.getAmountFromIndex(index);
		
		try {
			amount = Double.parseDouble(amountString);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			JOptionPane.showOptionDialog(new JDialog(), "Invalid amount format!\n", 
					"Invalid amount", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, dismiss, dismiss);
			ModelEvent invalid = new ModelEvent(this, index, "", balance);
			notifyChanged(invalid);
			return;
		}
		
		if(amount < 1) {
			JOptionPane.showOptionDialog(new JDialog(), "Amount must exceed 1!\n", 
					"Invalid amount", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, dismiss, dismiss);
			ModelEvent invalid = new ModelEvent(this, index, "", balance);
			notifyChanged(invalid);
			return;
		}
		
		switch (this.exchange) {
		case "$":
			balance -= amount;
			balance = (double)Math.round(balance * 100d) / 100d;
			list.updateIndexAmount(index, balance);
			ModelEvent usd = new ModelEvent(this, index, "", balance);
			notifyChanged(usd);
			break;
		case "€":
			amount = (amount/euroRate);
			balance -= amount;
			balance = (double)Math.round(balance * 100d) / 100d;
			list.updateIndexAmount(index, balance);
			balance = (balance*euroRate);
			balance = (double)Math.round(balance * 100d) / 100d;
			ModelEvent euro = new ModelEvent(this, index, "", balance);
			notifyChanged(euro);
			break;
		case "¥":
			amount = (amount/yuanRate);
			balance -= amount;
			balance = (double)Math.round(balance * 100d) / 100d;
			list.updateIndexAmount(index, balance);
			balance = (balance*yuanRate);
			balance = (double)Math.round(balance * 100d) / 100d;
			ModelEvent yuan = new ModelEvent(this, index, "", balance);
			notifyChanged(yuan);
			break;
		default:
			break;
		}
	}
	
	public String getTitleB() {
		return (list.getListItemA(index));
	}
	
	public String getAmountB() {
		Double temp = list.getAmountFromIndex(index);
		
		if(exchange == "€") {
			temp *= euroRate;
			temp = (double)Math.round(temp * 100d) / 100d;
		}
		else if (exchange == "¥") {
			temp *= yuanRate;
			temp = (double)Math.round(temp * 100d) / 100d;
		}
		
		return (temp.toString());
	}
}
