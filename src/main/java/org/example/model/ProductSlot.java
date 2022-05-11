package org.example.model;

public class ProductSlot {
    private int product_id;
    private int slot_id;

    public ProductSlot() {}

    public ProductSlot(int product_id, int slot_id) {
        this.product_id = product_id;
        this.slot_id = slot_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }
}
