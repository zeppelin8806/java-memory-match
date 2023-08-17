package service.model;

import java.util.List;

public class DrawnCards {
    private Boolean success;
    private String deckId;
    private List<DrawnCard> cards;
    private Integer remaining;

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

    public List<DrawnCard> getCards() {
        return cards;
    }

    public void setCards(List<DrawnCard> cards) {
        this.cards = cards;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }
}
