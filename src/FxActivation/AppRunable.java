package FxActivation;


import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import view.View;

public class AppRunable extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Model theModel = new Model();
		View theView = new View(primaryStage);
		Controller TheController = new Controller(theModel, theView);
	}

}
