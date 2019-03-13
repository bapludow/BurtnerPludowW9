
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.OsmMercator;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;

import au.com.bytecode.opencsv.CSVReader;

public class SchoolMap extends JFrame
	implements JMapViewerEventListener, MouseMotionListener, ActionListener {
	
	// Attributes
	private double radius = 150; // set radius for name display hover here
	
	private JMapViewer mapv = null;
	private JButton hsButton = new JButton("Only show High Schools");
	private boolean hsFlag = false;
	
	private ArrayList<School> schools = new ArrayList<School>();
	private CSVReader csvreader;
	
	private static final long serialVersionUID = 1L;
		
	// Map Constructor
	public SchoolMap() {
		super("Los Angeles Area Schools");
		setSize(1080,720);
		
		JPanel main = new JPanel(new BorderLayout());
		mapv = new JMapViewer();
		mapv.setDisplayPosition(new Coordinate(34,-118), 9);
		main.add(mapv);
		
		JPanel button = new JPanel(); // new panel that holds button
		hsButton.addActionListener(this); // add action listener that listens to this frame
		button.add(hsButton); // add button to panel
		main.add(button, BorderLayout.NORTH); // add one panel to other panel
		
		add(main, BorderLayout.CENTER);
		
		mapv.addMouseMotionListener(this);		
		
		try {
			String[] record = null;
			csvreader = new CSVReader(new FileReader(new File(SchoolMap.class.getResource("schools.csv").toURI())));
			
			while((record = csvreader.readNext()) != null) {
				String name = new String (record[0]);
				MapMarkerDot mapDots = new MapMarkerDot(new Coordinate(Double.parseDouble(record[1]), Double.parseDouble(record[2])));
				mapDots.setBackColor(Color.green);
				String type = new String (record[3]);
				schools.add(new School(name, mapDots, type));
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("something went wrong");
			}
		
		for (int i = 0; i < schools.size(); i++) {
			mapv.addMapMarker(schools.get(i).getMapDots());
		}
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	public static void main(String[] args) {
		new SchoolMap();
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
		Coordinate geo = mapv.getPosition(e.getPoint()); // use map marker instead of current set up so you can use this
		
		for (int i = 0; i < schools.size(); i++) {
			MapMarkerDot tmp = schools.get(i).getMapDots();
			tmp.setName("");
			double dist = OsmMercator.getDistance(geo.getLat(), geo.getLon(), tmp.getLat(), tmp.getLon());
			if(dist < radius) {
				tmp.setName(schools.get(i).getName());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == hsButton) {
			hsFlag = !hsFlag;
			mapv.removeAllMapMarkers();
			
			if(hsFlag) {
				hsButton.setText("Show all schools");
				
				for (int i = 0; i < schools.size(); i++) {
					MapMarkerDot tmp = schools.get(i).getMapDots();
					tmp.setBackColor(Color.blue);
					
					if(schools.get(i).getType().equals("High School")) {
						mapv.addMapMarker(tmp);
					}
				}
				
			} else {
				hsButton.setText("Only show High Schools");
				
				for (int i = 0; i < schools.size(); i++) {
					MapMarkerDot tmp = schools.get(i).getMapDots();
					tmp.setBackColor(Color.green);
					mapv.addMapMarker(tmp);
					}
					
				}
			}		
	}

}