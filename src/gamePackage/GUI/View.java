/**
 * Developed by: John Lee Davidson
 * Developed: August 26, 2021
 * Total lines of Code: 870
 */

package gamePackage.GUI;

import gamePackage.Game;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.util.*;

/**
 * GUI for Game play
 */
public class View extends Application {
    /**
     * Current Game in progress
     */
    private Game game;
    /**
     * Used to Start a New Game
     */
    private Button retry;
    /**
     * Scene for GUI
     */
    private Scene scene;
    /**
     * Grid for Button display
     */
    private GridPane gridBoard;
    /**
     * List of current existing Buttons
     */
    private ArrayList<Button> buttons;
    /**
     * List of Buttons pressed during Game
     */
    private ArrayList<Button> buttonsPressed;
    /**
     * HashMap of Buttons in Game with their resepctive coordinates
     */
    private HashMap<Button, int[]> buttonsNotPressed;
    /**
     * Display format of GUI
     */
    private GridPane display;
    /**
     * Score of GUI
     */
    private Text score;
    /**
     * Number of remaining Attempts in GUI
     */
    private Text attempts;
    /**
     * Text of Game events
     */
    private Text info;
    /**
     * Text for End results of Game
     */
    private Text endResult;
    /**
     * HashMap of Images for Cards
     */
    private HashMap<String, Image> pictures =  new HashMap<>(){{
        List<String> suites = Arrays.asList("Spades", "Clubs", "Diamonds", "Hearts");
        List<String> names = Arrays.asList("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King");
        for(int suite = 0; suite<4; suite++){
            for(int value = 0; value<13; value++){
                String key = names.get(value)+suites.get(suite);
                String path = "CardImages/"+key+".png";
                Image image = new Image(getClass().getResource(path).toExternalForm());
                put(key, image);
            }
        }
        Image image2 = new Image(getClass().getResource("CardImages/Card.png").toExternalForm());
        put("Card", image2);
        Image image3 = new Image(getClass().getResource("CardImages/Joker.png").toExternalForm());
        put("Joker", image3);
    }};

    /**
     * First Step of Game; requests User for # of attempts;
     * begins Initialization-2 afterwards
     * @param stage Stage of GUI
     */
    private void init(Stage stage){
        GridPane selection = new GridPane();
        selection.setVgap(20);
        selection.setHgap(20);
        selection.add(new Text("How many attempts would you like?"), 1, 0);

        Button choice1 = new Button("5");
        choice1.setMinSize(50,50);
        GridPane.setHalignment(choice1,HPos.CENTER);
        choice1.setOnAction((event)->{
            stage.hide();
            game = new Game(5);
            attempts = new Text("Attempts Remaining: 5");
            init2(stage);
        });
        Button choice2 = new Button("10");
        choice2.setMinSize(50,50);
        GridPane.setHalignment(choice2,HPos.CENTER);
        choice2.setOnAction((event)->{
            stage.hide();
            game = new Game(10);
            attempts = new Text("Attempts Remaining: 10");
            init2(stage);
        });
        Button choice3 = new Button("15");
        choice3.setMinSize(50,50);
        GridPane.setHalignment(choice3,HPos.CENTER);
        choice3.setOnAction((event)->{
            stage.hide();
            game = new Game(15);
            attempts = new Text("Attempts Remaining: 15");
            init2(stage);
        });
        selection.add(choice1, 0, 1);
        selection.add(choice2, 1, 1);
        selection.add(choice3, 2, 1);
        selection.setAlignment(Pos.CENTER);
        Scene selectionScreen = new Scene(selection);
        stage.setScene(selectionScreen);
        stage.setHeight(200);
        stage.setWidth(400);
        stage.show();

    }

