package acs.data;

public class Location {
	private double lat;
	private double lng;
	
	public Location() {
		// TODO Auto-generated constructor stub
	}

	public Location(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	double getLat() {
		return lat;
	}
	
	public double getLng() {
		return lng;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public void setLng(double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "Location [lat=" + lat + ", lng=" + lng + "]";
	}

}

