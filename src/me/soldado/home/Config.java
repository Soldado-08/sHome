package me.soldado.home;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	
	public Main plugin;
	
	public Config(Main plugin)
	{
		this.plugin = plugin;
	}
	
	File configFile;
	FileConfiguration config;
	
	int homelimitenormal;
	int homelimitevip1;
	int homelimitevip2;
	int delaynormal;
	int delayvip;
	String homepermlimitenormal;
	String homepermlimitevip1;
	String homepermlimitevip2;
	String homepermlimiteadm;
	String permcriarhome;
	String permdeletarhome;
	String permcriarloja;
	String permusarhome;
	String permdelayvip;
	String chave;

	private void setValores(){
		this.homelimitenormal = getInt("LimiteHomeNormal");
		this.homelimitevip1 = getInt("LimiteHomeVip1");
		this.homelimitevip2 = getInt("LimiteHomeVip2");
		this.delaynormal = getInt("DelayNormal");
		this.delayvip = getInt("DelayVip");
		this.homepermlimitenormal = getString("PermissaoHomeNormal");
		this.homepermlimitevip1 = getString("PermissaoHomeVip1");
		this.homepermlimitevip2 = getString("PermissaoHomeVip2");
		this.permcriarhome = getString("PermissaoCriarHome");
		this.permcriarloja = getString("PermissaoCriarLoja");
		this.permdeletarhome = getString("PermissaoDeletarHome");
		this.permusarhome = getString("PermissaoUsarHome");
		this.permdelayvip = getString("PermissaoDelayVip");
		this.chave = getString("Chave");
	}

	public void iniciarConfig(){

		if (configFile == null) {
			configFile = new File(plugin.getDataFolder(), "config.yml");
		}
		if (!configFile.exists()) {
			plugin.saveResource("config.yml", false);
		}
		config = YamlConfiguration.loadConfiguration(configFile);
		setValores();
	}
	
	private int getInt(String s){
		return config.getInt(s);
	}
	
	private String getString(String s){
		return config.getString(s);
	}
}
