package view;

import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import listeners.ChampionshipListenable;
import listeners.ViewListenable;


public class View {
	private static final int NUMBER_OF_PLAYERS = 8;
	private static final double ENLRAGMENT_FACTOR=1.5;
	private static final int CONTESTERS_IN_ROUND=2;
	private RadioButton rbHouse, rbBuilding, rbCastle;
	private CheckBox cbRoof, cbWindows, cbDoor;
	private ComboBox<String> roofColors;
	private ToggleGroup tg;
	private BorderPane bpRoot; // arrangement of Nodes to areas: LEFT,TOP,RIGHT,BOTTOM, CENTER
	private HBox hb;
	private Text txt;
	private HBox hbAddPlayer;
	private ArrayList<TextField> tfPlayers;
	private int nextPlayerIndex=0; // in order insert new names to textFields
	private ArrayList<Button> btnsPlayGames;
	private ToggleGroup tglGameType;
	private VBox vbChooseGameYype;
	private VBox vbMiddleStartGame;
	private VBox vbPlayGames; 
	
	// _____________________________________ 10Aug ->
	private ArrayList <TextField> tfPlayerNames2;
	
	
	private ArrayList<ViewListenable> allListeners;
	
	
	public View(Stage stage)
	{
	allListeners = new ArrayList<ViewListenable>();
	
	bpRoot = new BorderPane();
	bpRoot.setPadding(new Insets(20*ENLRAGMENT_FACTOR));

	//____________________________________toggle game type
	vbChooseGameYype = new VBox();
	setRdoGameType(vbChooseGameYype);
	
	
	//____________________________________ add player
	hbAddPlayer = new HBox();
	setHbAddPlayer (hbAddPlayer);
	
	//_____________________________________ Start game
	vbMiddleStartGame = new VBox();
	setVbStartGameUnderAddPlayer(vbMiddleStartGame,vbChooseGameYype);
	
	
	//__________________________________ player rows (General based on players.size() (in model))
	VBox vbPlayersInRound = new VBox();
	
	
	HBox hbMain = new HBox();
	hbMain.setPadding(new Insets(10));
	hbMain.setSpacing(10);
	hbMain.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
	
	
// TODO Remove Constans & use Data from Model inorder to  __________
	// Dynamically get proper # of duplicates & Spacing
	//_____________________________________ DYNAMICLY MAKE ALL ORGANS OF MAIN BOX_________________
	BtnPlayGameEventHandler2 btnPlayGameEvent2 = new BtnPlayGameEventHandler2();	
	ArrayList <VBox> vbNamesInRoundLbls2 = new ArrayList<VBox>();
	tfPlayerNames2 = new ArrayList<TextField>();
	
	ArrayList <VBox> vbPlayBtns2 = new ArrayList<VBox>();
	ArrayList <Button> btnPlayGame2 = new ArrayList<Button>();
	
	VBox vbNamesTemp2;
	TextField tfTemp2;
	
	VBox vbPlayTemp2;
	Button btnTemp2;
	
	
	
	HBox hbMainView2 = new HBox();
	int n=0,b=0,counter=0;// Names , Buttons , main round counter
	// |||||||||| i = Log2n || n = (Log2n)*n || b  = (Log2n)*(n/2) ||||||||||
	for (int i = NUMBER_OF_PLAYERS; i >= 1 ; i=i/2,counter++) { // runs log2n times
		// add VB of names  
		vbNamesTemp2 = new VBox();
		for(int j=0;j<i;j++,n++) {
			tfTemp2 = new TextField();
			tfTemp2.setEditable(false); // disable direct editing of cells.
			tfTemp2.setPrefHeight(10*ENLRAGMENT_FACTOR);
			tfPlayerNames2.add(tfTemp2); // add to the TextField arrayList [index n]
			vbNamesTemp2.getChildren().add(tfTemp2); // add to Current VBox [index k]
		}
		vbNamesTemp2.setAlignment(Pos.CENTER);
		vbNamesTemp2.setSpacing((8*(1+counter))*ENLRAGMENT_FACTOR);
		vbNamesTemp2.setPadding(new Insets(7*ENLRAGMENT_FACTOR));
		vbNamesInRoundLbls2.add(vbNamesTemp2); // add Current VBox to arrayList [index counter(i)]
		
		
		if (i!=1) { // in last round we don't need Playbtn(winner there)
		// add VB of Btns
		vbPlayTemp2 = new VBox();
		for(int k=0;k<i/2;k++,b++) { // maybe move k out, so that numbering 
			btnTemp2 = new Button();
			btnTemp2.setText("Play #" + b);
			btnTemp2.setOnAction(btnPlayGameEvent2); // set action
			btnTemp2.setPrefHeight(10*ENLRAGMENT_FACTOR);
			btnPlayGame2.add(btnTemp2); // add to Buttons arrayList [index b]
			vbPlayTemp2.getChildren().add(btnTemp2);  // add to Current VBox [index k]
			
		}
		vbPlayTemp2.setAlignment(Pos.CENTER);
		vbPlayTemp2.setSpacing((17*(2+counter))*ENLRAGMENT_FACTOR);
		vbPlayBtns2.add(vbPlayTemp2); // add Current VBox to arrayList [index counter(i)]
		
		
		// adding VBoxes to view 
		hbMainView2.getChildren().addAll(vbNamesInRoundLbls2.get(counter),vbPlayBtns2.get(counter));
		}
		else {
			hbMainView2.getChildren().addAll(vbNamesInRoundLbls2.get(counter));

		}
		
	}


	//________________________________________________________________________________________
	tfPlayers = new ArrayList<TextField>();
	vbPlayGames = new VBox();
	btnsPlayGames = new ArrayList<Button>();
	setBtnsPlayGames(NUMBER_OF_PLAYERS);
	
	
	VBox vbNamesInRound = new VBox();
	vbNamesInRound.setPadding(new Insets(10));
	vbNamesInRound.setSpacing(10*ENLRAGMENT_FACTOR);
	vbNamesInRound.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
	//TextField
//	TextField tfName = new TextField();
	for (int i = 0; i < NUMBER_OF_PLAYERS; i++) { // make it dynamic layer
		TextField tfTemp=new TextField();
		tfTemp.setEditable(false); // disable direct editing of cells.
		tfPlayers.add(tfTemp);
	}
	vbNamesInRound.getChildren().addAll(tfPlayers);
	vbNamesInRound.setAlignment(Pos.CENTER);
	

	//hirarcy hierarchy
	///____________TODO  CREATE ARRAY OF US ______________ Finish
	//FIXME return to normal Switch next two lines (comment out and uncomment out)
	hbMain.getChildren().addAll(vbNamesInRound,vbPlayGames);
//	hbMain.getChildren().addAll(vbNamesInRound,vboxesPlayRoundButtons.get(0).getVBox());

	for(Node ny: bpRoot.getChildren()) {
		((Label)ny).setBorder(new Border(new BorderStroke
				(Color.BLACK,BorderStrokeStyle.SOLID,null,new BorderWidths(20))));
		
	}
	bpRoot.setLeft(hbMainView2);
//	bpRoot.setLeft(hbMain);
	bpRoot.setRight(vbChooseGameYype);
	bpRoot.setCenter(vbMiddleStartGame);
	
	Scene scene = new Scene(bpRoot,760*ENLRAGMENT_FACTOR,420*ENLRAGMENT_FACTOR); 
	stage.setScene(scene);
	stage.show();
	


}	//Construe methods_________________________________________

