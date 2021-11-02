package gamePackage;

import gamePackage.Cards.Card;
import gamePackage.Cards.CardHolder;
import java.util.Iterator;

/**
 * Used to hold Cards for Game
 */
public class Board {
    /**
     * Actual board of game
     */
    private Card[][] board;
    /**
     * CardHolder for Card requests
     */
    private CardHolder holder;

    /**
     * Creates Board Object to be prepped with Cards for Game
     */
    public Board(){
        this.board = new Card[5][5];
        this.holder = new CardHolder();
        setCards();
    }

    /**
     * Distributes cards from CardHolder onto itself
     */
    private void setCards(){
        Iterator<Card> cards = holder.passOutCards();
        for(int x = 0; x<5; x++){
            for(int y = 0; y<5; y++){
                Card card = cards.next();
                board[x][y] = card;
            }
        }
    }

    /**
     * Given coordinates, locates and removes card from self
     * @param x X-coordinate of Board search
     * @param y Y-coordinate of Board search
     * @return Card located on Board; Null if not found
     */
    public Card popCard(int x, int y){
        if(!(x>5||x<0||y>5||y<0)) {
            Card card = board[x][y];
            board[x][y] = null;
            return card;
        }
        return null;
    }

    /**
     * Given coordinates, locates card from self without removing it
     * @param x X-coordinate of Board search
     * @param y Y-coordinate of Board search
     * @return Card located on Board; Null if not found
     */
    public Card locate(int x, int y){
        if(!(x>5||x<0||y>5||y<0)) {
            return board[x][y];
        }
        return null;
    }
}
