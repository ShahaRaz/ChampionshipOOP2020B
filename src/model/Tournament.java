package model;

public class Tournament {
	public enum gameType {
		Tennis, Basketball, Soccer
	};

	private gameType type;
	private Set teams;

	public Tournament() {
		teams = new Set();
	}

	public void addParticipant(Player partic) {
		teams.add(partic);
	}

	public gameType getType() {
		return type;
	}

	public void secondRound() {
		Set teams4=new Set();
		for (int i = 0; i < howMany(); i++) {
			if(teams.get(i).getWinsCounter()==1)
				teams4.add(teams.get(i));		
		}
		setTeams(teams4);
		System.out.println("moving to the semi-finals ! - 4 teams are left");
	}
	public void thirdRound() {
		Set teams2=new Set();
		for (int i = 0; i < howMany(); i++) {
			if(teams.get(i).getWinsCounter()==2)
				teams2.add(teams.get(i));		
		}
		setTeams(teams2);
		System.out.println("moving to the finals ! - 2 teams are left");
	}
	public Player getWinner() {
		for (int i = 0; i < howMany(); i++) {
			if(teams.get(i).getWinsCounter()==3)
				return teams.get(i);
		}
		return null;
	}
	public Set getTeams() {
		return teams;
	}

	public void setTeams(Set teams) {
		this.teams = teams;
	}

	public void setType(String name) {
		this.type = gameType.valueOf(name);
	}

	public void showParticipants() {
		teams.showList();
	}
	public int howMany() {
		return teams.size();
	}
	public void reset() {
		for (int i = 0; i < howMany(); i++) {
			teams.reset();
			
		}
	}

}
