package command;

import java.io.FileNotFoundException;
import java.io.IOException;

import classes.Store;
import singletone.Sender;
import view.StoreView;

public class SendMessageCommand implements Command{
private Sender sender;

	public SendMessageCommand(Store store, StoreView storeView) {
		this.sender = Sender.getInstance(store,storeView);
}

	@Override
	public void excute() throws FileNotFoundException, IOException {
String commerical = sender.getCommerical();
sender.getStore().notifyObservers(commerical);
	}

}
