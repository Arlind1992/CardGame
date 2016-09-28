package com.example.arlin.cardgames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Arlind on 17-May-16.
 */
public class PeseKateshPlayer extends Player {

    @Override
    public void orderCards() {
        Collections.sort(super.getCards(),new CardComperator());

    }

    @Override
    public Card throwCard() {
        return null;
    }

    private class CardComperator implements Comparator<Card> {

        @Override
        public int compare(Card o1, Card o2) {
            if(o1.getType().equals(o2.getType())){
                return 0;
            }else{
                switch(o1.getType()){
                    case SPADES:
                        return 1;
                    case CLUBS:
                        if(o2.getType().equals(Type.HEARTS)) return -1;
                        if(o2.getType().equals(Type.SPADES)) return -1;
                        if(o2.getType().equals(Type.DIAMONDS)) return 1;
                        break;
                    case HEARTS:
                        if(o2.getType().equals(Type.CLUBS)) return 1;
                        if(o2.getType().equals(Type.SPADES)) return -1;
                        if(o2.getType().equals(Type.DIAMONDS)) return 1;
                        break;
                    case DIAMONDS:
                        return -1;
                }
            }
            return 0;


        }
    }


}