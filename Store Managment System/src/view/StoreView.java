package view;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import listener.listenerView;

public class StoreView {
	// Save And Load map */
	// Components
	private ArrayList<listenerView> viewListener = new ArrayList<>();
	private FirstChoosenView firstChoosenView;
	private MainView mainChoosenView;
	private AddproductView addProductChoosenView;
	private ShowproductView showProductChoosenView;
	private FindproductView findProductChoosenView;
	private DeleteProductView deleteProductChoosenView;
	private SendAndApproveView sendChoosenView;
	private Stage storeStage;
	private Scene currentScene;
	private JCheckBox yes, no;
	private boolean undo = false;

	public StoreView(Stage primaryStage) throws Exception {
		storeStage = new Stage();
		storeStage.setTitle("Omer Sananes & Vladi Karasov");
		firstView();
	}

	public void registerListener(listenerView listener) {
		viewListener.add(listener);
	}

	private void firstView() {
		firstChoosenView = new FirstChoosenView();
		currentScene = firstChoosenView.SceneInit();
		storeStage.setScene(currentScene);
		storeStage.show();

		firstChoosenView.getBtnContinue().setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				firstChoosenView.cleanScreen();
				firstChoosenView.sortChs();
				for (listenerView v : viewListener)
					try {
						v.lisSortChoice(firstChoosenView.getChoice());
					} catch (IOException e) {
						e.printStackTrace();
					}
				mainView();

			}
		});

	}

	private void mainView() {
		mainChoosenView = new MainView();
		currentScene = mainChoosenView.SceneInit();
		storeStage.setScene(currentScene);
		storeStage.show();
		mainChoosenView.getBtnAddProduct().setOnAction(e -> {
			mainChoosenView.cleanScreen();
			addProductView();
		});
		mainChoosenView.getBtnShowMap().setOnAction(e -> {
			mainChoosenView.cleanScreen();
			ShowProductView();
		});
		mainChoosenView.getBtnProductByBarcode().setOnAction(e -> {
			mainChoosenView.cleanScreen();
			findProductView();
		});
		mainChoosenView.getBtnDeleteProduct().setOnAction(e -> {
			mainChoosenView.cleanScreen();
			DeleteProductView();
		});
		mainChoosenView.getBtnSendMessage().setOnAction(e -> {
			mainChoosenView.cleanScreen();
			sendview();
		});
		mainChoosenView.getBtnDeleteAll().setOnAction(e->{
			for (listenerView v : viewListener)
				try {
					v.deleteAllFromView();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		});

		mainChoosenView.getBtnUndo().setOnAction(e -> {
			if (undo) {
				undo = false;
				for (listenerView v : viewListener)
					try {
						v.deleteLastFromView();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				popMessage("last deleted");
			} else
				popMessage("Unable");
		});
	}

	private void sendview() {
		sendChoosenView = new SendAndApproveView();
		currentScene = sendChoosenView.SceneInit();
		storeStage.setScene(currentScene);
		storeStage.show();
		sendChoosenView.getBtnSend().setOnAction(e -> {
			if (sendChoosenView.isValid())
				popMessage("Cannot send empty message!!");
			else {
				for (listenerView v : viewListener)
					try {
						String message = sendChoosenView.getTxMessage().getText();
						v.SendMessageFromView(message);
						if (ShowingApproveText()) {
							v.approveMessageFromView();
							//sendChoosenView.cleanScreen();
							//threadView(message);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
			sendChoosenView.cleanScreen();
			mainView();

		});
	}
	private void addProductView() {
		addProductChoosenView = new AddproductView();
		currentScene = addProductChoosenView.SceneInit();
		storeStage.setScene(currentScene);
		storeStage.show();
		addProductChoosenView.getBtnAdd().setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (addProductChoosenView.isValid()) {
					addProductChoosenView.cleanScreen();
					mainView();
				} else {
					for (listenerView v : viewListener) {
						boolean sub = false;
						int cusPrice = Integer.parseInt(addProductChoosenView.getTxPCPrice().getText());
						int stoPrice = Integer.parseInt(addProductChoosenView.getTxPSPrice().getText());
						if (addProductChoosenView.getGroup().getSelectedToggle()
								.equals(addProductChoosenView.getrButtonSub()))
							sub = true;
						try {
							v.addProductFromView(addProductChoosenView.getTxBarcode().getText(),
									addProductChoosenView.getTxPName().getText(), stoPrice, cusPrice, sub,
									addProductChoosenView.getTxCusName().getText(),
									addProductChoosenView.getTxCusPhone().getText());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					addProductChoosenView.cleanScreen();

					mainView();
				}
			}
		});
	}

	public void SuccesAddingMessage() {
		undo = true;
		popMessage("Product added succesfully");
	}

	public boolean ShowingApproveText() {
		yes = new JCheckBox("Yes");
		no = new JCheckBox("no");
		String choose = "choose";
		Object[] options = { choose, yes, no };
		JOptionPane.showOptionDialog(null, "Would you like to see who approved the message?", null,
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (yes.isSelected() && no.isSelected()) {
			popMessage("Cannot choose both options!!");
			return false;
		} else if (yes.isSelected())
			return true;
		else if (no.isSelected())
			return false;
		else {
			popMessage("Please choose an option!!");
			return false;
		}
	}

	public void popMessage(String text) {
		JOptionPane.showMessageDialog(null, text);
	}
	
	public void approveMessage(String name) {
		JOptionPane am = new JOptionPane("customer " + name + " Approved message");
		JDialog dAm = am.createDialog("");
		dAm.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
try {
	Thread.sleep(1000);
	
} catch (InterruptedException e) {
e.printStackTrace();
}				
dAm.setVisible(false);
			}
		}).start();
		dAm.setVisible(true);
		//JOptionPane.showMessageDialog(null,  "customer " + name + " Approved message");
		
	}
	private void ShowProductView() {
		showProductChoosenView = new ShowproductView();
		currentScene = showProductChoosenView.SceneInit();
		storeStage.setScene(currentScene);
		for (listenerView v : viewListener)
			v.showAllProductsOnView();
		storeStage.show();
		showProductChoosenView.getBtnReturn().setOnAction(e -> {
			showProductChoosenView.cleanScreen();
			mainView();
		});
	}

	private void findProductView() {
		findProductChoosenView = new FindproductView();
		currentScene = findProductChoosenView.SceneInit();
		storeStage.setScene(currentScene);
		storeStage.show();
		findProductChoosenView.getBtnFind().setOnAction(e -> {
			findProductChoosenView.setTextFind("");
			if (findProductChoosenView.isValid()) {
				popMessage("Please enter barcode!!");
				findProductChoosenView.cleanScreen();
				mainView();
			} else {
				for (listenerView v : viewListener)
					v.ProductFoundByBarcodeFromView(findProductChoosenView.getTxBarcode().getText());
			}
		});
		findProductChoosenView.getBtnReturn().setOnAction(e -> {
			findProductChoosenView.cleanScreen();
			mainView();
		});

	}

	private void DeleteProductView() {
		deleteProductChoosenView = new DeleteProductView();
		currentScene = deleteProductChoosenView.SceneInit();
		storeStage.setScene(currentScene);
		storeStage.show();
		deleteProductChoosenView.getBtnDel().setOnAction(e -> {
			if (deleteProductChoosenView.isValid()) {
				popMessage("Please enter a barcode!!");
				deleteProductChoosenView.cleanScreen();
				mainView();
			} else {
				String barcode = deleteProductChoosenView.getTxBarcode().getText();
				for (listenerView v : viewListener) {
					try {
						v.deleteByBarcodeFromView(barcode);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				deleteProductChoosenView.cleanScreen();
				mainView();
			}
		});
	}

	public ShowproductView getShowProductChoosenView() {
		return showProductChoosenView;
	}

	public FindproductView getFindProductChoosenView() {
		return findProductChoosenView;
	}

	public SendAndApproveView getSendChoosenView() {
		return sendChoosenView;
	}
}
