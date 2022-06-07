package view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.product.Product;
import model.product.ProductObserver;

public class OrderShowcase {
    private HBox hBox;
    private String itemName;
    private int amount;
    private ProductObserver product;

    /**
     * Creates Small Editable Product Order Box
     * @param product takes product as parameter
     */
    public OrderShowcase(ProductObserver product){
        this.product = product;
    }

    /**
     * Custom HBox generator
     * @return returns HBox
     */
    public HBox getOrderBox(){
        hBox = new HBox();
        hBox.setSpacing(20);
        Text text = new Text(product.toString());
        text.setFont(new Font(14));
        Button editButton = new Button("Edit");
        Button removeButton = new Button("X");
        hBox.getChildren().addAll(text, editButton, removeButton);
        return hBox;
    }
}
