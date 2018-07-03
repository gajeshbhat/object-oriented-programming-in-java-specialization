package guiexperiments;


import java.awt.Color;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class EarthquakeMarker extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UnfoldingMap earthquakeMap;
	
	public SimplePointMarker getquakeLocationMarker(Location quakeLocation) {
		SimplePointMarker quakeLocationMarker = new SimplePointMarker(quakeLocation);
		return quakeLocationMarker;
	}
	
	public Location getquakeLocation(double latitude,double longitude) {
		Location quakeLocation = new Location(latitude,longitude );
		return quakeLocation;
	}
	
	public static SimplePointMarker setMarkerColorYellow(SimplePointMarker earthquakeLocationMarker) {
		Color sunnyYellow = new Color(255, 209, 0);
		earthquakeLocationMarker.setColor(sunnyYellow.hashCode());
		return earthquakeLocationMarker;
	}
	
	// Gets the final marker i.e driver for other getters and setters of marker and its properties.
	
	public SimplePointMarker getquakeLocationMapMarker(double latitude,double longitude) {
		Location earthquakeLocation = this.getquakeLocation(latitude, longitude);
		SimplePointMarker earthquakeLocationMarker = this.getquakeLocationMarker(earthquakeLocation);
		setMarkerColorYellow(earthquakeLocationMarker);
		return earthquakeLocationMarker;
	}
	
	
	public void setup() {
	earthquakeMap = new UnfoldingMap(this, 200, 50, 700, 500,new OpenStreetMap.OpenStreetMapProvider());	
	SimplePointMarker chillequakeMarker = getquakeLocationMapMarker(-38.14f, -73.04f); 
	earthquakeMap.addMarker(chillequakeMarker);
	MapUtils.createDefaultEventDispatcher(this, earthquakeMap);
	earthquakeMap.zoomToLevel(2);
	}
	
	public void draw() {
		background(50);
		resize(800,500);
		earthquakeMap.draw();
}
	
}
