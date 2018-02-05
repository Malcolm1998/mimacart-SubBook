package mimacart_subbook.com.mimacart_subbook;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by malcolm on 2018-02-03.
 */

public class Subscription {
    private String name;
    private Calendar dateStarted;
    private double monthlyCharge;

    Subscription(){
        dateStarted = Calendar.getInstance();
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setDateStarted(Calendar newDate){
        dateStarted = newDate;
    }

    public void setDateStartedDayOfMonth(int newDay){
        dateStarted.set(Calendar.DAY_OF_MONTH, newDay);
    }

    public void setDateStartedMonth(int newMonth){
        dateStarted.set(Calendar.MONTH, newMonth - 1);
    }

    public void setDateStartedYear(int newYear){
        dateStarted.set(Calendar.YEAR, newYear);
    }

    public void setMonthlyCharge(double newMonthlyCharge){
        monthlyCharge = newMonthlyCharge;
    }

    public Calendar getDateStarted(){
        return this.dateStarted;
    }

    public String getName(){
        return name;
    }

    public double getMonthlyCharge(){
        return monthlyCharge;
    }
}
