package id209006535;

import model.*;
import java.util.Scanner;
import model.Tournament;

public class Program {
	final int NUMBER_OF_PLAYERS=8;
	
	public static void playChampionship(Tournament tour,Scanner scn) {
		System.out.println("The " + tour.getType().name() + " Championship has begun!");
		for (int i = 0; i < tour.howMany(); i += 2) {
			playGame(tour, tour.getTeams().get(i), tour.getTeams().get(i + 1),scn);
		}
		for (int i = 0; i < tour.howMany(); i++) {
			if (tour.getTeams().get(i).getWinsCounter() == 1)
				System.out.println(tour.getTeams().get(i));
			
		}
		tour.secondRound();
		for (int i = 0; i < tour.howMany(); i += 2) {
			playGame(tour, tour.getTeams().get(i), tour.getTeams().get(i + 1),scn);
		}
		for (int i = 0; i < tour.howMany(); i++) {
			if (tour.getTeams().get(i).getWinsCounter() == 2)
				System.out.println(tour.getTeams().get(i));
		}
		tour.thirdRound();
		playGame(tour, tour.getTeams().get(0), tour.getTeams().get(1),scn);
		System.out
				.println("The winning team of the " + tour.getType().name() + " Championship is : " + tour.getWinner());
		tour.reset();
	}

	public static void playGame(Tournament tour, Player p1, Player p2,Scanner scn) {
		if (tour.getType().name().equalsIgnoreCase("tennis")) {
			tennisGame(p1, p2,scn);
		} else if (tour.getType().name().equalsIgnoreCase("basketball")) {
			basketballGame(p1, p2,scn);
		} else {
			soccerGame(p1, p2,scn);
		}

	}

	public static void tennisGame(Player p1, Player p2,Scanner scanTennis) {

		int p1Sets = 0;
		int p2Sets = 0;
		int p1Score, p2Score;
		for (int i = 1; i <= 5; i++) {
			System.out.println("Enter the scores of the #" + i + " set for each player/team");
			do {
				p1Score = enterPlayerScore(p1,"scores","# " + i,"set",0,10,scanTennis);
				p2Score = enterPlayerScore(p2,"scores","# " + i,"set",0,10,scanTennis);

				if (p1Score > 7 || p2Score > 7 || p1Score < 0 || p2Score < 0
						|| (Math.abs(p1Score - p2Score) < 2 && (p1Score + p2Score) < 13)
						|| (p1Score != 6 && p2Score != 6) && (p1Score + p2Score) < 12)
					System.out.println("not valid scores , try again");
			} while (p1Score > 7 || p2Score > 7 || p1Score < 0 || p2Score < 0
					|| (Math.abs(p1Score - p2Score) < 2 && (p1Score + p2Score) < 13)
					|| (p1Score != 6 && p2Score != 6) && (p1Score + p2Score) < 12);
			if (p1Score > p2Score)
				p1Sets++;
			else
				p2Sets++;
			if (p1Sets == 3) {
				announceWinner(p1);
				i = 6; // break;
			} else if (p2Sets == 3) {
				announceWinner(p2);
				i = 6; // break;
			}
		}

	}

