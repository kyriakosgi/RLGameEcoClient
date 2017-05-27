package gr.eap.RLGameEcoClient.comm;

public abstract class Response extends CommunicationsObject {
	private int commandID;
//	private ArrayList<String> availableCommands;
//	private GameState gameState;
	private ConnectionState connectionState;

	public int getCommandID() {
		return commandID;
	}

	public void setCommandID(int commandID) {
		this.commandID = commandID;
	}

// getAvailableCommands() - not yet implemented
//	public ArrayList<String> getAvailableCommands() {
//		return availableCommands;
//	}
//
//	public void setAvailableCommands(ArrayList<String> availableCommands) {
//		this.availableCommands = availableCommands;
//	}


//TODO Create a GameStateResponse class
//	public GameState getGameState() {
//		return gameState;
//	}
//
//	public void setGameState(GameState gameState) {
//		this.gameState = gameState;
//	}


	public ConnectionState getConnectionState() {
		return connectionState;
	}

	public void setConnectionState(ConnectionState connectionState) {
		this.connectionState = connectionState;
	}
	
	public abstract void process();
}
