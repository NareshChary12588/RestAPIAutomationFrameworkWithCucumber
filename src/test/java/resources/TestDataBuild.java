package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlaceSerialize;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlaceSerialize addPlacePayLoad(String name,String language, String address) {
		AddPlaceSerialize obj=new AddPlaceSerialize();
		Location loc=new Location();
		List<String> listTypes= new ArrayList<String>();
		listTypes.add("shoe park");
		listTypes.add("shop");
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		obj.setLocation(loc);
		obj.setAccuracy(50);
		obj.setName(name);
		obj.setAddress(address);
		obj.setLanguage(language);
		
		obj.setPhone_number("(+91) 983 893 3937");
		obj.setWebsite("https://rahulshettyacademy.com");
		obj.setTypes(listTypes);
		return obj;
		
	}
	
	public String  deletePlacePayLoad(String placeId) {
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";		
	}

}
