package visual;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import base.Eshop;
import base.EshopV4;

@SuppressWarnings("serial")
public class DesignV4 extends DesignV3 {
	
	public DesignV4(Eshop eshop) {
		super(eshop);
		
		if (eshop instanceof EshopV4) {
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent w) {
					try {
						save();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),
								"Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}
	}
	
	public void save() throws FileNotFoundException, IOException {
		((EshopV4)eshop).save();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Design ex;
				try {
					ex = new DesignV4(EshopV4.load());
					ex.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),
							"Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
	
}
