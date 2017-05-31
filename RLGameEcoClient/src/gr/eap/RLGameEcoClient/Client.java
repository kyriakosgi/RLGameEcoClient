package gr.eap.RLGameEcoClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;
import org.rlgame.gameplay.IPlayer;

import gr.eap.RLGameEcoClient.comm.LoginCommand;
import gr.eap.RLGameEcoClient.comm.Response;
import gr.eap.RLGameEcoClient.player.Player;
import gr.eap.RLGameEcoClient.comm.JsonCommObjectSerializer;


public class Client extends WebSocketClient {

	public static Player me;
	public static IPlayer machine;
	public Client(URI uri){
		super(uri);
	}
	public Client( Draft d , URI uri ) {
		super( uri, d );
	}
	/**
	 * @param args
	 */
	public static void main( String[] args ) {
		try {
			Client c = new Client(new URI("ws://localhost:33313"));
			
			c.connect();
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onMessage( String message ) {
		try {

			System.out.println(message);
			JsonCommObjectSerializer js = new JsonCommObjectSerializer();
			Response cmd = (Response) js.deserialize(message);
			if (cmd != null) {
				cmd.setSocket(getConnection());
				cmd.process();

			} else {
				System.err.println("Unknown command received\r\n" + message);
			}
		} catch (

		Exception ex) {
			System.err.println("onMessage:" + ex);
		}
	}

	@Override
	public void onMessage( ByteBuffer blob ) {
		//getConnection().send( blob );
	}

	@Override
	public void onError( Exception ex ) {
		System.out.println( "Error: " );
		ex.printStackTrace();
	}

	@Override
	public void onOpen( ServerHandshake handshake ) {
		LoginCommand lc = new LoginCommand();
		lc.setSocket(getConnection());
		lc.setUserName("player2");
		lc.setPassword("pass2");
		lc.send();
	}

	@Override
	public void onClose( int code, String reason, boolean remote ) {
		System.out.println( "Closed: " + code + " " + reason );
	}

	@Override
	public void onWebsocketMessageFragment( WebSocket conn, Framedata frame ) {
//		FrameBuilder builder = (FrameBuilder) frame;
//		builder.setTransferemasked( true );
//		getConnection().sendFrame( frame );
	}

}
