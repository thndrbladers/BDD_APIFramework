package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlaceData() {

		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setLanguage("French-IN");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setName("Frontline house");
		ap.setWebsite("http://google.com");
		ap.setAddress("29, side layout, cohen 09");

		Location l = new Location();
		l.setLat(-34.383494);
		l.setLng(34.427362);

		ap.setLocation(l);

		List<String> ty = new ArrayList<String>();
		ty.add("shoe park");
		ty.add("shop");

		ap.setTypes(ty);

		return ap;

	}

	public AddPlace addPlaceData(String name, String language, String address) {

		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setLanguage(language);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setName(name);
		ap.setWebsite("http://google.com");
		ap.setAddress(address);

		Location l = new Location();
		l.setLat(-34.383494);
		l.setLng(34.427362);

		ap.setLocation(l);

		List<String> ty = new ArrayList<String>();
		ty.add("shoe park");
		ty.add("shop");

		ap.setTypes(ty);

		return ap;
	}

	public String deletePlaceData(String place_id) {
		System.out.println(place_id);

		return "{\r\n"
				+ "\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}";

	}
}
