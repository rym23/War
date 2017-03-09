import java.util.ArrayList;
import java.util.Collections;

public class War
{
	public static void main (String[] args)
	{
		Deck player = new Deck();

		// PLAYER GETS 26 CARDS, COMPUTER GETS THE OTHER 26
		player.makeDeck();
		Deck computer = player.makeComputerDeck();
		int rounds = 1;
		boolean doubleWar = false;

		//  ENDS IF EITHER PLAYER REACHES 52 CARDS
		while (player.size() != 52 && computer.size() != 52)
		{
			//  LISTS OF EACH PLAYER'S WINNINGS FOR THE CURRENT ROUND
			ArrayList<Card> playerCardsWinnings = new ArrayList<Card>();
			ArrayList<Card> computerCardsWinnings = new ArrayList<Card>();

			//  SHUFFLE DECK TO PREVENT CONSTANT TIE
			player.shuffle();
			computer.shuffle();

			//  TRUE IF EITHER PLAYER HAS CARDS LEFT TO PLAY
			boolean cardsLeft = true;
			
			while (cardsLeft)
			{
				//  CURRENT FLIPPED CARD
				Card playerCard = player.deal();
				Card computerCard = computer.deal();

				System.out.println("Player: " + playerCard.toString() + "\t Computer: " + computerCard.toString());

				//  PLAYER'S CARD WINS, PLAYER TAKES BOTH
				if (playerCard.getCard() > computerCard.getCard())
				{
					playerCardsWinnings.add(playerCard);
					playerCardsWinnings.add(computerCard);
				}
				//  COMPUTER'S CARD WINS, COMPUTER TAKES BOTH
				else if (playerCard.getCard() < computerCard.getCard())
				{
					computerCardsWinnings.add(playerCard);
					computerCardsWinnings.add(computerCard);
				}
				//  WAR
				else
				{
					//  LISTS OF THE CARDS TAKEN IF EITHER PLAYER WINS THE WAR
					ArrayList<Card> playerWarCards = new ArrayList<Card>();
					ArrayList<Card> computerWarCards = new ArrayList<Card>();

					Card playerCardWar;
					Card computerCardWar;

					/*
					 *  Can't do war because there aren't enough cards.
					 *  Adds both of the cards back into the deck, as well as
					 *  adding all of the winnings for the current round
					 *  because otherwise it would infinitely try to do war
					 *  when it cannot.
					*/
					if (player.size() < 3 || computer.size() < 3)
					{
						player.addToDeck(playerCard);
						computer.addToDeck(computerCard);
						player.addToDeck(playerCardsWinnings);
						computer.addToDeck(computerCardsWinnings);
						System.out.println("Player: " + player.size() + "\t Computer: " + computer.size());
						cardsLeft = false;
						System.out.println("Rounds: " + rounds);
						System.out.println();
						rounds++;
					}
					else
					{

						do
						{
							/* There is a possibility of multiple wars, so
							 * if either player doesn't have enough cards
							 * then it gives all of their cards back and starts a new round
							 */
							if (player.size() < 2 || computer.size() < 2)
							{
								player.addToDeck(playerCard);
								player.addToDeck(computerCard);
								player.addToDeck(playerWarCards);
								computer.addToDeck(computerWarCards);
								player.addToDeck(playerCardsWinnings);
								computer.addToDeck(computerCardsWinnings);
								System.out.println("Player: " + player.size() + "\t Computer: " + computer.size());
								cardsLeft = false;
								System.out.println("Rounds: " + rounds);
								System.out.println();
								rounds++;
								break;
							}

							// FACE DOWN CARD
							playerWarCards.add(player.deal());
							computerWarCards.add(computer.deal());

							// FACE UP CARD
							playerCardWar = player.deal();
							computerCardWar = computer.deal();

							System.out.println();
							System.out.println("WAR:");
							System.out.println("\tPlayer: " + playerCardWar.toString());
							System.out.println("\tComputer: " + computerCardWar.toString());
							System.out.println();
							playerWarCards.add(playerCardWar);
							computerWarCards.add(computerCardWar);

							// Player won the war, so they take it all
							if (playerCardWar.getCard() > computerCardWar.getCard())
							{
								for (int i = 0; i < playerWarCards.size(); i++)
								{
									playerCardsWinnings.add(playerWarCards.get(i));
									playerCardsWinnings.add(computerWarCards.get(i));
								}
								playerCardsWinnings.add(playerCard);
								computerCardsWinnings.add(computerCard);
							}
							//  Computer won the war, so they take it all
							else if (playerCardWar.getCard() < computerCardWar.getCard())
							{
								for (int i = 0; i < computerWarCards.size(); i++)
								{
									computerCardsWinnings.add(playerWarCards.get(i));
									computerCardsWinnings.add(computerWarCards.get(i));
								}
								computerCardsWinnings.add(playerCard);
								computerCardsWinnings.add(computerCard);
							} 
						} while (playerCardWar.getCard() == computerCardWar.getCard());
					}
				}

				// If either player's deck is empty, the round is over
				if (player.isEmpty() || computer.isEmpty())
				{
					player.addToDeck(playerCardsWinnings);
					computer.addToDeck(computerCardsWinnings);
					System.out.println("Player: " + player.size() + "\t Computer: " + computer.size());
					cardsLeft = false;
					System.out.println("Rounds: " + rounds);
					System.out.println();
					rounds++;
				}
			}
		}

		// Determines which player won
		if (player.isEmpty())
		{
			System.out.println("Computer wins!");
		}
		else
		{
			System.out.println("Player wins!");
		}
	}
}
