package intefaces;

import java.awt.*;
import java.awt.TrayIcon.MessageType;


public class Notificacion {
	
	private String header;
	private String body;
	
	public Notificacion (String h, String b) {
		this.header = h;
		this.body = b;
	}
	
    public void displayTray() throws AWTException, java.net.MalformedURLException {

        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/resources/notificacionn.png"));
        TrayIcon trayIcon = new TrayIcon(image, "Mensaje Recibido");
        trayIcon.setImageAutoSize(true);
        tray.add(trayIcon);
        trayIcon.displayMessage(header, "El Usuario " + body + " te ha mencionado.", MessageType.NONE);
        tray.remove(trayIcon);
    }
    
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}