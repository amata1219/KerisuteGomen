package net.eq2online.permissions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class ReplicatedPermissionsContainer implements Serializable {

	private static final long serialVersionUID = -764940324881984960L;
	public String modName = "all";
	public Float modVersion = 0f;
	public Set<String> permissions = new TreeSet<String>();
	public long remoteCacheTimeSeconds = 600L;
	public static final String CHANNEL = "PERMISSIONSREPL";

	public ReplicatedPermissionsContainer(){

	}

	public ReplicatedPermissionsContainer(String modName, Float modVersion, Collection<String> permissions){
		this.modName = modName;
		this.modVersion = modVersion;
		this.permissions.addAll(permissions);
	}

	public void addAll(Collection<String> permissions){
		this.permissions.addAll(permissions);
	}

	public void sanitise(){
		if(modName == null || modName.length() < 1) modName = "all";
		if(modVersion == null || modVersion < 0f) modVersion = 0f;
		if(remoteCacheTimeSeconds < 0) this.remoteCacheTimeSeconds = 600L;
	}

	public byte[] getBytes(){
		try{
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			new ObjectOutputStream(stream).writeObject(this);
			return stream.toByteArray();
		}catch(IOException e){

		}
		return new byte[0];
	}

	public static ReplicatedPermissionsContainer fromBytes(byte[] data){
		try{
			ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(data));
			ReplicatedPermissionsContainer object = (ReplicatedPermissionsContainer )stream.readObject();
			return object;
		}catch(IOException e){

		}catch(ClassNotFoundException e){

		}catch(ClassCastException e){

		}
		return null;
	}

}
