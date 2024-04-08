/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.blackjack;

/**
 *
 * @author prash
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {
    private final Deck deck;
    private final Player player;
    private final Dealer dealer;
    private final Scanner scanner;

    public Blackjack() {
        this.deck = new Deck();
        this.player = new Player();
        this.dealer = new Dealer();
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        deck.shuffle();
        player.receiveCard(deck.dealCard());
        player.receiveCard(deck.dealCard());
        dealer.receiveCard(deck.dealCard());
        dealer.receiveCard(deck.dealCard());
    }
    
      public Player getPlayer() {
        return player;
    }
       
      public Dealer getDealer() {
    return dealer;
}
      public boolean isPlayerWinner() {
        if (isPlayerBust()) {
            return false;
        } else if (isDealerBust() || getPlayerScore() > getDealerScore()) {
            return true;
        } else {
            return false;
        }
    }
    public void playerHits() {
        player.receiveCard(deck.dealCard());
    }

    public void dealerHits() {
        dealer.receiveCard(deck.dealCard());
    }

    public boolean isPlayerBust() {
        return player.calculateScore() > 21;
    }

    public boolean isDealerBust() {
        return dealer.calculateScore() > 21;
    }

    public int getPlayerScore() {
        return player.calculateScore();
    }

    public int getDealerScore() {
        return dealer.calculateScore();
    }

    public void playRound() {
        startGame();
        boolean playerTurn = true;

        while (!isPlayerBust() && playerTurn) {
            System.out.println("Dealer's hand: " + dealer.getHand().getCards().get(0) + ", [Hidden]");
            System.out.println("Your hand: " + player.getHand());
            System.out.println("Your score: " + getPlayerScore());
            System.out.println("Hit (H) or Stand (S)?");
            String choice = scanner.next();

            if ("H".equalsIgnoreCase(choice)) {
                playerHits();
                if (isPlayerBust()) {
                    System.out.println("Dealer's hand: " + dealer.getHand());
                    System.out.println("Your hand: " + player.getHand());
                    System.out.println("Your score: " + getPlayerScore());
                    System.out.println("Bust! You lose.");
                    playerTurn = false;
                }
            } else if ("S".equalsIgnoreCase(choice)) {
                playerTurn = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Blackjack!");
        Blackjack game = new Blackjack();
        game.playRound();
    }
}

class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    public Rank getRank() {
        return rank;
    }
}

enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}

enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public Card dealCard() {
        return cards.remove(0);
    }
}

class Hand {
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int calculateScore() {
        int score = 0;
        int numAces = 0;
        for (Card card : cards) {
            switch (card.getRank()) {
                case TWO -> score += 2;
                case THREE -> score += 3;
                case FOUR -> score += 4;
                case FIVE -> score += 5;
                case SIX -> score += 6;
                case SEVEN -> score += 7;
                case EIGHT -> score += 8;
                case NINE -> score += 9;
                case TEN, JACK, QUEEN, KING -> score += 10;
                case ACE -> {
                    score += 11;
                    numAces++;
                }
            }
        }
        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }
        return score;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}

class Player {
    private final Hand hand;

    public Player() {
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public Hand getHand() {
        return hand;
    }

    public int calculateScore() {
        return hand.calculateScore();
    }
}

class Dealer extends Player {
    // No additional methods required for Dealer class
}
