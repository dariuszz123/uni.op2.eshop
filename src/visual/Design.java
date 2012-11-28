package visual;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
//import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import base.CartProduct;
import base.Eshop;
import base.Order;
import base.OrderProduct;
import base.Product;
import base.SpringUtilities;

public class Design extends JFrame {

	private static final long serialVersionUID = -6326135914126279074L;
	protected Eshop eshop;

	private JTable eshopProductsListTable;
	private JTable eshopProductsCartTable;
	private JLabel eshopTotalCartPrice;
	private Button eshopOrderButton;
	private JTable orderOrdersTable;
	protected JTabbedPane tabs;
	
	public JTable getEshopProductsListTable() {
		return eshopProductsListTable;
	}

	public void setEshopProductsListTable(JTable eshopProductsListTable) {
		this.eshopProductsListTable = eshopProductsListTable;
	}

	public JTable getEshopProductsCartTable() {
		return eshopProductsCartTable;
	}

	public void setEshopProductsCartTable(JTable eshopProductsCartTable) {
		this.eshopProductsCartTable = eshopProductsCartTable;
	}

	public JTable getOrderOrdersTable() {
		return orderOrdersTable;
	}

	public void setOrderOrdersTable(JTable orderOrdersTable) {
		this.orderOrdersTable = orderOrdersTable;
	}

	/**
	 * 
	 * @param eshop
	 */
	public Design(Eshop eshop) {
		this.setEshop(eshop);

		setLayout(new BorderLayout());

		/* Main window */
		setTitle("Eshop");
		setSize(990, 600);
		setMinimumSize(new Dimension(990, 600));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		/* Tabs */
		this.tabs = new JTabbedPane();
		tabs.addTab("Eshop", this.eshopPanel());
		tabs.addTab("Orders", this.ordersPanel());
		tabs.setSelectedIndex(0);
		add(tabs);
	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	private String formatDouble(double d) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(d);
	}

	/**
	 * 
	 */
	private void updateEshopTotalPrice() {
		this.eshopTotalCartPrice.setText("Total price " + Product.getCurrency()
				+ ": " + this.formatDouble(eshop.getCart().getTotalPrice()));
	}

	/**
	 * 
	 */
	public void updateEshopProductsTableRows() {
		DefaultTableModel model = (DefaultTableModel) this.eshopProductsListTable
				.getModel();
		model.getDataVector().removeAllElements();

		// Add already existing products
		Iterator<Product> productsIterator = this.eshop.getProducts()
				.iterator();

		while (productsIterator.hasNext()) {
			Product product = productsIterator.next();
			model.addRow(new Object[] { product.getIsbn(), product.getName(),
					product.getDescription(),
					this.formatDouble(product.getPrice()), product.getWeight(),
					product.getQuantity() });
		}

		this.eshopProductsListTable.getSelectionModel().clearSelection();
		model.fireTableDataChanged();
	}

	/**
	 * 
	 */
	public void updateEshopCartTableRows() {
		DefaultTableModel model = (DefaultTableModel) this.eshopProductsCartTable
				.getModel();
		model.getDataVector().removeAllElements();

		Iterator<CartProduct> productsIterator = this.eshop.getCart()
				.getProducts().iterator();

		while (productsIterator.hasNext()) {
			CartProduct product = productsIterator.next();
			model.addRow(new Object[] { product.getIsbn(), product.getName(),
					product.getQuantity(),
					this.formatDouble(product.getTotalPrice()) });
		}

		this.eshopProductsCartTable.getSelectionModel().clearSelection();
		model.fireTableDataChanged();
	}

	/**
	 * 
	 */
	public void updateOrdersOrdersTableRows() {
		DefaultTableModel model = (DefaultTableModel) this.orderOrdersTable
				.getModel();
		model.getDataVector().removeAllElements();

		Format dateFormatter;
		dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Iterator<Order> ordersIterator = this.eshop.getOrders().iterator();

		while (ordersIterator.hasNext()) {
			Order order = ordersIterator.next();
			model.addRow(new Object[] {
					order.getNum(),
					dateFormatter.format(order.getCreatedAt()),
					order.getName(),
					order.getSurname(),
					this.formatDouble(order.getTotalPrice()),
					order.getProducts().size() + " ("
							+ order.getTotalProductsQuantity() + ")" });
		}

		this.eshopProductsCartTable.getSelectionModel().clearSelection();
		model.fireTableDataChanged();
	}

