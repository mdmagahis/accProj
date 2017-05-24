package simpleAccount.controller;

import simpleAccount.model.AccountModel;
import simpleAccount.view.AccountView;
import simpleAccount.view.JFrameView;

import java.io.FileNotFoundException;

public class AccountController extends AbstractController {
	public AccountController(String file) throws FileNotFoundException{
		setModel(new AccountModel());
		((AccountModel)getModel()).readInput(file);
		setView(new AccountView((AccountModel)getModel(), this));
		((JFrameView)getView()).setVisible(true);
	}

	public String[] createConcatListA() {
		return(((AccountModel)getModel()).createConcatListB());
	}
	
	public void operation(String option) throws FileNotFoundException {
		switch (option) {
			case AccountView.Exit:
				((AccountModel) getModel()).save();		//-- Save current contents
				System.exit(0);							//-- Exit the program
			case AccountView.Save:
				((AccountModel) getModel()).save();		//-- Save current contents
				break;
			case AccountView.USD:
				((AccountModel) getModel()).edit("$");
				break;
			case AccountView.Euros:
				((AccountModel) getModel()).edit("€");
				break;
			case AccountView.Yuan:
				((AccountModel) getModel()).edit("¥");
				break;
			default:
				break;
		}
	}
	
	public void updateSelect(int index){
		((AccountModel)getModel()).updateSelect(index);
	}

}
