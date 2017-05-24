package simpleAccount.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * The UserList class contains utility functions. It is responsible for 
 * reading data from an inFile
 * <p>
 * Also included a method for detecting an if a string contains only integers,
 * a method for ensuring all IDs are unique, and a method to save the contents
 * of a list.
 * @author markmagahis
 *
 */
public class UserList {
	private ArrayList<UserAcc> list = new ArrayList<>();
	private String inFile;
	private boolean modified = false;
	
	String readInput(String file) throws FileNotFoundException {
		String line = "";
		UserAcc tempAcc;
		
		this.inFile = file;
		
		File input = new File(file);
		
		
		if (!(input.exists())) {
			//-- File not found
			return "Error 404: File not found!";
		}
		else {
			Scanner inputFile = new Scanner(input);
			
			String[] dismiss = {"dismiss"};
			

			//-- Skip first two lines (Headers and Line break)
			inputFile.nextLine();
			inputFile.nextLine();
			
			int lineNo = 3;
			
			while(inputFile.hasNext()) {
				//-- Read next line
				line = inputFile.nextLine();
				
				//-- Store contents of line into data
				String[] data = line.split("[|]");
//				System.out.println(data[0] + " | " + data[1] + " | " + data[2]);
				//-- Name
				data[0] = data[0].substring(0, (data[0].length() - 1));	//-- start at 0 because name starts at 0, whitespace ignored
				//-- ID
				data[1] = data[1].substring(1, (data[1].length() - 1));	//-- Start at 1 to skip ' '
				//-- Amount
				data[2] = data[2].substring(2); 						//-- Start at 2 to skip ' ' and '$'
				
				if (!(isInt(data[1]))) {
					//-- ID isn't in integer format
					JOptionPane.showOptionDialog( new JDialog(), "ID is not in correct format on line " + lineNo + "!",
							"ID Format Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
							null, dismiss, dismiss);
				}
				else {
					//-- Check if ID exists
					if(idExists(data[1])) {
						//-- ID already exists
						JOptionPane.showOptionDialog( new JDialog(), "ID already exists!",
								"Duplicate ID Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE,
								null, dismiss, dismiss);
					}
					else {
						//-- ID doesn't already exist
						//-- Check if Name is in proper format (fName + " " + lName)
						if (!(Pattern.matches("[a-zA-Z ]+", data[0]))) {
							//-- Incorrect format for Name
							JOptionPane.showOptionDialog( new JDialog(), "Name is not in correct format on line " + lineNo + "!",
									"Name Format Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE,
									null, dismiss, dismiss);
						}
						else {
							//-- Correct format for name
							if (!(isDouble(data[2]))) {
								//-- Amount not in double format
								JOptionPane.showOptionDialog( new JDialog(), "Amount is not in correct format on line " + lineNo + "!",
										"Amount Format Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE,
										null, dismiss, dismiss);
							}
							else {
								//-- all input valid
								//-- assign contents to tempAcc
								tempAcc = new UserAcc(data[0], Integer.parseInt(data[1]), Double.parseDouble(data[2]));
								//-- Add tempAcc to list
								list.add(tempAcc);
							}
						}
					}
				}
				lineNo++;
			}
			inputFile.close();
			return "Success!";
		}
	}
		
	
	//-- Check if a string consists of only integers
	private Boolean isInt(String s) {
	    try { 
	        Integer.parseInt(s);
	    } catch(NumberFormatException | NullPointerException e) {
	        return false; 	//-- not an Integer
	    }
		return true;		//-- s is an Integer
	}

	private Boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch(NumberFormatException | NullPointerException e) {
			return false;
		}
		return true;
	}
	
	//-- Check if contents of s exists as an ID in list
	private boolean idExists(String idToCheck) {
		boolean exists = false;
		int id = Integer.parseInt(idToCheck);

		for (UserAcc aList : list) {
			if (id == aList.getId()) {
				//-- ID exists
				exists = true;
			}
		}
		return exists;
	}
	
	
	//-- Save contents of list to outFile
	void save() throws FileNotFoundException {
		if(!modified)	return;
		
		File output = new File(inFile);
		String name, newLine;
		Integer id;
		Double amount;
		
		if( output.exists() ) {
			PrintWriter outFile = new PrintWriter(output);
		    
			//-- Default first two lines: Headers & Line break
			outFile.println("    name          | id     |   amount ");
			outFile.println("--------------------------------------");

			for (UserAcc aList : list) {
				name = aList.getName();
				id = aList.getId();
				amount = aList.getAmount();

				newLine = name + " | " + Integer.toString(id) + " | $" + Double.toString(amount);

				outFile.println(newLine);
			}
			outFile.close();
			modified = false;
		}
	}
	
	Integer getSize(){
		return list.size();
	}
	
	String getListItemA(Integer index) {
		return (list.get(index).getListItemB());
	}
	
	Double getAmountFromIndex(Integer index) {
		return (list.get(index).getAmount());
	}
	
	//-- Sort list in increasing order of IDs
	void sort() {
		boolean needSort = true;
		
		while (needSort) {
			needSort = false;
			
			for (int i = 0; i < (list.size() - 1); i++) {
				if(list.get(i).getId() > list.get(i+1).getId()) {
					UserAcc tempAcc = list.get(i);
					list.set(i, list.get(i+1));
					list.set(i+1, tempAcc);
					needSort = true;
				}
			}
		}
	}

	void updateIndexAmount(int index, Double balance) {
		// TODO Auto-generated method stub
		list.get(index).setAmount(balance);
		modified = true;
	}
}