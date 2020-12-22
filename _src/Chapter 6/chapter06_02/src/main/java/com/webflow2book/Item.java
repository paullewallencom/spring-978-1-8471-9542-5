package com.webflow2book;

import java.io.Serializable;

/**
 * An item for a shopping cart. Includes a title of the
 * product and a price.
 * @author Sven
 */
public class Item implements Serializable {

    private static final long serialVersionUID = 2401655063039143584L;
    private String title;
    private double price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
