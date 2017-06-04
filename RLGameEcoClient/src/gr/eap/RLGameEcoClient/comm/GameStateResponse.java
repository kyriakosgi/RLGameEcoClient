package gr.eap.RLGameEcoClient.comm;

import java.util.UUID;

import org.rlgame.gameplay.GameState;

import gr.eap.RLGameEcoClient.Client;
import gr.eap.RLGameEcoClient.game.Move;

public class GameStateResponse extends Response {
	private GameState state;
	private UUID gameUid;
	
	public UUID getGameUid() {
		return gameUid;
	}

	public GameState getState() {
		return state;
	}
	
	public GameStateResponse(GameState state, UUID gameUid) {
		this.state = state;
		this.gameUid = gameUid;
		this.setType("gr.eap.RLGameEcoServer.comm.GameStateResponse");
	}

	@Override
	public void process() {
		//When game state is deserialized, pawns are created without their boardSize and baseSize properties set
		//We will check if this is true and correct it
		if (getState().getWhitePawns()[0].getBoardSize() == 0){
			for (byte i = 0; i < Client.currentNumberOfPawns; i++){
				getState().getWhitePawns()[i].setBoardSize(Client.currentBoardSize);
				getState().getWhitePawns()[i].setBaseSize(Client.currentBaseSize);
				getState().getBlackPawns()[i].setBoardSize(Client.currentBoardSize);
				getState().getBlackPawns()[i].setBaseSize(Client.currentBaseSize);
			}
		}
		if (getState().getTurn() == Client.machine.getId()){
			
			Move pickedMove = Client.machine.returnPickedMove(getState());
			
			MoveCommand mc = new MoveCommand();
			mc.setSocket(getSocket());
			mc.setPawnId(pickedMove.getPawn().getId());
			mc.setToXCoord(pickedMove.getToSquare().getXCoord());
			mc.setToYCoord(pickedMove.getToSquare().getYCoord());
			mc.setUserId(getUserId());
			mc.setGameUid(getGameUid());
			mc.send();
			

		}
		
	}


}
