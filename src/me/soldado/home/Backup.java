package me.soldado.home;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Backup {
	
	public Main plugin;
	public Core core;

	public Backup(Main plugin)
	{
		this.plugin = plugin;
		this.core = plugin.core;
	}
	
	File bdfile;
	FileConfiguration bd;
	
	public void iniciarBanco(){
		if (bdfile == null) {
			bdfile = new File(plugin.getDataFolder(), "homes.dat");
		}
		if (!bdfile.exists()) {
			plugin.saveResource("homes.dat", false);
		}
		bd = YamlConfiguration.loadConfiguration(bdfile);
	}
	
	public void carregarHomes(){
		if(bd.getStringList("homes") != null){
			List<String> s = bd.getStringList("homes");
			for(String str : s){
				Home home = desserializar(str);
				plugin.core.recarregarHome(home);
			}
		}
	}
	
	public void salvarHomes(){
		ArrayList<Home> homes = plugin.core.homes;
		if(bd.getStringList("homes") != null){
			List<String> s = bd.getStringList("homes");
			s.clear();
			for(Home l : homes){
				String str = l.getString();
				s.add(str);
			}
			bd.set("homes", s);
			try {
				bd.save(bdfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Home desserializar(String s){

		Home h = null;
		String[] param = s.split(";");
		
		if(param.length != 4) return null;
		
		String sp = param[0];
		String sloc = param[1];
		String sn = param[2];
		String sa = param[3];
		
		try{
			Player p = Bukkit.getServer().getPlayer(sp);
			String[] xyz = sloc.split(":");
			int x = Integer.parseInt(xyz[0]);
			int y = Integer.parseInt(xyz[1]);
			int z = Integer.parseInt(xyz[2]);
			World w = Bukkit.getServer().getWorld(xyz[3]);
			Location loc = new Location(w, x, y, z);
			String nome = sn;
			boolean loja;
			if(sa.equals("true")){
				loja = true;
			}else loja = false;
			h = new Home(p, loc, nome, loja);
		}catch(Exception e){
			e.printStackTrace();
		}
		return h;
	}

}
