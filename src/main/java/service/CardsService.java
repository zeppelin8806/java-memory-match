package service;

import service.model.DrawnCard;

import java.util.List;

public interface CardsService {

    void reshuffle();

    List<DrawnCard> drawCards();

}