	public static void basketballGame(Player p1, Player p2,Scanner scanBasketball) {
		int sum1 = 0;
		int sum2 = 0;
		int score1, score2;

		for (int i = 1; i <= 4; i++) {
			do {
				score1 = enterPlayerScore(p1,"points","# " + i,"quarter",0,60,scanBasketball);
				score2 = enterPlayerScore(p2,"points","# " + i,"quarter",0,60,scanBasketball);
//				if (score1 < 0 || score2 < 0)
//					System.out.println("there is an issue with the scores you entered , try again");
			} while (score1 < 0 || score2 < 0);
			sum1 += score1;
			sum2 += score2;
		}
		if (sum1 > sum2) {
			announceWinner(p1);
		} else if (sum2 > sum1) {
			announceWinner(p2);
		} else {
			int extraTimeCounter = 0;
			System.out.println(p1.getName() + " and " + p2.getName()
					+ " scores are equal , we are going to extra time until scores differ!");
			while (sum1 == sum2) {
				do {
					extraTimeCounter++;
//					System.out.println("Please enter how many points " + p1.getName() + " scored in the #"
//							+ extraTimeCounter + " extra time ");
//					score1 = scanBasketball.nextInt();
					score1 = enterPlayerScore(p1,"points","# " + extraTimeCounter,"Extra time half",0,60,scanBasketball);
//					System.out.println("Please enter how many points " + p2.getName() + " scored in the #"
//							+ extraTimeCounter + " extra time");
//					score2 = scanBasketball.nextInt();
					score2 = enterPlayerScore(p2,"points","# " + extraTimeCounter,"Extra time half",0,60,scanBasketball);
//
//					if (score1 < 0 || score2 < 0)
//						System.out.println("there is an issue with the scores you entered , try again");
				} while (score1 < 0 || score2 < 0);
				sum1 += score1;
				sum2 += score2;
			}
			if (sum1 > sum2) {
				announceWinner(p1);
			} else {
				announceWinner(p2);
			}
		}
	}
	
	private static void announceWinner(Player winner) {
		System.out.println(winner.getName() + " is the Winner ");
		winner.uppWins();
	}
	
	private static int enterPlayerScore(Player p , String scoreName,String roundCount,String gameSequence,int minResult,int maxResult , Scanner scn) {
		if(minResult<0)
			minResult=0;
		System.out.println("Please enter how many " + scoreName + " " + p.getName() + " scored in the " + roundCount +" "+ gameSequence);
		String userInput="";
		int result=-1;
		boolean validResult=false;
		while(validResult==false) {
			userInput=scn.nextLine();
		try {
			result=Integer.parseInt(userInput);
		}catch (Exception e) {
			System.err.println("haha, no exception thrown");
		}
		if(result>=minResult && result<=maxResult) {
			validResult=true;
			break;
		}
		System.out.println("please insert valid result between: " + minResult + " -> " + maxResult);
	}
	return result;
	}

