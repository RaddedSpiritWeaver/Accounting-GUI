package dataModels;

import java.util.LinkedList;

public class Cart {

    private User user;
    private LinkedList<CountableProduct> items;

    private class CountableProduct {
        Product product;
        int count;

        public CountableProduct(Product product, int count) {
            this.product = product;
            this.count = count;
        }
    }

    public Cart(User user) {
        this.user = user;
        this.items = new LinkedList<>();
    }

    public void addItem(Product addedProduct) {
        CountableProduct foundEntry = null;

        // simple search
        for (CountableProduct entry : this.items)
            if(addedProduct.getName().equals(entry.product.getName()))
                foundEntry = entry;

        if (foundEntry == null) {
            // if not exists add it to the list
            CountableProduct newEntry = new CountableProduct(addedProduct, 1);
            this.items.add(newEntry);
            System.out.println("cart: (addItem) - new entry added");
        }
        else {
            // if exists, increase its counter
            foundEntry.count++;
        }
    }

    public void addItem(Product addedProduct, int count) {
        CountableProduct foundEntry = null;

        // simple search
        for (CountableProduct entry : this.items)
            if(addedProduct.getName().equals(entry.product.getName()))
                foundEntry = entry;

        if (foundEntry == null) {
            // if not exists add it to the list
            CountableProduct newEntry = new CountableProduct(addedProduct, count);
            this.items.add(newEntry);
            System.out.println("cart: (addItem) - new entry added");
        }
        else {
            // if exists, increase its counter
            foundEntry.count = foundEntry.count + count;
        }
    }

    public void removeItem(Product selectedProduct) {
        CountableProduct foundEntry = null;

        // simple search to find product entry
        for (CountableProduct entry : this.items)
            if(selectedProduct.getName().equals(entry.product.getName()))
                foundEntry = entry;

        // return since item does not exist
        if (foundEntry == null) {
            System.out.println("cart: (removeItem) - item not found");
            return;
        }

        // if it exists
        if (foundEntry.count == 1) {
            if (this.user == null) {
                // means its the inventory and we should not remove the entry
                foundEntry.count = 0;
                return;
            }
            // totally remove the item
            this.items.remove(foundEntry);
            System.out.println("cart: (removeItem) - item removed from the entire list");
        }
        else {
            foundEntry.count --;
        }
    }

    public int getCount(Product selectedProduct) {
        CountableProduct foundEntry = null;

        // simple search
        for (CountableProduct entry : this.items)
            if(selectedProduct.getName().equals(entry.product.getName()))
                foundEntry = entry;

        if (foundEntry == null) {
            System.out.println("cart: (getCount) - item not exist");
            return 0;
        }

        return foundEntry.count;
    }

    public void show() {
        System.out.println("Showing cart");
        for (CountableProduct entry : this.items)
            System.out.println(String.format("%S : %d", entry.product.getName(), entry.count));
    }
}
