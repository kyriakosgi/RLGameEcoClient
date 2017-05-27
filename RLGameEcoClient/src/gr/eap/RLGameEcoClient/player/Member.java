package gr.eap.RLGameEcoClient.player;

public class Member extends Player {
	private transient Avatar avatar;

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
	
	public void registerAvatar(){}
}
