package guiexperiments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;
import parsing.ParseFeed;

public class LifeExpectency extends PApplet {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private UnfoldingMap lifeExpectencyMap;
	HashMap<String, Float> LifeExpectencyValueMap = getLifeExpectencyValueMap();
	List<Feature> countriesList = GeoJSONReader.loadData(this, "countries.geo.json");
	List<Marker> countryMarkers = MapUtils.createSimpleMarkers(countriesList);
	
	public HashMap<String, Float> getLifeExpectencyValueMap(){
		String FILENAME="LifeExpectancyWorldBank.csv";
		HashMap<String, Float> lifeExpectencyValueMap = ParseFeed.loadLifeExpectancyFromCSV(this, FILENAME);
		return lifeExpectencyValueMap;
	}
	
	private void shadeCountries() {
		for (Marker marker : countryMarkers) {
			String countryId = marker.getId();
			if(LifeExpectencyValueMap.containsKey(countryId)) {
				float lifeExp = LifeExpectencyValueMap.get(countryId);
				int colorLevel = (int) map(lifeExp,40,90,10,255);
				marker.setColor(color(255-colorLevel,100,colorLevel));
			}
			else {
				marker.setColor(color(150,150,150));
			}
		}
	}
	
	public void setup() {
		lifeExpectencyMap = new UnfoldingMap(this, 200, 50, 700, 500,new OpenStreetMap.OpenStreetMapProvider());	 
		MapUtils.createDefaultEventDispatcher(this, lifeExpectencyMap);
		lifeExpectencyMap.zoomToLevel(2);
		lifeExpectencyMap.addMarkers(countryMarkers);
		shadeCountries();
		}
		
		public void draw() {
			background(50);
			resize(800,500);
			lifeExpectencyMap.draw();
	}

}
