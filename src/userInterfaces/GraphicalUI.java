package userInterfaces;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.View;

public class GraphicalUI implements Messageable {
	private static int defFontSize = (int) (15*View.ENLRAGMENT_FACTOR);
	String userInput;
	VBox vbPopup;
	Text text;

	@Override
	public void showMessage(String message) {

		Stage miniStage = new Stage();
		vbPopup = new VBox();
		miniStage.initStyle(StageStyle.UNDECORATED);
		text = new Text(message);
		text.setStyle("-fx-font: " + defFontSize + " arial;");

		Button btnClose = new Button();
		btnClose.setText("Ok");
		btnClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				miniStage.close();
			}
		});
		vbPopup.getChildren().addAll(text, btnClose);
		vbPopup.setAlignment(Pos.TOP_CENTER);
		vbPopup.setSpacing(20 * View.ENLRAGMENT_FACTOR);



		Scene scene = new Scene(vbPopup, 300 * View.ENLRAGMENT_FACTOR, 150 * View.ENLRAGMENT_FACTOR);
		miniStage.setScene(scene);

		text.wrappingWidthProperty().bind(scene.widthProperty().subtract(50));
		
		
//		scene.widthProperty().subtract(-20).greaterThan(text.getWrappingWidth());
//		scene.heightProperty().subtract(-40).greaterThan(vbPopup.getMaxHeight());
		
		
		
		miniStage.show();

		miniStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent theEvent) {
				if (theEvent.getCode() == KeyCode.ENTER || theEvent.getCode() == KeyCode.ESCAPE)
					miniStage.close();
			}
		});
	}

	@Override
	public String getMessage(String message) {
		userInput = "";
		Stage miniStage = new Stage();
		vbPopup = new VBox();
		miniStage.initStyle(StageStyle.UNDECORATED);
		text = new Text(message);
		text.setStyle("-fx-font: " + defFontSize + " arial;");

		TextField tfName = new TextField(); // Text Field

		Scene scene = new Scene(vbPopup, 250 * View.ENLRAGMENT_FACTOR, 150 * View.ENLRAGMENT_FACTOR);
		miniStage.setScene(scene);

		text.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));

		miniStage.show();

		// ENTER BY ENTER
		tfName.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {
					userInput = tfName.getText();
				}
			}
		});
		// ENTER BY BUTTON
		Button btnAddPlayer = new Button();
		btnAddPlayer.setText("add");

		btnAddPlayer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				userInput = tfName.getText();
			}
		}); // inner class closure
		return userInput;
	}

	@Override
	public void showErrMessage(String message) {
		Stage miniStage = new Stage();
		vbPopup = new VBox();
		miniStage.setTitle("ERROR!");
		text = new Text(message);
		text.setStyle("-fx-font: " + defFontSize + " arial;");
		text.setStyle("-fx-text-fill: red");
		Button btnClose = new Button();
		btnClose.setText("Ok");
		btnClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				miniStage.close();
			}
		});
		vbPopup.getChildren().addAll(text, btnClose);
		vbPopup.setAlignment(Pos.CENTER);
		vbPopup.setSpacing(20 * View.ENLRAGMENT_FACTOR);

		Scene scene = new Scene(vbPopup, 200 * View.ENLRAGMENT_FACTOR, 100 * View.ENLRAGMENT_FACTOR);
		miniStage.setScene(scene);

		//text.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));

		scene.widthProperty().subtract(40).greaterThan(text.getWrappingWidth());
		scene.heightProperty().subtract(40).greaterThan(vbPopup.getHeight());
		miniStage.show();

		miniStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent theEvent) {
				if (theEvent.getCode() == KeyCode.ENTER || theEvent.getCode() == KeyCode.ESCAPE)
					miniStage.close();
			}
		});
	}
}
