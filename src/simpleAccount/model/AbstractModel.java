package simpleAccount.model;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Abstract root class of Model hierarchy - provides basic 
 * notification behaviour
 * @author markmagahis
 *
 */
public abstract class AbstractModel implements Model {
	private ArrayList listeners = new ArrayList(5);
	
	/**
	 * Method that is called by subclasses of AbstractModel when they want to 
	 * notify other classes of changes to themselves.
	 */
	public void notifyChanged(ModelEvent event){
		ArrayList list = (ArrayList)listeners.clone();
		Iterator it = list.iterator();
		while(it.hasNext()){
			ModelListener ml = (ModelListener)it.next();
			ml.modelChanged(event);
		}
	}
	
	/**
	 * Add a ModelListener to the list of objects interested in ModelEvents.
	 * @param l
	 */
	public void addModelListener(ModelListener l){
		listeners.add(l);
	}
	
	/**
	 * Remove a ModelListener from the list of objects interested in ModelEvents 
	 * @param l
	 */
	public void removeModelListener(ModelListener l){
		listeners.remove(l);
	}
}
