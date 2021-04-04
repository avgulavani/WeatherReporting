package compare.files;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import project.util.ProjectUtil;

public class CompareWeatherData {

	
	private static final String apifile = System.getProperty("user.dir") + File.separator + "outdir" + File.separator
			+ "apidata.txt";
	private static final String uifile = System.getProperty("user.dir") + File.separator + "outdir" + File.separator+ "uidata.txt";

	@SuppressWarnings("unused")
	@Test
	public static void compareWeatherData()
	 {
			
	
		 Map<Object, Object> apimap=new HashMap<Object, Object>();
		 Map<Object, Object> uimap=new HashMap<Object, Object>();

		 List<String> apilist=ProjectUtil.readTextFile(apifile);
		 List<String> uilist=ProjectUtil.readTextFile(uifile);
	
		 apimap=ProjectUtil.convertListtoMap(apilist);
		 uimap=ProjectUtil.convertListtoMap(uilist);
		 
		 Assert.assertTrue(ProjectUtil.checkWeatherData(apimap,uimap));
			  
	 }
	

	

}
