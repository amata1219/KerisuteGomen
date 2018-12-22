package amata1219.kerisute.gomen;

public class Mod {

	private final Type type;

	private final String id;
	private final String version;

	private boolean block;

	public Mod(Type type, String id, String version){
		this.type = type;
		this.id = id;
		this.version = version;
	}

	public Type getType(){
		return type;
	}

	public String getId() {
		return id;
	}

	public String getVersion() {
		return version;
	}

	public boolean isBlocked() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

}
