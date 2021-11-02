package gamePackage.Cards;

/**
 * Parent class for basic Card Details during game;
 */
public abstract class Card {
    /**
     * Name of Card Value
     */
    private String name;
    /**
     * Suite of Card
     */
    private String suite;
    /**
     * Determines if card is inverted or not on board
     */
    private boolean inverted;

    /**
     * Basic requirements for a Card
     * @param name Name of Card value
     * @param suite Suite of Card
     * @param inverted If card is inverted
     */
    public Card(String name, String suite, boolean inverted){
        this.name = name;
        this.suite = suite;
        this.inverted = inverted;
    }

    /**
     * Getter for Name
     * @return Card's name
     */
    public String getName(){return name;}

    /**
     * Getter for Suite
     * @return Card's Suite
     */
    public String getSuite(){return suite;}

    /**
     * Getter for card inversion status
     * @return If card is inverted
     */
    public boolean isInverted(){return inverted;}

    /**
     * Getter for if card is a Joker or not
     * @return If card is Joker
     */
    public boolean isJokerCard(){return false;}
}
