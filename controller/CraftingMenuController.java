package controller;

import javafx.stage.Stage;
import model.infrastructure.Patisserie;
import model.product.Product;
import view.CraftingMenu;

import static model.utilities.ObjectModeler.productFromString;

public class CraftingMenuController {

    private CraftingMenu craftingMenu;
    private Stage stage;

    /**
     * Creates crafting menu
     * @param craftingMenu
     * @param patisserie
     * @param stage
     */
    public CraftingMenuController(CraftingMenu craftingMenu, Patisserie patisserie, Stage stage){
        this.craftingMenu = craftingMenu;
        this.stage = stage;
        fillBox(patisserie);
        craftingMenu.getCraftButton().setOnAction(event -> { //najprv musim vybrat produkt ktory chcem vyrobiť
            int amount = Integer.parseInt(craftingMenu.getAmountField().getText());
            String productName = (String) craftingMenu.getComboBox().getSelectionModel().getSelectedItem();
            if(amount > 0 && productName != null) { //a zadat pocet
                Object obj = productFromString(productName, amount);
                Product product = (Product) obj; //a potom ho len vyrobim a pridam do skladu
                if(patisserie.getWarehouse().checkProductIngredientsAvailability((Product) obj)){
                   patisserie.getWarehouse().removeIngredients((Product) obj); //musim odobar suroviny co som pouzil
                   patisserie.getWarehouse().add(obj);
                    System.out.println("I crafted " + obj);
                }
            }
        });
    }

    /**
     * Filling box
     * @param patisserie
     */
    void fillBox(Patisserie patisserie){
        for(Product product : patisserie.getProductList()){
            craftingMenu.getComboBox().getItems().add(product.getClass().getSimpleName());
        }
    }
}
