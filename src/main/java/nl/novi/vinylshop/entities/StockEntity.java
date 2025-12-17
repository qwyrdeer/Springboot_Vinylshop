package nl.novi.vinylshop.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class StockEntity extends BaseEntity {
    private int quantity;
    private String condition;
    private double price;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private AlbumEntity album;

    public StockEntity(String condition, double price) {
        this.condition = condition;
        this.price = price;
    }

    public StockEntity() {

    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
