package gamePackage.Cards;

import java.util.*;

/**
 * Creates, holds, and distributes necessary cards for Game onto the board
 */
public class CardHolder {
    /**
     * List for all existing Cards
     */
    private ArrayList<Card> cardList;

    /**
     * Creates and randomizes all cards and holds them for board distribution
     */
    public CardHolder(){
        this.cardList = new ArrayList<Card>();
        List<String> suites = Arrays.asList("Spades", "Clubs", "Diamonds", "Hearts");
        List<String> names = Arrays.asList("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King");
        Random random= new Random();
        for(int suite = 0; suite<4; suite++){
            for(int value = 1; value<14; value++){
                int value2 = value;
                if(suite>1){
                    value2 = -(value2);
                }
                cardList.add(new ValueCard(names.get(value-1), suites.get(suite), random.nextBoolean(), value2));
            }
        }
        cardList.add(new JokerCard("Joker", null, random.nextBoolean()));
        cardList.add(new JokerCard("Joker", null, random.nextBoolean()));
        Collections.shuffle(cardList);
    }

    /**
     * Returns list of 25 Cards needed for Game to the Board
     * @return
     */
    public Iterator<Card> passOutCards(){
        return cardList.subList(0, 25).iterator();
    }
}
