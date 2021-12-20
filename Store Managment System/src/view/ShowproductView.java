package view;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ShowproductView implements ViewType {
	private BorderPane bp;
	private ScrollPane sp;
	private HBox hbDownBtn;
	private Patterns patterns = new Patterns();
	private Button btnReturn;
	private Scene sneShow;
	private Text text;

	public ShowproductView() {
		bp = new BorderPane();
		initShowproductView();
	}

	private void initShowproductView() {
		bp.setTop(patterns.initTxt("Products in store:"));
		initButton();
	}

	private void initButton() {
		btnReturn = patterns.newButton("Back to main menu");
		hbDownBtn = patterns.HBox();
		hbDownBtn.getChildren().addAll(patterns.initExitBt(), btnReturn);
		bp.setBottom(hbDownBtn);
	}

	@Override
	public Scene SceneInit() {
		sneShow = new Scene(bp, 500, 500);
		return sneShow;
	}

	public void showText(String str) {
		text = new Text(str);
		text.setFill(Color.BLACK);
		text.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		sp = new ScrollPane();
		sp.setFitToHeight(true);
		sp.setContent(text);
		bp.setCenter(sp);
	}

	@Override
	public void cleanScreen() {
		bp.getChildren().clear(); // Reset the screen
		patterns.cleanScreen();
	}

	public Button getBtnReturn() {
		return btnReturn;
	}

}
