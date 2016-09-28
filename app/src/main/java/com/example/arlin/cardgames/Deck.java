package com.example.arlin.cardgames;


import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Arlind on 16-May-16.
 */
public class Deck  {
    private ArrayList<Card> cards;



    public Deck(){
        cards=new ArrayList<Card>();


    }

    public void loadFirstCards(){
        int i;
        Card card;
        for(i=1;i<14;i++ ){
            for(Type type:Type.values()){
                card = new Card( i, type,false,false);
                this.cards.add(card);
            }
        }
    }

    public void loadJockers(){
        Card card;
        card=new Card(14,false,false);
        this.cards.add(card);
        card=new Card(15,false,false);
        this.cards.add(card);
    }

    public void shuffle(){
        Random rnd = new Random();
        for (int i = cards.size() - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Card card = cards.get(index);
            cards.set(index,cards.get(i));
            cards.set(i,card);
        }

    }

    public ArrayList<Card> getCards(){
        return this.cards;
    }

}
