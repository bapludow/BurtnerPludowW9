/**
 * To do:
 * Set initial display of map
 * Display name on mouse over
 * Only show high schools
 * Change buttons (1 vs 3)
 */

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;

import au.com.bytecode.opencsv.CSVReader;

public class SchoolMap extends JFrame
	implements JMapViewerEventListener, MouseListener, MouseMotionListener {
	
	// Attributes
	private static double radius = 40; // set radius for point buffer here
	private static CSVReader csvreader;
	private Schools schools;
	private static final long serialVersionUID = 1L;
	private JMapViewer mapv = null;
	private static int numSchools;
	
	// Map Constructor
	public SchoolMap(Schools schools) {
		super("Santa Barbara Area Schools");
		setSize(1000,500);
		mapv = new JMapViewer();
		mapv.addJMVListener(this);
		add(mapv);
		
		for (int i = 0; i < schools.size(); i++) {
			MapMarkerDot tmp = new MapMarkerDot(new Coordinate(schools.get(i).getCenter().getX(),
					schools.get(i).getCenter().getY()));		
			mapv.addMapMarker(tmp);	
		}
		
		mapv.setDisplayToFitMapMarkers();
		mapv.setZoom(8);

		mapv.addMouseListener(this);
		mapv.addMouseMotionListener(this);
	}
	
	public static void main(String[] args) {
		Schools schools = new Schools();

		try {
			csvreader = new CSVReader(new FileReader("schools.csv"), '\t');
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("something went wrong");
		}
		
		schools.importData(csvreader, radius);
		numSchools = schools.size();
		
		new SchoolMap(schools).setVisible(true);
	}
	
	private void updateZoomParameters() {
	}
	
	@Override
	public void processCommand(JMVCommandEvent command) {
	if (command.getCommand().equals(JMVCommandEvent.COMMAND.ZOOM) ||
			command.getCommand().equals(JMVCommandEvent.COMMAND.MOVE)) {
		      }
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		java.awt.Point p = e.getPoint();
		boolean cursorHand = mapv.getAttribution().handleAttributionCursor(p);
		if(cursorHand) {
			mapv.setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else {
			mapv.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {	
// Having issues with making this component work
//		if (e.getButton() == MouseEvent.BUTTON1) {
//			Point temp = new Point((double)e.getX(), (double)e.getY());
//			for (int i = 0; i < numSchools; i++) {
//		        	if (schools.get(i).isInside(temp)) {
//		        		MapMarkerDot tmp = new MapMarkerDot(schools.get(i).getName(), new Coordinate(schools.get(i).getCenter().getX(),
//		    					schools.get(i).getCenter().getY()));
//		    			mapv.addMapMarker(tmp);
//		        	}
//			}
//		}
//		
//		if (e.getButton() == MouseEvent.BUTTON3) {
//				for (int i = 0; i < numSchools; i++) {
//					if(schools.get(i).getType() == "High School") {
//						MapMarkerDot tmp = new MapMarkerDot(new Coordinate(schools.get(i).getCenter().getX(),
//								schools.get(i).getCenter().getY()));
//						mapv.addMapMarker(tmp);
//						}
//					}
//				}
		
// copied this code of Jano's in just to see if the mouse click component worked at al
		 if (e.getButton() == MouseEvent.BUTTON1) {
	  		  MapMarkerDot tmp = new MapMarkerDot("POI", mapv.getPosition(e.getX(), e.getY()));	 
	  		  mapv.addMapMarker(tmp);
	        }
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}