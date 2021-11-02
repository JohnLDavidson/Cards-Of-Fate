package gamePackage.GUI;

/**
 * Used to inform GUI View of updates to board
 */
public class GameModel {
    /**
     * Newer Score of game
     */
    private int score;
    /**
     * If selected card is a Joker
     */
    private boolean jokerFound;
    /**
     * If selected card is inverted
     */
    private boolean inverted;
    /**
     * Details of selected Card
     */
    private String cardPicked;
    /**
     * Number of current remaining attempts
     */
    private int attempts;
    /**
     * Additional details for selected card
     */
    private String info;

    /**
     * Creates container model with updating details
     * @param score Current Game Score
     * @param jokerFound If Joker is found
     * @param inverted If Card is inverted
     * @param cardPicked Details of Card (Name & Suite)
     * @param attempts Remaining Game Attempts
     * @param info Additional text information
     */
    public GameModel(int score, boolean jokerFound, boolean inverted, String cardPicked, int attempts, String info){
        this.score = score;
        this.jokerFound = jokerFound;
        this.inverted = inverted;
        this.cardPicked = cardPicked;
        this.attempts = attempts;
        this.info = info;
    }

    /**
     * Getter for Score
     * @return Current Score
     */
    public int getScore(){return score;}

    /**
     * Getter for if Joker is found
     * @return If Joker is found
     */
    public boolean isJokerFound(){return jokerFound;}

    /**
     * Getter for if Card is inverted
     * @returnIf Card is inverted
     */
    public boolean isInverted(){return inverted;}

    /**
     * Getter for Card details
     * @return Card's details
     */
    public String getCardPicked(){return cardPicked;}

    /**
     * Getter for remaining Attempts
     * @return Remaining attempts
     */
    public int getAttempts(){return attempts;}

    /**
     * Getter for Additional Card information
     * @return Additional Text Info
     */
    public String getInfo(){ return info;}
}
