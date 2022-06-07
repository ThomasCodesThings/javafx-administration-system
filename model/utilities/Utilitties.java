package model.utilities;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.Period;

public abstract class Utilitties {
    /**
     * Function to calculate age based on date
     * @param datePicker given date
     * @return age from date to today
     */
    public static int calculatePersonAge(DatePicker datePicker){
        Period diff = Period.between(datePicker.getValue(), LocalDate.now());
        return diff.getYears();
    }
}
