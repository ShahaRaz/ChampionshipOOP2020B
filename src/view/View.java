package view;

import java.util.ArrayList;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import listeners.ChampionshipListenable;
import listeners.ViewListenable;

public class View {
	private static final int NUMBER_OF_PLAYERS = 8;
	private static final double ENLRAGMENT_FACTOR=1.5;
	private RadioButton rbHouse, rbBuilding, rbCastle;
	private CheckBox cbRoof, cbWindows, cbDoor;
	private ComboBox<String> roofColors;
	private ToggleGroup tg;
	private BorderPane bpRoot; // arrangement of Nodes to areas: LEFT,TOP,RIGHT,BOTTOM, CENTER
	private HBox hb;
	private Text txt;
	ArrayList<TextField> tfPlayers;
	private int nextPlayerIndex=0; // in order insert new names to textFields
	
	
	
	private ArrayList<ViewListenable> allListeners;
	
	
	public View(Stage stage)
	{
	allListeners = new ArrayList<ViewListenable>();
	tfPlayers = new ArrayList<TextField>();
	bpRoot = new BorderPane();
	bpRoot.setPadding(new Insets(20*ENLRAGMENT_FACTOR));

	
	
	Label lblTop= new Label("Top");
	Label lblBottom= new Label("Bottom");
	Label lblLeft= new Label("Left");
	Label lblRight= new Label("Right");
	Label lblCenter= new Label("Center");
	
	//____________________________________toggle game type
	VBox vbChooseGameYype = new VBox();
	setRdoGameType(vbChooseGameYype);
	
	
	//____________________________________ add player
	HBox hbAddPlayer = new HBox();
	setHbAddPlayer (hbAddPlayer);
	
	//_____________________________________ Start game
	VBox vbMiddleStartGame = new VBox();
	setVbStartGameUnderAddPlayer(vbMiddleStartGame,hbAddPlayer);
	//__________________________________ player rows (General based on players.size() (in model))
	VBox vbPlayersInRound = new VBox();
	
	TextField tfNameRow = new TextField();
	
	tfNameRow.setDisable(true);
	
	
	
	HBox hbMain = new HBox();
	hbMain.setPadding(new Insets(10));
	hbMain.setSpacing(10);
	hbMain.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
	
	
// TODO Remove Constans & use Data from Model inorder to  __________
	// Dynamically get proper # of duplicates & Spacing
	
	VBox vbPlayGames = new VBox();
	
	vbPlayGames.setPadding(new Insets(26));
	vbPlayGames.setSpacing(48);
	vbPlayGames.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
	
	ArrayList<Button> stageBtns = new ArrayList<Button>();
	Button btnTmp;
	for (int i = 0; i < 4; i++) { // make it dynamic layer
		btnTmp = new Button();
		btnTmp.setText("Play a Game");
		stageBtns.add(btnTmp);
	}
	vbPlayGames.getChildren().addAll(stageBtns);
	
	VBox vbNamesInRound = new VBox();
	vbNamesInRound.setPadding(new Insets(10));
	vbNamesInRound.setSpacing(10);
	vbNamesInRound.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
	///____________TODO  CREATE ARRAYs OF US ______________ START 
	//____TODO: update number of new buttons using the model's round $ info
	//Play game button 
	
	
	//TextField
//	TextField tfName = new TextField();
	for (int i = 0; i < NUMBER_OF_PLAYERS; i++) { // make it dynamic layer
		TextField tfTemp=new TextField();
		tfPlayers.add(tfTemp);
		vbNamesInRound.getChildren().addAll(tfTemp);
	}
	

	
	///____________TODO  CREATE ARRAY OF US ______________ Finish
	hbMain.getChildren().addAll(vbNamesInRound,vbPlayGames);
	
	
	bpRoot.setTop(lblTop);
	bpRoot.setBottom(lblBottom);
	bpRoot.setLeft(lblLeft);
	bpRoot.setRight(lblRight);
	bpRoot.setCenter(lblCenter);
	
	
	for(Node n: bpRoot.getChildren()) {
		((Label)n).setBorder(new Border(new BorderStroke
				(Color.BLACK,BorderStrokeStyle.SOLID,null,new BorderWidths(20))));
		
	}
	// Make Dynamically Modifiable _____________________________ Finish
	bpRoot.setLeft(hbMain);
	bpRoot.setRight(vbChooseGameYype);
	bpRoot.setCenter(vbMiddleStartGame);
	
	Scene scene = new Scene(bpRoot,760*ENLRAGMENT_FACTOR,420*ENLRAGMENT_FACTOR); 
	stage.setScene(scene);
	stage.show();
	


}	//Construe methods_________________________________________

