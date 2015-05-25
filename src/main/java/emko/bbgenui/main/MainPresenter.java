package emko.bbgenui.main;

import java.net.URL;
import java.util.ResourceBundle;

import emko.bbgenui.board.BoardView;
import emko.bbgenui.wordlist.WordListView;
import javafx.animation.TranslateTransition;
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

public class MainPresenter implements Initializable {

	@FXML AnchorPane mainPane;
	//@FXML AnchorPane subMainPane;
	
	WordListView wordListView;
	
	BoardView boardView;

	@FXML Button boardBtn;

	@FXML Button wordListBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		double em = Font.getDefault().getSize();
		
//		wordListView = new WordListView();
//		Parent wordListViewParent = wordListView.getView();
//		double em = Font.getDefault().getSize();
//		AnchorPane.setLeftAnchor(wordListViewParent, 0.5 * em);
//		AnchorPane.setRightAnchor(wordListViewParent, 0.5 * em);
//		AnchorPane.setTopAnchor(wordListViewParent, 0.5 * em);
//		AnchorPane.setBottomAnchor(wordListViewParent, 0.5 * em);
//
//		mainPane.getChildren().addAll(wordListViewParent);
		
		//create a board view
		boardView = new BoardView();
		Parent boardViewParent = boardView.getView();
		AnchorPane.setLeftAnchor(boardViewParent, 0.5 * em);
		AnchorPane.setRightAnchor(boardViewParent, 0.5 * em);
		AnchorPane.setTopAnchor(boardViewParent, 0.5 * em);
		AnchorPane.setBottomAnchor(boardViewParent, 0.5 * em);

		//add to graph
		mainPane.getChildren().add(boardViewParent);		

		
		
		
		
		//wordListView.getPresenter();
	}

	@FXML public void showBoard(ActionEvent event) {
		
	}

	@FXML public void showWordList(ActionEvent event) {
		//the animation boundary
		double translateX = mainPane.getWidth() + 10; //pane width + reserve
		
		//move everything out
		if (mainPane.getChildren().size() > 0) {
			for (Node child : mainPane.getChildren()) {
				TranslateTransition moveLeftTra = new TranslateTransition(Duration.millis(500d), child);
				moveLeftTra.setByX(- translateX);
				moveLeftTra.play();				
			}
		}
		
		//clear the graph
		mainPane.getChildren().clear();
		
		//create the word list
		wordListView = new WordListView();
		Parent wordListViewParent = wordListView.getView();
		double em = Font.getDefault().getSize();
		AnchorPane.setLeftAnchor(wordListViewParent, 0.5 * em);
		AnchorPane.setRightAnchor(wordListViewParent, 0.5 * em);
		AnchorPane.setTopAnchor(wordListViewParent, 0.5 * em);
		AnchorPane.setBottomAnchor(wordListViewParent, 0.5 * em);

		//move it out of the scene
		wordListViewParent.setTranslateX(translateX);		
		mainPane.getChildren().addAll(wordListViewParent);
		
		//animate it back
		TranslateTransition moveLeftTra = new TranslateTransition(Duration.millis(500), wordListViewParent);
		moveLeftTra.setByX(- translateX);
		moveLeftTra.play();
	}
	

	
}
