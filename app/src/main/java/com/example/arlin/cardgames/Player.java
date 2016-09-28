package com.example.arlin.cardgames;

import android.graphics.Rect;

        import java.util.ArrayList;

/**
 * Created by Arlind on 13-May-16.
 */
public abstract class Player {
    private ArrayList<Card> cards;
    private Rect position;
    public Player(){
        cards=new ArrayList<Card>();
    }
    public ArrayList<Card> getCards(){
        return cards;
    }

    public void addCard(Card card){
        cards.add(card);
    }
    public Rect getPosition(){
        return position;
    }

    public void setCards(ArrayList<Card> cards){
        this.cards=cards;
    }
    public abstract void orderCards();
    public abstract Card throwCard();


}
