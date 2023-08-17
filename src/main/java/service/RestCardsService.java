package service;

import org.springframework.web.client.RestTemplate;
import service.model.DrawnCard;
import service.model.Deck;
import service.model.DrawnCards;
import service.model.ReturnCards;

import java.util.Arrays;
import java.util.List;

public class RestCardsService implements CardsService {

    private final String API_BASE_URL = "https://deckofcardsapi.com/api/deck";
    private final String API_NEW_SHUFFLED_DECK_URL = API_BASE_URL + "/new/shuffle/?deck_count=1";

    private final RestTemplate template = new RestTemplate();
    private Deck deck;

    public RestCardsService(String deckId){
        this.deck = new Deck();
        this.deck.setDeckId(deckId);
        this.reshuffle();
    }

    public RestCardsService(){
        this.deck = this.template.getForObject(API_NEW_SHUFFLED_DECK_URL, Deck.class);
        System.out.println(this.deck);
    }

    public String getDeck(){
        return this.deck.toString();
    }

    @Override
    public void reshuffle() {

        this.returnCardsToDeck();

        final String url = API_BASE_URL + "/" + this.deck.getDeckId() + "/shuffle";
        this.deck = this.template.getForObject(url, Deck.class);
    }

    @Override
    public List<DrawnCard> drawCards() {

        final String url = API_BASE_URL + "/" + this.deck.getDeckId() + "/draw/?count=52";
        DrawnCards drawnCards = this.template.getForObject(url, DrawnCards.class);

        return drawnCards.getCards();
    }

    private boolean returnCardsToDeck(){

        final String url = API_BASE_URL + "/" + this.deck.getDeckId() + "/return";
        ReturnCards response = this.template.getForObject(url, ReturnCards.class);

        return response.getSuccess();
    }
}
