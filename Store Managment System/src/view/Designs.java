package view;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
public class Designs {
	
	public HBox HBox() {
        HBox hBox = new HBox(5);
        hBox.setMinSize(450, 50);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }
    public Button newButton(String text) {
    	Button b = new Button(text);
    	b.setStyle(
				"-fx-background-color: ALICEBLUE; -fx-border-color: BLACK; -fx-border-width: 2px; -fx-font-size: 18; ");
      return b;
  
    }
}