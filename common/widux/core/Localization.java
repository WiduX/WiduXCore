package widux.core;

import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Properties;

import net.minecraft.util.StringTranslate;

/**
 * Simple mod localization class.
 *
 * @author Jimeo Wan
 * @license Public domain
 *
 */
public class Localization
{
	private static final String DEFAULT_LANGUAGE = "en_US";
	private String modName = null;
	private String loadedLanguage = null;
	private Properties defaultMappings = new Properties();
	private Properties mappings = new Properties();

	/**
	 * Loads the mod's localization files. All language files must be stored in
	 * "[modname]/lang/", in .properties files. (ex: for the mod 'invtweaks',
	 * the french translation is in: "invtweaks/lang/fr_FR.properties")
	 *
	 * @param modName The mod name
	 */
	public Localization(String modName)
	{
		if (modName == null)
		{
			throw new InvalidParameterException("Mod name can't be null");
		}
		this.modName = modName;
		load(getCurrentLanguage());
	}

	/**
	 * Get a string for the given key, in the currently active translation.
	 *
	 * @param key
	 * @return
	 */
	public synchronized String get(String key)
	{
		String currentLanguage = getCurrentLanguage();
		if (!currentLanguage.equals(loadedLanguage))
		{
			load(currentLanguage);
		}
		return mappings.getProperty(key, defaultMappings.getProperty(key, key));
	}

	private void load(String newLanguage)
	{
		defaultMappings.clear();
		mappings.clear();
		try
		{
			InputStream langStream = Localization.class.getResourceAsStream(modName + "/lang/" + newLanguage + ".properties");
			InputStream defaultLangStream = Localization.class.getResourceAsStream(modName + "/lang/" + DEFAULT_LANGUAGE + ".properties");
			mappings.load((langStream == null) ? defaultLangStream : langStream);
			defaultMappings.load(defaultLangStream);

			if (langStream != null)
			{
				langStream.close();
			}
			defaultLangStream.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		loadedLanguage = newLanguage;
	}

	private static String getCurrentLanguage()
	{
		return StringTranslate.getInstance().getCurrentLanguage();
	}

}