package org.rlgame.gameplay;

import gr.eap.RLGameEcoClient.game.Move;

public interface  IPlayer {

	public int getId();
	public int getPlayerType();

	public StringBuffer getMovesLog();

	public void pickMove(GameState passedGameState);
	public Move returnPickedMove(GameState passedGameState);
	public void finishGameSession();
	public void addMoveLog(String s);

}
