package com.webflow2book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a very simple shopping cart.
 * 
 * @author Sven
 */
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = -4928926176100893795L;
    private List<Item> itemList;

    public ShoppingCart() {
        this.itemList = new ArrayList<Item>();
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
