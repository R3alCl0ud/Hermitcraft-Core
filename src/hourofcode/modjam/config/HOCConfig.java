package hourofcode.modjam.config;

import java.io.File;

import hourofcode.modjam.HourOfCode;
import net.minecraftforge.common.config.Configuration;

public final class HOCConfig 
{

	public static boolean verboseDebug;
	
	public static void init(File configFile)
	{
	
		Configuration config = new Configuration(configFile);
		
		try
		{
			config.load();
			
			verboseDebug = config.get("verboseDebugging", "misc", false, "More Verbose Logging").getBoolean();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(config.hasChanged())
			{
				config.save();
			}
		}
		
	}
	public static File configFile(String fileName) {return new File(HourOfCode.CONFIG_DIR, fileName);}
	
	
}
