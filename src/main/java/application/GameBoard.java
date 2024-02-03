package application;

import model.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/*
 * There are 52 cards in a normal-sized deck of cards (not counting
 * jokers). There are 4 card suits, each with the numbers 2 to 10 and
 * the Jack, Queen, King, and Ace for a total of 13.
 *
 * Go back and modify the code to have a total of 52 cards and 4 copies
 * of each card, meaning x4 2s, x4 3s, x4 Jacks, ... one of each suit.
 * You can use Jacks=11, Queens=12, Kings=12, Aces=13
 *
 * EXTRA: You can use real card faces images instead of numbers by using
 * the images in the CardImages folder and the setFaceUpIcon() method.
 */
public class GameBoard extends JFrame implements ActionListener {

    static final int TOTAL_CARDS = 52;
    static Card firstSelectedCard = null;
    static Card secondSelectedCard = null;

    /*
     * TODO: 1. Declare an ArrayList of Card objects
     */
    ArrayList<Card> cards;

    public void setup() {

        /*
         * TODO: 2. Initialize the ArrayList of Cards to a new ArrayList
         */
        cards = new ArrayList<>();

        /*
         * TODO: 3. Create TOTAL_CARDS number of Card objects and add
         *  them to the ArrayList. To make a Card object 2 arguments need
         *  to be passed into the Card constructor:
         *      - first argument: the loop index (0-51)
         *      - second argument: the keyword 'this', which is
         *                         this instance of the GameBoard
         *  example: Card newCard = new Card(0, this);
         */
        for (int i = 0; i < TOTAL_CARDS; i++) {
            Card newCard = new Card(i, this);
            cards.add(newCard);
        }

        /*
         * TODO: 4. Shuffle the order of the cards in the ArrayList
         */
        Collections.shuffle(cards);
    }

    /*
     * TODO: 5. Call the draw method on all the cards in the ArrayList
     */
    public void drawCards() {
        for( Card eachCard : cards ) {
            eachCard.draw();
        }
    }

    /*
     * TODO: 6. Checking if 2 selected cards match
     *  - When the user selects the first card to turn over,
     *    the variable "firstSelectedCard" will NOT be null.
     *  - When the user selects the second card to turn over,
     *    the variable "secondSelectedCard" will NOT be null.
     *  - Both cards are the same if the following is true:
     *      firstSelectedCard.isSame(secondSelectedCard)
     *  - If there is match, call the .remove() method on the
     *    first and second cards to remove them from the board.
     *  - If the cards are NOT the same, call .setFaceUp(false)
     *    on the first and second cards to put them face down again.
     *  - Regardless if the 2 selected cards matched, set both
     *    selected cards to "null" afterwards so a new pair of
     *    cards can be selected by the user.
     */
    public void checkCards() {

        if( firstSelectedCard != null && secondSelectedCard != null ) {
            // Two cards are selected

            if( firstSelectedCard.isSame(secondSelectedCard) ) {
                // Match

                // Remove cards from game
                firstSelectedCard.remove();
                secondSelectedCard.remove();
            } else {
                // No match

                // Keep cards in game and put them both face down
                firstSelectedCard.setFaceUp(false);
                secondSelectedCard.setFaceUp(false);
            }

            // Reset selected cards
            firstSelectedCard = null;
            secondSelectedCard = null;
        }
    }

    /*
     * TODO: 7. return true only if every card in the ArrayList
     *  bas been matched with its pair. Each Card object has a
     *  .isMatched() method that returns "true" if it has been
     *   matched and returns false if it has not been matched.
     */
    private boolean allCardsMatched() {

        for(Card eachCard : cards ) {
            if( !eachCard.isMatched() ) {
                return false;
            }
        }

        return true;
    }


























    /*
     * Daniel's super secret Java code
     * You have been warned
     */
    JPanel panel;
    JLabel timeLabel;
    JButton newGameButton;

    Timer updateTimer;
    Timer gameClock;
    int seconds;

    public void run() {
        gameClock = new Timer(1000, this);
        updateTimer = new Timer(750, this);
        setupGui(cards);
    }

    private void setupGui(ArrayList<Card> cards) {
        setTitle("Memory Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setPreferredSize(new Dimension(1090, 500));

        panel = new JPanel();
        add(panel);

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        timeLabel = new JLabel("current time: " + (seconds / 60) + ":" + (seconds % 60));

        for( Card eachCard : cards ) {
            panel.add(eachCard);
        }

        panel.add(newGameButton);
        panel.add(timeLabel);

        pack();
    }

    private void startGame() {
        this.seconds = 0;
        gameClock.start();
        updateTimer.start();
    }

    private void endGame(boolean gameWon) {
        gameClock.stop();
        updateTimer.stop();

        if(gameWon) {
            // Matched all the cards

            int response = JOptionPane.showConfirmDialog(null, "You win !! "
                            + "\nPlaying time " + (seconds / 60) + ":" + (seconds % 60)
                            + "\nAgain ?", "You win",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                getContentPane().removeAll();
                this.setup();
                this.run();
                this.startGame();
            } else if (response == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        } else {
            // New game button pressed

            getContentPane().removeAll();
            this.setup();
            this.run();
            this.startGame();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if( e.getSource() == this.updateTimer ) {

            checkCards();
            drawCards();

            if( allCardsMatched() ) {
                endGame(true);
            }

        } else if( e.getSource() == this.gameClock ) {

            this.seconds++;
            timeLabel.setText("current time: " + (seconds / 60) + ":" + (seconds % 60));

        } else if( e.getSource() == this.newGameButton ){

            endGame(false);

        } else {
            // Card selected

            Card newCard = (Card)e.getSource();

            if( firstSelectedCard == null ) {
                // First of 2 cards selected

                firstSelectedCard = newCard;
                firstSelectedCard.setFaceUp(true);
                drawCards();
                updateTimer.stop();
            } else if( secondSelectedCard == null && newCard != firstSelectedCard ) {
                // Second of 2 cards selected

                secondSelectedCard = newCard;
                secondSelectedCard.setFaceUp(true);
                drawCards();
                updateTimer.restart();
            }
        }
    }
}
