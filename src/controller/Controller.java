package controller;
import view.View;
import listeners.ChampionshipListenable;
import listeners.ViewListenable;
import model.Model;


public class Controller implements ChampionshipListenable , ViewListenable {
	private Model theModel;
	private View theView;
	

	public Controller(Model m, View v) {
		theModel = m;
		theView = v;
		
		theModel.registerListener(this);
		theView.registerListener(this); //throws
	}


	@Override
	public void modelUpdateWinner() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void modelUpdateNoRoom() {
		theView.alertNoRoom();
	}




	@Override
	public void modelAddedName(String name) {
		theView.addNewName( name);
	}
	
	@Override
	public void modelUpdatePlayerAlreadyAdded(String name) {
		theView.alertPlayerAlreadyExists(name);
	}

	@Override
	public void viewAskToPlayGame(String player) {
		// view needs to send all the boxes of input to 2 arraylists
		theModel.checkScoreBoard(player, null, null);
		
		// TODO trace forward & backward the logic here \
		// ALSO back to the view: if results arnt valid... written in one of the functions.
	}

	@Override
	public void viewAddedPlayer(String name) {
		theModel.addPlayer(name);
		System.out.println("maybe change to void, and fire update from model");
		
	}


	@Override
	public void viewStartChamp() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void viewSetGamesType() {
		// TODO Auto-generated method stub
		
	}










	
	
	
}
	
	



// _____________________________________
//public class Controller {
//	private Model theModel;
//	private View theView;
//	
//	public Controller(Model m, View v) {
//		theModel = m;
//		theView = v;
//		
//		theView.update(theModel);
//		
//		// add events
//		// change Listener to Toggle group (of radio buttons)
//		ChangeListener<Toggle> chl = new ChangeListener<Toggle>() {
//
//				@Override
//			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
//
//				String kind = theView.getKind();
//				// 1. update Model
//				theModel.update(kind,theView.getRoofIsSelected(),theView.getWindowsIsSelected(),
//						theView.getDoorIsSelected());
//				// 2. remove old windows and add new windows
//				theView.removeWindows();
//				addWindowsToView();
//				// 3. show the updated model
//				theView.update(theModel);
//			}
//		};
//		// attach change Listener to toggle Group
//		theView.addChangeListenerToToggleGroup(chl);
//		
//		ChangeListener<Boolean> clDoor = new ChangeListener<Boolean>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//				// 1. UPDATE MODEL
//				theModel.setProperty("Door",theView.getDoorIsSelected());
//				// 2. show the updated model
//				theView.update(theModel);
//			
//			}
//		};
//		theView.addChangeListenerToDoor(clDoor);
//	
//		// adding change listener for check box Windows
//		ChangeListener<Boolean> clWindows = new ChangeListener<Boolean>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//				boolean isSelected = theView.getWindowsIsSelected();
//				// 1. UPDATE MODEL
//				theModel.setProperty("Windows",isSelected);
//				if(isSelected) { // add windows to view
//					addWindowsToView();
//				}
//				else // remove windows from view
//				{
//					theView.removeWindows();
//				}
//				// 2. show the updated model
//				theView.update(theModel);
//			
//			}
//		};
//		theView.addChangeListenerToWindows(clWindows);
//
//		
//		// change Listener to cbRoof
//		ChangeListener<Boolean> clRoof = new ChangeListener<Boolean>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//				if(theView.getRoofIsSelected())
//					theView.addRoofColorsComboBox(); // 
//				else
//					theView.hideRoofColorsComboBox();
//				
//				// 1. UPDATE MODEL
//				theModel.setProperty("Roof",theView.getRoofIsSelected());
//				// 2. show the updated model
//				theView.update(theModel);
//			}
//		};
//		theView.addChangeListenerToRoof(clRoof);
//	
//		// add Change Listener to roof colors Combo box
//		ChangeListener<String> clRoofColors = new ChangeListener<String>() {
//
//			@Override
//			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
//				// what to do when the listener executes...
//				theModel.changeRoofColor(theView.getRoofColor());
//				theView.update(theModel);
//			}
//		};
//		theView.addChangeListenerToRoofColors(clRoofColors);
//	}
//	public void addWindowsToView() {
//		int numWindows = theModel.getNumWindows(); 
//		for(int i = 0;i<numWindows;i++) {
//			Rectangle r = theModel.getWindow(i); // get all data from model
//			Rectangle tmpWindow = new Rectangle(r.getX(), r.getY(),r.getWidth(), r.getHeight());
//			tmpWindow.setFill(r.getFill());
//			tmpWindow.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//				@Override
//				public void handle(MouseEvent event) {
//					// find the index of a window that is responsible of the event (click)
//						int index = theView.getWindowIndex(event.getTarget());
//					//theModel.changeWindowColor(index);
//						theView.changeWindowColor(index);
//				}
//			});
//			theView.addWindow(tmpWindow);
//
//		}
//	}
//}