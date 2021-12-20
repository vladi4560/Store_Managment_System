package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import classes.Customer;
import classes.Product;
import classes.Store;
import command.AddProductCommand;
import command.DeleteAllCommand;
import command.DeleteByBarcodeCommand;
import command.FindCommand;
import command.RecieveCommand;
import command.SendMessageCommand;
import command.ShowAllProductsCommand;
import command.UndoCommand;
import listener.listenerModel;
import listener.listenerView;
import view.StoreView;

public class Controller implements listenerModel, listenerView {
	private Store model;
	private ShowAllProductsCommand showCommand;
	private AddProductCommand addCommand;
	private FindCommand findCommand;
	private UndoCommand undoCommand;
	private DeleteAllCommand deleteAllCommand;
	private DeleteByBarcodeCommand deleteByBarcodeCommand;
	private SendMessageCommand sendMessageCommand;
	private RecieveCommand recieveCommand;
	private StoreView storeView;

	public Controller(Store model, StoreView storeView) {
		this.model = model;
		this.storeView = storeView;
		showCommand = new ShowAllProductsCommand(model, storeView);
		model.registerListener(this);
		storeView.registerListener(this);

	}
	public void cancelYellowError() {
		
	}
	@Override
	public void lisSortChoice(int temp) throws FileNotFoundException, IOException {
		model.setSortChoice(temp);
		model.sortMap();
	}

	@Override
	public void showAllProductsOnView() {
		showCommand.excute();
	}

	@Override
	public void sendProducts(String str) {
		showCommand.excute(str);
	}

	@Override
	public void addProductFromView(String barcode, String productName, int storePrice, int customerPrice, boolean sub,
			String customerName, String phone) throws FileNotFoundException, IOException {
		addCommand = new AddProductCommand(this.model, barcode,
				new Product(productName, storePrice, customerPrice, new Customer(customerName, phone, sub)));
		addCommand.excute();

	}

	@Override
	public void SuccesAddingToModel() {
		this.storeView.SuccesAddingMessage();
	}

	@Override
	public void duplicateBarcode() {
		this.storeView.popMessage("Barcode is already in store!!");
	}

	@Override
	public void ProductFoundByBarcodeFromView(String barcode) {
		findCommand = new FindCommand(this.model, barcode);
		findCommand.excute();
	}

	@Override
	public void deleteLastFromView() throws FileNotFoundException, IOException {
		System.out.println(" OK IN CONTROLLER");
		undoCommand = new UndoCommand(this.model);
		undoCommand.excute();
	}

	@Override
	public void deleteAllFromView() throws FileNotFoundException, IOException {
		deleteAllCommand = new DeleteAllCommand(model);
		deleteAllCommand.excute();
	}

	@Override
	public void deleteByBarcodeFromView(String barcode) throws FileNotFoundException, IOException {
		deleteByBarcodeCommand = new DeleteByBarcodeCommand(model, barcode);
		deleteByBarcodeCommand.excute();
	}
	@Override
	public void errorMessage() {
		storeView.popMessage("ERROR!!");
	}
	@Override
	public void noBarcodeFound() {
		storeView.popMessage("Product by barcode is not in store!!");
	}

	@Override
	public void succesRemovingFromModel() {
		storeView.popMessage("Product by barcode removed succesfully");

	}

	@Override
	public void SendMessageFromView(String commercial) throws FileNotFoundException, IOException {
		sendMessageCommand = new SendMessageCommand(model, storeView);
		sendMessageCommand.excute();
	}

	@Override
	public void messageApprove(String name) {
		storeView.approveMessage(name);
		 //storeView.popMessage( "customer " + name + " Approved message");
	}

	@Override
	public void approveMessageFromView() throws FileNotFoundException, IOException {
		recieveCommand = new RecieveCommand(model);
		recieveCommand.excute();
	}

	@Override
	public void messageSent() {
		storeView.popMessage("Message sent to all subscribers!!");
	}

	@Override
	public void foundProductByBarcode(Product product) {
		storeView.getFindProductChoosenView().showText(product.toString());

	}
}
