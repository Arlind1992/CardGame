package com.example.arlin.cardgames;

import android.content.Context;
import android.graphics.PointF;

import java.util.ArrayList;

/**
 * Created by Arlind on 17-May-16.
 */
public class PeseKateshGameController extends GameController {
    private final int NUMOFPLAYERS = 4;



    public PeseKateshGameController(GameView gameView,Context context) {
        super(gameView,context);
        super.setDeck(new Deck52Cards());

    }

    @Override
    public  void initialize(){
        final Context context=super.getContext();
        final PeseKateshGameController controller=this;

        Thread in = new Thread(new Runnable() {
            public void run() {
                initializeGame();
                initializeCards(context);
                controller.setInizialized(true);
                synchronized (this) {
                    notify();
                }
            }
        });
        in.start();
        try {
            in.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void initializeGame() {
        int i;
        Player player;
        player = new PeseKateshPlayer();
         Player AIPlayer=null;
        super.getPlayers().add(player);
        for (i = 0; i < NUMOFPLAYERS-1; i++) {

            AIPlayer = new PeseKateshOponentAI();
            super.getPlayers().add(AIPlayer);
        }
        divideCards();
        player.orderCards();
        //TODO change the way current player is assigned
        super.setCurrentPlayer(player);
        super.setPlaying(true);
    }

    @Override
    public void divideCards() {

        super.getDeck().shuffle();
        int i = 0;
        for (Card card : super.getDeck().getCards()) {
            super.getPlayers().get(i % NUMOFPLAYERS).addCard(card);
            i++;
        }
    }

    @Override
    public boolean checkPlayerChoise(Card card) {
        if (super.getCardsOnTable().isEmpty()) {
            return true;
        } else {
            if (super.getCardsOnTable().get(0).getType().equals(card.getType())) {
                return true;
            } else {
                if (!hasTypeCardInHand(super.getCurrentPlayer(), super.getCardsOnTable().get(0))) {
                    return true;
                } else {
                    return false;
                }
            }
        }

    }

    @Override
    public void handleThrow() {
        //TODO make sure that only one card is selected
        if(super.getSelectedCards().size()!=0){
            Card selectedCard=super.getSelectedCards().get(0);
            if(checkPlayerChoise(selectedCard)){
                int x=this.getTable().getThrowCards().centerX();
                //TODO calculate difference
                int y=this.getTable().getThrowCards().centerY()-50;
                PointF dest=new PointF(x,y);
                selectedCard.setDestination(dest);
                synchronized(this) {
                    notify();
                }
            }

        }

    }

    @Override
    public void playGame() {
        while(super.isPlaying()){
            if(getCurrentPlayer().equals(this.getPlayers().get(0))){
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                Card tothrow=this.getCurrentPlayer().throwCard();
                tothrow.setVisible(true);
                //TODO change way of the destination depending on the position of the AIoponent
                int x=this.getTable().getThrowCards().centerX();
                int y=this.getTable().getThrowCards().centerY();
                PointF dest=new PointF(x,y);
                tothrow.setDestination(dest);
                this.getCardsOnTable().add(tothrow);
                this.setNextPlayer();
            }
        }
    }

    private boolean hasTypeCardInHand(Player player, Card card) {
        for (Card playerCard : player.getCards()) {
            if (playerCard.getType().equals(card.getType())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void initializeCards(Context context) {
        PointF pointOne = super.getTable().getStartDrawingForOne();
        PointF pointTwo = super.getTable().getStartDrawingForTwo();
        PointF pointThree = super.getTable().getStartDrawingForThree();
        PointF pointFour = super.getTable().getStartDrawingForFour();
        ArrayList<PointF> points=new ArrayList();
        points.add(pointOne);
        points.add(pointTwo);
        points.add(pointThree);
        points.add(pointFour);
        int i = 0;

        int toAddWidth=Card.getWidth();
        int toAddHeight=Card.getHeight();
        for (i = 0; i < NUMOFPLAYERS; i++) {
            int j=0;
            for (Card card : super.getPlayers().get(i).getCards()) {

                if (i == 0) {
                    card.setVisible(true);
                } else {
                    if(i==1||i==3){
                        card.setHorizontal(true);
                    }else{
                        card.setVisible(false);
                    }

                }
                PointF pointToSet=null;
                //TODO check the way you increase point
                switch (i) {
                    case 0:
                        pointToSet=new PointF(points.get(i).x+j*toAddWidth,points.get(i).y);
                        break;
                    case 1:
                        pointToSet=new PointF(points.get(i).x,points.get(i).y+toAddHeight*j);
                        break;
                    case 2:
                        pointToSet=new PointF(points.get(i).x+toAddWidth*j,points.get(i).y);
                        break;
                    case 3:
                        pointToSet=new PointF(points.get(i).x,points.get(i).y+toAddHeight*j);
                        break;

                }
                card.setPosition(pointToSet);
                card.setResourceName();
                card.setBitmap(context);
                j++;
            }
        }
    }




}
