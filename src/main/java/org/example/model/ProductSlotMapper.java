package org.example.model;

public class ProductSlotMapper {
    private Product product;
    private Slot slot;

    public ProductSlotMapper() {}

    public ProductSlotMapper(Product product, Slot slot) {
        this.product = product;
        this.slot = slot;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
