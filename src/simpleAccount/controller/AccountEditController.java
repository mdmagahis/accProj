package simpleAccount.controller;

import java.io.FileNotFoundException;

import simpleAccount.model.AccountEditModel;
import simpleAccount.model.UserList;
import simpleAccount.view.AccountEditView;
import simpleAccount.view.JFrameView;

public class AccountEditController extends AbstractController {

	public AccountEditController(String exchange, int index, UserList list) {
		// TODO Auto-generated constructor stub
		setModel(new AccountEditModel(exchange, index, list));
		setView(new AccountEditView(((AccountEditModel)getModel()), this, exchange, index));
		((JFrameView)getView()).setVisible(true);
	}
	
	public String getTitleA() {
		return (((AccountEditModel)getModel()).getTitleB());
	}

	public String getAmountA() {
		// TODO Auto-generated method stub
		return (((AccountEditModel)getModel()).getAmountB());
	}

	public void operation(String option, String amount) throws FileNotFoundException {
		switch (option) {
			case AccountEditView.Deposit:
				((AccountEditModel)getModel()).deposit(amount);
				break;
			case AccountEditView.Withdraw:
				((AccountEditModel)getModel()).withdraw(amount);
				break;
			case AccountEditView.Dismiss:
				((JFrameView)getView()).setVisible(false);
				((JFrameView)getView()).dispose();
			default:
				break;
		}
	}
}
