package visual;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

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
		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		if(eshop instanceof EshopV2) {
			PieDataset dataset = ((EshopV2) this.eshop).createDataset();
			// based on the dataset we create the chart
			JFreeChart chart = ChartFactory.createPieChart3D("Available quantity",          // chart title
		            dataset,                // data
		            true,                   // include legend
		            true,
		            false);

		    PiePlot3D plot = (PiePlot3D) chart.getPlot();
		    plot.setStartAngle(290);
		    plot.setDirection(Rotation.CLOCKWISE);
		    plot.setForegroundAlpha(0.5f);
			
			// we put the chart into a panel
			ChartPanel chartPanel = new ChartPanel(chart);
			// default size
			chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
			
			p1.add(chartPanel, BorderLayout.CENTER);
		}
		return p1;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Design ex = new DesignV2(new EshopV2());
				ex.setVisible(true);
			}
		});
	}

}
