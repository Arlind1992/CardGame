package com.example.arlin.cardgames;

import java.util.Random;

/**
 * Created by Arlind on 24-Sep-16.
 */

public class OponentAIPesekatesh extends OponentAI {
    @Override
    public Card chooseCard(GameState gameState) {
        return null;
    }

    @Override
    public void orderCards() {

    }

    @Override
    public Card throwCard() {
        Random randomGenerator=new Random();
        int num=randomGenerator.nextInt(super.getCards().size());
        Card throwCard=super.getCards().get(num);
        super.getCards().remove(throwCard);
        return throwCard;
    }
}
