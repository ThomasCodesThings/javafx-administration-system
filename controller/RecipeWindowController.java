package controller;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.database.PatisserieDatabase;
import model.infrastructure.Patisserie;
import model.infrastructure.Warehouse;
import model.ingredients.Ingredient;
import model.product.Product;
import model.product.cake.Cake;
import view.RecipeWindow;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static model.utilities.ObjectModeler.productFromString;

public class RecipeWindowController {

    public RecipeWindowController(RecipeWindow recipeWindow, Patisserie patisserie){
        Stage stage = new Stage();
        stage.setMinWidth(400);
        stage.setMinHeight(400);
        stage.setTitle("Recipe builder v1.0");
        stage.setScene(recipeWindow.getScene());
        stage.show();
        List<HBox> hBoxes = new ArrayList<>();
        fillBox(recipeWindow.getComboBox(), patisserie);

        recipeWindow.getShowButton().setOnAction(event -> {
            VBox vBox = recipeWindow.createList();
            Scene nextScene = new Scene(vBox);
            stage.setScene(nextScene);
            VBox itemBox = (VBox) vBox.getChildren().get(1);
            for(Product product : patisserie.getProductList()){
                itemBox.getChildren().add(new Text(product.toString()));
            }
            recipeWindow.getBackButton().setOnAction(event1 -> {
                stage.setScene(recipeWindow.getScene());
            });
        });
        recipeWindow.getAddButton().setOnAction(event -> {
            //recipeWindow.getvBox().getChildren().add();
            HBox hBox = recipeWindow.createRecipeLine();
            ComboBox comboBox = (ComboBox) hBox.getChildren().get(0);
            TextField amountTextField = (TextField) hBox.getChildren().get(1);
            Button removeButton = (Button) hBox.getChildren().get(2);

            Warehouse.Node temp = patisserie.getWarehouse().getHead();
            while(temp != null){
                comboBox.getItems().add(temp.getItem());
                temp = temp.getNext();
            }

            removeButton.setOnAction(event1 -> {
                recipeWindow.getvBox().getChildren().remove(hBox);
                hBoxes.remove(hBox);
            });
            recipeWindow.getvBox().getChildren().add(hBox);
            hBoxes.add(hBox);
        });

        recipeWindow.getCreateButton().setOnAction(event -> {
            recipeBuílder(hBoxes, patisserie, recipeWindow);
        });
    }

    void recipeBuílder(List<HBox> hBoxes, Patisserie patisserie, RecipeWindow recipeWindow){
        String selectedProductString = (String) recipeWindow.getComboBox().getSelectionModel().getSelectedItem();
       /* Class cls = null;
        if(selectedProductString.contains("Cake")){
            try {
                cls = Class.forName("model.product.cake." + selectedProductString);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else if(selectedProductString.contains("IceCream")){
            try {
                cls = Class.forName("model.product.icecream." + selectedProductString);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            try {
                cls = Class.forName("model.product.desert." + selectedProductString);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        Object obj = null;
        if(cls != null){

            try {
                Constructor constructor = cls.getConstructor(int.class);
                obj = constructor.newInstance(1);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }*/
        Object obj = productFromString(selectedProductString, 1);
        Product product = (Product) obj;
        for(HBox hBox : hBoxes){
            ComboBox comboBox = (ComboBox) hBox.getChildren().get(0);
            TextField amountTextField = (TextField) hBox.getChildren().get(1);
            Ingredient ingredient = (Ingredient) comboBox.getSelectionModel().getSelectedItem();
            int amount = Integer.parseInt(amountTextField.getText());
            if(amount > 0 && ingredient != null){
                ingredient.setAmount(amount);
                if(obj != null) {
                    product.getProductIngredients().add(ingredient);
                }
            }
        }
        patisserie.getProductList().add(product);
        System.out.println("Added " + product.getClass().getSimpleName());
        PatisserieDatabase.save();
    }

    void fillBox(ComboBox comboBox, Patisserie patisserie){
        List<String> productList = new ArrayList<>();
        File f = new File("src/model/product/cake");
        File []files = f.listFiles();
        if(files != null) {
            for (File file : files) {
                String result = (file.getName().toString()).substring(0, (file.getName().toString()).indexOf("."));
                if (!result.equals("Cake")) {
                    if (patisserie.getProductList().size() == 0) {
                        productList.add(result);
                    } else {
                        for (Product product : patisserie.getProductList()) {
                            if (!product.getClass().getSimpleName().equals(result)) {
                                productList.add(result);
                                break;
                            }
                        }
                    }
                }
                }
            }

        f = new File("src/model/product/desert");
        files = f.listFiles();
        if(files != null) {
            for (File file : files) {
                String result = (file.getName().toString()).substring(0, (file.getName().toString()).indexOf("."));
                if (!result.equals("Desert")) {
                    if(patisserie.getProductList().size() == 0){
                        productList.add(result);
                    }else {
                        for (Product product : patisserie.getProductList()) {
                            if (!product.getClass().getSimpleName().equals(result)) {
                                productList.add(result);
                                break;
                            }
                        }
                    }
                }
            }
        }

        f = new File("src/model/product/icecream");
        files = f.listFiles();
        if(files != null) {
            for (File file : files) {
                String result = (file.getName().toString()).substring(0, (file.getName().toString()).indexOf("."));
                if (!result.equals("IceCream")) {
                    if(patisserie.getProductList().size() == 0){
                        productList.add(result);
                    }else {
                        for (Product product : patisserie.getProductList()) {
                            if (!product.getClass().getSimpleName().equals(result)) {
                                productList.add(result);
                                break;
                            }
                        }
                    }
                }
            }
        }

        for(String str: productList){
            comboBox.getItems().add(str);
        }
    }
}
