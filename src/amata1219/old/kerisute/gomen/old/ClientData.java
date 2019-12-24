package amata1219.old.kerisute.gomen.old;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.entity.Player;

import amata1219.old.kerisute.gomen.ModOld;

public class ClientData {

	private Player player;
	private String clientName;
	private List<String> otherClients = new ArrayList<String>();
	private Set<String> usingChannels = new HashSet<String>();
	private boolean isForgeClient, isLiteLoaderClient, isLabyModClient;
	private List<ModOld> forgeMods = new ArrayList<ModOld>(), liteLoaderMods = new ArrayList<ModOld>();
	private boolean isUsing5zig;
	private List<ModOld> blockedMods = new ArrayList<ModOld>();
	private boolean shouldKick;
	private boolean fcheck, lcheck;

	public ClientData(Player player){
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isIgnoreModCheck(){
		return player.hasPermission("kerisute.samurai.ignore");
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public List<String> getOtherClients() {
		return otherClients;
	}

	public void setOtherClients(List<String> otherClients) {
		this.otherClients = otherClients;
	}

	public Set<String> getUsingChannels() {
		return usingChannels;
	}

	public void setUsingChannels(Set<String> usingChannels) {
		this.usingChannels = usingChannels;
	}

	public boolean isVanillaClient(){
		return clientName.equals("vanilla") && !isNonForge5zigClient();
	}

	public boolean isForgeClient() {
		return isForgeClient;
	}

	public void setForgeClient(boolean isForgeClient) {
		this.isForgeClient = isForgeClient;
	}

	public boolean isLiteLoaderClient() {
		return isLiteLoaderClient;
	}

	public void setLiteLoaderClient(boolean isLiteLoaderClient) {
		this.isLiteLoaderClient = isLiteLoaderClient;
	}

	public boolean isNonForge5zigClient() {
		return clientName.equals("vanilla") && isUsing5zig;
	}

	public void setLabyModClient(boolean isLabyModClient){
		this.isLabyModClient = isLabyModClient;
	}

	public boolean isLabyModClient(){
		return isLabyModClient;
	}

	public boolean isForgeWurstClient() {
		boolean b = false;
		for(ModOld mod : forgeMods){
			if(mod.getId().equals("forgewurst")){
				b = true;
				continue;
			}
		}
		return b;
	}

	public boolean isOtherClient(){
		return !otherClients.isEmpty();
	}

	public List<ModOld> getForgeMods() {
		return forgeMods;
	}

	public void setForgeMods(List<ModOld> forgeMods) {
		this.forgeMods = forgeMods;
	}

	public List<ModOld> getLiteLoaderMods() {
		return liteLoaderMods;
	}

	public void setLiteloaderMods(List<ModOld> liteLoaderMods) {
		this.liteLoaderMods = liteLoaderMods;
	}

	public void setUsing5zig(boolean isUsing5zig){
		this.isUsing5zig = isUsing5zig;
	}

	public boolean isUsing5zig(){
		return isUsing5zig;
	}

	public List<ModOld> getBlockedMods() {
		return blockedMods;
	}

	public void setBlockedMods(List<ModOld> blockedMods) {
		this.blockedMods = blockedMods;
	}

	public boolean shouldKick() {
		return shouldKick;
	}

	public void setShouldKick(boolean shouldKick) {
		this.shouldKick = shouldKick;
	}

	public boolean isFcheck() {
		return fcheck;
	}

	public void setFcheck(boolean fcheck) {
		this.fcheck = fcheck;
	}

	public boolean isLcheck() {
		return lcheck;
	}

	public void setLcheck(boolean lcheck) {
		this.lcheck = lcheck;
	}

}
