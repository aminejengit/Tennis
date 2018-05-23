package com.tennis.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerGame {

	private String playerName;
	private int wonSets;
	private int wonGamesInTheCurrentSet;
	private List<Integer> wonGamesPerSets;
	private int wonPointsInTheCurrentGame;
	private boolean winner = false;
	private boolean tieBreakGame = false;

	public PlayerGame(String playerName) {
		super();
		this.playerName = playerName;
		this.wonGamesPerSets = new ArrayList();
	}

	public boolean isWinner() {
		return winner;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getWonSets() {
		return wonSets;
	}

	public int getWonGamesInTheCurrentSet() {
		return wonGamesInTheCurrentSet;
	}

	private int getWonPointsInTheCurrentGame() {
		return wonPointsInTheCurrentGame;
	}

	private void winsASet(PlayerGame otherPlayer) {
		this.wonSets++;
		if (this.wonSets == RulesConstants.NUMBER_OF_SETS_TO_WIN_A_MATCH) {
			this.winner = true;
		}
		saveGamesPerSet(otherPlayer);

	}

	private void saveGamesPerSet(PlayerGame otherPlayer) {
		this.wonGamesPerSets.add(this.wonGamesInTheCurrentSet);
		otherPlayer.wonGamesPerSets.add(otherPlayer.wonGamesInTheCurrentSet);
	}

	private void winsAGame(PlayerGame otherPlayer) {
		this.wonGamesInTheCurrentSet++;
		checkTieBreak(otherPlayer);
		if (setIsWonAgainst(otherPlayer)) {
			this.winsASet(otherPlayer);
			this.startANewSet();
			otherPlayer.startANewSet();
		}
	}

	private void checkTieBreak(PlayerGame otherPlayer) {
		if (this.wonGamesInTheCurrentSet == RulesConstants.NUMBER_OF_GAMES_TO_WIN_A_SET
				&& otherPlayer.wonGamesInTheCurrentSet == RulesConstants.NUMBER_OF_GAMES_TO_WIN_A_SET) {
			tieBreakGame = true;
		}
	}

	public void winsAPointAgainst(PlayerGame otherPlayer) {
		this.wonPointsInTheCurrentGame++;
		if (gameIsWonAgainst(otherPlayer)) {
			this.winsAGame(otherPlayer);
			this.startANewGame();
			otherPlayer.startANewGame();
		}
	}

	private boolean gameIsWonAgainst(PlayerGame otherPlayer) {
		int setPoints = RulesConstants.NUMBER_OF_POINTS_TO_WIN_A_GAME;
		if (tieBreakGame) {
			setPoints = RulesConstants.NUMBER_OF_POINTS_TO_WIN_A_TIE_BREAK;
		}
		return this.wonPointsInTheCurrentGame >= setPoints
				&& getDifferenceOfPointsWith(otherPlayer) >= RulesConstants.DIFFERENSE_TO_WIN_THE_GAME;
	}

	private boolean setIsWonAgainst(PlayerGame otherPlayer) {
		return (this.wonGamesInTheCurrentSet >= RulesConstants.NUMBER_OF_GAMES_TO_WIN_A_SET
				&& getDifferenceOfGamesWith(otherPlayer) >= RulesConstants.DIFFERENSE_TO_WIN_THE_GAME)
				|| (this.tieBreakGame && this.wonGamesInTheCurrentSet > otherPlayer.wonGamesInTheCurrentSet);
	}

	private int getDifferenceOfGamesWith(PlayerGame playerGame2) {
		return this.wonGamesInTheCurrentSet - playerGame2.wonGamesInTheCurrentSet;
	}

	private int getDifferenceOfPointsWith(PlayerGame otherPlayer) {
		return this.wonPointsInTheCurrentGame - otherPlayer.wonPointsInTheCurrentGame;
	}

	private void startANewSet() {
		this.wonGamesInTheCurrentSet = 0;
		this.wonPointsInTheCurrentGame = 0;
		this.tieBreakGame = false;
	}

	private void startANewGame() {
		this.wonPointsInTheCurrentGame = 0;
	}

	public void startANewMatch() {
		this.wonSets = 0;
		this.wonGamesInTheCurrentSet = 0;
		this.wonPointsInTheCurrentGame = 0;
	}

	@Override
	public String toString() {
		return "PlayerGame [playerName=" + playerName + ", wonSets=" + wonSets + ", wonGamesInTheCurrentSet="
				+ wonGamesInTheCurrentSet + ", wonGamesPerSets=" + wonGamesPerSets + ", wonPointsInTheCurrentGame="
				+ wonPointsInTheCurrentGame + ", winner=" + winner + "]";
	}

}
