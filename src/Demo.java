
public class Demo {
	
}
/*
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

public class Demo extends JFrame {
	
	private Eshop shop;
	private JLabel statusbar;
	private JPopupMenu menu;
	
	
	public Demo(Eshop shop) {
		this.shop = shop;
		initUI();
	}

	public final void initUI() {

		JPanel panel = new JPanel();
		getContentPane().add(panel);

		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(5, 4, 5, 5));

		JButton quitButton = new JButton("Quit");
		quitButton.setToolTipText("A button component");

		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		panel.add(quitButton);

		int i;
		for (i = 0; i < 20; i++) {
			JButton test = new JButton("test" + i);
			
			test.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					statusbar.setText(shop.calcTotalOrderedProducts() + "/" + shop.calcTotalOrdersAmount());
				}
			});
			
			panel.add(test);
		}

		String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };

		// Create the combo box, select item at index 4.
		// Indices start at 0, so 4 specifies the pig.
		JComboBox petList = new JComboBox(petStrings);
		petList.setSelectedIndex(4);
		petList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {	
				statusbar.setText(event.toString());
			}
		});

		panel.add(petList);
		
		JList list = new JList(petStrings); //data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		
		panel.add(list);
		
		JMenuBar menubar = new JMenuBar();
		// ImageIcon icon = new ImageIcon(getClass().getResource("exit.png"));

		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);

		JMenuItem eMenuItem = new JMenuItem("Exit");
		eMenuItem.setMnemonic(KeyEvent.VK_C);
		eMenuItem.setToolTipText("Exit application");
		eMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}

		});

		file.add(eMenuItem);

		JMenu view = new JMenu("View");
		view.setMnemonic(KeyEvent.VK_V);

		JCheckBoxMenuItem sbar = new JCheckBoxMenuItem("Show StatuBar");
		sbar.setState(true);

		sbar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (statusbar.isVisible()) {
					statusbar.setVisible(false);
				} else {
					statusbar.setVisible(true);
				}
			}

		});

		view.add(sbar);

		menubar.add(file);

		menubar.add(view);

		setJMenuBar(menubar);

		statusbar = new JLabel(" Statusbar");
		statusbar.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		add(statusbar, BorderLayout.SOUTH);

		// toolkit = this.getToolkit();

		menu = new JPopupMenu();
		JMenuItem menuItemBeep = new JMenuItem("Beep");

		menuItemBeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// toolkit.beep();
			}
		});

		menu.add(menuItemBeep);

		JMenuItem menuItemClose = new JMenuItem("Close");
		menuItemClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menu.add(menuItemClose);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					menu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		setTitle("Quit button");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				Demo ex = new Demo(new Eshop());
				ex.setVisible(true);
			}
		});
	}
}*/