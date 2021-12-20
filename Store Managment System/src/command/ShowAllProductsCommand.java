package command;

import classes.Store;
import view.StoreView;

public class ShowAllProductsCommand implements Command {
private Store store;
private String str;
private StoreView storeView;

	public ShowAllProductsCommand(Store store,StoreView storeView) {
	this.store = store;
	this.storeView=storeView;
}

	@Override
	public void excute() {
store.AllProducts();
	}
	public void excute(String str) {
		this.storeView.getShowProductChoosenView().showText(str);
			}
	public String getStr() {
		return str;
	}

}
