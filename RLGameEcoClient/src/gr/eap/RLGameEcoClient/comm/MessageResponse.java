package gr.eap.RLGameEcoClient.comm;

public class MessageResponse extends Response {
	private Message message;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public MessageResponse() {
		this.setType("gr.eap.RLGameEcoClient.comm.MessageResponse");
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}

}
