/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Scanner;

/**
 *
 * @author gubotdev
 */
public class Dealer {
    private Player[] myPlayers;
    private Deck myDeck = new Deck();
    private Hand dealerHand = new Hand();
    private Scanner scan = new Scanner(System.in);
    private Hand dHand = new Hand();
    
    public Dealer(int num){
        initMyPlayers(num);
    }
    
        public void playGame(){
        dealOpeningHand();
        takePlayerTurns();
        playOutDealerHand();
        declareWiners();
    }
    public void dealOpeningHand(){
        for(int i =0; i < 2 ; i++){
            for(Player currPlayer : myPlayers){
                currPlayer.getMyHand().addCard(myDeck.dealCard());
            }
        }
    }
    public void takePlayerTurns(){
        for(Player currPlayer : myPlayers){
            while(currPlayer.getMyHand().getNumOfCards() < 5 && 
                    currPlayer.getMyHand().getScore() < 21){
                System.out.println(currPlayer.getName() + " Hand");
                currPlayer.getMyHand().printHand();
                System.out.println("Wanna Hit? (y/n)");
                char opt = scan.next().charAt(0);
                if(opt == 'y'){
                    currPlayer.getMyHand().addCard(myDeck.dealCard());
                }else{
                    break;
                }
            }
            currPlayer.getMyHand().printHand();
        }
    }
    public void playOutDealerHand(){
        while(dealerHand.getScore() < 16){
            dealerHand.addCard(myDeck.dealCard());
        }
        dealerHand.printHand();
        
    }
    
    public void declareWiners(){
        System.out.println("Dealer's Hand");
        dealerHand.printHand();
        
        for(int i = 0; i < myPlayers.length; i++){
           
            Player currPlayer = myPlayers[i];
            System.out.println(currPlayer.getName() + "'s Hand");
            currPlayer.getMyHand().printHand();
         
            if(dealerHand.getScore() > 21 || currPlayer.getMyHand().getScore()
                    > 21 ||currPlayer.getMyHand().getScore() > 21){
                if(currPlayer.getMyHand().getScore() > 21){
                    System.out.println(currPlayer.getName() + 
                            " YOU busted, DEALER WON!");
                }else{
                    System.out.println(currPlayer.getName() + 
                            "DEALER BUSTED, U WONT! ");
                }
          
            }else if(dealerHand.getScore()== 21||dealerHand.getNumOfCards()>4){
                    System.out.println(currPlayer.getName() + 
                    "The dealer is Hot tonight.. you lose!");
          
            }else if(currPlayer.getMyHand().getNumOfCards() > 4){
                System.out.println(currPlayer.getName() + 
                        "five cards only kid");
          
            }else if(currPlayer.getMyHand().getScore()> dealerHand.getScore()){
                System.out.println(currPlayer.getName()+"YOU WIN THIS ONE!");
            }else{
                System.out.println(currPlayer.getName()+"quit while you got"
                        + "enough for a cab ride home kid");
            }
        }
    }
    private void initMyPlayers(int num){
        myPlayers = new Player[num];
        for(int i = 0; i < myPlayers.length; i ++){
            System.out.println("Player" + (i+1) + "What's your name?");
            String name = scan.next();
            if(name.equals("")){
                myPlayers[i] = new Player(i+1);
            }else{
                myPlayers[i] = new Player(name);
            }
        }
    }
    
}