	private void setVbStartGameUnderAddPlayer(VBox vbMiddleStartGame, HBox hbAddPlayer) {
		Button btnStartGame = new Button();
		btnStartGame.setText("Start Championship");

		
		btnStartGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				opensPlayGameWindow("Test1", "Test2");
				System.out.println("After opensPlayGameWindow");
//				for(ViewListenable l : allListeners) {
//					l.viewAskToPlayGame(null);
//				}
			}
		
		}); // inner class closure
		
		vbMiddleStartGame.getChildren().addAll(hbAddPlayer,btnStartGame);
		vbMiddleStartGame.setAlignment(Pos.CENTER);
		vbMiddleStartGame.setSpacing(20*ENLRAGMENT_FACTOR);		
	}
	
	
	private void opensPlayGameWindow(String player1,String player2) {
		Stage gameStage = new Stage();
		gameStage.setTitle("PlayGame");
		VBox vbPlayerSeperator = new VBox();
		vbPlayerSeperator.setPadding(new Insets(10*ENLRAGMENT_FACTOR));
		vbPlayerSeperator.setSpacing(10*ENLRAGMENT_FACTOR);
		vbPlayerSeperator.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		System.out.println("be4 4 4 4 ");
		// each layer in VBox is a player row (usually 2 players)
		int numOfRounds = 2; // = getNumOfRounds();
		int numOfPlayers = 2; // keep modifiable for generic reasons
		ArrayList<HBox> hbPlayerScores = new ArrayList<HBox>();
		int playerIndex=1,roundIndex=1;
		for(HBox hbPS : hbPlayerScores){
			System.out.println("in array");
			hbPS = new HBox();
			hbPS.setPadding(new Insets(10*ENLRAGMENT_FACTOR));
			hbPS.setSpacing(10*ENLRAGMENT_FACTOR);
			hbPS.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
			Label lblPName= new Label();
			lblPName.setText("player" + playerIndex++); // TODO make dynamic -> array / main architecture 
			hbPS.getChildren().add(lblPName); // when working, move out from the loop
			TextField[] tfScoreCells = new TextField[numOfRounds];
			for(TextField tfSC : tfScoreCells) {
				tfSC = new TextField();
				tfSC.setText("round" + roundIndex); 
				hbPS.getChildren().add(tfSC); // when working, move out from the loop
			}
//			hbPS.getChildren().add(lblPName);
//			hbPS.getChildren().addAll(tfScoreCells);
			hbPS.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
			
			vbPlayerSeperator.getChildren().add(hbPS);
			//FIXME the function doesn't work this way, fix it later.
		//	hbPlayerScores[i].getChildren().addAll(tfScoreCells);
		}
//		HBox[] hbPlayerScores = new HBox[numOfPlayers];
//		for (int i = 0; i < hbPlayerScores.length; i++) {
//			System.out.println("in array");
//			hbPlayerScores[i] = new HBox();
//			hbPlayerScores[i].setPadding(new Insets(10*ENLRAGMENT_FACTOR));
//			hbPlayerScores[i].setSpacing(10*ENLRAGMENT_FACTOR);
//			hbPlayerScores[i].setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
//			Label lblPName= new Label();
//			lblPName.setText("player" + i); // TODO make dynamic -> array / main architecture 
//			TextField[] tfScoreCells = new TextField[numOfRounds];
//			for(TextField tfSC : tfScoreCells) {
//				tfSC = new TextField();
//				//tfSC.autosize(); // see if works 
//			}
//			hbPlayerScores[i].getChildren().addAll(lblPName,hbPlayerScores[0],hbPlayerScores[1]);
//		//	hbPlayerScores[i].getChildren().addAll(tfScoreCells);
//		}
/*	
//		HBox hbPlayer1NScore = new HBox();
//		hbPlayer1NScore.setPadding(new Insets(10*ENLRAGMENT_FACTOR));
//		hbPlayer1NScore.setSpacing(10*ENLRAGMENT_FACTOR);
//		hbPlayer1NScore.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
//		Label lblP1Name= new Label();
//		lblP1Name.setText(player1);
//		TextField tfP1Name = new TextField(); // make it an arraylist
//		hbPlayer1NScore.getChildren().addAll(lblP1Name,tfP1Name);

//		
		
//		vbPlayerSeperator.getChildren().addAll(hbPlayer1NScore,hbPlayer2NScore);
	
*/
	//	vbPlayerSeperator.getChildren().addAll(hbPlayerScores);
		vbPlayerSeperator.setAlignment(Pos.CENTER);
		System.out.println(vbPlayerSeperator);
		
		Scene sceneGame = new Scene(vbPlayerSeperator,300*ENLRAGMENT_FACTOR,200*ENLRAGMENT_FACTOR); 
		gameStage.setScene(sceneGame);
		gameStage.show();
		
		
		
		
	}

	private void setHbAddPlayer(HBox hbAddPlayer) {
		Label lblEnterName = new Label();
		lblEnterName.setText("Enter name");
		TextField tfName = new TextField(); // Text Field
		Button btnAddPlayer = new Button();
		btnAddPlayer.setText("add");
		
		btnAddPlayer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				for(ViewListenable l : allListeners) {
					l.viewAddedPlayer(tfName.getText());
				}
				tfName.setText("");
			}
		
		}); // inner class closure
		
		hbAddPlayer.getChildren().addAll(lblEnterName,tfName,btnAddPlayer);
		hbAddPlayer.setAlignment(Pos.CENTER);
		hbAddPlayer.setSpacing(5*ENLRAGMENT_FACTOR);
				
	}
	
	private void setRdoGameType(VBox vbChooseGameType) {
		vbChooseGameType.setPadding(new Insets(30*ENLRAGMENT_FACTOR));
		vbChooseGameType.setSpacing(20*ENLRAGMENT_FACTOR);
		vbChooseGameType.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		
		ToggleGroup tglGameType = new ToggleGroup();
		RadioButton rdoTennis = new RadioButton("Tennis");
		RadioButton rdoSoccer = new RadioButton("Soccer");
		RadioButton rdoBasketball= new RadioButton("Basketball");
		
		rdoTennis.setToggleGroup(tglGameType);
		rdoSoccer.setToggleGroup(tglGameType);
		rdoBasketball.setToggleGroup(tglGameType);
		
		vbChooseGameType.getChildren().addAll(rdoTennis,rdoSoccer,rdoBasketball);
		
		vbChooseGameType.setAlignment(Pos.CENTER);
		vbChooseGameType.setAlignment(Pos.CENTER_LEFT);
	}
	
	
	public void registerListener(ViewListenable l) {
		allListeners.add(l);
	}
	
	
	private void firePlayGameBtn(String players) {
		for (ViewListenable l : allListeners)
			l.viewAskToPlayGame(players);
	}
	
	
	
	
	
	// Inner Classes ________________________________________________
	class PlayGameEvent implements EventHandler<Event>{

		@Override
		public void handle(Event e) {
			Button btn = (Button)(e.getSource());
			String btnText = btn.getText();
			firePlayGameBtn(btnText); // TODO Implemnet to controller
			}
		
		
	}





	public void addNewName(String name) {
		tfPlayers.get(nextPlayerIndex).setText(name);
		nextPlayerIndex++;
	}

	public void alertPlayerAlreadyExists(String name) {
		Stage errStage = new Stage();
		errStage.setTitle("Error");	
		TextField tfError = new TextField();
		tfError.setText("Player Already in game, choose another one.");
		tfError.setAlignment(Pos.CENTER);
		Scene scene = new Scene(tfError,200*ENLRAGMENT_FACTOR,100*ENLRAGMENT_FACTOR); 
		errStage.setScene(scene);
		errStage.show();
		
		
	}

	public void alertNoRoom() {
		Stage errStage = new Stage();
		errStage.setTitle("Error");	
		TextField tfError = new TextField();
		tfError.setText("Leauge is Full, no room");
		tfError.setAlignment(Pos.CENTER);
		Scene scene = new Scene(tfError,200*ENLRAGMENT_FACTOR,100*ENLRAGMENT_FACTOR); 
		errStage.setScene(scene);
		errStage.show();		
	}
	

	
	
	
}






