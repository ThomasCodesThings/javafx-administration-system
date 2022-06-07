package model.infrastructure;

import model.Residence;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Supplier{
    private String companyName;
    private Residence companyResidence;
    private String date;

    public String getCompanyName() {
        return companyName;
    }

    public Residence getCompanyResidence() {
        return companyResidence;
    }

    public String getDate() {
        return date;
    }

    private String[] companyNameList = {
            "DailyPraire", "whisket", "beejella", "Caramint", "cowcow", "Plumkism", "foodley", "graint", "vachetta", "Sodavoro"
    };
    private String[] companyAddress = {
         "Edgewood 2887", "Clover Cir 36", "Centre St 8125", "Memory Ln 1004", "Gil Ln 9", "Perry St 28", "N Hyland St 14", "Legion Dr 61" , "Pine Cir 763", "4th Ave 441"
    };

    private String[] companyTown = {
            "Perry" , "Weatherly", "Rock Falls", "Gresham", "Davisville", "Prentiss", "Seguin", "Casey", "Thomasville", "Solvang"
    };

    /**
     * Constructor of Supplier, it´s automatically generated
     */
    public Supplier(){
        int index = new Random().nextInt(10);
        this.companyName = companyNameList[index];
        Residence residence = new Residence(companyAddress[index], String.valueOf(new Random().nextInt(100000-10000+1) + 10000),companyTown[index], "United States of America");
        this.companyResidence = residence;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.date = dtf.format(now);
    }
}
