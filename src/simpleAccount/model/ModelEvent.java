package simpleAccount.model;
import java.awt.event.ActionEvent;

/**
 * Used to notify interested objects of changes in the 
 * state of a model
 * @author markmagahis
 *
 */
@SuppressWarnings("serial")
public class ModelEvent extends ActionEvent {
	private Double amount;
	public ModelEvent(Object obj, int id, String message, Double amount){
		super(obj, id, message);
		this.amount = amount;
	}
	public Double getAmount() {
		return amount;
	}
}
