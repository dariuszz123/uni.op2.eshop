import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Design extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Eshop eshop;
	
	public Design(Eshop eshop) {
		this.setEshop(eshop);
	}

	public Eshop getEshop() {
		return eshop;
	}

	public void setEshop(Eshop eshop) {
		this.eshop = eshop;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				Design ex = new Design(new Eshop());
				ex.setVisible(true);
			}
		});
	}
	
}
