package model;
public class Player {
	private String name;
	private int winsCounter;
	
	public Player(String name) {
		this.name=name;
		winsCounter=0;
	}
	
	public void uppWins() {
		winsCounter++;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWinsCounter() {
		return winsCounter;
	}

	public boolean equals(Player other) {
		return other.getName().equalsIgnoreCase(this.getName());
	}

	@Override
	public String toString() {
		return "Player [name=" + name + " wins = "+winsCounter+"]";
	}
	
}
