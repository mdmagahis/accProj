package simpleAccount.view;
import javax.swing.*;

import simpleAccount.controller.Controller;
import simpleAccount.model.AbstractModel;
import simpleAccount.model.Model;
import simpleAccount.model.ModelListener;

/**
 * The JFrameView class is the root class of the view class hierarchy for top level
 * (swing) frames. It allows a controller and a model to be registered and can register
 * * itself with a model as an observer of that model. 
 * <p>
 * It this extends the JFrame class.
 * <p>
 * It requires the implementation of the <code>modelChanged(ModelEvent event);</code> 
 * method in order that it can work with the notification mechanism in Java.
 * @author markmagahis
 *
 */
@SuppressWarnings("serial")
abstract public class JFrameView extends JFrame implements View, ModelListener {
	private Model model;
	private Controller controller;
	public JFrameView (Model model, Controller controller){
		setModel(model);
		setController(controller);
	}
	public void registerWithModel(){
		((AbstractModel)model).addModelListener(this);
	}
	public Controller getController(){return controller;}
	
	public void setController(Controller controller){this.controller = controller;}
	
	public Model getModel(){return model;}
	
	public void setModel(Model model) {
		this.model = model;
		registerWithModel();
	}
	
}