//public class View {
//	private Group root;
//	private RadioButton rbHouse, rbBuilding, rbCastle;
//	private CheckBox cbRoof, cbWindows, cbDoor;
//	private ComboBox<String> roofColors;
//	private ToggleGroup tg;
//	private BorderPane bp; // arrangement of Nodes to areas: LEFT,TOP,RIGHT,BOTTOM, CENTER
//	private HBox hb;
//	private Text txt;
//	private ArrayList<Rectangle> allWindows;
//	public View(Stage stage)
//	{
//		root = new Group();
//		
//		tg = new ToggleGroup();
//		rbHouse = new RadioButton("House");
//		rbHouse.setSelected(true);
//		rbHouse.setToggleGroup(tg);
//		rbBuilding = new RadioButton("Building");
//		rbBuilding.setToggleGroup(tg);
//		rbCastle = new RadioButton("Castle");
//		rbCastle.setToggleGroup(tg);
//		
//		cbRoof = new CheckBox("Roof");
//		cbWindows = new CheckBox("Windows");
//		cbDoor = new CheckBox("Door");
//		
//		VBox vbLeft = new VBox();
//		vbLeft.getChildren().addAll(rbHouse, rbBuilding, rbCastle);
//		vbLeft.setAlignment(Pos.CENTER_LEFT);
//		
//		VBox vbRight = new VBox();
//		vbRight.getChildren().addAll(cbRoof, cbWindows, cbDoor);
//		vbRight.setAlignment(Pos.CENTER_LEFT);
//		
//		Pane drawPane = new Pane();
//		drawPane.getChildren().add(root);
//		
//		// add windows as controls
//		allWindows = new ArrayList<>();
//		
//		
//		// roof colors
//		roofColors = new ComboBox<>();
//		roofColors.getItems().addAll("Red","Green","Gray");
//		roofColors.setValue("Red");
//		txt = new Text("Roof Colors: ");
//		
//		// Horizontal box for combobox and its title
//		hb = new HBox();
////		hb.getChildren().addAll(txt,roofColors);
//		hb.setAlignment(Pos.CENTER);
//		
//		// border pane
//		bp = new BorderPane();
//		bp.setLeft(vbLeft);
//		bp.setRight(vbRight);
//		bp.setCenter(drawPane);
//		bp.setBottom(hb);
//		
//		Scene scene = new Scene(bp,500,500);
//		stage.setScene(scene);
//		stage.show();
//	}
//	
//	public void update(Model m)
//	{
//		root.getChildren().clear(); // clean the previous view
//		m.show(root);
//		if(cbWindows.isSelected())
//			root.getChildren().addAll(allWindows);
//	}
//	
//	public String getKind() {
//		if(rbHouse.isSelected())
//			return rbHouse.getText();
//		else if(rbBuilding.isSelected())
//			return rbBuilding.getText();
//		else return rbCastle.getText();
//	}
//	
//	// change listener has been created in Control
//	public void addChangeListenerToToggleGroup(ChangeListener<Toggle> chl) {
//		tg.selectedToggleProperty().addListener(chl);
//	}
//	
//	public void addChangeListenerToRoof(ChangeListener<Boolean> cl) {
//		cbRoof.selectedProperty().addListener(cl);
//	}
//	
//	public void addChangeListenerToRoofColors(ChangeListener<String> cl) {
//		roofColors.valueProperty().addListener(cl); // connect roofColors to cl that was defined in Controller
//	}
//	
//	public boolean getRoofIsSelected() {
//		return cbRoof.isSelected();
//	}
//	
//	public boolean getWindowsIsSelected() {
//		return cbWindows.isSelected();
//	}
//	
//	public boolean getDoorIsSelected() {
//		return cbDoor.isSelected();
//	}
//	
//	public String getRoofColor() {
//		return roofColors.getValue();
//	}
//	
//	public void addRoofColorsComboBox() {
//		hb.getChildren().addAll(txt,roofColors);
//	}
//	public void hideRoofColorsComboBox() {
//		hb.getChildren().clear();
//	}
//	
//	public void addChangeListenerToDoor(ChangeListener<Boolean> listener) {
//		cbDoor.selectedProperty().addListener(listener);
//	}
//	
//	public void addChangeListenerToWindows(ChangeListener<Boolean> listener) {
//		cbWindows.selectedProperty().addListener(listener);
//	}
//	
//	// when the windows check box is unselected we don't need windows
//	public void removeWindows() {
//		allWindows.clear();
//	}
//	
//	public void addWindow(Rectangle window) {
//		allWindows.add(window);
//	}
//	
//	public int getWindowIndex(Object o) {
//		return allWindows.indexOf(o);
//	}
//	
//	public void changeWindowColor(int index) {
//		if(allWindows.get(index).getFill()==Color.BLACK) {
//			allWindows.get(index).setFill(Color.YELLOW);
//		}
//		else 	
//			allWindows.get(index).setFill(Color.BLACK);
//
//	}
//}