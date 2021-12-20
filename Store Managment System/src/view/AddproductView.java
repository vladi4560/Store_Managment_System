package view;

import javax.swing.JOptionPane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AddproductView implements ViewType {
	private BorderPane bp;

	private StackPane spProduct, spCustomer;

	private VBox vtProduct, vtCustomer;

	private HBox hbDownBtn, hbCusSub;
	private HBox hbPName, hbPSPrice, hbPCPrice, hbBarcode, hbCusName, hbCusPhone;

	private TextField txPName, txPSPrice, txPCPrice, txBarcode, txCusName, txCusPhone;

	private Label lbPName, lbPSPrice, lbPCPrice, lbBarcode, lbCusName, lbCusphone;

	private ToggleGroup group;

	private RadioButton rButtonSub, rButtonUnSub;

	private Patterns patterns = new Patterns();

	private Button btnAdd;

	private Scene sneAdd;

	public AddproductView() {
		bp = new BorderPane();
		initAddproductView();
	}

	private void initAddproductView() {
		bp.setTop(patterns.initTxt("Adding Prodcut"));
		
		initButton();
		
		initTxtField();
	}

	private void initButton() {
		btnAdd = patterns.newButton("Add");
		hbDownBtn = patterns.HBox();
		hbDownBtn.getChildren().addAll(patterns.initExitBt(), btnAdd);
		bp.setBottom(hbDownBtn);
	}

	private void initTxtField() {
		hbPName = patterns.HBox();
		hbPSPrice = patterns.HBox();
		hbPCPrice = patterns.HBox();
		hbBarcode = patterns.HBox();
		hbCusName = patterns.HBox();
		hbCusPhone = patterns.HBox();

		lbPName = new Label("Product Name:");
		txPName = patterns.onlyAlphabetic();
		txPName.addEventFilter(KeyEvent.KEY_TYPED, patterns.lengthRequested(50));
		hbPName.getChildren().addAll(lbPName, txPName);
		hbPName.setSpacing(10);

		lbBarcode = new Label("Barcode:");
		txBarcode = new TextField();
		txBarcode.addEventFilter(KeyEvent.KEY_TYPED, patterns.lengthRequested(20));
		hbBarcode.getChildren().addAll(lbBarcode, txBarcode);
		hbBarcode.setSpacing(10);

		lbPSPrice = new Label("Store Price:");
		txPSPrice = patterns.onlyNumaric();
		txPSPrice.addEventFilter(KeyEvent.KEY_TYPED, patterns.lengthRequested(4));
		hbPSPrice.getChildren().addAll(lbPSPrice, txPSPrice);
		hbPSPrice.setSpacing(10);

		lbPCPrice = new Label("Customer Price:");
		txPCPrice = patterns.onlyNumaric();
		txPCPrice.addEventFilter(KeyEvent.KEY_TYPED, patterns.lengthRequested(4));
		hbPCPrice.getChildren().addAll(lbPCPrice, txPCPrice);
		hbPCPrice.setSpacing(10);

		lbCusName = new Label("Customer Name:");
		txCusName = patterns.onlyAlphabetic();
		txCusName.addEventFilter(KeyEvent.KEY_TYPED, patterns.lengthRequested(50));
		hbCusName.getChildren().addAll(lbCusName, txCusName);
		hbCusName.setSpacing(10);

		lbCusphone = new Label("Phone number: 05");
		txCusPhone = patterns.onlyNumaric();
		txCusPhone.addEventFilter(KeyEvent.KEY_TYPED, patterns.lengthRequested(8));
		hbCusPhone.getChildren().addAll(lbCusphone, txCusPhone);
		hbCusPhone.setSpacing(10);

		vtProduct = new VBox();
		vtProduct.getChildren().addAll(hbPName, hbPSPrice, hbPCPrice, hbBarcode);
		vtProduct.setAlignment(Pos.CENTER);
		vtProduct.setSpacing(10);

		vtCustomer = new VBox();
		vtCustomer.getChildren().addAll(hbCusName, hbCusPhone, initRadioButtons());
		vtCustomer.setAlignment(Pos.CENTER);
		vtCustomer.setSpacing(10);

		spProduct = new StackPane();
		spProduct.getChildren().add(vtProduct);

		spCustomer = new StackPane();
		spCustomer.getChildren().addAll(vtCustomer);

		bp.setTop(spProduct);
		bp.setCenter(spCustomer);
	}

	private HBox initRadioButtons() {
		group = new ToggleGroup();

		hbCusSub = patterns.HBox();

		rButtonSub = new RadioButton("Subscribe");
		rButtonUnSub = new RadioButton("NOT Subscribe");

		rButtonSub.setToggleGroup(group);
		rButtonUnSub.setToggleGroup(group);

		hbCusSub.getChildren().addAll(rButtonSub, rButtonUnSub);

		return hbCusSub;
	}

	@Override
	public Scene SceneInit() {
		sneAdd = new Scene(bp, 500, 500);

		return sneAdd;
	}

	@Override
	public void cleanScreen() {
		txBarcode.clear();
		txPCPrice.clear();
		txPSPrice.clear();
		txPName.clear();
		txCusName.clear();
		txCusPhone.clear();
		group.selectToggle(null);
		bp.getChildren().clear(); // Reset the screen
		patterns.cleanScreen();
	}

	public boolean isValid() {
		if ((txPName.getText().isEmpty()) || (txBarcode.getText().isEmpty()) || txCusName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please dont leave name(s) or barcode without text!!");
			return true;
		}
		if (group.getSelectedToggle() == null) {
			JOptionPane.showMessageDialog(null, "Please choose an option on the subscribe!!");
			return true;
		}
		if (txPCPrice.getText().isEmpty())
			txPCPrice.setText("0");
		if (txPSPrice.getText().isEmpty())
			txPSPrice.setText("0");
		if (txCusPhone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a phone number!!");
			return true;
		}
		if (txCusPhone.getText().length() != 8) {
			JOptionPane.showMessageDialog(null, "Please enter a valid phone number!!");
			return true;
		}
		return false;
	}

	public Button getBtnAdd() {
		return btnAdd;
	}

	public TextField getTxPName() {
		return txPName;
	}

	public TextField getTxPSPrice() {
		return txPSPrice;
	}

	public TextField getTxPCPrice() {
		return txPCPrice;
	}

	public TextField getTxBarcode() {
		return txBarcode;
	}

	public ToggleGroup getGroup() {
		return group;
	}

	public RadioButton getrButtonSub() {
		return rButtonSub;
	}

	public TextField getTxCusName() {
		return txCusName;
	}

	public TextField getTxCusPhone() {
		return txCusPhone;
	}

}