    /**
     * Second step of Game; Using game details and User Input,
     * set up and display GUI for Game play
     * @param stage Stage of GUI
     */
    private void init2(Stage stage){
        buttons = new ArrayList<>();
        buttonsPressed = new ArrayList<>();
        buttonsNotPressed = new HashMap<>();

        score = new Text("Score: 0");
        GridPane.setHalignment(attempts,HPos.CENTER);
        info = new Text("");
        GridPane.setHalignment(info, HPos.CENTER);
        endResult = new Text("");
        GridPane.setHalignment(endResult, HPos.CENTER);

        gridBoard = new GridPane();
        gridBoard.setAlignment(Pos.CENTER);
        gridBoard.setHgap(20);
        gridBoard.setVgap(20);

        display = new GridPane();
        display.setAlignment(Pos.CENTER);
        display.setHgap(20);
        display.setVgap(20);

        Text Player1 = new Text("Host");
        GridPane.setHalignment(Player1, HPos.CENTER);
        Text Player2 = new Text("Challenger");
        GridPane.setHalignment(Player2, HPos.CENTER);

        for (int width = 0; width<5; width++){
            for(int length = 0; length<5; length++){
                int x = width;
                int y = length;
                Button cardButton = new Button();
                ImageView cardImage = new ImageView(pictures.get("Card"));
                cardImage.setFitWidth(60);
                cardImage.setFitHeight(60);
                cardButton.setGraphic(cardImage);
                cardButton.setOnAction((event)->{
                    cardButton.setOnAction((event2)->{});
                    buttonsPressed.add(cardButton);
                    GameModel model = game.choose(x,y);
                    attempts.setText("Attempts Remaining: "+model.getAttempts());
                    score.setText("Score: "+model.getScore());
                    info.setText(model.getInfo());
                    String key = model.getCardPicked();
                    ImageView reveal = new ImageView(pictures.get(key));
                    reveal.setFitWidth(60);
                    reveal.setFitHeight(60);
                    if(model.isInverted()){
                        reveal.setRotate(reveal.getRotate()+180);
                    }
                    cardButton.setGraphic(reveal);

                    if(model.getAttempts()==0){
                        end(1, model.getScore());
                    }
                    if(model.isJokerFound()){
                        if(model.isInverted()){
                            end(3,model.getScore());
                        }
                        else{
                            end(2, model.getScore());
                        }
                    }

                } );
                int[] coor = {x,y};
                buttonsNotPressed.put(cardButton, coor);
                buttons.add(cardButton);
                gridBoard.add(cardButton, width, length);
            }
        }
        retry = new Button("Retry");
        retry.setVisible(false);
        retry.setOnAction((event)->{
            stage.hide();
            init(stage);
        });

        display.add(attempts,0,0);
        display.add(Player1, 0, 1);
        display.add(gridBoard, 0, 2);
        display.add(Player2, 0, 3);
        display.add(info, 0, 4);
        display.add(endResult,0,5);
        display.add(score, 1, 2);
        display.add(retry, 1, 3);

        scene = new Scene(display);
        stage.setHeight(700);
        stage.setWidth(600);
        stage.setScene(scene);

        stage.show();
    }

    /**
     * Ends current Game and displays results of Game for User viewing
     * @param event Scenario of how game ended
     * @param score Score for Final Score
     */
    public void end(int event, int score){
        this.score.setText("Final Score: "+score);
        for(Button button:buttons){
            if(!buttonsPressed.contains(button)){
                int[] coor = buttonsNotPressed.get(button);
                int x = coor[0];
                int y = coor[1];
                String[] key = game.locate(x,y);
                ImageView image = new ImageView(pictures.get(key[0]));
                image.setFitWidth(60);
                image.setFitHeight(60);
                if(!(key[1]==null)){
                    image.setRotate(image.getRotate()+180);
                }
                button.setGraphic(image);
                button.setStyle("-fx-background-color:#6619FF");
                button.setDisable(true);
            }
        }
        if(event==1){
            if(score>0){
                endResult.setText("The Challenger Wins");
                gridBoard.setStyle("-fx-background-color:#B3D9FF");
            }
            else if(score<0){
                endResult.setText("The Host Wins");
                gridBoard.setStyle("-fx-background-color:#FF8095");
            }
            else{
                endResult.setText("The game has ended in a Draw");
                gridBoard.setStyle("-fx-background-color:#D9B3FF");
            }
        }
        if(event==2){
            endResult.setText("The Challenger Wins");
            gridBoard.setStyle("-fx-background-color:#B3D9FF");
        }
        if(event==3){
            endResult.setText("The Host Wins");
            gridBoard.setStyle("-fx-background-color:#FF8095");
        }
        retry.setVisible(true);
    }

