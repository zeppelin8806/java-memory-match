package service.model;

public class ReturnCards {
    private Boolean success;
    private String deckId;
    private Boolean shuffled;
    private Integer remaining;
    private Piles piles;

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

    public Piles getPiles() {
        return piles;
    }

    public void setPiles(Piles piles) {
        this.piles = piles;
    }
}
