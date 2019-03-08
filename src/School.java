
public class School extends PointBuffer {

	// Attributes
	private String name;
	private String type;
	// lat = y?, lon = x?
	
	// Constructor
	public School(String name, double lat, double lon, String type, double radius) {
		super(new Point(lat,lon), radius);
		this.name = name;
		this.type = type;
	}

	// Getters & Setters
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
	
}
