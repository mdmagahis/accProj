package simpleAccount.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.junit.Test;

public class JUnitTestCases {

	@Test
	public void testWithDraw() {
		Object[] dismiss = {"dismiss"};
		Object expected = null;
		ArrayList<UserAcc> testList = new ArrayList<>();
		UserAcc testAccount = new UserAcc("test", 0, 0.00);
		testList.add(testAccount);
//				(JOptionPane.showOptionDialog(new JDialog(), "Invalid amount format!\n", 
//				"Invalid amount", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, dismiss , dismiss));
		
		AccountEditModel testModel = new AccountEditModel("test", 0, null);
		testModel.withdraw("0.01");
		
		Object actual = null;
		/**
		 * Exception thrown and withdraw JUnit pseudo-code:
		 * 	assert that testModel.withdraw("0.01"); creates JOptionPane on lines 21-22. 
		 * 	I was unable in creating such a test.
		 */
	}

}
