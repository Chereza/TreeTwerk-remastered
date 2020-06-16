package treetwerk.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class YamlManager 
{

	private File file;
	private FileConfiguration fileConf;
	
	public YamlManager(String path, String nameAndExtension) 
	{
		file = new File(path, nameAndExtension);
		
		if (!file.getParentFile().exists()) 
		{
			file.getParentFile().mkdirs();
		}
		
		if (!file.exists()) 
		{
			try 
			{
				file.createNewFile();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
				return;
			}
		}
				
		//create yaml configuration and load into file
		fileConf = YamlConfiguration.loadConfiguration(file); 

	}
	
	
	
	
	public File getFile() 
	{
		return file;
	}
	
	public FileConfiguration getConfiguration() 
	{
		return fileConf;
	}
	
	public void save() 
	{
		try
		{
			fileConf.save(file);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void load() 
	{
		try 
		{
			fileConf.load(file);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean isEmpty() 
	{
		return file.length() == 0;
	}
	
}
