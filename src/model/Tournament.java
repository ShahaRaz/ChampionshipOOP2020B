package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Tournament {
	public enum gameType {
		Tennis, Basketball, Soccer
	};

	private gameType type;
	private Set teams;

	public Tournament() {
		teams = new Set();
	}

	public boolean addParticipant(Player partic) {
		return teams.add(partic);
	}

	public gameType getType() {
		return type;
	}

	public void secondRound() {
		Set teams4 = new Set();
		for (int i = 0; i < getNumOfPlayerInRound(); i++) {
			if (teams.get(i).getWinsCounter() == 1)
				teams4.add(teams.get(i));
		}
		setTeams(teams4);
		System.out.println("moving to the semi-finals ! - 4 teams are left");
	}

	public void thirdRound() {
		Set teams2 = new Set();
		for (int i = 0; i < getNumOfPlayerInRound(); i++) {
			if (teams.get(i).getWinsCounter() == 2)
				teams2.add(teams.get(i));
		}
		setTeams(teams2);
		System.out.println("moving to the finals ! - 2 teams are left");
	}

	public Player getWinner() {
		for (int i = 0; i < getNumOfPlayerInRound(); i++) {
			if (teams.get(i).getWinsCounter() == 3)
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

	public int getNumOfPlayerInRound() {
		return teams.size();
	}

	public void reset() {
		for (int i = 0; i < getNumOfPlayerInRound(); i++) {
			teams.reset();

		}
	}

	public int getNumberOfRounds(int gameState) {

		return 6; // TODO FIX ME
//		if(!isTieBreaker) {
//		switch(type) {
//		case Basketball:
//			return 4;
//			break;
//			
//		case Soccer:
//			return 2;
//			break;
//			
//		case Tennis:
//			return 5; //TODO  if 3-0 happens, we also stop
//			break;
//			
//		default: 
//			return -1;
//		 }
//		}
//		else { // it is a tie breaker
//			switch(type) {
//			case Basketball:
//				return 2;
//				break;
//			case Soccer: // penalty
//				return 5;
//				break;
//			case Tennis:
//				return 2;
//				break;
//			default: 
//				return -1;
//			 }	
//		}
	}

	public int setWinner(ArrayList<Integer> scoresPlayer1, ArrayList<Integer> scoresPlayer2) {
		// TODO - return [i] if player [i] wins [i=1/2] , return 0 if tie
		// WRITE ME
		return 0;
	}

	private boolean enterRoundScore(int gameType, int gameStage, int p1Score, int p2Score, int sum1, int sum2) {
		// gameStage will say which part (for example, basketBall have 4 quarters, then
		// 2 even-brakers,
		// so valid input for basketball games would be betweeen 1& 6 //
		// must use the entire logic in the model for each score
		// FIXME

		switch (type) {
		case Tennis:
			if (p1Score > 7 || p2Score > 7 || p1Score < 0 || p2Score < 0
					|| (Math.abs(p1Score - p2Score) < 2 && (p1Score + p2Score) < 13)
					|| (p1Score != 6 && p2Score != 6) && (p1Score + p2Score) < 12) {
				System.out.println("not valid scores , try again"); // fire NOT VALID SCORE
				return false;
			} else {
				if (p1Score > p2Score)
					sum1++;
				else
					sum2++;
				return true;
			}
		case Basketball:
			if (p1Score < 0 || p2Score < 0) {
				System.out.println("fire NOT VALID"); // OR THROWS
				return false;
			} else {
				sum1 += p1Score;
				sum2 += p2Score;
				return true;
			}
		case Soccer:
			if ((p1Score < 0 || p2Score < 0) && (gameType != 2)) {
				System.out.println("fire NOT VALID"); // OR THROWS
				return false;
			} else {
				if ((p1Score < 0 || p2Score < 0 || p2Score > 1 || p1Score > 1) && (gameType == 2)) {
					System.out.println("fire NOT VALID"); // OR THROWS
					return false;
				}
			}
			sum1 += p1Score;
			sum2 += p2Score;
			return true;
		}

		return true; // all
	}

	protected boolean checkBoard(int gameType, ArrayList<Integer> p1Score, ArrayList<Integer> p2Score,
			int gameId) {
		if (p1Score.size() != p2Score.size())
			return false; // fire popup
		int sum1 = 0, sum2 = 0;
		int totalRoundsPlayed = p1Score.size(); // for readability
		for (int i = 0; i < totalRoundsPlayed; i++) {
			if (!enterRoundScore(gameType, i + 1, p1Score.get(i), p2Score.get(i), sum1, sum2)) {
				printInstructions(gameType, i + 1);
				// Throw popup exception
				return false;
				// gametype 0 -> normal game 1-> extraTime 2-> pendelties
			}

		}
		if (sum1 > sum2) {
			// player 1 win logic
			teams.upWins(gameId);
		} else {
			if (sum2 > sum1) {
				// player 2 win logic
				teams.upWins(gameId + 1);
			} else {
				// draw
				System.out.println("scores are equal we still don't have a winner");
				
			}
		}
		return true;
	}

	private void printInstructions(int gameType, int gameStage) { /* need to change syso to fx messages*/
		// TODO print to view a short explanation of valid values
		// maybe add a red boxes on invalid rounds
		switch (type) {
		case Tennis:
			System.out.println(
					"set scores need to be between [0-6] while 6 is the goal in order"
					+ " to win \n with difference of 2 points at least,"
					+ " if both players win at least 5 games the winner must win 7 games ");
		case Soccer:
			if(gameType!=2) 
				System.out.println("scores must be higher than 0");			
			else 
				System.out.println("we are in deciding penalties phase , goal = 1 , miss = 0"
						+ " \n other values are not valid!");
			
		case Basketball:
			System.out.println("scores must be higher than 0");
		}

	}

}



