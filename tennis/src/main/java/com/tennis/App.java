package com.tennis;

import com.tennis.model.PlayerGame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       
        PlayerGame player1 = new  PlayerGame("jen");
        PlayerGame player2 = new  PlayerGame("yessine");
     
        
        while (!player1.isWinner() && !player2.isWinner()) {
        	if (Math.random()>=0.5) {
        		   player1.winsAPointAgainst(player2);
        	}else {
        		player2.winsAPointAgainst(player1);
        	}
    		
        }
        System.out.println(player1);
		System.out.println(player2);
		System.out.println();
		System.out.println();
    }
}
	