package gamePackage;

import gamePackage.Cards.ValueCard;

/**
 * Used as total for Game
 */
public class Score {
    /**
     * Integer for Game's total scoring
     */
    private int score;

    /**
     * Creates Score for Game use
     */
    public Score(){
        this.score = 0;
    }

    /**
     * Returns current Score of Game
     * @return Game's Score
     */
    public int getScore(){return score;}

    /**
     * Adds values of given card to the score
     * @param card ValueCard for adding value to Score
     */
    public void addValue(ValueCard card){
            if (card.isInverted()) {
                score -= card.getValue();
            } else {
                score += card.getValue();
            }
    }
}
