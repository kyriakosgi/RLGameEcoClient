package gr.eap.RLGameEcoClient.comm;

import java.util.ArrayList;

import gr.eap.RLGameEcoClient.game.Game;
import gr.eap.RLGameEcoClient.game.GamesRegister;

public class GamesListResponse extends Response {
	private ArrayList<Game> gamesList;

	public GamesListResponse() {
		gamesList = GamesRegister.getInstance().getGamesList();
		this.setType("gr.eap.RLGameEcoClient.comm.GamesListResponse");
	}

	public GamesListResponse(ArrayList<Game> gamesList) {
		this.gamesList = gamesList;
		this.setType("gr.eap.RLGameEcoClient.comm.GamesListResponse");
	}
	
	public ArrayList<Game> getGamesList() {
		return gamesList;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}

}
