package widux.core;

import java.io.File;

import net.minecraftforge.common.Configuration;

/**
 * 
 * @author WiduX
 *
 */
public class ConfigAssist
{
	
	//private String mod;
	private Configuration config;
	
	public ConfigAssist(String modName, File configFile)
	{
		//this.mod = modName;
		this.config = new Configuration(configFile);
	}
	
	public void init()
	{
		config.load();
	}
	
	public void save()
	{
		config.save();
	}
	
	public int getBlockID(int defaultID, String nameInConfig, String comment)
	{
		if(comment != null)
		{
			return this.config.getBlock(nameInConfig, defaultID, comment).getInt();
		}
		else
		{
			return this.config.getBlock(nameInConfig, defaultID).getInt();
		}
	}
	
	public int getItemID(int defaultID, String nameInConfig, String comment)
	{
		if(comment != null)
		{
			return this.config.getItem(nameInConfig, defaultID, comment).getInt();
		}
		else
		{
			return this.config.getItem(nameInConfig, defaultID).getInt();
		}
	}
	
	public boolean getBoolean(boolean defaultValue, String nameInConfig, String category, String comment)
	{
		if(comment != null)
		{
			return this.config.get(category, nameInConfig, defaultValue, comment).getBoolean(false);
		}
		else
		{
			return this.config.get(category, nameInConfig, defaultValue).getBoolean(false);
		}
	}
	
	public int getInt(int defaultValue, String nameInConfig, String category, String comment)
	{
		if(comment != null)
		{
			return this.config.get(category, nameInConfig, defaultValue, comment).getInt();
		}
		else
		{
			return this.config.get(category, nameInConfig, defaultValue).getInt();
		}
	}
	
}
