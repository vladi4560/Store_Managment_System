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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SendAndApproveView implements ViewType {
	private BorderPane bp;
	private StackPane sp;
	private VBox vtTxt;
	private HBox hbDownBtn;
	private TextField txMessage;
	private Label lbMessage;
	private Patterns patterns = new Patterns();
	private Button btnSend;
	private Scene sneSend;

	public SendAndApproveView() {
		bp = new BorderPane();
		initSendView();
	}

	private void initSendView() {
		bp.setTop(patterns.initTxt("Send message to subscribed customers"));
		initButton();
		initTxtField();
	}

	private void initButton() {
		btnSend = patterns.newButton("Send message");
		hbDownBtn = patterns.HBox();
		hbDownBtn.getChildren().addAll(patterns.initExitBt(), btnSend);
		bp.setBottom(hbDownBtn);
	}

	private void initTxtField() {
		lbMessage = new Label("Enter your Message (up to 100 characters):");
		lbMessage.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		lbMessage.setAlignment(Pos.TOP_CENTER);
		txMessage = new TextField();
		txMessage.addEventFilter(KeyEvent.KEY_TYPED, patterns.lengthRequested(100));
		txMessage.setPrefWidth(50);
		txMessage.setPrefHeight(100);
		txMessage.setAlignment(Pos.TOP_LEFT);
		
		vtTxt = new VBox();
		vtTxt.getChildren().addAll(lbMessage, txMessage);
		vtTxt.setAlignment(Pos.CENTER);
		sp = new StackPane();
		sp.getChildren().add(vtTxt);
		bp.setCenter(sp);
		bp.setCenter(sp);
	}

	@Override
	public Scene SceneInit() {
		sneSend = new Scene(bp, 500, 500);
		return sneSend;
	}
	@Override
	public void cleanScreen() {
		txMessage.clear();
		bp.getChildren().clear(); // Reset the screen
		patterns.cleanScreen();
	}

	public boolean isValid() {
		if (txMessage.getText().isEmpty())
			return true;
		return false;
	}

	public Button getBtnSend() {
		return btnSend;
	}

	public TextField getTxMessage() {
		return txMessage;
	}

}
