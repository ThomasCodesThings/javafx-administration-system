package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.database.PatisserieDatabase;
import model.infrastructure.Patisserie;
import model.infrastructure.Supplier;
import model.infrastructure.Warehouse;
import model.ingredients.Ingredient;
import model.ingredients.Milk;
import view.SupplyWindow;
import view.WarehouseDisplay;
import view.amountPopUp;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static controller.WarehouseController.createSupplierText;

public class SupplyController {

    private SupplyWindow supplyWindow;
    private amountPopUp amountPopUp;
    private ArrayList<String> strings = new ArrayList<>();
    private WarehouseDisplay warehouseDisplay;
    Stage mainStage = new Stage();
    public SupplyController(SupplyWindow supplyWindow, amountPopUp amountPopUp, Patisserie patisserie, WarehouseDisplay warehouseDisplay){
        this.supplyWindow = supplyWindow;
        this.amountPopUp = amountPopUp;
        this.warehouseDisplay = warehouseDisplay;
        fillTable(patisserie);
        mainStage.setTitle("New order");
        mainStage.setScene(supplyWindow.getScene());
        mainStage.show();
        supplyWindow.getConfirmButton().setOnAction(event -> {
            checkSelected(patisserie);
        });
    }

    void checkSelected(Patisserie patisserie){
        String className = (String) supplyWindow.getListView().getSelectionModel().getSelectedItem();
        try {
            if(className == null){
                return;
            }
            Class cls = Class.forName("model.ingredients." + className);
            Constructor constructor = cls.getConstructor(int.class);
            Stage stage = new Stage();
            stage.setScene(amountPopUp.getScene());
            stage.setTitle("Amount");
            stage.show();
            amountPopUp.getOkButton().setOnAction(event -> {
            int amount = (int) amountPopUp.getSpinner().getValue();
            if(amount == 0){
                amount = Integer.parseInt(amountPopUp.getSpinner().getEditor().getText());
            }
            if(amount > 0){
                try {
                  Object obj = constructor.newInstance(amount);
                  warehouseDisplay.getTextArea().appendText(createSupplierText());
                  patisserie.getWarehouse().add(obj);
                    PatisserieDatabase.save();
                  //TODO warehose table?
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                stage.close();
                mainStage.close();
            }
            });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    void fillTable(Patisserie patisserie){
        File f = new File("src/model/ingredients");
        File []files = f.listFiles();
        if(files != null) {
            for (File file : files) {
                String result = (file.getName().toString()).substring(0, (file.getName().toString()).indexOf("."));
                if(!result.equals("Ingredient") && !result.contains("Inter")) {
                    Warehouse.Node temp = patisserie.getWarehouse().getHead();
                    boolean duplicate = false;
                    while (temp != null) {
                        if (result.equals(temp.getItem().getClass().getSimpleName())) {
                            duplicate = true;
                            break;
                        }
                        temp = temp.getNext();
                    }
                    if (!duplicate) {
                        supplyWindow.getListView().getItems().add(result);
                        strings.add(result);
                    }
                }
            }
        }
    }
}
