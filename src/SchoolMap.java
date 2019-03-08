import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;

import au.com.bytecode.opencsv.CSVReader;
	
public class SchoolMap extends JFrame
		implements JMapViewerEventListener, MouseListener, MouseMotionListener{
	
	// Attributes
	private static CSVReader csvreader;
	private static ArrayList<String[]> data;
	private static Schools schools;
	private static double radius = 40; // set radius for point buffer here
		
	private static final long serialVersionUID = 1L;
	private JMapViewer mapv = null;
		
	// Map class
	public SchoolMap() {
		super("A TinyJMapViewer Demo to Get You Started!");
		
		// read the file and split by tabulator
		ArrayList<String[]> data = new ArrayList<String[]>();
		Schools schools = new Schools();

			try {
				csvreader = new CSVReader(new FileReader("schools.csv"), '\t');
				String[] record = null;
				while((record = csvreader.readNext()) != null) {
				data.add(record);
			}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// split each record into individual variables and add as a new school
			for (int i = 0; i < data.size(); i++) {
				StringTokenizer tokenizer = new StringTokenizer(data.get(i)[0], ",");
				ArrayList<String> hold = new ArrayList<String>(4);
				while(tokenizer.hasMoreTokens()) {
					hold.add(tokenizer.nextToken());
				}
				schools.add(new school(hold.get(0), Double.valueOf(hold.get(1)), Double.valueOf(hold.get(2)), hold.get(3), radius));
			}setSize(700,700);
		mapv = new JMapViewer();
		mapv.addJMVListener(this);
		add(mapv);
		for (int i = 0; i < schools.size(); i++) {
			MapMarkerDot tmp = new MapMarkerDot(schools.get(i).getName(), new Coordinate(schools.get(i).getCenter().getX(),
					schools.get(i).getCenter().getY()));
			mapv.addMapMarker(tmp);
			}
			mapv.addMouseListener(this);
			mapv.addMouseMotionListener(this);
		}
		
		public static void main(String[] args) {
			new SchoolMap().setVisible(true);
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
	public void mouseMoved(MouseEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getButton() == MouseEvent.BUTTON1) {
			// 2 lines of code to add a POI
			// This is assignment
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
