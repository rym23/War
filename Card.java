public class Card
{
	private int suit, card;

	public Card(int suit, int card)
	{
		this.suit = suit;
		this.card = card;
	}

	// Tells you which card it is to help determine winners
	public int getCard()
	{
		return card;
	}

	// Prints out what the card is
	public String toString()
	{
		String output = "";

		if (card > 1 && card < 11)
		{
			output += card + " of ";
		}
		else
		{
			if (card == 11)
			{
				output += "Jack of ";
			}
			else if (card == 12)
			{
				output += "Queen of ";
			}
			else if (card == 13)
			{
				output += "King of ";
			}
			else
			{
				output += "Ace of ";
			}
		}

		if (suit == 1)
		{
			output += "clubs";
		}
		else if (suit == 2)
		{
			output += "spades";
		}
		else if (suit == 3)
		{
			output += "diamonds";
		}
		else
		{
			output += "hearts";
		}

		return output;
	}
}