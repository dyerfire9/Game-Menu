import java.awt.*;
import hsa.Console;
import javax.imageio.*;
import java.io.*;
import java.util.Random;

public class Project
{
    static Console c;
    static Random rand = new Random ();

    public static Image[] loadCardImages ()  // Loads card gif files into array
    {
	Image pictureDeck[] = new Image [13]; // Create array of 13 card images

	for (int x = 0 ; x < 13 ; x++)
	    pictureDeck [x] = loadImage ("cards/" + (x + 1) + ".gif"); // Loads appropriate image from cards2 folder

	return pictureDeck;
    }


    public static Image loadImage (String name)  //Loads image from file
    {
	Image img = null;
	try
	{
	    img = ImageIO.read (new File (name)); // Load file into Image object
	}
	catch (IOException e)
	{
	    System.out.println ("File not found");
	}

	return img;
    }


    public static int[] initDeck ()  // Initialize standard deck
    {
	int deck[] = new int [13];
	for (int x = 0 ; x < deck.length ; x++)
	    deck [x] = x; // Cards represented by numbers 0-13
	return deck;
    }


    public static void show (int deck[], Image imageDeck[])  // Display the deck
    {
	for (int x = 0 ; x < deck.length ; x++)
	    c.drawImage (imageDeck [deck [x]], 20 * x % 260 + 160, 50 * (x / 13) + 43, null); // % and / create four rows of cards for display
    }


    public static int getCard ()
    {
	return rand.nextInt (11) + 2;
    }


    public static char getChoice (char take, char stop)  //Forces the user to enter a one of two options
    {
	char enter;
	int val = 20;
	enter = c.getChar ();
	while (enter != take && enter != stop)
	{
	    c.drawString ("Take or stop  ONLY(t/s)", 15, 200 + val);
	    enter = c.getChar ();
	    val = val + 20;
	}
	if (enter == take)
	    return 't';
	else
	    return 's';
    }


    public static int cardVal (int card)  // Calculates the value of the card
    {
	if (card <= 10)
	    return card;
	else if (card >= 11 && card <= 13)
	    return 10;
	else
	    return 11;
    }


    public static int calc (int val1, int val2)  //Calculates the total value of the cards
    {
	int total;
	total = val1 + val2;

	if (total > 21)
	    return 0;
	else
	    return total;
    }


    public static int takeP (int total, int totalC, Image deck[])
    {
	int ntotal, tCard, Total;
	char card;
	if (total <= 21)
	{
	    tCard = getCard ();
	    c.drawString ("Player draws ", 35, 215);
	    c.drawImage (deck [tCard], 40, 220, null);

	    Total = total + cardVal (tCard);

	    if (Total > 21)
	    {
		c.drawString ("Computer WINS!", 25, 350);
		c.drawString ("Computer Stops", 510, 215);
		c.drawString ("Computer Points: " + totalC, 500, 230);
		c.drawString ("Player points: " + Total, 27, 365);
		return 0;
	    }
	    else
		return 1;
	}
	return 2;
    }


    public static void main (String[] args)
    {
	c = new Console ();

	int player1, player2, Computer1, Computer2;
	int np1, np2, nc1, nc2;
	int totalP, totalC;
	int pTake = 0;
	char pick, take = 't', stop = 's';
	c.setTextBackgroundColor (Color.black);
	c.setColor (new Color (0, 204, 0));
	// Creation of image deck and integer deck
	c.setFont (new Font ("Rockwell", Font.BOLD, 14));
	c.drawString ("Please wait while game loads...", 200, 200);
	Image imageDeck[] = loadCardImages ();
	int deck[] = initDeck ();

	c.clear ();
	show (deck, imageDeck); // Show the deck
	c.drawString ("Welcome to Balck Jack", 225, 15);
	c.drawString ("Made by Shameer, Abdul, Adshayan , Areeb", 1650, 33);
	c.drawString ("Player's Cards", 30, 75);
	c.drawString ("Computer's Cards", 500, 75);

	player1 = getCard ();
	player2 = getCard ();
	Computer1 = getCard ();
	Computer2 = getCard ();

	c.drawImage (imageDeck [player1], 30, 85, null);
	c.drawImage (imageDeck [player2], 55, 85, null);
	c.drawImage (imageDeck [Computer1], 515, 85, null);
	c.drawImage (imageDeck [Computer2], 540, 85, null);

	np1 = cardVal (player1);
	np2 = cardVal (player2);
	nc1 = cardVal (Computer1);
	nc2 = cardVal (Computer2);

	totalP = calc (player1, player2);
	totalC = calc (Computer1, Computer2);

	c.drawString ("Take or stop (t/s)", 27, 200);
	pick = getChoice (take, stop);

	while (pick != 's')
	{
	    pTake = takeP (totalP, totalC, imageDeck);
	    if (pTake != 0)
	    {
		c.drawString ("Take or stop (t/s)", 27, 330);
		pick = getChoice (take, stop);
	    }
	    else
	    {
		break;
	    }
	}

    }
}
