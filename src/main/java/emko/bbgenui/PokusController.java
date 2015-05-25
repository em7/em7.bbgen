package emko.bbgenui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class PokusController implements Initializable {

	@FXML Pane cntPane;
	
	Button btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Group icon = (Group)FXMLLoader.load(getClass().getResource("/icons/minus20-red.fxml"));
			double em = Font.getDefault().getSize();
			
			btn = new Button();
			btn.setGraphic(icon);
			btn.setMaxSize(2.0 * em, 2.0 * em);
			btn.setMinSize(2.0 * em, 2.0 * em);
			btn.setPrefSize(2.0 * em, 2.0 * em);
			
			Bounds iconBounds = icon.getBoundsInParent();
			System.out.println(iconBounds);
			
			double scale = (1.4 * em) / iconBounds.getHeight();

			icon.getTransforms().add(new Scale(scale, scale, iconBounds.getWidth() / 2, iconBounds.getHeight() / 2));
			
			cntPane.getChildren().add(btn);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
