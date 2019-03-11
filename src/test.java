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

public class test extends JFrame
	implements JMapViewerEventListener, MouseListener, MouseMotionListener{
	
	// Attributes
	private static CSVReader csvreader;
	private static Schools schools;
	private static double radius = 40; // set radius for point buffer here
	
	private static final long serialVersionUID = 1L;
	private JMapViewer mapv = null;
	
	public test(Schools schools) {
		super("A TinyJMapViewer Demo to Get You Started!");
		setSize(700,700);
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
		Schools schools = new Schools();

		try {
			csvreader = new CSVReader(new FileReader("schools.csv"), '\t');
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("something went wrong");
		}
		
		schools.importData(csvreader, radius);
		
		new test(schools).setVisible(true);
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
