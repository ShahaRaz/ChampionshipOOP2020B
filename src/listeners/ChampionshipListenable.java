package listeners;

public interface ChampionshipListenable {

	void modelAddedName(String name); // change me if needed, just examples
	void modelUpdatePlayerAlreadyAdded(String name);
	void modelUpdateWinner();
	void modelUpdateNoRoom();

	
}
