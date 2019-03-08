import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;

public class TinyDemo extends JFrame 
	implements JMapViewerEventListener, MouseListener, MouseMotionListener{

  private static final long serialVersionUID = 1L;

  private JMapViewer mapv = null;

  public TinyDemo() {
      super("A Tiny JMapViewer Demo to Get You Started!");
      setSize(700, 700);
      mapv = new JMapViewer();
      mapv.addJMVListener(this);
      add(mapv);
      MapMarkerDot ucsb = new MapMarkerDot("UCSB", new Coordinate(34.41254, -119.84813));
      mapv.addMapMarker(ucsb);
      mapv.addMouseListener(this);
      mapv.addMouseMotionListener(this);
      
      //  mapv.addMapMarker(new MapMarkerDot(43.604, 1.444));
      //  mapv.addMapMarker(new MapMarkerCircle(53.343, -6.267, 0.666));
      //  mapv.addMapRectangle(new MapRectangleImpl(new Coordinate(53.343, -6.267), new Coordinate(43.604, 1.444)));
  }


  public static void main(String[] args) {
      new TinyDemo().setVisible(true);
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
public void mouseDragged(MouseEvent e) {
}

@Override
public void mouseMoved(MouseEvent e) {
	  Point p = e.getPoint();
      boolean cursorHand = mapv.getAttribution().handleAttributionCursor(p);
      if (cursorHand) {
          mapv.setCursor(new Cursor(Cursor.HAND_CURSOR));
      } else {
          mapv.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      }
	
}
@Override
public void mouseClicked(MouseEvent e) {
	 if (e.getButton() == MouseEvent.BUTTON1) {
  		  MapMarkerDot tmp = new MapMarkerDot("POI", mapv.getPosition(e.getX(), e.getY()));	 
  		  mapv.addMapMarker(tmp);
        }
	
}
@Override
public void mouseEntered(MouseEvent e) {
}
@Override
public void mouseExited(MouseEvent e) {
}
@Override
public void mousePressed(MouseEvent e) {
}
@Override
public void mouseReleased(MouseEvent e) {
}

}