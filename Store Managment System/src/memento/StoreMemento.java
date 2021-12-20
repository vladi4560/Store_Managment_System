package memento;

import java.util.ArrayList;
import java.util.HashMap;

import classes.Product;
import classes.Store;
import observer.Observer;

public class StoreMemento {
	private Store store;
	private HashMap<String, Product> productsMap;
	private ArrayList<String> barcodesList;
	private ArrayList<Observer> obsList;

	public StoreMemento(Store store) {
		this.store = store;
		this.barcodesList = new ArrayList<>();
		this.productsMap = new HashMap<>();
		this.obsList = new ArrayList<>();
		
	}
public void save() {
	productsMap.putAll(store.getProductsMap());
	barcodesList.addAll(store.getBarcodesList());
	obsList.addAll(store.getObsList());
}
	public HashMap<String, Product> getProductsMap() {
		return productsMap;
	}

	public ArrayList<String> getBarcodesList() {
		return barcodesList;
	}

	public ArrayList<Observer> getObserversList() {
		return obsList;
	}

}
