package command;

import java.io.FileNotFoundException;
import java.io.IOException;

import classes.Product;
import classes.Store;

public class AddProductCommand implements Command {
private Store store;
private String barcode;
private Product product;
	public AddProductCommand(Store store,String barcode,Product product) {
	this.store = store;
	this.product= product;
	this.barcode = barcode;
}
	@Override
	public void excute() throws FileNotFoundException, IOException {
		this.store.addProduct(barcode, product);
		
	}

}
