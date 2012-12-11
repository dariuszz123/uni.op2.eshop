package visual;

import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import base.Eshop;
import base.EshopV2;
import base.EshopV3;

@SuppressWarnings("serial")
public class DesignV3 extends DesignV2 {

	public DesignV3(Eshop eshop) {
		super(eshop);
	}
	
	public JPanel ordersPanel() {
		JPanel p = super.ordersPanel();

		Component[] components = p.getComponents();
		Component component;
		for (int i = 0; i < components.length; i++) {
			component = components[i];
			if (component.getName() != null
					&& component.getName().equals("buttons_list")) {

				JPanel cpanel = (JPanel) component;

				// sort by num button
				Button sortByNumButton = new Button("Sort by Num ASC");
				sortByNumButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (eshop instanceof EshopV2) {
							((EshopV3) eshop).sortOrdersByNum();
							updateOrdersOrdersTableRows();
						}
					}
				});

				cpanel.add(sortByNumButton);

				// sort by price button
				Button sortByPrice = new Button("Sort by Price DESC");
				sortByPrice.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (eshop instanceof EshopV2) {
							((EshopV3) eshop).sortOrdersByPrice();
							updateOrdersOrdersTableRows();
						}
					}
				});

				cpanel.add(sortByPrice);

			}
		}

		return p;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Design ex;
				try {
					ex = new DesignV3(new EshopV3());
					ex.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),
							"Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}

}
