package view;

import java.text.DecimalFormat;
import java.text.ParsePosition;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public class Patterns extends Designs {
	private StackPane spTxt;
	private Button btnExit;

	public StackPane initTxt(String text) {
		spTxt = new StackPane();
		Label centerTitle = new Label(text);
		centerTitle.setStyle(
				"-fx-background-color: ALICEBLUE; -fx-border-color: BLACK; -fx-border-width: 2px; -fx-font-size: 18; ");
		spTxt.getChildren().add(centerTitle);
		spTxt.setAlignment(Pos.TOP_CENTER);
		return spTxt;
	}

	public Button initExitBt() {
		btnExit = newButton("exit");
		btnExit.setOnAction(e -> {
			javafx.application.Platform.exit();
		});
		return btnExit;

	}

	public void cleanScreen() {
		spTxt.getChildren().clear();
	}

	public TextField onlyNumaric() {
		DecimalFormat format = new DecimalFormat("#.0");
		TextField tx = new TextField();
		tx.setTextFormatter(new TextFormatter<>(c -> {
			if (c.getControlNewText().isEmpty())
				return c;
			ParsePosition parsepos = new ParsePosition(0);
			Object obj = format.parse(c.getControlNewText(), parsepos);
			if (obj == null || parsepos.getIndex() < c.getControlNewText().length())
				return null;
			else
				return c;
		}));
		return tx;
	}

	public EventHandler<KeyEvent> lengthRequested(final Integer integer) {
		return new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {

				TextField tx = (TextField) arg0.getSource();
				if (tx.getText().length() >= integer) {
					arg0.consume();
				}

			}

		};

	}

	public TextField onlyAlphabetic() {
		TextField tx = new TextField();
		tx.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\sa-zA-Z*")) {
				tx.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
			}
		});
		return tx;
	}
}
