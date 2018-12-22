package amata1219.kerisute.gomen;

public class Client {

	private final String name;

	private boolean block;

	public Client(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isBlocked() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

}
