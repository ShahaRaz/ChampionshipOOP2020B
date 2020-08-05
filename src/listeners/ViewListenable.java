package listeners;

public interface ViewListenable {
	 // change me if needed, just example

	void viewAskToPlayGame(String players);
	
	void viewAddedPlayer(String name);
	
	void viewStartChamp(); // maybe make it boolean
	
	void viewSetGamesType(); // Tennis/Basketball/soccer

	
}


//void viewUpdatedSetNameToSeatIsPressed(int index, String name);
//String viewAsksForActiveSeatName(int index);
//boolean viewAsksIfSeatIsFree(int index);

