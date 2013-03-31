package widux.core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 
 * @author WiduX
 *
 */
public class UpdateCheck
{
	
	private URL versionFile;
	private String mod;
	private String remoteVersion;
	private String localVersion;
	
	public UpdateCheck(String modName, URL updateUrl, String currentVersion)
	{
		versionFile = updateUrl;
		mod = modName;
		localVersion = currentVersion;
	}
	
	public boolean checkForUpdate(boolean sendMessage)
	{
		if(fetchFile(versionFile)) // If the fetching and reading was successful
		{
			if(checkVersions(remoteVersion, localVersion)) // If there is a new version available
			{
				if(sendMessage)
				{
					System.out.println("[" + mod + "] There's a new version available! Current: " + localVersion + ", Newest: " + remoteVersion);
					return true;
				}
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	private boolean fetchFile(URL file)
	{
		try
		{
			InputStream in = file.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			String content;
			boolean eof = false;
			int iteration = 0;
			
			while(!eof)
			{
				content = reader.readLine();
				iteration++;
				if(content == null)
				{
					eof = true;
				}
				if(iteration == 1)
				{
					remoteVersion = content;
				}
			}
			
			return true;
		}
		catch(Exception e)
		{
			System.out.println("[" + mod + "] Severe error while retrieving version info!");
			return false;
		}
	}
	
	private boolean checkVersions(String remote, String current)
	{
		String[] currentDiv = current.split("\\.");
		String[] remoteDiv = remote.split("\\.");
		int[] currentVers = new int[currentDiv.length];
		int[] remoteVers = new int[remoteDiv.length];
		
		try
		{
			for(int c = 0; c < currentDiv.length; c++)
			{
				currentVers[c] = Integer.parseInt(currentDiv[c]);
				System.out.println("Parse, C: " + currentVers[c]);
			}
			
			for(int r = 0; r < remoteDiv.length; r++)
			{
				remoteVers[r] = Integer.parseInt(remoteDiv[r]);
				System.out.println("Parse, R: " + currentVers[r]);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("[" + mod + "] Severe error while parsing version info!");
		}
		
		for(int part = 0; part < remoteVers.length; part++)
		{
			if(currentVers[part] < remoteVers[part])
			{
				return true;
			}
			else
			{
				continue;
			}
		}
		
		return false;
		
	}
	
}