	private void setBtnsPlayGames(int numberOfPlayers) {
		vbPlayGames.getChildren().clear();
		vbPlayGames.setPadding(new Insets(10*ENLRAGMENT_FACTOR));
		vbPlayGames.setSpacing(38*ENLRAGMENT_FACTOR);
		vbPlayGames.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		
		
		btnsPlayGames.clear();
		// TODO maybe add a toggle group for these buttons
		BtnPlayGameEventHandler btnPlayGameEvent = new BtnPlayGameEventHandler();
		for (int i = 0; i <  (numberOfPlayers/2); i++) { 
			Button btnTmp = new Button();
			btnTmp.setText("Play game " + i);
			btnTmp.setOnAction(btnPlayGameEvent);
			btnsPlayGames.add(btnTmp);
		}
		vbPlayGames.getChildren().addAll(btnsPlayGames);
		vbPlayGames.setAlignment(Pos.CENTER);
		
	}
	 // merge somewhere pretty into the code
	class BtnPlayGameEventHandler implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent btnPressed) {
			int gameNum = btnsPlayGames.indexOf(btnPressed.getSource());
			((Button)(btnPressed.getSource())).setStyle("-fx-text-fill: green");
			System.out.println("button num " + gameNum + " pressed");
			// maybe paint it so we know which game is on now 
			firePlayGameBtn(gameNum,0);
			
		}	
	}
	

	class BtnPlayGameEventHandler2 implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent btnPressed) {
			String btnContent = ((Button)btnPressed.getSource()).getText();
			Integer gameNum2 = Integer.parseUnsignedInt(btnContent, 6, btnContent.length(), 10);
			((Button)(btnPressed.getSource())).setStyle("-fx-text-fill: green");
			System.out.println("button num " + gameNum2 + " pressed");
			// maybe paint it so we know which game is on now 
			firePlayGameBtn(gameNum2,0);
		}	
	}
	

	
	
	
	

	

	private void setVbStartGameUnderAddPlayer(VBox vbMiddleStartGame,VBox vbChooseGameYype) {
		Button btnStartGame = new Button();
		btnStartGame.setText("Start Championship");
		btnStartGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
					String gameType = getGameType();
					if((gameType!="") && checkLeaugeIsFull())
					{
						bpRoot.getChildren().remove(vbMiddleStartGame);
						bpRoot.getChildren().remove(vbChooseGameYype);
						fireStartChampionship((gameType));
						//opensPlayGameWindow("Test1", "Test2");
						System.out.println("After opensPlayGameWindow");
					}
//				for(ViewListenable l : allListeners) {
//					l.viewAskToPlayGame(null);
//				}
			}
		
		}); // inner class closure
		
		vbMiddleStartGame.getChildren().addAll(hbAddPlayer,btnStartGame);
		vbMiddleStartGame.setAlignment(Pos.CENTER);
		vbMiddleStartGame.setSpacing(20*ENLRAGMENT_FACTOR);		
	}
	
	
	
	
	protected boolean checkLeaugeIsFull() {
		boolean result = (nextPlayerIndex==NUMBER_OF_PLAYERS);
		if (!result) {
			popUpShortMassage("Can't start game", "please enter " + (NUMBER_OF_PLAYERS-nextPlayerIndex) +
					" more players", 200, 100);
			return false;
		}
		return true;
	}

	protected String getGameType() {
		RadioButton chk;
		chk = (RadioButton)tglGameType.getSelectedToggle(); // Cast object to radio button
		if (chk==null) {
	    	popUpShortMassage("Can't start game", "please select game type", 200, 100);
	    	return "";
		}
        return chk.getText();


	}
	
	public void opensPlayGameWindow(int numOfRounds,String player1,String player2,int gameState,int gameId) {
		// number of rounds (inorder to know how many textboxes to open
		String[] tempStrArr = {player1,player2}; // for the loop..
		Stage gameStage = new Stage();
		gameStage.setTitle("PlayGame");
		VBox vbPlayerSeperator = new VBox();
		vbPlayerSeperator.setPadding(new Insets(10*ENLRAGMENT_FACTOR));
		vbPlayerSeperator.setSpacing(10*ENLRAGMENT_FACTOR);
		vbPlayerSeperator.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		Label lblHeadline = new Label(player1 + " Vs. " + player2 );
		lblHeadline.setMinHeight(10*NUMBER_OF_PLAYERS);
		lblHeadline.setAlignment(Pos.TOP_CENTER);
		lblHeadline.setFont(Font.font("Cambria", 32));
		vbPlayerSeperator.getChildren().add(lblHeadline); 
		// each layer in VBox is a player row 
		ArrayList<HBox> hbPlayerScores = new ArrayList<HBox>(); // array of HBoxes
		HBox hbTempPS; // each element in Array of HBoxes
		for (int i = 0; i < CONTESTERS_IN_ROUND; i++) {	
		//horizontal box initialize
			hbTempPS = new HBox();
			hbTempPS.setPadding(new Insets(10*ENLRAGMENT_FACTOR));
			hbTempPS.setSpacing(10*ENLRAGMENT_FACTOR);
			hbTempPS.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
		//name label
			Label lblPName= new Label();
			lblPName.setText(tempStrArr[i]); 
			lblPName.setMinWidth(40*ENLRAGMENT_FACTOR);
			lblPName.setMaxWidth(40*ENLRAGMENT_FACTOR);
			hbTempPS.getChildren().add(lblPName); // hbTempPS[0]
		//Scores 
			//TextField[] tfScoreCells = new TextField[numOfRounds];
			System.out.println("num of rounds: " + numOfRounds);
			ArrayList<TextField> tfScoreCells =  new ArrayList<TextField>();
			TextField tfTempSC;
			for (int j = 0; j < numOfRounds; j++) {
				tfTempSC = new TextField();
				tfTempSC.setText("R" + (j+1)); 
				tfTempSC.setMinWidth(15*ENLRAGMENT_FACTOR);
				tfScoreCells.add(tfTempSC);
			}
			hbTempPS.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
			
			hbTempPS.getChildren().addAll(tfScoreCells); //hbTempPS[1+j] (each 1 of them is
			hbPlayerScores.add(hbTempPS);// hbPlayerScores[i]
		}
		vbPlayerSeperator.getChildren().addAll(hbPlayerScores);

		vbPlayerSeperator.setAlignment(Pos.CENTER);
		//Btn to check results
		Button btnEnterResults = new Button();
		btnEnterResults.setText("Enter Results");
		btnEnterResults.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				//TODO collect values from cells into arraylist and send to model for checkig
				ArrayList<Integer> p1Results = new ArrayList<Integer>();
				ArrayList<Integer> p2Results = new ArrayList<Integer>();
			//	scanResultsFromBoard(p1Results,p2Results,hbPlayerScores,numOfRounds);
				String temp1,temp2;
				boolean scoresValid=true;
				for (int j = 0; j < numOfRounds; j++) {
					temp1 = ((TextField)((HBox) hbPlayerScores.get(0)).getChildren().get(1+j)).getText();
					temp2 = ((TextField)((HBox) hbPlayerScores.get(1)).getChildren().get(1+j)).getText();
					if(!(temp1.matches("\\d*"))||(!(temp2.matches("\\d*")))) {
						popUpShortMassage("Invalid input" ,
								"In round: " + (j+1) + "  Enter only integers " , 300, 150);
						scoresValid=false;
						break;
					}
					else {
						p1Results.add(Integer.parseInt(temp1));
						p2Results.add(Integer.parseInt(temp2));
						
					}	
				}
					// if(temp1) parse to integer try catch or set textfields to accept only digits
					if(scoresValid) {
						fireCheckResults(p1Results, p2Results, 0,gameId); // gameStage -- where to determain
						gameStage.close();
						// paint playButton in green
					}
					else {
						//Garbage collector or 
						p1Results.clear(); 
						p2Results.clear();
					}
				}
			
			

			
		});
		
		//show time
		vbPlayerSeperator.getChildren().add(btnEnterResults);
		vbPlayerSeperator.setAlignment(Pos.CENTER);
		Scene sceneGame = new Scene(vbPlayerSeperator,400*ENLRAGMENT_FACTOR,200*ENLRAGMENT_FACTOR); 
		gameStage.setScene(sceneGame);
		gameStage.show();
		
	}

	private void setHbAddPlayer(HBox hbAddPlayer) {
		Label lblEnterName = new Label();
		lblEnterName.setText("Enter name");
		TextField tfName = new TextField(); // Text Field
		// ENTER BY ENTER
		tfName.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {
				if(keyEvent.getCode()==KeyCode.ENTER) {
					handleNameText(tfName);
				}
			}
		});
		// ENTER BY BUTTON
		Button btnAddPlayer = new Button();
		btnAddPlayer.setText("add");
		
		btnAddPlayer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				handleNameText(tfName);
			}
		
		}); // inner class closure
		
		hbAddPlayer.getChildren().addAll(lblEnterName,tfName,btnAddPlayer);
		hbAddPlayer.setAlignment(Pos.CENTER);
		hbAddPlayer.setSpacing(5*ENLRAGMENT_FACTOR);
				
	}
	
	private void handleNameText(TextField tfTxt) {
		if(tfTxt.getText().length()!=0) {
			fireAskToAddNewPlayer(tfTxt.getText());
			tfTxt.setText("");
		}
	}
	


	private void setRdoGameType(VBox vbChooseGameType) {
		vbChooseGameType.setPadding(new Insets(30*ENLRAGMENT_FACTOR));
		vbChooseGameType.setSpacing(20*ENLRAGMENT_FACTOR);
		vbChooseGameType.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		
		tglGameType = new ToggleGroup();
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

	// Inner Classes ________________________________________________

	public void addNewName(String name) {
//		tfPlayers.get(nextPlayerIndex).setText(name);
		tfPlayerNames2.get(nextPlayerIndex).setText(name);
		nextPlayerIndex++;
		if(nextPlayerIndex==NUMBER_OF_PLAYERS)
			hbAddPlayer.setVisible(false);
	} // action started from 

	public void alertPlayerAlreadyExists(String name) {
		popUpShortMassage("Error", name + " Already in the leauge, choose another one.", 200, 100);
	}

	public void alertNoRoom() {
		popUpShortMassage("Error", "league is full, No room" , 200, 100);
	}
	
	private void popUpShortMassage(String headLine , String Massage,int Width, int Height) {
		Stage miniStage = new Stage();
		VBox vbPopup = new VBox();
		miniStage.setTitle(headLine);	
		Label lblMiniPopup = new Label();
		lblMiniPopup.setText(Massage);
		lblMiniPopup.setAlignment(Pos.CENTER);
		setStageCONSTSize(miniStage, Width, Width, Height, Height);
		Button btnClose = new Button();
		btnClose.setText("Ok");
		btnClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				miniStage.close();
			}
		});
		vbPopup.getChildren().addAll(lblMiniPopup,btnClose);
		vbPopup.setAlignment(Pos.CENTER);
		vbPopup.setSpacing(20*ENLRAGMENT_FACTOR);
		
		Scene scene = new Scene(vbPopup,Width*ENLRAGMENT_FACTOR,Height*ENLRAGMENT_FACTOR); 
		miniStage.setScene(scene);
		
		//miniStage.initStyle(StageStyle.UNDECORATED);
		miniStage.show();
		
		miniStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent theEvent) {
				if(theEvent.getCode()==KeyCode.ENTER||theEvent.getCode()==KeyCode.ESCAPE)
					miniStage.close();
			}
		});
		}

	
	private void setStageCONSTSize(Stage stg,int minWidth  , int maxWidth, int minHeihgt,int maxHeight) { // height 
	stg.setMinHeight(minHeihgt*ENLRAGMENT_FACTOR);
	stg.setMaxHeight(maxHeight*ENLRAGMENT_FACTOR);
	stg.setMinWidth(minWidth*ENLRAGMENT_FACTOR);
	stg.setMaxWidth(maxWidth*ENLRAGMENT_FACTOR);
	}

	private void fireCheckResults(ArrayList<Integer> p1Score,ArrayList<Integer> p2Score,int gameStage,int gameId) {
		for (int i = 0; i < p1Score.size(); i++) {
			System.out.print("p1 score in round " + (i+1) + " is " + p1Score.get(i));
			System.out.println("\tp2 score in round " + (i+1) + " is: "+p2Score.get(i));
			for (ViewListenable l : allListeners) {
				l.viewAskToPlayGame(p1Score, p2Score, gameStage, gameId);
			}
		}
		
		//TODO Continue me -> Do check b4 if the we're happy with the logic.
	}
	
	private void fireStartChampionship(String gameType) {
		for (ViewListenable l : allListeners)
			l.viewStartChamp(gameType);

	}
	
	protected void firePlayGameBtn(int gameNumInRound,int gameState) {
		for (ViewListenable l : allListeners)
			l.viewAskForGameFormat(gameNumInRound,gameState);
	}
	
	protected void fireAskToAddNewPlayer(String name) {
		for(ViewListenable l : allListeners) {
			l.viewAskToAddPlayer(name);
		}		
	}

	
	public void alertNameNotValid(String message) {
		popUpShortMassage("Error", "name not valid! ," + message , 200, 100);
	}

	public void registerListener(ViewListenable l) {
		allListeners.add(l);
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