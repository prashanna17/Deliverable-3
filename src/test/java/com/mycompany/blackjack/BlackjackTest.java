/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.blackjack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlackjackTest {
    
    @Test
    public void testStartGame() {
        Blackjack game = new Blackjack();
        assertNotNull(game);
        game.startGame();
        assertNotNull(game.getPlayer().getHand());
        assertNotNull(game.getDealer().getHand());
        assertTrue(game.getPlayerScore() >= 4 && game.getPlayerScore() <= 21);
        assertTrue(game.getDealerScore() >= 4 && game.getDealerScore() <= 21);
    }

    @Test
    public void testPlayerHits() {
        Blackjack game = new Blackjack();
        game.startGame();
        int initialPlayerScore = game.getPlayerScore();
        game.playerHits();
        int newPlayerScore = game.getPlayerScore();
        assertTrue(newPlayerScore > initialPlayerScore && newPlayerScore <= 21);
    }

    @Test
    public void testDealerHits() {
        Blackjack game = new Blackjack();
        game.startGame();
        int initialDealerScore = game.getDealerScore();
        game.dealerHits();
        int newDealerScore = game.getDealerScore();
        assertTrue(newDealerScore > initialDealerScore && newDealerScore <= 21);
    }

    @Test
    public void testIsPlayerBust() {
        Blackjack game = new Blackjack();
        game.startGame();
        assertFalse(game.isPlayerBust());
        while (!game.isPlayerBust()) {
            game.playerHits();
        }
        assertTrue(game.isPlayerBust());
    }

    @Test
    public void testIsDealerBust() {
        Blackjack game = new Blackjack();
        game.startGame();
        assertFalse(game.isDealerBust());
        while (!game.isDealerBust()) {
            game.dealerHits();
        }
        assertTrue(game.isDealerBust());
    }

    @Test
    public void testIsPlayerWinner() {
        Blackjack game = new Blackjack();
        game.startGame();
        while (!game.isPlayerBust() && game.getPlayerScore() < 17) {
            game.playerHits();
        }
        if (game.isPlayerBust()) {
            assertFalse(game.isPlayerWinner());
        } else {
            game.dealerHits();
            if (game.isDealerBust() || game.getPlayerScore() > game.getDealerScore()) {
                assertTrue(game.isPlayerWinner());
            } else {
                assertFalse(game.isPlayerWinner());
            }
        }
    }

    @Test
    public void testGetPlayerHand() {
        Blackjack game = new Blackjack();
        game.startGame();
        assertNotNull(game.getPlayer().getHand());
    }

    @Test
    public void testGetDealerHand() {
        Blackjack game = new Blackjack();
        game.startGame();
        assertNotNull(game.getDealer().getHand());
    }

    @Test
    public void testGetPlayerScore() {
        Blackjack game = new Blackjack();
        game.startGame();
        assertTrue(game.getPlayerScore() >= 4 && game.getPlayerScore() <= 21);
    }

    @Test
    public void testGetDealerScore() {
        Blackjack game = new Blackjack();
        game.startGame();
        assertTrue(game.getDealerScore() >= 4 && game.getDealerScore() <= 21);
    }
}
