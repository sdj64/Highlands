package highlands;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class LanguageLoader {
	
	/**
	 * List directory contents for a resource folder. Not recursive.
	 * This is basically a brute-force implementation.
	 * Works for regular files and also JARs.
	 * 
	 * @author Greg Briggs
	 * @param clazz Any java class that lives in the same place as the resources you want.
	 * @param path Should end with "/", but not start with one.
	 * @return Just the name of each member item, not the full paths.
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static String[] getResourceListing(Class clazz, String path) throws URISyntaxException, IOException {
		URL dirURL = clazz.getClassLoader().getResource(path);
		
		if (dirURL != null && dirURL.getProtocol().equals("file")) {
			return new File(dirURL.toURI()).list();
		}

		if (dirURL == null) {
			String me = clazz.getName().replace(".", "/")+".class";
			dirURL = clazz.getClassLoader().getResource(me);
		}

		if (dirURL.getProtocol().equals("jar")) {
			String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!")); //strip out only the JAR file
			JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
			Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
			HashSet<String> result = new HashSet<String>(); //avoid duplicates in case it is a subdirectory
			
			while (entries.hasMoreElements()) {
				String name = entries.nextElement().getName();
				
				if (name.startsWith(path)) { //filter according to the path
					String entry = name.substring(path.length());
					int checkSubdir = entry.indexOf("/");
					
					if (checkSubdir >= 0) {
						// if it is a subdirectory, we just return the directory name
						entry = entry.substring(0, checkSubdir);
					}
					
					result.add(entry);
				}
			}
			
			jar.close();
			return result.toArray(new String[result.size()]);
		}

		throw new UnsupportedOperationException("Cannot list files for URL " + dirURL);
	}
	
	public static final String LOCALIZATION_PATH = "assets/" + HighlandsMain.modid + "/lang/";
	
	public static void loadLanguages()
	{
		String[] langList = new String[0];

		try
		{
			langList = getResourceListing(HighlandsMain.class, LOCALIZATION_PATH);
		}
		catch (Exception ex) {
			System.err.println("Failed to get list of language files in \"" + LOCALIZATION_PATH + "\".");
			ex.printStackTrace();
		}

		for (String langFilename : langList)
		{
			try
			{
				String path = "/" + LOCALIZATION_PATH + langFilename;
				System.out.println("Attempting to load language file at \"" + path + "\"");
				int dot = langFilename.lastIndexOf('.');
				
				if (dot != -1)
				{
					String lang = langFilename.substring(0, dot);
					String extension = langFilename.substring(dot + 1);
					boolean xml = extension.equals("xml");
					LanguageRegistry.instance().loadLocalization(path, lang, xml);
				}
				else
				{
					System.err.println("Found but failed to load filename: " + langFilename);
				}
			}
			catch (Exception ex) {
				System.err.println("Language loading failed while attempting to load \"/" + LOCALIZATION_PATH + langFilename + "\"");
				ex.printStackTrace();
			}
		}
	}
	
}
