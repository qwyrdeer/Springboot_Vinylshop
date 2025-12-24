package nl.novi.vinylshop.dtos.stock;

public class StockRequestDTO {

    private Long id;
    private int quantity;
    private String condition;
    private double price;

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

    public Long getId() {
        return id;
    }
}
