package command;

import java.io.FileNotFoundException;
import java.io.IOException;
import classes.Store;

public class UndoCommand implements Command {
private Store store;
	public UndoCommand(Store store) {
	this.store = store;
}

	@Override
	public void excute() throws FileNotFoundException, IOException {
store.removeLast();
	}
}
