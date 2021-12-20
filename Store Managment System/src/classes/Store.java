package classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import file.FileIterator;
import listener.listenerModel;
import memento.StoreMemento;
import observer.Observable;
import observer.Observer;
import thread.Reciever;

public class Store implements Observable {
	public final String fileName = "product.txt";
	private ArrayList<listenerModel> modelListener;
	private HashMap<String, Product> productsMap;
	private ArrayList<String> barcodesList;
	private ArrayList<Observer> obsList;
	private StoreMemento storeMemento;
	private int sortChoice;
	private FileIterator fi;

	public Store() throws FileNotFoundException, IOException {
		modelListener = new ArrayList<>();
		this.barcodesList = new ArrayList<String>();
		this.productsMap = new HashMap<>();
		this.obsList = new ArrayList<Observer>();
		storeMemento = new StoreMemento(this);
		initFile();
	}

	private void initFile() throws FileNotFoundException, IOException {
		fi = new FileIterator(fileName);
		this.productsMap = fi.readFromFile();
		FillFromFile();
	}

	private void FillFromFile() {
		for (Entry<String, Product> p : this.productsMap.entrySet()) {
			this.barcodesList.add(p.getKey());
			if (p.getValue().getCustomer().isSubscribe())
				obsList.add(p.getValue().getCustomer());
		}
	}

	public void registerListener(listenerModel listener) {
		modelListener.add(listener);
	}

	public void findProductByKey(String barcode) {
		if (barcodesList.contains(barcode)) {
			for (listenerModel m : modelListener)
				m.foundProductByBarcode(productsMap.get(barcode));
		} else {
			for (listenerModel m : modelListener)
				m.noBarcodeFound();
		}
	}

	public void sortMap() {
		if (sortChoice == 1)
			Collections.sort(barcodesList);
		else if (sortChoice == -1)
			Collections.sort(barcodesList, Collections.reverseOrder());
	}

	public void addProduct(String barcode, Product product) throws FileNotFoundException, IOException {
		if (barcodesList.contains(barcode)) {
			for (listenerModel m : modelListener)
				m.duplicateBarcode();
		} else {
			storeMemento.save();
			barcodesList.add(barcode);
			productsMap.put(barcode, product);
			if (product.getCustomer().isSubscribe()) 
				obsList.add(product.getCustomer());
				// addObserver(o);
			sortMap();
			fi.writeToFileAProduct(barcode, product);
			for (listenerModel m : modelListener)
				m.SuccesAddingToModel();
		}
	}

	public void removeAllProductsFromStore() throws FileNotFoundException, IOException {
		this.barcodesList = new ArrayList<>();
		this.productsMap = new HashMap<>();
		this.obsList = new ArrayList<>();
		storeMemento.save();
		fi.removeAll();

	}

	public void removeBybarcode(String barcode) throws FileNotFoundException, IOException {
		System.out.println("IM OK");
		if (!barcodesList.contains(barcode)) {
			for (listenerModel m : modelListener)
				m.noBarcodeFound();
		} else {
			obsList.remove(productsMap.get(barcode).getCustomer());
			productsMap.remove(barcode);
			barcodesList.remove(barcode);
			storeMemento.save();
			fi.removeByBarcode(barcode);
			for (listenerModel m : modelListener)
				m.succesRemovingFromModel();
		}
	}

	public void removeLast() throws FileNotFoundException, IOException {
		System.out.println("fine in remove last");
		setBarcodesList(storeMemento.getBarcodesList());
		setProductsMap(storeMemento.getProductsMap());
		setObsList(storeMemento.getObserversList());
		System.out.println("fine before file");
		fi.removeLast();
		System.out.println("fine After file");

	}

	public HashMap<String, Product> getProductsMap() {
		return productsMap;
	}

	public void AllProducts() {
		StringBuffer sb = new StringBuffer("List of products:\n");
		if (productsMap.isEmpty())
			sb.append("No products to present!!\n");
		int i = 1;
		for (String barcode : barcodesList) {
			sb.append("###############Product " + i + "################\n");
			sb.append("Barcode: " + barcode + productsMap.get(barcode).toString());
			i++;
		}
		for (listenerModel m : modelListener)
			m.sendProducts(sb.toString());
	}

	@Override
	public void addObserver(Observer o) {
		obsList.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		obsList.remove(o);
	}

	@Override
	public void notifyObservers(String commercial) {
		for (Observer o : obsList)
			o.updateSending(this, commercial);
	}

	public void recieveMessage() {
		for (int i = 0; i < obsList.size(); i++)
			obsList.get(i).updateRecieveing(this);
		Reciever sms = new Reciever(this);
		sms.run();
	}

	public void setSortChoice(int sortChoice) {
		this.sortChoice = sortChoice;
	}

	public ArrayList<String> getBarcodesList() {
		return barcodesList;
	}

	public ArrayList<Observer> getObsList() {
		return obsList;
	}

	public void setProductsMap(HashMap<String, Product> productsMap) {
		this.productsMap = productsMap;
	}

	public void setBarcodesList(ArrayList<String> barcodesList) {
		this.barcodesList = barcodesList;
	}

	public void setObsList(ArrayList<Observer> obsList) {
		this.obsList = obsList;
	}

	public ArrayList<listenerModel> getModelListener() {
		return modelListener;
	}

}
