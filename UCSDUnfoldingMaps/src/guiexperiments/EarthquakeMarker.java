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
public class EarthquakeMarker extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UnfoldingMap earthquakeMap;
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	
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
	
	public SimplePointMarker getquakeLocationMapMarker(Location earthquakeLocation) {
		SimplePointMarker earthquakeLocationMarker = this.getquakeLocationMarker(earthquakeLocation);
		setMarkerColorYellow(earthquakeLocationMarker);
		return earthquakeLocationMarker;
	}
	
	// Returns the markers list
	
	public List<Marker> getearthquakeLocationMarkerMapList() {
		List<Marker> markers = new ArrayList<Marker>();
		List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
		for (PointFeature pointFeature : earthquakes) {
			markers.add(getquakeLocationMapMarker(pointFeature.location));
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
