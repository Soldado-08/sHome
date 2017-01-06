package me.soldado.home;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Core {

	public Main plugin;
	public Config cfg;
	public Mensagens msg;

	public Core(Main plugin)
	{
		this.plugin = plugin;
		this.cfg = plugin.cfg;
		this.msg = plugin.msg;
	}
	
	ArrayList<Home> homes = new ArrayList<Home>();
	
	public void recarregarHome(Home h){
		if(!homes.contains(h)) homes.add(h);
	}
	
	public void usarHome(Player p, String s){
		boolean match = false;
		Home home = null;
		for(Home h : homes){
			if(h.getDono().equals(p) && h.getNome().equalsIgnoreCase(s)){
				match = true;
				home = h;
				break;
			}
		}
		if(match){
			p.sendMessage(msg.teleportado.replace("%home%", s));
			Location loc = home.getLoc();
			int delay = 5;
			if(p.hasPermission(cfg.permdelayvip)) delay = 0;
			new BukkitRunnable()
			{
				public void run()
				{
					p.teleport(loc);
				}
			}.runTaskLater(plugin, delay * 20L);
		}else p.sendMessage(msg.homenaoencontrada);
	}
	
	@SuppressWarnings("deprecation")
	public void usarHomeAlheia(Player p, String a, String s){
		OfflinePlayer dono = null;
		try{
			dono = Bukkit.getServer().getOfflinePlayer(a);
		}catch(Exception e){
			p.sendMessage(msg.jogadornaoencontrado);
			return;
		}
		if(dono != null){
			boolean match = false;
			Home home = null;
			for(Home h : homes){
				if(h.getDono().equals(dono) && h.getNome().equalsIgnoreCase(s)){
					match = true;
					home = h;
					break;
				}
			}
			if(match){
				if(home.isLoja()){
					p.sendMessage(msg.teleportado.replace("%home%", s));
					Location loc = home.getLoc();
					int delay = 5;
					if(p.hasPermission(cfg.permdelayvip)) delay = 0;
					new BukkitRunnable()
					{
						public void run()
						{
							p.teleport(loc);
						}
					}.runTaskLater(plugin, delay * 20L);
				}else p.sendMessage(msg.homeprivada);
			}else p.sendMessage(msg.homenaoencontrada);
		}
	}
	
	public void criarHome(Player p, String s){
		int max = getMaxHome(p);
		int atual = getNumeroHomes(p);
		Location loc = p.getLocation();
		
		if(atual < max){
			if(!mesmoNome(p,s)){
				Home h = new Home(p, loc, s, false);
				homes.add(h);
				p.sendMessage(plugin.msg.homecriada.replace("%nome%", s));
			}else p.sendMessage(msg.jaexiste);
		}else p.sendMessage(msg.maxhomes);
	}
	
	public void definirLoja(Player p, String s){
		boolean match = false;
		Home home = null;
		for(Home h : homes){
			if(h.getDono().equals(p) && h.getNome().equalsIgnoreCase(s)){
				match = true;
				home = h;
				break;
			}
		}
		if(match){
			home.setLoja(true);
			p.sendMessage(msg.lojadefinida);
		}else p.sendMessage(msg.homenaoencontrada);
	}
	
	public void deletarHome(Player p, String s){
		boolean match = false;
		Home home = null;
		for(Home h : homes){
			if(h.getDono().equals(p) && h.getNome().equalsIgnoreCase(s)){
				match = true;
				home = h;
				break;
			}
		}
		if(match){
			homes.remove(home);
			p.sendMessage(msg.homedeletada);
		}else p.sendMessage(msg.homenaoencontrada);
	}
	
	public int getNumeroHomes(Player p){
		int i = 0;
		for(Home h : homes){
			if(h.getDono().equals(p)) i++;
		}
		return i;
	}
	
	public int getMaxHome(Player p){
		int returnval = 0;
		if(p.hasPermission(cfg.homepermlimitenormal)){
			returnval = cfg.homelimitenormal;
		}else if(p.hasPermission(cfg.homepermlimitevip1)){
			returnval = cfg.homelimitevip1;
		}else if(p.hasPermission(cfg.homepermlimitevip2)){
			returnval = cfg.homelimitevip2;
		}
		return returnval;
	}
	
	public boolean isAdm(Player p){
		if(p.hasPermission(cfg.homepermlimiteadm)) return true;
		else return false;
	}
	
	public boolean mesmoNome(Player p, String s){
		for(Home h : homes){
			if(h.getDono().equals(p) && h.getNome().equals(s)){
				return true;
			}
		}
		return false;
	}
	
}
