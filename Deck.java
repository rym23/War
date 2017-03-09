import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
	private final int DECK_COUNT = 52;
	private ArrayList<Card> deck = new ArrayList<Card>(DECK_COUNT);

	// Populates an arraylist with a full deck of cards
	public void makeDeck()
	{
		for (int i = 1; i < 5; i++)
		{
			for (int j = 2; j < 15; j++)
			{
				deck.add(new Card(i,j));
			}
		}
	}

	// Shuffles the list
	public void shuffle()
	{
		Collections.shuffle(deck);
	}

	// Splits the deck into two, giving half and returning it
	public Deck splitDeck()
	{
		Deck computer = new Deck();

      	for (int i = 0; i < (DECK_COUNT/2); i++)
      	{
        	 computer.addToDeck(deck.remove(0));
      	}

		return computer;
	}

	// Size of the deck
	public int size()
	{
		return deck.size();
	}

	public boolean isEmpty()
	{
		return deck.size() == 0;
	}

	// Removes a card and returns it
	public Card deal()
	{
		Card card = deck.remove(0);
		return card;
	}

	// Adds a single card to the deck
	public void addToDeck(Card c)
	{
		deck.add(c);
	}

	// Adds many cards to the deck
	public void addToDeck(ArrayList<Card> cards)
	{
		for (int i = 0; i < cards.size(); i++)
		{
			deck.add(cards.get(i));
		}
	}
}