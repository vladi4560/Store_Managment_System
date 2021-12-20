package command;

import classes.Store;

public class FindCommand implements Command {
	private Store store;
	private String barcode;

	public FindCommand(Store store, String barcode) {
		this.store = store;
		this.barcode = barcode;
	}

	@Override
	public void excute() {
		store.findProductByKey(barcode);
	}

}
