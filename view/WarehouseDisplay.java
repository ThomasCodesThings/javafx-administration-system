package view;

import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.infrastructure.Warehouse;
import model.ingredients.Ingredient;
import model.product.Product;

import java.util.List;

public class WarehouseDisplay {

    private Scene scene;
    private Text text = new Text("Warehouse");
    private TableView tableView = new TableView();
    private Button editButton = new Button("Edit");
    private Button removeButton = new Button("Remove");
    private Button addButton = new Button("Add new");
    private TextArea textArea = new TextArea();
    private VBox vBox = new VBox();

    public TextArea getTextArea() {
        return textArea;
    }

    public VBox getvBox() {
        return vBox;
    }

    public Scene getScene(){
        return scene;
    }

    public TableView getTableView() {
        return tableView;
    }

    public Button getEditButton() {
        return editButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public Button getAddButton() {
        return addButton;
    }

    /**
     * Creating Warehouse Window
     * @param warehouse
     */
    public WarehouseDisplay(Warehouse<Object> warehouse){
        vBox = new VBox();
        vBox.setSpacing(5);
        Text mainText = new Text("Warehouse");
        mainText.setFont(new Font(20));
        vBox.getChildren().add(mainText);
        HBox initBox = new HBox();
        initBox.setSpacing(10);
        Text firstText = new Text("Item name");
        firstText.setFont(new Font(15));
        Text secondText = new Text("Amount");
        secondText.setFont(new Font(15));
        initBox.getChildren().addAll(firstText, secondText);
        vBox.getChildren().add(initBox);
        Warehouse.Node temp = warehouse.getHead();
            while(temp != null) {
                HBox hBox = new HBox();
                hBox.setSpacing(10);
                TextField itemNameTextField = new TextField();
                TextField itemAmountTextField = new TextField();
                itemNameTextField.setEditable(false);
                itemAmountTextField.setEditable(false);
                Button removeButton = new Button("Remove");
                if(temp.getItem() instanceof Ingredient) {
                    Ingredient ingredient = (Ingredient) temp.getItem();
                    itemNameTextField.setText(ingredient.toString());
                    itemAmountTextField.setText(String.valueOf(ingredient.getAmount()));
                    Button addButton = new Button("Add");
                    hBox.getChildren().addAll(itemNameTextField, itemAmountTextField, addButton, removeButton);
                }else if(temp.getItem() instanceof Product) {
                    Product product = (Product) temp.getItem();
                    itemNameTextField.setText(product.getClass().getSimpleName());
                    itemAmountTextField.setText(String.valueOf(product.getAmount()));
                    hBox.getChildren().addAll(itemNameTextField, itemAmountTextField, removeButton);
                }
                vBox.getChildren().add(hBox);
                temp = temp.getNext();
            }
            vBox.getChildren().add(textArea);
            vBox.getChildren().add(addButton);
        scene = new Scene(vBox);
    }
}
