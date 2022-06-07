package model.database;

import model.infrastructure.Patisserie;

import java.io.*;
import java.util.ArrayList;

public class PatisserieDatabase implements Serializable {
    private static PatisserieDatabase patisserieDatabaseInstance;
    private static String databaseFileName = "patisserieDatabase.data";

    public static String getDatabaseFileName() {
        return databaseFileName;
    }

    private ArrayList<Patisserie> patisseries = new ArrayList<>();

    public ArrayList<Patisserie> getPatisseries() {
        return patisseries;
    }

    private PatisserieDatabase() {

    }

    /**
     * Function for creating only one instance of singleton class
     * @return returns single singleton instance of database
     */
    public static PatisserieDatabase getInstance() {
        if (patisserieDatabaseInstance == null) {
            patisserieDatabaseInstance = new PatisserieDatabase();
        }
        return patisserieDatabaseInstance;
    }

    /**
     * Function to load database from file
     */
    public static void load(){
        try {
            FileInputStream fis = new FileInputStream(databaseFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            patisserieDatabaseInstance = (PatisserieDatabase) ois.readObject();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to save all states of objects in database after closing main window/finishing work
     */
    public static void save(){
        try {
            FileOutputStream fos = new FileOutputStream(databaseFileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(patisserieDatabaseInstance);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
