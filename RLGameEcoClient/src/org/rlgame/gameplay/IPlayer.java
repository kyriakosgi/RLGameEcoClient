package org.rlgame.gameplay;

import gr.eap.RLGameEcoClient.game.Move;

public interface  IPlayer {

	public int getId();
	public int getPlayerType();

	public StringBuffer getMovesLog();

	public Move pickMove(GameState passedGameState);
	public void finishGameSession();
	public void addMoveLog(String s);

}
