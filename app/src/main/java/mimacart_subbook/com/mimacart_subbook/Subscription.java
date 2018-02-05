package mimacart_subbook.com.mimacart_subbook;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by malcolm on 2018-02-03.
 */

public class Subscription {
    private String name;
    private String comments;
    private Calendar dateStarted;
    private double monthlyCharge;

    Subscription(){
        dateStarted = Calendar.getInstance();
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setMonthlyCharge(double newMonthlyCharge){
        monthlyCharge = newMonthlyCharge;
    }

    public void setComments(String newComments){
        this.comments = newComments;
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

    public String getName(){
        return name;
    }

    public double getMonthlyCharge(){
        return monthlyCharge;
    }

    public String getComments(){
        return this.comments;
    }

    public Date getDateStarted(){
        return this.dateStarted.getTime();
    }

    public int getDateStartedDayOfMonth(){
        return dateStarted.get(Calendar.DAY_OF_MONTH);}

    public int getDateStartedMonth(){
        return dateStarted.get(Calendar.MONTH);
    }

    public int getDateStartedYear(){
        return dateStarted.get(Calendar.YEAR);
    }


}
