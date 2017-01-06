package me.soldado.home;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Mensagens {

	public Main plugin;

	public Mensagens(Main plugin)
	{
		this.plugin = plugin;
	}
	
	File msgFile;
	FileConfiguration msgs;
	
	String semperm;
	String maxhomes;
	String homecriada;
	String homenaoencontrada;
	String lojadefinida;
	String jaexiste;
	String homedeletada;
	String teleportado;
	String jogadornaoencontrado;
	String homeprivada;
	
	public void iniciarMensagens(){

		if (msgFile == null) {
			msgFile = new File(plugin.getDataFolder(), "mensagens.yml");
		}
		if (!msgFile.exists()) {
			plugin.saveResource("mensagens.yml", false);
		}
		msgs = YamlConfiguration.loadConfiguration(msgFile);
		setValores();
	}
	
	private void setValores(){
		this.semperm = getString("SemPermissao").replace("&", "§");
		this.maxhomes = getString("MaxHomes").replace("&", "§");
		this.homecriada = getString("HomeCriada").replace("&", "§");
		this.homenaoencontrada = getString("HomeNaoEncontrada").replace("&", "§");
		this.lojadefinida = getString("LojaDefinida").replace("&", "§");
		this.jaexiste = getString("HomeComMesmoNome").replace("&", "§");
		this.homedeletada = getString("HomeDeletada").replace("&", "§");
		this.jogadornaoencontrado = getString("JogadorNaoEncontrado").replace("&", "§");
		this.homeprivada = getString("HomePrivada").replace("&", "§");
		this.teleportado = getString("Teleportado").replace("&", "§");
	}
	
	private String getString(String s){
		return msgs.getString(s);
	}
}
