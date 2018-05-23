package com.tennis.model;

public class PlayerGame {

	private String playerName;
	private int wonSets;
	private int wonGamesInTheCurrentSet;
	private int wonPointsInTheCurrentGame;
	private boolean isWinner = false;;

	public PlayerGame(String playerName) {
		super();
		this.playerName = playerName;
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

	private void winsASet() {
		this.wonSets++;
		if (this.wonSets == RulesConstants.NUMBER_OF_SETS_TO_WIN_A_MATCH) {
			this.isWinner = true;
		}
		startANewSet();
	}

	private void winsAGame(PlayerGame playerGame2) {
		this.wonGamesInTheCurrentSet++;
		if (this.wonGamesInTheCurrentSet >= RulesConstants.NUMBER_OF_GAMES_TO_WIN_A_SET) {
			if (getDifferenceOfGamesWith(playerGame2) >= RulesConstants.DIFFERENSE_TO_WIN_THE_GAME) {
				this.winsASet();
				playerGame2.startANewSet();
			}
		}
		startANewGame();
	}

	private int getDifferenceOfGamesWith(PlayerGame playerGame2) {
		return this.wonGamesInTheCurrentSet - playerGame2.wonGamesInTheCurrentSet;
	}

	public void winsAPointAgainst(PlayerGame playerGame2) {
		this.wonPointsInTheCurrentGame++;
		if (this.wonPointsInTheCurrentGame >= RulesConstants.NUMBER_OF_POINTS_TO_WIN_A_GAME) {
			if (getDifferenceOfPointsWith(playerGame2) >= RulesConstants.DIFFERENSE_TO_WIN_THE_GAME) {
				this.winsAGame(playerGame2);
				playerGame2.startANewGame();
			}
		}
		System.out.println(this);
	}

	private int getDifferenceOfPointsWith(PlayerGame playerGame2) {
		return this.wonPointsInTheCurrentGame - playerGame2.wonPointsInTheCurrentGame;
	}

	private void startANewSet() {
		this.wonGamesInTheCurrentSet = 0;
		this.wonPointsInTheCurrentGame = 0;
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
				+ wonGamesInTheCurrentSet + ", wonPointsInTheCurrentGame=" + wonPointsInTheCurrentGame + "]";
	}

}
