
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Dashboard extends JFrame {

    private JPanel mainPanel; // the panel where content is loaded dynamically

    public Dashboard() {
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top navigation
        JPanel navPanel = new JPanel();
        JButton itemBtn = new JButton("Item");
        JButton customerBtn = new JButton("Customer");
        JButton orderBtn = new JButton("Order");
        navPanel.add(itemBtn);
        navPanel.add(customerBtn);
        navPanel.add(orderBtn);
        add(navPanel, BorderLayout.NORTH);

        // Main panel where views are swapped
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Button actions
        customerBtn.addActionListener(e -> showCustomerTabs());

        // Initial view
        mainPanel.add(new JLabel("Welcome to Dashboard", JLabel.CENTER), BorderLayout.CENTER);
    }

    private void showCustomerTabs() {
        JTabbedPane tabbedPane = new JTabbedPane();

        // Placeholder panel for Add Customer (will load form when selected)
        JPanel addCustomerPanel = new JPanel(new BorderLayout());
        tabbedPane.add("Add Customer", addCustomerPanel);

        // Static panels for other tabs
        tabbedPane.add("Update Customer", new JLabel("Update Customer View"));
        tabbedPane.add("Delete Customer", new JLabel("Delete Customer View"));

        // Listen for tab changes
        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            String selectedTitle = tabbedPane.getTitleAt(selectedIndex);

            if (selectedTitle.equals("Add Customer") && addCustomerPanel.getComponentCount() == 0) {
                // Load the form only once
                addCustomerPanel.add(createAddCustomerForm(), BorderLayout.CENTER);
                addCustomerPanel.revalidate();
                addCustomerPanel.repaint();
            }
        });

        // Load the tabbed pane into main panel
        mainPanel.removeAll();
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createAddCustomerForm() {
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        formPanel.add(new JLabel("Name:"));
        formPanel.add(new JTextField(15));
        formPanel.add(new JLabel("Email:"));
        formPanel.add(new JTextField(15));
        formPanel.add(new JLabel(""));
        formPanel.add(new JButton("Submit"));
        return formPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard().setVisible(true));
    }
}
