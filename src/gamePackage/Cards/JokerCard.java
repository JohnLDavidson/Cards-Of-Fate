package gamePackage.Cards;

/**
 * Special Card used to end a game prematurely
 */
public class JokerCard extends Card {
    /**
     * Card Name
     */
    private String name;
    /**
     * Card Suite
     */
    private String suite;

    /**
     * Makes a Joker Card Object
     * @param name Auto-defaults name to "Joker"
     * @param suite Auto-defaults suite to Null
     * @param inverted Determines if Joker Card is inverted or not
     */
    public JokerCard(String name, String suite, boolean inverted){
        super(name = "Joker", suite, inverted);
        this.name = "Joker";
        this.suite = null;
    }

    /**
     * Declares that Joker Card is indeed a Joker Card
     * @return True that card is a Joker
     */
    @Override
    public boolean isJokerCard(){return true;}
}
