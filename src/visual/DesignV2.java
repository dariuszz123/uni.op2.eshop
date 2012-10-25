package visual;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import base.Eshop;
import base.EshopV2;

@SuppressWarnings("serial")
public class DesignV2 extends Design {

	public DesignV2(Eshop eshop) {
		super(eshop);
		
		this.tabs.addTab("Stats", this.statsPanel());
		this.tabs.setEnabledAt(2, false);
		
		if(eshop instanceof EshopV2) {
			this.tabs.setEnabledAt(2, true);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public JPanel statsPanel() {
		return new JPanel();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Design ex = new DesignV2(new Eshop());
				ex.setVisible(true);
			}
		});
	}

}
