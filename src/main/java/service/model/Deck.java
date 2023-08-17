package service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Deck {

    private boolean success;

    @JsonProperty("deck_id")
    private String deckId;

    private boolean shuffled;
    private int remaining;

    public Deck(){}

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public Boolean getShuffled() {
        return shuffled;
    }

    public void setShuffled(Boolean shuffled) {
        this.shuffled = shuffled;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    @Override
    public String toString(){
        String str = "Card Deck\n" +
                     "---------\n" +
                     "success ...........: " + this.success + "\n" +
                     "deck id ...........: " + this.deckId + "\n" +
                     "shuffled ..........: " + this.shuffled + "\n" +
                     "cards remaining ...: " + this.remaining + "\n";
        return str;
    }
}