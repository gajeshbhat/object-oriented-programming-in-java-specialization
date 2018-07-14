package guiexperiments;


import java.awt.Color;

import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;
import parsing.ParseFeed;
import java.util.ArrayList;

import module4.EarthquakeCityMap;
import module4.EarthquakeCityMap.*;

public class EarthquakeMarker extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UnfoldingMap earthquakeMap;
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	private static float EXTREEME = (float)4.5;
	private static float DANGER = (float)4.0;
	private static float MANAGEABLE = (float)3.0;
	private static Color YELLOW = new Color(255, 209, 0);
	private static Color RED = new Color(252, 4, 4);
	private static Color BLUE = new Color(71, 83, 255);
	private static Color GREY = new Color(218, 218, 218);
	
	public SimplePointMarker getquakeLocationMarker(Location quakeLocation) {
		SimplePointMarker quakeLocationMarker = new SimplePointMarker(quakeLocation);
		return quakeLocationMarker;
	}
	
	public Location getquakeLocation(double latitude,double longitude) {
		Location quakeLocation = new Location(latitude,longitude );
		return quakeLocation;
	}
	
	public static SimplePointMarker setMarkerColorYellow(SimplePointMarker earthquakeLocationMarker) {
		earthquakeLocationMarker.setColor(YELLOW.hashCode());
		return earthquakeLocationMarker;
	}
	public static SimplePointMarker setMarkerColorRed(SimplePointMarker earthquakeLocationMarker) {
		earthquakeLocationMarker.setColor(RED.hashCode());
		return earthquakeLocationMarker;
	}
	
	public static SimplePointMarker setMarkerColorBlue(SimplePointMarker earthquakeLocationMarker) {
		earthquakeLocationMarker.setColor(BLUE.hashCode());
		return earthquakeLocationMarker;
	}
	
	public static SimplePointMarker setMarkerColorGrey(SimplePointMarker earthquakeLocationMarker) {
		earthquakeLocationMarker.setColor(GREY.hashCode());
		return earthquakeLocationMarker;
	}
	
	// Gets the final marker i.e driver for other getters and setters of marker and its properties.
	
	public SimplePointMarker getquakeLocationMapMarker(PointFeature pointFeature) {
		
		SimplePointMarker earthquakeLocationMarker = this.getquakeLocationMarker(pointFeature.location);
		float ritcherMagnitude = (float) pointFeature.getProperties().values().toArray()[1];
		
		
		if(ritcherMagnitude >= EXTREEME) {
			setMarkerColorRed(earthquakeLocationMarker);
		}
		else if(ritcherMagnitude >= DANGER && ritcherMagnitude < EXTREEME) {
			setMarkerColorYellow(earthquakeLocationMarker);
		}
		else if(ritcherMagnitude <= MANAGEABLE ) {
			setMarkerColorBlue(earthquakeLocationMarker);
		}
		else {
			setMarkerColorGrey(earthquakeLocationMarker);
		}
		
		
		
		return earthquakeLocationMarker;
	}
	
	// Returns the markers list
	
	public List<Marker> getearthquakeLocationMarkerMapList() {
		List<Marker> markers = new ArrayList<Marker>();
		List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
		for (PointFeature pointFeature : earthquakes) {
			markers.add(getquakeLocationMapMarker(pointFeature));
		}
		return markers;
	}
	
	public void setup() {
	earthquakeMap = new UnfoldingMap(this, 200, 50, 700, 500,new OpenStreetMap.OpenStreetMapProvider());	 
	earthquakeMap.addMarkers(getearthquakeLocationMarkerMapList());
	MapUtils.createDefaultEventDispatcher(this, earthquakeMap);
	earthquakeMap.zoomToLevel(2);
	}
	
	public void draw() {
		background(50);
		resize(800,500);
		earthquakeMap.draw();
}
	
}
