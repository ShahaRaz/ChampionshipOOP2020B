package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Tournament {
	public enum gameType {
		Tennis, Basketball, Soccer
	};
	
	private  gameType type;
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
		Set teams4=new Set();
		for (int i = 0; i < getNumOfPlayerInRound(); i++) {
			if(teams.get(i).getWinsCounter()==1)
				teams4.add(teams.get(i));		
		}
		setTeams(teams4);
		System.out.println("moving to the semi-finals ! - 4 teams are left");
	}
	public void thirdRound() {
		Set teams2=new Set();
		for (int i = 0; i < getNumOfPlayerInRound(); i++) {
			if(teams.get(i).getWinsCounter()==2)
				teams2.add(teams.get(i));		
		}
		setTeams(teams2);
		System.out.println("moving to the finals ! - 2 teams are left");
	}
	public Player getWinner() {
		for (int i = 0; i < getNumOfPlayerInRound(); i++) {
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
	

	public int setWinner(ArrayList<Integer> scoresPlayer1, ArrayList<Integer> scoresPlayer2 ) {
		//TODO - return [i] if player [i] wins [i=1/2] , return 0 if tie
		// WRITE ME 
		return 0;
	}
	
	
	private boolean enterRoundScore(int gameType,int gameStage,
			int p1Score,int p2Score, int minResult, int maxResult) {
		// gameStage will say which part (for example, basketBall have 4 quarters, then 2 even-brakers, 
		// so valid input for basketball games would be betweeen 1& 6 //
		// must use the entire logic in the model for each score 
		// FIXME 
		
		if (minResult < 0)
			minResult = 0;
		if(maxResult>100)
			maxResult=100;
		
		switch(type) {
			case Tennis:
				if (p1Score > 7 || p2Score > 7 || p1Score < 0 || p2Score < 0
						|| (Math.abs(p1Score - p2Score) < 2 && (p1Score + p2Score) < 13)
						|| (p1Score != 6 && p2Score != 6) && (p1Score + p2Score) < 12) {
					System.out.println("not valid scores , try again"); // fire NOT VALID SCORE
					return false;
				}
				else 
					return true;
			
			case Basketball:
				if(p1Score<0||p2Score<0) {
					System.out.println("fire NOT VALID"); // OR THROWS 
					return false;
				}
				else 
					return true;
			case Soccer:
				if(p1Score<0||p2Score<0) {
					System.out.println("fire NOT VALID"); // OR THROWS 
					return false;
				}
				else 
					return true;		
		}
		
		
		return true; // all 
	}
	
	protected boolean checkBoard(int gameStage,ArrayList <Integer> p1Score,
			ArrayList <Integer> p2Score,int gameId) {
		if (p1Score.size()!=p2Score.size())
			return false; // fire popup 
		int totalRoundsPlayed = p1Score.size(); // for readability 
		for (int i = 0; i < totalRoundsPlayed; i++) {
			if(!enterRoundScore(gameStage, i+1, p1Score.get(i), p2Score.get(i), 1, 7)) {
				printInstructions(gameStage,i+1);
				// Throw popup exception
				return false;
				//gametype  0 -> normal game 1-> extraTime 2-> pendelties 
			}
			
		}
		//DEFINITIONS
		// gameNumber / gameId - Define game in current round ->
		// -> (8 players - 4 games -> gameNumber [0,3]first round, [4,5] second round [6] last round)
		// -> use this to know which players are playing with Model.getPlayersInGivenRound(int gameNumber) 
		
		// gameStage - [0 - regular], [1 - ExtraTime], [2 - Penalties] (in football),
		// in tennis [1=2=3=...n (till tieBreak)] // in basketball [0-3] reg, & [4,+] are extraTime
		
		// GAMESTAGE:
		//	Football: [0,1] are reg halfs , [2,3] are extra time halfs , [4-8] are penalties, 8+ are 1shot penalties..
		// 	Basketball: [0,1,2,3] are reg quarters , 4+ are extra times
		//	Tennis: [0-4] all regular rounds.
		
		
		return true;
		
	}
	
	private static void printInstructions(int gameType,int gameStage) {
		// TODO print to view a short explanation of valid values
		// maybe add a red boxes on invalid rounds 
		System.out.println("entered printInstructions");
		
		// 
	}
	
	
	



}

// TODO Each sport has its own rules, get this logic into class to make it
// accessable from View

