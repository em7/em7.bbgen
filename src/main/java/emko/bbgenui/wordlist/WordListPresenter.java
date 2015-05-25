package emko.bbgenui.wordlist;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Font;
import javafx.util.converter.LongStringConverter;

public class WordListPresenter implements Initializable {
	
	@FXML ObservableList<WordViewModel> words;
	@FXML TableView<WordViewModel> wordsTableView;
	@FXML TableColumn<WordViewModel, String> wordTableColumn;
	@FXML TableColumn<WordViewModel, Long> probabilityTableColumn;
	@FXML TableColumn<WordViewModel, Boolean> actionsTableColumn;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		words = FXCollections.observableArrayList();
		
		//add random words
		for (int i = 0; i < 25; i++) {
			Word word = new Word("Word " + i, i + 1);
			words.add(new WordViewModel(word));
		}

		//set the columns and styles
		wordsTableView.setItems(words);
		wordTableColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
		wordTableColumn.setCellFactory(TextFieldTableCell.<WordViewModel>forTableColumn());
		wordTableColumn.setStyle("-fx-alignment: baseline-left;");
		probabilityTableColumn.setCellValueFactory(new PropertyValueFactory<>("probability"));
		probabilityTableColumn.setCellFactory(TextFieldTableCell.<WordViewModel, Long>forTableColumn(new LongStringConverter()));
		probabilityTableColumn.setStyle("-fx-alignment: baseline-right;");
		actionsTableColumn.setCellFactory(c -> new ButtonCell());
		actionsTableColumn.setStyle("-fx-alignment: center;");
		
		//set column widths
		double em = Font.getDefault().getSize();
		//wordTableColumn.setPrefWidth(20 * em);
		probabilityTableColumn.setMinWidth(10 * em);
		probabilityTableColumn.setPrefWidth(10 * em);
		probabilityTableColumn.setMaxWidth(10 * em);
		actionsTableColumn.setMinWidth(10 * em);
		actionsTableColumn.setPrefWidth(10 * em);
		actionsTableColumn.setMaxWidth(10 * em);
		
	}
	
private class ButtonCell extends TableCell<WordViewModel, Boolean> {
		
		private final Button btn = new Button("-");
		
		public ButtonCell() {
			btn.setOnAction(ae -> {
				int rowIdx = getIndex();
				System.out.println(rowIdx);
			});
			btn.setTooltip(new Tooltip("Delete this row"));
			
			btn.getStyleClass().add("binButtonSmall");
			btn.setText(null);
		}

		@Override
		protected void updateItem(Boolean item, boolean empty) {
			super.updateItem(item, empty);
			if (! empty) {
				setGraphic(btn);
				setText(null);
			}
			else {
				setText(null);
				setGraphic(null);
			}
		}
		
	}

// S V G   G R A P H I C   B U T T O N
	
//	private class ButtonCell extends TableCell<WordViewModel, Boolean> {
//		
//		private final Button btn = new Button("-");
//		
//		public ButtonCell() {
//			btn.setOnAction(ae -> {
//				int rowIdx = getIndex();
//				System.out.println(rowIdx);
//			});
//			btn.setTooltip(new Tooltip("Delete this row"));
//			
//			try {
//				Group icon = (Group)FXMLLoader.load(getClass().getResource("/icons/minus20-red.fxml"));
//				double em = Font.getDefault().getSize();
//				double buttonHeight = 2.0 * em;
//				double buttonWidth = 1.5 * buttonHeight;
//				
//				btn.setGraphic(icon);
//				
//				btn.setMaxSize(buttonWidth, buttonHeight);
//				btn.setMinSize(buttonWidth, buttonHeight);
//				btn.setPrefSize(buttonWidth, buttonHeight);
////				btn.setMaxSize(100, 100);
////				btn.setMinSize(100, 100);
////				btn.setPrefSize(100, 100);
//				btn.setAlignment(Pos.CENTER);
//				
//				Bounds iconBounds = icon.getBoundsInParent();
////				System.out.println(iconBounds);
//				
//				double scale = (0.5 * buttonHeight)  / iconBounds.getHeight();
//				double iconCentre = iconBounds.getWidth() / 2.0;
//				double translateX = (0.3 * buttonWidth) - iconCentre;
////				System.out.println(iconCentre * scale);
////				System.out.println(translateX);
//				
//				icon.getTransforms().add(new Translate(translateX, 0));
//				icon.getTransforms().add(new Scale(scale, scale, iconBounds.getWidth() / 2.0, iconBounds.getHeight() / 2.0));
//				
//
////				iconBounds = icon.getBoundsInParent();
////				System.out.println(iconBounds);
//				
//			} catch (IOException e) {
//				btn.setText("Remove");
//			}
//		}
//
//		@Override
//		protected void updateItem(Boolean item, boolean empty) {
//			super.updateItem(item, empty);
//			if (! empty) {
//				setGraphic(btn);
//			}
//			else {
//				setText(null);
//				setGraphic(null);
//			}
//		}
//		
//	}

}
