package thread;

import classes.Customer;
import classes.Store;
import listener.listenerModel;
import singletone.Sender;

public class Reciever extends Thread {
	private Store store;
	private String name;

	public Reciever(Store store) {
		this.store = store;
	}

	@Override
	public void run() {
		try {
			System.out.println(store.getObsList().size());
			for (int i = 0; i < store.getObsList().size(); i++) {
				Customer c = (Customer) store.getObsList().get(i);
				name = c.getName();
				for (listenerModel m : store.getModelListener())
					m.messageApprove(name);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			for (listenerModel m : store.getModelListener())
				m.errorMessage();
		} finally {
			Sender.setInstance(null);
		}
	}
}
