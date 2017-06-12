package gr.eap.RLGameEcoClient.comm;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.rlgame.gameplay.MMPlayer;
import org.rlgame.gameplay.RLPlayer;
import org.rlgame.gameplay.RandomPlayer;
import org.rlgame.gameplay.Settings;

import gr.eap.RLGameEcoClient.Client;
import gr.eap.RLGameEcoClient.player.Player;
import gr.eap.RLGameEcoClient.player.Member;
import gr.eap.RLGameEcoClient.player.Participant.Role;

public class MessageResponse extends Response {
	private Message message;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public MessageResponse() {
		this.setType("gr.eap.RLGameEcoServer.comm.MessageResponse");
	}

	@Override
	public void process() {
		Player sender = getMessage().getSender();
		String currentMessage = getMessage().getText();
		String regexCreateGame = "(?i)((?!not).)*create.*game.*board.*size.*?(\\d+).*base.*size.*?(\\d+).*?(\\d+).*pawns.*";
		Matcher matchCreateGame = Pattern.compile(regexCreateGame,Pattern.CASE_INSENSITIVE).matcher(currentMessage);
		String reply = "";
		byte boardSize = 0, baseSize = 0, numberOfPawns = 0;
		UUID joinGameUid = null;
		if (sender != null && sender.isHuman() && ((Member)sender).getAvatar().equals(Client.me)){
			
			if (matchCreateGame.matches()){
				try {
					boardSize = Byte.parseByte(matchCreateGame.group(2));
					baseSize = Byte.parseByte(matchCreateGame.group(3));
					numberOfPawns = Byte.parseByte(matchCreateGame.group(4));
					reply = "Creating the game. Hope you join me!";
				} catch (NumberFormatException e) {
					reply = "I couldn't quite get the numbers, sorry :(";
				}
				
			}
//			else if (matchCreateGame != null && matchCreateGame.toMatchResult().group(1).groupCount()>1 && matchCreateGame.group(1) != null){ // caught the word "not"
//				reply = "Not creating a game for now";
//			}
			else{
				String regexJoinGame = "join UID:(.+)\\sboardsize:(\\d+)\\sbasesize:(\\d+)\\spawns:(\\d+)";
				Matcher matchJoinGame = Pattern.compile(regexJoinGame,Pattern.CASE_INSENSITIVE).matcher(currentMessage);
				if (matchJoinGame.matches()){
					joinGameUid = UUID.fromString(matchJoinGame.group(1));
					boardSize = Byte.parseByte(matchJoinGame.group(2));
					baseSize = Byte.parseByte(matchJoinGame.group(3));
					numberOfPawns = Byte.parseByte(matchJoinGame.group(4));
					
					reply = "Joining game...";
				}
				else
				{
					reply = "OK";
				}
			}

		}
		
		if (reply != ""){
			MessageCommand replyCommand = new MessageCommand();
			replyCommand.setMessageText(reply);
			replyCommand.getRecipientsIds().add(sender.getId());
			replyCommand.setSocket(getSocket());
			replyCommand.setUserId(getUserId());
			replyCommand.send();
		}
		
		if (boardSize != 0 && baseSize != 0 && numberOfPawns != 0){
			int ident, opponent, plies;
			if (joinGameUid != null) {
				ident = Settings.BLACK_PLAYER;
				opponent = Settings.WHITE_PLAYER;
				plies = Settings.PLAYER_B_PLIES;
			}
			else
			{
				ident = Settings.WHITE_PLAYER;
				opponent = Settings.BLACK_PLAYER;
				plies = Settings.PLAYER_W_PLIES;
			}
			
			String playerType = Client.clientSettings.getProperty("playerType");
			switch (playerType) {
			case "RANDOM_PLAYER":
				Client.machine = new RandomPlayer(ident);
				break;
			case "MM_PLAYER":
				Client.machine = new MMPlayer(ident, plies, opponent, boardSize, baseSize, numberOfPawns);
				break;
			case "RL_PLAYER":
				Client.machine = new RLPlayer(ident, boardSize, baseSize, numberOfPawns);
				break;

			default:
				break;
			}
			
			Client.currentBaseSize = baseSize;
			Client.currentBoardSize = boardSize;
			Client.currentNumberOfPawns = numberOfPawns;
			
			if (joinGameUid != null) {
				JoinGameCommand joinCommand = new JoinGameCommand();
				joinCommand.setGameUid(joinGameUid);
				joinCommand.setRole(Role.BLACKPLAYER);
				joinCommand.setId(0);
				joinCommand.setSocket(getSocket());
				joinCommand.setUserId(getUserId());
				joinCommand.send();
			}
			else
			{
				CreateGameCommand createGameCommand = new CreateGameCommand();
				createGameCommand.setBaseSize(baseSize);
				createGameCommand.setBoardSize(boardSize);
				createGameCommand.setNumberOfPawns(numberOfPawns);
				createGameCommand.setSocket(getSocket());
				createGameCommand.setUserId(getUserId());
				createGameCommand.send();
			}
			
			
		}
		
		
	}

}
