package amata1219.old.kerisute.gomen;

public class Mod {

	private final ModLoaderType type;

	private final String id;
	private final String version;

	private boolean block;

	public Mod(ModLoaderType type, String id, String version){
		this.type = type;
		this.id = id;
		this.version = version;
	}

	public ModLoaderType getType(){
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
