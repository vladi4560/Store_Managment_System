package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DeleteProductView implements ViewType {
	private BorderPane bp;
	
	private StackPane sp;
	
	private VBox vtTxt;
	
	private HBox hbDownBtn, hbBarcode;
	
	private TextField txBarcode;
	
	private Label lbBarcode;
	
	private Patterns patterns = new Patterns();
	
	private Button btnDel;
	
	private Scene sneDel;

	public DeleteProductView() {
		bp = new BorderPane();
		initDeleteProductView();
	}

	private void initDeleteProductView() {
		bp.setTop(patterns.initTxt("Delete product by barcode"));
		initButton();
		initTxtField();
	}

	private void initButton() {
		btnDel = patterns.newButton("Delete barcode(if exist)");
		hbDownBtn = patterns.HBox();
		hbDownBtn.getChildren().addAll(patterns.initExitBt(), btnDel);
		bp.setBottom(hbDownBtn);
	}

	private void initTxtField() {
		hbBarcode = patterns.HBox();
		lbBarcode = new Label("Barcode:");
		txBarcode = new TextField();
		txBarcode.addEventFilter(KeyEvent.KEY_TYPED, patterns.lengthRequested(20));
		hbBarcode.getChildren().addAll(lbBarcode, txBarcode);
		hbBarcode.setSpacing(10);
		vtTxt = new VBox();
		vtTxt.getChildren().addAll(hbBarcode);
		vtTxt.setAlignment(Pos.CENTER);
		vtTxt.setSpacing(10);
		sp = new StackPane();
		sp.getChildren().add(vtTxt);
		bp.setCenter(sp);
	}

	@Override
	public Scene SceneInit() {
		sneDel = new Scene(bp, 500, 500);
		return sneDel;
	}

	@Override
	public void cleanScreen() {
		bp.getChildren().clear(); // Reset the screen
		patterns.cleanScreen();
	}

	public boolean isValid() {
		if (txBarcode.getText().isEmpty())
			return true;
		return false;
	}

	public Button getBtnDel() {
		return btnDel;
	}

	public TextField getTxBarcode() {
		return txBarcode;
	}

}
