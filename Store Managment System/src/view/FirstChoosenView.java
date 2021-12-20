package view;

import javax.swing.JOptionPane;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class FirstChoosenView implements ViewType {
	private HBox hbRButtons,hbDownBtn;
	
	private Button btnContinue;
	
	private ToggleGroup group;
	
	private BorderPane bp;
	
	private RadioButton rButtonAsc,rButtonDes,rButtonNull;
	
	private Scene sneFirst;
	
	private int choice = 0;
	
	private Patterns patterns = new Patterns();

	public FirstChoosenView() {
		bp = new BorderPane();
		initFirstView();
	}

	private void initFirstView() {
		bp.setTop(patterns.initTxt("Hello\nBefore we start, please choose how you would like to sort"));
		initBt();
		initRadioButtons();
	}
private void initBt() {
	hbDownBtn = patterns.HBox();
	btnContinue = patterns.newButton("continue");
	hbDownBtn.getChildren().addAll(btnContinue,patterns.initExitBt());
	bp.setBottom(hbDownBtn);

}
	public void sortChs() {
		if (group.getSelectedToggle() == null) {
			JOptionPane.showMessageDialog(null, "Please choose an option!");
			return;
		}
		if (group.getSelectedToggle().equals(rButtonAsc))
			choice = 1;
		else if (group.getSelectedToggle().equals(rButtonDes))
			choice = -1;
		else
			choice = 0;
	}

	private void initRadioButtons() {
		group = new ToggleGroup();
		
		hbRButtons = patterns.HBox();
		
		rButtonAsc = new RadioButton("Sort in ascending order");
		rButtonDes = new RadioButton("Sort in descending order");
		rButtonNull = new RadioButton("None of the above");
		
		rButtonAsc.setToggleGroup(group);
		rButtonDes.setToggleGroup(group);
		rButtonNull.setToggleGroup(group);
		
		hbRButtons.getChildren().addAll(rButtonAsc, rButtonDes, rButtonNull);
		bp.setCenter(hbRButtons);

	}

	public void cleanScreen() {

		bp.getChildren().clear(); // Reset the screen
		patterns.cleanScreen();
	}

	@Override
	public Scene SceneInit() {
		sneFirst = new Scene(bp, 500, 500);
		return sneFirst;
	}

	public BorderPane getBp() {
		return bp;
	}

	public Patterns getPatterns() {
		return patterns;
	}

	public Button getBtnContinue() {
		return btnContinue;
	}

	public int getChoice() {
		return choice;
	}
	
}
