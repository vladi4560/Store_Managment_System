package main;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import classes.Store;
import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import view.StoreView;

public class Main extends Application {

	public final String mediaName = "Song1.wav";
private void playbackGroundMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	File targetFile = new File("Song1.wav");
	AudioInputStream aIs = AudioSystem.getAudioInputStream(targetFile);
	Clip audioClip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, aIs.getFormat()));
	audioClip.open(aIs);
	audioClip.start();
}
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		playbackGroundMusic();
		Store model = new Store();
		StoreView view = new StoreView(primaryStage);
		Controller controller = new Controller(model, view);
		controller.cancelYellowError();
	}
}
