package gamePackage;

import gamePackage.Cards.Card;
import gamePackage.Cards.ValueCard;
import gamePackage.GUI.GameModel;

/**
 * The Game itself; passes information between the Board and the
 * GUI View.
 */
public class Game {
    /**
     * Board of the Game
     */
    private Board board;
    /**
     * Score for the current game
     */
    private Score score;
    /**
     * Number of attempts remaining in Game
     */
    private int attempts;

    /**
     * Creates the Game Object
     * @param attempts Determines number of attempts Game should start with
     */
    public Game(int attempts){
        this.attempts = attempts;
        newGame();
    }

    /**
     * Restarts Board and Score of Game
     */
    private void retry(){
        this.board = new Board();
        this.score = new Score();
    }

    /**
     * Restarts Game
     */
    private void newGame(){
        retry();
    }

    /**
     * Given coordinates, chooses Card from Board and calculates
     * results from the Card; Converts information to model for
     * GUI View updating
     * @param x X-coordinate of board search
     * @param y Y-coordinate of board search
     * @return Model of updated board for View GUI
     */
    public GameModel choose(int x, int y){
        attempts -= 1;

        boolean jokerFound = false;
        boolean inverted = false;

        Card card = board.popCard(x,y);
        String cardPicked;
        String info = "Found ";

        if(card.isInverted()){
            inverted = true;
            info = info+"Inverted ";
        }
        if(card.isJokerCard()){
            jokerFound = true;
            info = info+"Joker";
            cardPicked = card.getName();
        }
        else{
            ValueCard value = (ValueCard)card;
            cardPicked = card.getName()+card.getSuite();
            info = info+card.getName()+" of "+card.getSuite();
            score.addValue(value);
        }
        return new GameModel(score.getScore(), jokerFound, inverted, cardPicked, attempts, info);
    }

    /**
     * Locates details of Card on Board without removing it
     * @param x X-coordinate of board search
     * @param y Y-coordinate of board search
     * @return String[] with details of located Card
     */
    public String[] locate(int x, int y){
        Card card = board.locate(x,y);
        String[] response = new String[2];
        if(card.isJokerCard()){
            response[0] = "Joker";
        }
        else{
            response[0] = card.getName()+card.getSuite();
        }
        if(card.isInverted()){
            response[1] = "Inverted";
        }
        else{
            response[1] = null;
        }
        return response;
    }
}
