package me.soldado.home;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	Mensagens msg;
	Config cfg;
	Core core;
	Comandos cmds;
	Backup bck;
	
	public void onEnable(){
		
		this.getLogger().info("Plugin CubosHome habilitado!");
		this.getLogger().info("Autor: Soldado_08");
		
		msg = new Mensagens(this);
		cfg = new Config(this);
		core = new Core(this);
		cmds = new Comandos(this);
		bck = new Backup(this);

		bck.iniciarBanco();
		cmds.iniciarComandos();
		cfg.iniciarConfig();
		msg.iniciarMensagens();
		
	}
	
	public void onDisable(){
		
		this.getLogger().info("Plugin CubosHome desabilitado!");
		this.getLogger().info("Autor: Soldado_08");
		
	}
	
}
