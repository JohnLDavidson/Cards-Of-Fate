package gamePackage.Cards;

/**
 * Special Card used to change the Score of a Game
 */
public class ValueCard extends Card {
    /**
     * Value of Card
     */
    private int value;

    /**
     * Creates a ValueCard used for calculations in Game
     * @param name Card's Name
     * @param suite Card's Suite
     * @param inverted If Card is inverted
     * @param value Card's Value
     */
    public ValueCard(String name, String suite, boolean inverted, int value){
        super(name, suite, inverted);
        this.value = value;

    }

    /**
     * Getter for Card's integer value
     * @return Card's Value
     */
    public int getValue(){return value;}
}
