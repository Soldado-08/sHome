package me.soldado.home;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Comandos implements CommandExecutor {

	public Main plugin;

	public Comandos(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("sethome")){
				if(p.hasPermission(plugin.cfg.permcriarhome)){
					if(args.length == 1){
						String s = args[0];
						plugin.core.criarHome(p, s);
					}else p.sendMessage(ChatColor.RED + "Formato incorreto! Use: /sethome <nome>");
				}else{
					p.sendMessage(plugin.msg.semperm);
				}
				return true;
			}else if(cmd.getName().equalsIgnoreCase("deletarhome")){
				if(p.hasPermission(plugin.cfg.permdeletarhome)){
					if(args.length == 1){
						String s = args[0];
						plugin.core.deletarHome(p, s);
					}else p.sendMessage(ChatColor.RED + "Formato incorreto! Use: /deletarhome <nome>");
				}else{
					p.sendMessage(plugin.msg.semperm);
				}
				return true;
			}else if(cmd.getName().equalsIgnoreCase("loja")){
				if(p.hasPermission(plugin.cfg.permcriarloja)){
					if(args.length == 1){
						String s = args[0];
						plugin.core.definirLoja(p, s);
					}else p.sendMessage(ChatColor.RED + "Formato incorreto! Use: /loja <nome>");
				}else{
					p.sendMessage(plugin.msg.semperm);
				}
				return true;
			}else if(cmd.getName().equalsIgnoreCase("home")){
				if(p.hasPermission(plugin.cfg.permusarhome)){
					if(args.length == 1){
						String s = args[0];
						plugin.core.usarHome(p, s);
					}else if(args.length == 2){
						String a = args[0];
						String s = args[1];
						plugin.core.usarHomeAlheia(p, a, s);
					}else p.sendMessage(ChatColor.RED + "Formato incorreto! Use: /home <nome> ou /home <jogador> <home>");
				}else{
					p.sendMessage(plugin.msg.semperm);
				}
				return true;
			}
		}
		return false;
	}
	
	public void iniciarComandos(){
		plugin.getCommand("sethome").setExecutor(this);
		plugin.getCommand("deletarhome").setExecutor(this);
		plugin.getCommand("loja").setExecutor(this);
		plugin.getCommand("home").setExecutor(this);
	}

}
