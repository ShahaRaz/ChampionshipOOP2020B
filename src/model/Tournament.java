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
	
	
	public int getNumberOfRounds(boolean isTieBreaker) {
		return 2; // TODO FIX ME
//		if(!isTieBreaker) {
//		switch(type) {
//		case Basketball:
//			return 4;
//			break;
//		case Soccer:
//			return 2;
//			break;
//		case Tennis:
//			return 5; //TODO  if 3-0 happens, we also stop
//			break;
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
	
	
	private static boolean enterRoundScore(String gameType,int gameStage,
			int p1inputedResult,int p2inputedResult, int minResult, int maxResult) {
		// gameStage will say which part (for example, basketBall have 4 quarters, then 2 even-brakers, 
		// so valid input for basketball games would be betweeen 1& 6 //
		// must use the entire logic in the model for each score 
		// FIXME 
		
		if (minResult < 0)
			minResult = 0;
		if(maxResult>100)
			maxResult=100;
//		if(inputedResult<minResult || inputedResult>maxResult)
//			return false; // needs to ask user again for proper input
		
		return true; // all 
	}
	
	protected static boolean checkBoard(String gameType,ArrayList <Integer> p1Score,ArrayList <Integer> p2Score) {
		if (p1Score.size()!=p2Score.size())
			return false;
		int totalRoundsPlayed = p1Score.size(); // for readability 
		for (int i = 0; i < totalRoundsPlayed; i++) {
			if(!enterRoundScore(gameType, i+1, p1Score.get(i), p2Score.get(i), 1, 10)) {
				printInstructions(gameType,i+1);
				return false;
				
			}
		}
		// update view that all ok 
		return true;
		
		
	}
	
	private static void printInstructions(String gameType,int gameStage) {
		// TODO print to view a short explenation of valid values
		// maybe add a red boxes on invalid rounds 
		System.out.println("entered printInstructions");
	}
	
	
	



}

// TODO Each sport has its own rules, get this logic into class to make it
// accessable from View

