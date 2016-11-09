package shultz.shopping;

public class Item {
	String description;
	double weight;
	int quantity;
	double price;
	
	public Item(String description, int quantity, double weight, double price){
		this.setDescription(description);
		this.setQuantity(quantity);
		this.setWeight(weight);
		this.setPrice(price);
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
