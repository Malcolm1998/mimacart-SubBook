/*
 * Subscription
 *
 * February 4, 2018
 *
 * Copyright © 2018 Team X, CMPUT301, University of Alberta - All rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code
 * of Student Behaviour at University of Alberta.
 * You can find a copy of the license in this project. Otherwise contact mimacart@ualberta.ca
 */

package mimacart_subbook.com.mimacart_subbook;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Holds all information about Subscriptions
 *
 * @author Malcolm MacArthur
 * @see CreateSubscriptionActivity
 */
public class Subscription implements Serializable {
    private String name;
    private String comments;
    private Calendar dateStarted;
    private double monthlyCharge;

    /**
     * The constructor for the Subscription class. It just gets a calender instance for date started
     */
    Subscription(){
        dateStarted = Calendar.getInstance();
    }

    /**
     * Overwrites current name with a new name
     *
     * @param newName String name to set
     */
    public void setName(String newName){
        this.name = newName;
    }

    /**
     * Overwrites current monthly charge with a new monthly charge
     *
     * @param newMonthlyCharge double monthly charge to set
     */
    public void setMonthlyCharge(double newMonthlyCharge){
        monthlyCharge = newMonthlyCharge;
    }

    /**
     * Overwrites current comments with new comments
     *
     * @param newComments String comments to set
     */
    public void setComments(String newComments){
        this.comments = newComments;
    }

    /**
     * Overwrites current date started with new date started
     *
     * @param newDate Calender date to set
     */
    public void setDateStarted(Calendar newDate){
        dateStarted = newDate;
    }

    /**
     * Overwrites current day of the month started with new day of the month started
     *
     * @param newDay int day of the month started to set
     */
    public void setDateStartedDayOfMonth(int newDay){
        dateStarted.set(Calendar.DAY_OF_MONTH, newDay);
    }

    /**
     * Overwrites current month started started with new  month stareted
     *
     * @param newMonth int month started to set
     */
    public void setDateStartedMonth(int newMonth){
        dateStarted.set(Calendar.MONTH, newMonth - 1);
    }

    /**
     * Overwrites current year started with new year started
     *
     * @param newYear int year started to set
     */
    public void setDateStartedYear(int newYear){
        dateStarted.set(Calendar.YEAR, newYear);
    }

    /**
     * get current name
     *
     * @return String name
     */
    public String getName(){
        return name;
    }

    /**
     * get monthly charge
     *
     * @return double monthly charge
     */
    public double getMonthlyCharge(){
        return monthlyCharge;
    }

    /**
     * get comments
     *
     * @return String comments
     */
    public String getComments(){
        return this.comments;
    }

    /**
     * get date started
     *
     * @return Date date started
     */
    public Date getDateStarted(){
        return this.dateStarted.getTime();
    }

    /**
     * get day of the month started
     *
     * @return int day of moth started
     */
    public int getDateStartedDayOfMonth(){
        return dateStarted.get(Calendar.DAY_OF_MONTH);}

    /**
     * get month started
     *
     * @return int month started
     */
    public int getDateStartedMonth(){
        return dateStarted.get(Calendar.MONTH);
    }

    /**
     * get year started
     *
     * @return int year started
     */
    public int getDateStartedYear(){
        return dateStarted.get(Calendar.YEAR);
    }


}
