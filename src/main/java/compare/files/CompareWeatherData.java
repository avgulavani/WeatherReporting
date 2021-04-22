package compare.files;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
				System.out.println(apimap);
			
				
				
				
				Set<Object> commonkeys =new HashSet<Object>(apimap.keySet());
				commonkeys.retainAll((Collection<?>) uimap);
				System.out.println(commonkeys);
				
				
				return ProjectUtil.checkWeatherData(apimap,uimap);	 
				//return ProjectUtil.checkWeatherData(apimap,uimap);	 
		
		
	 }
	
}