    /**
     * Default start method for GUI;
     * Details initial Stage properties before beginning
     * Instruction display
     * @param stage
     */
    @Override
    public void start(Stage stage){
        stage.setOnCloseRequest((event)->{
            System.exit(0);
        });
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Cards of Fate");
        begin(stage);
    }

    /**
     * Displays Game rules and details;
     * displays Start button for when Users want to start
     * Initialization-1
     * @param stage
     */
    public void begin(Stage stage){
        Button start = new Button("start");
        start.setOnAction((event)->{
            stage.hide();
            init(stage);
        });
        GridPane instructions = new GridPane();
        instructions.setAlignment(Pos.CENTER);
        instructions.setHgap(20);
        instructions.setVgap(20);

        ImageView image1 = new ImageView(pictures.get("Card"));
        image1.setFitWidth(60);
        image1.setFitHeight(60);
        ImageView image2 = new ImageView(pictures.get("2Spades"));
        image2.setFitWidth(60);
        image2.setFitHeight(60);
        ImageView image3 = new ImageView(pictures.get("8Clubs"));
        image3.setFitWidth(60);
        image3.setFitHeight(60);
        image3.setRotate(image3.getRotate()+180);
        ImageView image4 = new ImageView(pictures.get("5Diamonds"));
        image4.setFitWidth(60);
        image4.setFitHeight(60);
        ImageView image5 = new ImageView(pictures.get("QueenHearts"));
        image5.setFitWidth(60);
        image5.setFitHeight(60);
        image5.setRotate(image4.getRotate()+180);
        ImageView image6 = new ImageView(pictures.get("Joker"));
        image6.setFitWidth(60);
        image6.setFitHeight(60);
        ImageView image7 = new ImageView(pictures.get("Joker"));
        image7.setFitWidth(60);
        image7.setFitHeight(60);
        image7.setRotate(image7.getRotate()+180);

        Text text1 = new Text("Welcome to Cards of Fate." +
                "\nThis is a 2-player card adding game with very simple rules." +
                "\nThere will be a board of 5x5 Cards and you will take turns picking" +
                "\ncards. The value of the cards will add to the score. The objective" +
                "\nfor Player 1 (Challenger) is to get a positive final value at the end." +
                "\nWhile vice versa, Player 2 (Host) will want a negative final value.");
        Text text2 = new Text("The board will be filled with un-flipped cards" +
                "\nsuch as this.");
        Text text3 = new Text("Black cards will ADD their value to the score.");
        Text text4 = new Text("However, there is a catch; inverted black cards will" +
                "\nSUBTRACT their value from the score.");
        Text text5 = new Text("Red cards will SUBTRACT their value from the score.");
        Text text6 = new Text("However, as with the black cards, inverted red cards" +
                "\nwill ADD their value to the score instead.");
        Text text7 = new Text("There is one special card that can immediately end the game" +
                "\nregardless of score: the JOKER." +
                "\nRevealing a JOKER automatically wins the game for the Challenger.");
        Text text8 = new Text("However, if an inverted JOKER is revealed, then the Host" +
                "\nautomatically wins.");
        Text text9 = new Text("A final Score of 0 ends in a DRAW." +
                "\nThere is 1 of every card and 2 Jokers in deck." +
                "\nTry playing solo too and test your luck.");

        instructions.add(text1,0,0);
        instructions.add(text2,0,1);
        instructions.add(text3,0,2);
        instructions.add(text4,0,3);
        instructions.add(text5,0,4);
        instructions.add(text6,0,5);
        instructions.add(text7,0,6);
        instructions.add(text8,0,7);
        instructions.add(text9,0,8);
        instructions.add(image1,1,1);
        instructions.add(image2,1,2);
        instructions.add(image3,1,3);
        instructions.add(image4,1,4);
        instructions.add(image5,1,5);
        instructions.add(image6,1,6);
        instructions.add(image7,1,7);
        instructions.add(start,1,8);
        Scene scene = new Scene(instructions);
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(800);
        stage.show();
    }
}
