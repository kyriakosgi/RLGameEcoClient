package gr.eap.RLGameEcoClient.comm;

public class CommObjectClassCreator {
	public static Class<?> create(String commObjectType){
		if (commObjectType.equals("gr.eap.RLGameEcoClient.comm.LoginCommand"))
			return LoginCommand.class;
		else if (commObjectType.equals("gr.eap.RLGameEcoClient.comm.MessageCommand"))
			return MessageCommand.class;
		else if (commObjectType.equals("gr.eap.RLGameEcoClient.comm.CreateGameCommand"))
			return CreateGameCommand.class;
		else if (commObjectType.equals("gr.eap.RLGameEcoClient.comm.JoinGameCommand"))
			return JoinGameCommand.class;
		else if (commObjectType.equals("gr.eap.RLGameEcoClient.comm.ConfirmStartGameCommand"))
			return ConfirmStartGameCommand.class;
		else if (commObjectType.equals("gr.eap.RLGameEcoClient.comm.LeaveGameCommand"))
			return LeaveGameCommand.class;
		else if (commObjectType.equals("gr.eap.RLGameEcoClient.comm.MoveCommand"))
			return MoveCommand.class;
		else if (commObjectType.equals("gr.eap.RLGameEcoClient.comm.MessageResponse"))
			return Response.class;
		else if (commObjectType.equals("gr.eap.RLGameEcoClient.comm.PlayersListResponse"))
			return PlayersListResponse.class;
		else if (commObjectType.equals("gr.eap.RLGameEcoClient.comm.GamesListResponse"))
			return GamesListResponse.class;
		else if (commObjectType.equals("gr.eap.RLGameEcoClient.comm.GameStateResponse"))
			return GameStateResponse.class;
		else
			return null;
	}

}
