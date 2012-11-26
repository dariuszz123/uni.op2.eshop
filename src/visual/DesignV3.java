package visual;

import javax.swing.SwingUtilities;

import base.Eshop;
import base.EshopV3;

@SuppressWarnings("serial")
public class DesignV3 extends DesignV2 {

	public DesignV3(Eshop eshop) {
		super(eshop);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Design ex = new DesignV3(new EshopV3());
				ex.setVisible(true);
			}
		});
	}

}
