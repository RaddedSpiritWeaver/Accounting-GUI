package views;

import dataModels.Cart;
import dataModels.Product;
import enums.ViewPanels;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class ShoppingPanel extends JPanel {

    static int banThreshold = -1000;

    private MainFrame mainFrame;
    private JPanel currentItemViewPanel;
    private JPanel itemContainerPanelRef;

    public ShoppingPanel(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
        this.currentItemViewPanel = new ItemViewPanel(mainFrame.getProgram().getProducts());
        init();
    }

    private void init() {
        // need to statically set position for objects
        this.setLayout(null);

        // top bar
        JPanel topPanel = new JPanel();
        topPanel.setBounds(0,0,600, 100);
        topPanel.setBackground(Color.red);

        JButton viewProfileButton = new JButton("View Profile");
        viewProfileButton.addActionListener(e -> {
            this.mainFrame.changeView(ViewPanels.Profile);
        });

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> {
            if (mainFrame.getProgram().getCurrentUser().getDebt() < banThreshold) {
                System.out.println("shopping panel - checkout button - to much debt");
                JDialog errorDialog = new JDialog(this.mainFrame, "no check out");
                errorDialog.setSize(200, 100);

                JLabel textLabel = new JLabel("your Debt: " + mainFrame.getProgram().getCurrentUser().getDebt());
                errorDialog.add(textLabel);

                errorDialog.setVisible(true);
                return;
            }

            if(!this.mainFrame.getProgram().getCurrentUser().hasEmptyCart())
                this.mainFrame.changeView(ViewPanels.Checkout);
            else {
                System.out.println("shopping panel - checkout button - nothing in cart, no check out");
                JDialog errorDialog = new JDialog(this.mainFrame, "no check out");
                errorDialog.setSize(200, 100);

                JLabel textLabel = new JLabel("nothing in cart, no check out");
                errorDialog.add(textLabel);

                errorDialog.setVisible(true);
            }
        });

        JTextField searchField = new JTextField(40);

        JButton searchButton = new JButton("search");
        searchButton.addActionListener(e -> {
            LinkedList<Product> results = new LinkedList<>();

            if (!searchField.getText().equals("")) {
                // simple search
                for(Product p : mainFrame.getProgram().getProducts())
                    if (p.getName().contains(searchField.getText()))
                        results.add(p);
            }
            else {
                results = mainFrame.getProgram().getProducts();
            }

            this.itemContainerPanelRef.remove(this.currentItemViewPanel);


            this.currentItemViewPanel = new ItemViewPanel(results);
            this.itemContainerPanelRef.add(this.currentItemViewPanel);
            this.mainFrame.resetVisibility();
        });

        topPanel.add(viewProfileButton);
        topPanel.add(checkoutButton);
        topPanel.add(searchField);
        topPanel.add(searchButton);

        // create a base panel and fill it with ItemViewPanel later and on updates
        JPanel itemViewPanelContainer = new JPanel();
        itemViewPanelContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        itemViewPanelContainer.setBackground(Color.darkGray);
        itemViewPanelContainer.setPreferredSize(new Dimension(600, 1200));
        // todo: maybe change for searching mechanisim
        itemViewPanelContainer.add(this.currentItemViewPanel);
        this.itemContainerPanelRef = itemViewPanelContainer;

        // make it scrollable
        JScrollPane scrollableItemViewContainer = new JScrollPane(itemViewPanelContainer);
        scrollableItemViewContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableItemViewContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableItemViewContainer.setBounds(0, 100, 600, 900);

        this.add(topPanel);
        this.add(scrollableItemViewContainer);
    }

    private class ItemViewPanel extends JPanel {

        static int COL_COUNT = 3;

        LinkedList<Product> products;

        public ItemViewPanel(LinkedList<Product> products) {
            super();
            this.products = (LinkedList<Product>) products.clone();
            init();
        }

        private void init() {
            // we have 3 item categories, we need one card for each
            this.setLayout(new GridLayout(3 ,1));

            LinkedList<Product>[] parts = new LinkedList[3];

            int i = 0;

            for (dataModels.enums.ProductType type : dataModels.enums.ProductType.values()){
                parts[i] = getProductsByType(type);
                i++;
            }

            int[] rawSizes = new int[] {
                    (int) Math.ceil((double) parts[0].size() / COL_COUNT),
                    (int) Math.ceil((double) parts[1].size() / COL_COUNT),
                    (int) Math.ceil((double) parts[2].size() / COL_COUNT),
            };

            i = 0;
            int rows = Arrays.stream(rawSizes).max().getAsInt();
            for (dataModels.enums.ProductType type : dataModels.enums.ProductType.values()){
                this.add(new ItemViewCard(type.toString(), parts[i], rows, COL_COUNT));
                i++;
            }
        }

        private LinkedList<Product> getProductsByType(dataModels.enums.ProductType type) {
            LinkedList<Product> result = new LinkedList<>();

            for(Product p : this.products)
                if(p.getProductType().equals(type)){
                    result.add(p);
                }

            products.removeAll(result);

            return result;
        }

    }

    private class ItemViewCard extends JPanel {

        String category;
        LinkedList<Product> products;
        int rows;
        int cols;

        public ItemViewCard(String category, LinkedList<Product> products, int rows, int cols) {
            super();
            this.category = category;
            this.products = products;
            this.rows = rows;
            this.cols = cols;
            init();
        }

        private void init() {
            int maxCarditems = this.cols * this.rows;
            int delta = maxCarditems - this.products.size();

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JLabel categoryName = new JLabel(category);
            JPanel itemContainer = new JPanel();

            if (this.products.size() != 0) {
                itemContainer.setLayout(new GridLayout(this.rows, this.cols));

                // fill with items
                for (Product p : this.products)
                    itemContainer.add(new ItemPanel(mainFrame, p));

                // fill with empty spaces so item cards have the same size
                int i = 0;
                while (i < delta){
                    JPanel empty = new JPanel();
                    empty.setBackground(Color.green);
                    itemContainer.add(empty);
                    i++;
                }

            }
            else {
                JLabel nothingLabel = new JLabel("nothing!");
                itemContainer.add(nothingLabel);
            }

            this.add(categoryName);
            this.add(itemContainer);

        }
    }
}
