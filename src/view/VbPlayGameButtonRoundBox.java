package view;


// ABONDATE THIS.. LEAVE IF DECIDE THAT IT IS WORTH DEALTING WITH IT
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import listeners.ViewListenable;
import view.View.BtnPlayGameEventHandler;

public class VbPlayGameButtonRoundBox {
	VBox me;
	ArrayList<Button> btnsPlayGame;
	
	public VbPlayGameButtonRoundBox(int numberOfPlayers,double enlargmentFactor) {
		me = new VBox();
		me.setPadding(new Insets(10*enlargmentFactor));
		me.setSpacing(38*enlargmentFactor);
		me.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		btnsPlayGame = new ArrayList<Button>();
		setBtnsPlayGames1(numberOfPlayers);
	}
	
	private void setBtnsPlayGames1(int numberOfPlayers) {
		// TODO maybe add a toggle group for these buttons
		//BtnPlayGameEventHandler1 btnPlayGameEvent = new BtnPlayGameEventHandler1();
		for (int i = 0; i <  (numberOfPlayers/2); i++) { 
			Button btnTmp = new Button();
			btnTmp.setText("Play game " + i);
//			btnTmp.setOnAction(btnPlayGameEvent);
			btnsPlayGame.add(btnTmp);
		}
		me.getChildren().addAll(btnsPlayGame);
	}

	protected ArrayList<Button> getBtnsPlay() {
		return btnsPlayGame;
	}
	
	protected VBox getVBox() {
		return me;
	}

	


	
}


	
//tmpWindow.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//@Override
//public void handle(MouseEvent event) {
//	// find the index of a window that is responsible of the event (click)
//		int index = theView.getWindowIndex(event.getTarget());
//	//theModel.changeWindowColor(index);
//		theView.changeWindowColor(index);
//}
//});

