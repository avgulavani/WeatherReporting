package compare.files;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.util.ProjectUtil;

public class CompareWeatherData {

		@SuppressWarnings("unused")
		public static boolean compareWeatherData(String apifile,String uifile) 
	 {
			

		 Map<Object, Object> apimap=new HashMap<Object, Object>();
		 Map<Object, Object> uimap=new HashMap<Object, Object>();
		 List<String> apilist = null,uilist = null;
		 
				apilist=ProjectUtil.readTextFile(apifile);	
				uilist=ProjectUtil.readTextFile(uifile);	
				
				apimap=ProjectUtil.convertListtoMap(apilist);
				uimap=ProjectUtil.convertListtoMap(uilist);
			
				return ProjectUtil.checkWeatherData(apimap,uimap);	 
		
		
	 }
	
}
