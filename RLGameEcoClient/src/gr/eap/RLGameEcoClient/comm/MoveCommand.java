package gr.eap.RLGameEcoClient.comm;

import java.util.UUID;


public class MoveCommand extends Command {

	private int pawnId;
	private int toXCoord;
	private int toYCoord;
	private UUID gameUid;
	
	public UUID getGameUid() {
		return gameUid;
	}

//	private void setGameUid(UUID gameUid) {
//		this.gameUid = gameUid;
//	}
	

	
	public int getPawnId() {
		return pawnId;
	}



	public void setPawnId(int pawnId) {
		this.pawnId = pawnId;
	}



	public int getToXCoord() {
		return toXCoord;
	}



	public void setToXCoord(int toXCoord) {
		this.toXCoord = toXCoord;
	}



	public int getToYCoord() {
		return toYCoord;
	}



	public void setToYCoord(int toYCoord) {
		this.toYCoord = toYCoord;
	}




}
