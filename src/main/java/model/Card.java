package model;

import application.GameBoard;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Card extends JButton {
    /*
     * Use this for the accessing the card images
     */
    private static final String cardImagesPath = "src/main/resources/card-images/";
    private static final String faceDownImageFilename = cardImagesPath + "B1.png";

    private ImageIcon faceDownIcon = null;
    private ImageIcon faceUpIcon = null;

    private int value;
    private boolean faceUp = false;
    private boolean matched = false;

    public Card(int value, GameBoard board) {
        this.value = value;
        addActionListener(board);
        setup(value);
    }

    private void setup(int value) {
        setHorizontalTextPosition(JButton.CENTER);
        setBorder(new LineBorder(Color.BLACK));
        setSize(new Dimension(72, 96));
        setPreferredSize(new Dimension(72, 96));

        // Set the image for the card face down
        if (faceDownIcon == null) {
            faceDownIcon = createImageIcon(faceDownImageFilename);
        }

        // Expecting a loop variable for this.value so +1
        String faceUpImageFilename = cardImagesPath + (value + 1) + ".png";
        this.faceUpIcon = createImageIcon(faceUpImageFilename);

        // Start with cards facing down
        setIcon(faceDownIcon);
    }

    public int getValue() {
        return this.value;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public boolean isMatched() {
        return this.matched;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    public ImageIcon createImageIcon(String filename) {
        ImageIcon icon = null;
        try {
            BufferedImage image = ImageIO.read(new File(filename));
            icon = new ImageIcon(image.getScaledInstance(72, 96, Image.SCALE_SMOOTH));
        } catch (IOException ex) {
            System.out.println("ERROR: Unable to get image from: " + filename);
            System.out.println(ex);
        } catch (IllegalArgumentException ex) {
            System.out.println("ERROR: Unable to get image from: " + filename);
        }

        return icon;
    }

    public void remove() {
        setFaceUp(false);
        setMatched(true);
        setIcon(null);
        setText(null);
        setBorder(null);
        setEnabled(false);
    }

    public void draw() {
        if (!this.matched) {
            // This card is still on the board

            if (this.faceUp) {
                if (this.faceUpIcon == null) {
                    setText("" + this.value);
                }
                setIcon(faceUpIcon);
            } else {
                setText("");
                setIcon(faceDownIcon);
            }
        }
    }

    public boolean isSame(Card otherCard) {
        if (this.faceUpIcon == null) {
            return this.getValue() == otherCard.getValue();
        }
        return (this.getValue() / 4) == (otherCard.getValue() / 4);
    }
}
