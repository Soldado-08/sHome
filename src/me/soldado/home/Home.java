package me.soldado.home;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Home {
	
	OfflinePlayer dono;
	Location loc;
	String nome;
	boolean loja;
	
	public Home(OfflinePlayer dono, Location loc, String nome, boolean loja){
		this.dono = dono;
		this.loc = loc;
		this.nome = nome;
		this.loja = loja;
	}

	public OfflinePlayer getDono() {
		return dono;
	}

	public void setDono(Player dono) {
		this.dono = dono;
	}

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isLoja() {
		return loja;
	}

	public void setLoja(boolean loja) {
		this.loja = loja;
	}
	
	public String getString(){
		Home h = this;
		OfflinePlayer pl = h.getDono();
		Location loc = h.getLoc();
		String nome = h.getNome();
		boolean adm = h.isLoja();
		
		String s = pl.getName();
		String x = loc.getBlockX()+"";
		String y = loc.getBlockY()+"";
		String z = loc.getBlockZ()+"";
		String xyz = x+":"+y+":"+z+":"+loc.getWorld().getName();
		String n = nome;
		String a = adm+"";
		
		String se = s +";"+xyz+";"+n+";"+a;
		return se;
	}
	
}