	/**
	 * Styled JLabel
	 * 
	 * @param text
	 * @param style
	 * @param fontSize
	 * @param borderTop
	 * @param borderLeft
	 * @param borderBottom
	 * @param borderRight
	 * @return
	 */
	private JPanel styledLabel(String text, int style, int fontSize,
			int borderTop, int borderLeft, int borderBottom, int borderRight) {
		JPanel p = new JPanel();
		Label label = new Label(text);

		p.setLayout(new GridLayout(1, 1));
		p.setBorder(BorderFactory.createEmptyBorder(borderTop, borderLeft,
				borderBottom, borderRight));

		label.setFont(new Font("Arial", style, fontSize));

		p.add(label);
		return p;
	}

	/**
	 * 
	 */
	private void addProductFrame() {
		final JFrame frame = new JFrame("Add product");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel p2 = new JPanel();
		p2.setLayout(new SpringLayout());

		int fields = 0;

		fields++;
		p2.add(new JLabel("Isbn: "));
		final JTextField productIsbn = new JTextField(15);
		p2.add(productIsbn);

		fields++;
		p2.add(new JLabel("Name: "));
		final JTextField productName = new JTextField(15);
		p2.add(productName);

		fields++;
		p2.add(new JLabel("Description: "));
		final JTextField productDescription = new JTextField(15);
		p2.add(productDescription);

		fields++;
		p2.add(new JLabel("Price " + Product.getCurrency() + ": "));
		final JTextField productPrice = new JTextField(15);
		p2.add(productPrice);

		fields++;
		p2.add(new JLabel("Weight: "));
		final JTextField productWeight = new JTextField(15);
		p2.add(productWeight);

		fields++;
		p2.add(new JLabel("Quantity: "));
		final JTextField productQuantity = new JTextField(15);
		p2.add(productQuantity);

		SpringUtilities.makeCompactGrid(p2, fields, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		p1.add(p2, BorderLayout.CENTER);

		Button addButton = new Button("Add");

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Double price = new Double(productPrice.getText());
					Integer weight = new Integer(productWeight.getText());
					Integer quantity = new Integer(productQuantity.getText());

					eshop.addProduct(productIsbn.getText(),
							productName.getText(),
							productDescription.getText(), price, weight,
							quantity);
					frame.dispose();
					updateEshopProductsTableRows();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),
							"Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		p1.add(addButton, BorderLayout.PAGE_END);

		frame.add(p1);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	private void addOrderFrame() {
		final JFrame frame = new JFrame("Order details");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel p2 = new JPanel();
		p2.setLayout(new SpringLayout());

		int fields = 0;
		/**
		 * private String name; private String surname; private String country;
		 * private String city; private String address; private String postCode;
		 */
		fields++;
		p2.add(new JLabel("Name: "));
		final JTextField orderName = new JTextField(15);
		p2.add(orderName);

		fields++;
		p2.add(new JLabel("Surname: "));
		final JTextField orderSurname = new JTextField(15);
		p2.add(orderSurname);

		fields++;
		p2.add(new JLabel("Country: "));
		final JTextField orderCountry = new JTextField(15);
		p2.add(orderCountry);

		fields++;
		p2.add(new JLabel("City: "));
		final JTextField orderCity = new JTextField(15);
		p2.add(orderCity);

		fields++;
		p2.add(new JLabel("Address: "));
		final JTextField orderAddress = new JTextField(15);
		p2.add(orderAddress);

		fields++;
		p2.add(new JLabel("Post code: "));
		final JTextField orderPostCode = new JTextField(15);
		p2.add(orderPostCode);

		SpringUtilities.makeCompactGrid(p2, fields, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		p1.add(p2, BorderLayout.LINE_END);

		Button orderButton = new Button("Confirm");

		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					eshop.createOrder(orderName.getText(),
							orderSurname.getText(), orderCountry.getText(),
							orderCity.getText(), orderAddress.getText(),
							orderPostCode.getText());
					eshopOrderButton.setEnabled(true);
					frame.dispose();
					updateOrdersOrdersTableRows();
					updateEshopCartTableRows();
					updateEshopTotalPrice();

					JOptionPane.showMessageDialog(null,
							"Order created successfully", "Message",
							JOptionPane.PLAIN_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(),
							"Message", JOptionPane.PLAIN_MESSAGE);
				}

			}
		});

		p1.add(orderButton, BorderLayout.PAGE_END);

		/**
		 * 
		 */
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				eshopOrderButton.setEnabled(true);
				frame.dispose();
			}
		});

		frame.add(p1);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	private void viewOrderFrame(int orderNum) {
		JFrame frame = new JFrame("Order details");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		DefaultTableModel model = new DefaultTableModel();

		// Disable editing
		@SuppressWarnings("serial")
		JTable orderProductsTable = new JTable(model) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};

		// Allow select only one row
		orderProductsTable
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		orderProductsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// Add scroller
		JScrollPane pane = new JScrollPane(orderProductsTable);
		pane.setPreferredSize(new Dimension(600, 200));
		p1.add(pane, BorderLayout.CENTER);
		// DefaultTableModel models = (DefaultTableModel)
		// this.eshopProductsListTable.getModel();

		// Columns
		model.addColumn("ISBN");
		model.addColumn("Name");
		model.addColumn("Price " + Product.getCurrency());
		model.addColumn("Quantity");
		model.addColumn("Total Price " + Product.getCurrency());

		TableColumnModel tcm = orderProductsTable.getColumnModel();

		tcm.getColumn(0).setMinWidth(140);
		tcm.getColumn(1).setMinWidth(100);
		tcm.getColumn(2).setMinWidth(100);
		tcm.getColumn(3).setMinWidth(50);
		tcm.getColumn(4).setMinWidth(100);

		Order order = this.eshop.findOrderByNum(orderNum);

		Iterator<OrderProduct> productsIterator = order.getProducts()
				.iterator();

		while (productsIterator.hasNext()) {
			OrderProduct product = productsIterator.next();
			model.addRow(new Object[] { product.getIsbn(), product.getName(),
					this.formatDouble(product.getPrice()),
					product.getQuantity(),
					this.formatDouble(product.getTotalPrice()) });
		}

		JPanel p2 = new JPanel();
		p2.setLayout(new SpringLayout());
		p2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		int fields = 0;

		fields++;
		p2.add(new JLabel("Name: "));
		p2.add(new JLabel(order.getName()));

		fields++;
		p2.add(new JLabel("Surname: "));
		p2.add(new JLabel(order.getSurname()));

		fields++;
		p2.add(new JLabel("Country: "));
		p2.add(new JLabel(order.getCountry()));

		fields++;
		p2.add(new JLabel("City: "));
		p2.add(new JLabel(order.getCity()));

		fields++;
		p2.add(new JLabel("Address: "));
		p2.add(new JLabel(order.getAddress()));

		fields++;
		p2.add(new JLabel("Post code: "));
		p2.add(new JLabel(order.getPostCode()));

		SpringUtilities.makeCompactGrid(p2, fields, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		p1.add(p2, BorderLayout.LINE_END);

		p1.add(new Label("Total price " + Product.getCurrency() + ": "
				+ this.formatDouble(order.getTotalPrice())),
				BorderLayout.PAGE_END);

		frame.add(p1);

		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("serial")
	private JPanel eshopProductsList() {
		JPanel p1 = new JPanel();

		p1.setLayout(new BorderLayout());
		p1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());

		p2.add(this.styledLabel("Products", Font.BOLD, 14, 0, 0, 15, 0),
				BorderLayout.LINE_START);

		JPanel p3 = new JPanel();

		// Create new product button
		Button createProductButton = new Button("Add product");

		createProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProductFrame();
			}
		});

		p3.add(createProductButton);

		// Add quantity
		Button addQuantity = new Button("Add quantity");

		addQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = eshopProductsListTable.getSelectedRow();

				if (selectedRowIndex >= 0) {

					String str = (String) JOptionPane.showInputDialog(null,
							"Add quantity: ", "Add quantity",
							JOptionPane.PLAIN_MESSAGE, null, null, "1");

					Integer quantity = Integer.valueOf(str);

					if (quantity > 0) {

						String isbn = (String) eshopProductsListTable
								.getValueAt(selectedRowIndex, 0);

						eshop.addQuantity(eshop.findProduct(isbn), quantity);

						updateEshopProductsTableRows();

					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Select product first!", "Message",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

		p3.add(addQuantity);

		p2.add(p3, BorderLayout.LINE_END);

		p1.add(p2, BorderLayout.PAGE_START);

		DefaultTableModel model = new DefaultTableModel();

		// Disable editing
		this.eshopProductsListTable = new JTable(model) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};

		// Allow select only one row
		this.eshopProductsListTable
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.eshopProductsListTable
				.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// Add scroller
		JScrollPane pane = new JScrollPane(this.eshopProductsListTable);

		p1.add(pane, BorderLayout.CENTER);
		// DefaultTableModel models = (DefaultTableModel)
		// this.eshopProductsListTable.getModel();

		// Columns
		model.addColumn("ISBN");
		model.addColumn("Name");
		model.addColumn("Description");
		model.addColumn("Price " + Product.getCurrency());
		model.addColumn("Weight");
		model.addColumn("Avail. Qty");

		TableColumnModel tcm = this.eshopProductsListTable.getColumnModel();

		tcm.getColumn(0).setMinWidth(140);
		tcm.getColumn(1).setMinWidth(100);
		tcm.getColumn(2).setMinWidth(200);
		tcm.getColumn(3).setMinWidth(50);

		// Add already existing produts
		this.updateEshopProductsTableRows();

		// Add to cart
		Button addToCartButton = new Button("Add to cart");

		addToCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = eshopProductsListTable.getSelectedRow();

				if (selectedRowIndex >= 0) {

					String str = (String) JOptionPane.showInputDialog(null,
							"quantity: ", "quantity",
							JOptionPane.PLAIN_MESSAGE, null, null, "1");

					Integer quantity = 0;

					if (str != null) {
						quantity = Integer.valueOf(str);
					}

					if (quantity > 0) {

						String isbn = (String) eshopProductsListTable
								.getValueAt(selectedRowIndex, 0);

						try {
							eshop.getCart().addProduct(eshop.findProduct(isbn),
									quantity);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									e1.getMessage(), "Warning",
									JOptionPane.WARNING_MESSAGE);
						}

						updateEshopCartTableRows();
						updateEshopTotalPrice();
						updateEshopProductsTableRows();

					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Select product first!", "Message",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

		addToCartButton.setPreferredSize(new Dimension(100, 50));

		p1.add(addToCartButton, BorderLayout.PAGE_END);

		return p1;
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("serial")
	public JPanel eshopCart() {
		JPanel p4 = new JPanel();
		p4.setLayout(new BorderLayout());
		p4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		p4.add(this.styledLabel("Cart", Font.BOLD, 14, 0, 0, 15, 0),
				BorderLayout.PAGE_START);

		DefaultTableModel model = new DefaultTableModel();

		// Disable editing
		this.eshopProductsCartTable = new JTable(model) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};

		// Allow select only one row
		this.eshopProductsCartTable
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.eshopProductsCartTable
				.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// Add scroller
		JScrollPane pane = new JScrollPane(this.eshopProductsCartTable);
		pane.getViewport().setPreferredSize(new Dimension(280, 0));

		// Columns
		model.addColumn("isbn");
		model.addColumn("Name");
		model.addColumn("Qty");
		model.addColumn("Total price " + Product.getCurrency());

		TableColumnModel tcm = this.eshopProductsCartTable.getColumnModel();

		tcm.getColumn(1).setMinWidth(100);
		tcm.getColumn(3).setMinWidth(100);

		// hide column
		tcm.getColumn(0).setMaxWidth(0);
		tcm.getColumn(0).setMinWidth(0);
		tcm.getColumn(0).setPreferredWidth(0);

		p4.add(pane, BorderLayout.CENTER);

		JPanel p5 = new JPanel();
		p5.setLayout(new BorderLayout());

		// Remove from cart
		Button cartRemveButton = new Button("Remove from cart");

		cartRemveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = eshopProductsCartTable.getSelectedRow();

				if (selectedRowIndex >= 0) {
					String isbn = (String) eshopProductsCartTable.getValueAt(
							selectedRowIndex, 0);

					try {
						eshop.getCart().removeProduct(isbn,
								eshop.findProduct(isbn));
					} catch (NoSuchElementException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),
								"Warning", JOptionPane.WARNING_MESSAGE);
					}

					updateEshopTotalPrice();
					updateEshopProductsTableRows();
					updateEshopCartTableRows();
				} else {
					JOptionPane.showMessageDialog(null,
							"Select product first!", "Message",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

		p5.add(cartRemveButton, BorderLayout.PAGE_START);

		// Total price
		this.eshopTotalCartPrice = new JLabel();
		this.eshopTotalCartPrice.setPreferredSize(new Dimension(200, 60));
		this.eshopTotalCartPrice.setVerticalAlignment(JLabel.CENTER);
		p5.add(eshopTotalCartPrice, BorderLayout.CENTER);

		// Set existing price
		this.updateEshopTotalPrice();

		// Order button
		eshopOrderButton = new Button("Order");
		eshopOrderButton.setPreferredSize(new Dimension(200, 50));
		eshopOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (eshop.getCart().getProducts().size() > 0) {
					eshopOrderButton.setEnabled(false);
					addOrderFrame();
				} else {
					JOptionPane.showMessageDialog(null, "Cart is empty!",
							"Message", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

		p5.add(eshopOrderButton, BorderLayout.PAGE_END);

		p4.add(p5, BorderLayout.PAGE_END);

		// Add existing products
		this.updateEshopCartTableRows();
		updateEshopTotalPrice();

		return p4;
	}

	/**
	 * 
	 * @return
	 */
	public JPanel eshopPanel() {
		JPanel base = new JPanel();
		base.setLayout(new BorderLayout());

		base.add(this.eshopProductsList(), BorderLayout.CENTER);
		// base.add(this.eshopMainButtons(), BorderLayout.PAGE_END);
		base.add(this.eshopCart(), BorderLayout.LINE_END);

		return base;
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("serial")
	public JPanel ordersPanel() {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		DefaultTableModel model = new DefaultTableModel();

		p.add(this.styledLabel("Orders", Font.BOLD, 14, 0, 0, 15, 0),
				BorderLayout.PAGE_START);

		// Disable editing
		this.orderOrdersTable = new JTable(model) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};

		// Allow select only one row
		this.orderOrdersTable
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.orderOrdersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// Add scroller
		JScrollPane pane = new JScrollPane(this.orderOrdersTable);

		p.add(pane, BorderLayout.CENTER);
		// DefaultTableModel models = (DefaultTableModel)
		// this.eshopProductsListTable.getModel();

		// Columns
		model.addColumn("Num");
		model.addColumn("Created at");
		model.addColumn("Name");
		model.addColumn("Surname");
		model.addColumn("Total price " + Product.getCurrency());
		model.addColumn("Products");

		TableColumnModel tcm = this.orderOrdersTable.getColumnModel();

		tcm.getColumn(0).setMinWidth(40);
		tcm.getColumn(1).setMinWidth(150);
		tcm.getColumn(2).setMinWidth(100);
		tcm.getColumn(3).setMinWidth(150);
		tcm.getColumn(4).setMinWidth(100);

		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(13, 1));
		p2.setPreferredSize(new Dimension(250, 0));

		Button orderDetailsButton = new Button("Order details");

		orderDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = orderOrdersTable.getSelectedRow();

				if (selectedRowIndex >= 0) {
					Integer id = (Integer) orderOrdersTable.getValueAt(
							selectedRowIndex, 0);
					viewOrderFrame(id);
					// JOptionPane.showMessageDialog(null, "id"+id, "Message",
					// JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Select order first!",
							"Message", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

		p2.add(orderDetailsButton);

		p2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		p2.setName("buttons_list");
		p.add(p2, BorderLayout.LINE_END);
		updateOrdersOrdersTableRows();

		return p;
	}

	/**
	 * 
	 * @return
	 */
	public Eshop getEshop() {
		return eshop;
	}

	/**
	 * 
	 * @param eshop
	 */
	public void setEshop(Eshop eshop) {
		this.eshop = eshop;
	}

	/**
	 *
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Design ex = new Design(new Eshop());
				ex.setVisible(true);
			}
		});
	}

}
