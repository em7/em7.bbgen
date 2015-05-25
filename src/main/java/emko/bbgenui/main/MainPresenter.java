package emko.bbgenui.main;

import java.net.URL;
import java.util.ResourceBundle;

import emko.bbgenui.board.BoardView;
import emko.bbgenui.wordlist.WordListView;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.WindowEvent;
import javafx.scene.control.Button;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class MainPresenter implements Initializable {

	@FXML AnchorPane mainPane;
	//@FXML AnchorPane subMainPane;
	
	WordListView wordListView;
	Parent wordListViewParent;
	
	BoardView boardView;
	Parent boardViewParent;

	@FXML Label screenNameLabel;


	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		double em = Font.getDefault().getSize();
				
		//create word list view
		wordListView = new WordListView();
		Parent wordListViewParent = wordListView.getView();
		AnchorPane.setLeftAnchor(wordListViewParent, 0.5 * em);
		AnchorPane.setRightAnchor(wordListViewParent, 0.5 * em);
		AnchorPane.setTopAnchor(wordListViewParent, 0.5 * em);
		AnchorPane.setBottomAnchor(wordListViewParent, 0.5 * em);
		this.wordListViewParent = wordListViewParent;
		
		//create a board view
		boardView = new BoardView();
		Parent boardViewParent = boardView.getView();
		AnchorPane.setLeftAnchor(boardViewParent, 0.5 * em);
		AnchorPane.setRightAnchor(boardViewParent, 0.5 * em);
		AnchorPane.setTopAnchor(boardViewParent, 0.5 * em);
		AnchorPane.setBottomAnchor(boardViewParent, 0.5 * em);
		this.boardViewParent = boardViewParent;

		//add default view to graph
		mainPane.getChildren().add(boardViewParent);		
	}

	@FXML public void showBoard() {
		swipeContent(SwipeDirection.RIGHT, boardViewParent);
		changeScreenName("Board");
	}
	
	@FXML public void showWordList() {
		swipeContent(SwipeDirection.LEFT, wordListViewParent);
		changeScreenName("Words");
	}

	private enum SwipeDirection {
		LEFT, RIGHT
	}
	
	private void swipeContent(SwipeDirection direction, Parent newContent) {
		//the animation boundary
		double transX = mainPane.getWidth() + 10; // pane width + reserve
		if (direction == SwipeDirection.RIGHT)
			transX = - transX;
		
		final double translateX = transX; //it's used from anonymous innter class, must be final

		TranslateTransition moveOutTra = new TranslateTransition(Duration.millis(250d), mainPane);
		moveOutTra.setByX(-translateX);
		
		FadeTransition fadeOutTra = new FadeTransition(Duration.millis(300d), mainPane);
		fadeOutTra.setFromValue(1.0);
		fadeOutTra.setToValue(0.3);

		moveOutTra.setOnFinished(evt -> {
			//clear the graph
			mainPane.getChildren().clear();
			
			//move new content out of the scene
			mainPane.setTranslateX(translateX);		
			mainPane.getChildren().addAll(newContent);
			
			//animate it back
			TranslateTransition moveInTra = new TranslateTransition(Duration.millis(250), mainPane);
			moveInTra.setByX(- translateX);
			
			FadeTransition fadeInTra = new FadeTransition(Duration.millis(300d), mainPane);
			fadeInTra.setFromValue(0.3);
			fadeInTra.setToValue(1.0);
			
			moveInTra.play();
			fadeInTra.play();
		});
		
		fadeOutTra.play();
		moveOutTra.play();
		
	}

	private void changeScreenName(String newName) {
		FadeTransition fadeOutTra = new FadeTransition(Duration.millis(250d), screenNameLabel);
		fadeOutTra.setFromValue(1.0);
		fadeOutTra.setToValue(0d);
		fadeOutTra.setOnFinished(evt -> {
			screenNameLabel.setText(newName);
			FadeTransition fadeInTra = new FadeTransition(Duration.millis(250d), screenNameLabel);
			fadeInTra.setFromValue(0d);
			fadeInTra.setToValue(1.0);
			fadeInTra.play();
		});
		
		fadeOutTra.play();
	}
	

	
}
