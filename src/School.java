import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

public class School {

	// Attributes
	private String name;
	private String type;
	private MapMarkerDot mapDots;
	
	// Constructor
	public School(String name, MapMarkerDot mapDot, String type) {
		this.mapDots = mapDot;
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MapMarkerDot getMapDots() {
		return mapDots;
	}

	public void setMapDots(MapMarkerDot mapDots) {
		this.mapDots = mapDots;
	}

	// Getters & Setters

}
