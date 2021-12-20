package singletone;

import classes.Store;
import view.StoreView;

public class Sender {
	private static Sender instance;
	private Store store;
	private String commerical;

	private Sender(Store store, StoreView storeView) {
		this.store = store;
		this.commerical = storeView.getSendChoosenView().getTxMessage().getText();
	}

	public String getCommerical() {
		return commerical;
	}

	public static Sender getInstance(Store store, StoreView storeView) {
		if (instance == null) {
			instance = new Sender(store, storeView);
		}
		return instance;
	}

	public static void setInstance(Sender instance) {
		Sender.instance = instance;
	}

	public Store getStore() {
		return store;
	}

}