	public static void soccerGame(Player p1, Player p2,Scanner scanSoccer) {
		int sum1 = 0;
		int sum2 = 0;
		int score1, score2;

		for (int i = 1; i <= 2; i++) {
			do {
//				System.out.println("Please enter how many goals " + p1.getName() + " scored in the #" + i + " half");
//				score1 = scanSoccer.nextInt();
				score1 = enterPlayerScore(p1,"goals","# " + i,"half",0,10,scanSoccer);
//				System.out.println("Please enter how many goals " + p2.getName() + " scored in the #" + i + " half");
//				score2 = scanSoccer.nextInt();
				score2 = enterPlayerScore(p2,"goals","# " + i,"half",0,10,scanSoccer);

				if (score1 < 0 || score2 < 0)
					System.out.println("there is an issue with the scores you entered , try again!");
			} while (score1 < 0 || score2 < 0);
			sum1 += score1;
			sum2 += score2;
		}
		if (sum1 > sum2) {
			announceWinner(p1);
		} else if (sum2 > sum1) {
			announceWinner(p2);
		} else { // tie
			System.out.println(p1.getName() + " and " + p2.getName()
					+ " scores are equal , we are going to extra time , if scores stay equal, we'll move to penalty kicks ");
			do {
				score1 = enterPlayerScore(p1,"goals","","extra time",0,10,scanSoccer);
				score2 = enterPlayerScore(p2,"goals","","extra time",0,10,scanSoccer);

				if (score1 < 0 || score2 < 0)
					System.out.println("there is an issue with the scores you entered , try again");
			} while (score1 < 0 || score2 < 0);
			sum1 += score1;
			sum2 += score2;
			if (sum1 > sum2) {
				announceWinner(p1);
			} else if (sum2 > sum1) {
				announceWinner(p2);
			} else {
				System.out.println("Score is " + sum1 + "-" + sum2);
				System.out.println(" There's still a tie between " + p1.getName() + " and " + p2.getName());
				System.out.println("Moving to penalty shots , good luck to the goalkeepers!");
				sum1 = 0;
				sum2 = 0;
				String ans;
				for (int i = 0; i < 5; i++) {
					System.out.println("#" + (i+1)+ " shot for " + p1.getName()
							+ "\n is it goal ? (enter 'yes' for a goal and 'no' for a miss)");
					ans = scanSoccer.nextLine();
					if (i == 0)
						ans=scanSoccer.nextLine();
					if (ans.equalsIgnoreCase("yes"))
						sum1++;
					if ((sum2 + (5 - (i+1))) < sum1) {
						announceWinner(p1);
						i = 5;
					}
					else {				
					System.out.println("#" + (i+1)+ " shot for " + p2.getName()
							+ "\n is it goal ? (enter 'yes' for a goal and 'no' for a miss)");
					ans = scanSoccer.nextLine();

					if (ans.equalsIgnoreCase("yes"))
						sum2++;
					}
					if ((sum1 + (5 - (i+1))) < sum2) {
						announceWinner(p2);
						i = 5;
					}
					
				}
				if (p1.getWinsCounter() == p2.getWinsCounter() && sum1 > sum2) {
					announceWinner(p1);
				} else if (p1.getWinsCounter() == p2.getWinsCounter() & sum2 > sum1) {
					announceWinner(p2);
				} else if (p1.getWinsCounter() == p2.getWinsCounter()) {
					int extraKickCounter = 0;
					System.out.println(" penalty shots are tied ! \n extra 1 shot each time until score differs");
					while (sum1 == sum2) {
						extraKickCounter++;
						System.out.println("#" + extraKickCounter + "  extra penalty shot for " + p1.getName()
								+ "\n is it goal ? (enter 'yes' for a goal and 'no' for a miss)");
						ans = scanSoccer.nextLine();
						if (ans.equalsIgnoreCase("yes"))
							sum1++;
						System.out.println("#" + extraKickCounter + " extra penalty shot for " + p2.getName()
								+ "\n is it goal ? (enter 'yes' for a goal and 'no' for a miss)");
						ans = scanSoccer.nextLine();
						if (ans.equalsIgnoreCase("yes"))
							sum2++;
					}
					if (p1.getWinsCounter() == p2.getWinsCounter() && sum1 > sum2) {
						announceWinner(p1);
					} else if (p1.getWinsCounter() == p2.getWinsCounter()) {
						announceWinner(p2);
					}
				}

			}

		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Tournament tour = new Tournament();
		String gameChoice;
		boolean flag = false;
		String mainChoice;
		String gameMenu = "Menu:\n 1) Tennis (1)\n 2) Basketball (2)\n" + " 3) Soccer (3)\n";
		do {
			System.out.println(gameMenu);
			gameChoice = scan.nextLine();
			switch (gameChoice) {
			case "1":
				tour.setType("Tennis");
				flag = true;
				break;
			case "2":
				tour.setType("Basketball");
				flag = true;
				break;
			case "3":
				tour.setType("Soccer");
				flag = true;
				break;
			default:
				
				System.out.println("wrong input,try again");
			}

		} while (flag != true);
		System.out.println(tour.getType().name());
		String particName;
		Player partic;
		
		String mainMenu = "Menu:\n 1) Add participant\n 2) Start Championship\n " + "3) Exit\n 4) Auto add";
		flag=false;
		do {
			System.out.println(mainMenu);
			mainChoice = scan.nextLine();
			switch (mainChoice) {
			case "1":
				System.out.println("Enter the Team/Participant name");
				particName = scan.nextLine();
				partic = new Player(particName);
				tour.addParticipant(partic);
				break;
			case "2":
				if (tour.howMany() != 8)
					System.out.println("you must insert 8 teams/participants in order to start a championship!");
				else {
					System.out.println("You chose to start a Championship ! ");
					tour.showParticipants();
					playChampionship(tour,scan);
				}
				break;

			case "3":
				System.out.println("Bye thank you for playing ");
				flag=true;
				break;
			case "4":
				for (char alphabet = 'A'; alphabet <= 'H'; alphabet++) {
					tour.addParticipant(new Player(Character.toString(alphabet)));
				}
				break;
		
				
			default:
				System.out.println("wrong input,try again");
			}

		} while (flag != true);

		scan.close();
	}
	//check


}