/*
 * CreateSubscriptionActivity
 *
 * February 4, 2018
 *
 * Copyright © 2018 Team X, CMPUT301, University of Alberta - All rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code
 * of Student Behaviour at University of Alberta.
 * You can find a copy of the license in this project. Otherwise contact mimacart@ualberta.ca
 */

package mimacart_subbook.com.mimacart_subbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

/**
 * The class responsible for creating a new subscription
 *
 * @author Malcolm MacArthu
 * @see Subscription
 * @see SubBookActivity
 */
public class CreateSubscriptionActivity extends AppCompatActivity {

    private EditText subscriptionNameInput;
    private EditText dayInput;
    private EditText monthInput;
    private EditText yearInput;
    private EditText monthlyChargeInput;
    private EditText commentsInput;
    private Button createSubscriptionButton;

    /**
     * Called when the activity related to this class created
     *
     * @param savedInstanceState the state from last opening
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Calendar currentDate = Calendar.getInstance();
        int currentDayOfMonth;
        int currentMonth;
        int currentYear;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_subscription);

        // Get view object instances.
        subscriptionNameInput = findViewById(R.id.nameBody);
        dayInput = findViewById(R.id.dayBody);
        monthInput = findViewById(R.id.monthBody);
        yearInput = findViewById(R.id.yearBody);
        monthlyChargeInput = findViewById(R.id.monthlyChargeBody);
        commentsInput = findViewById(R.id.commentsBody);
        createSubscriptionButton = findViewById(R.id.addSubscription);

        // Store current date information into simpler variables.
        currentDayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);
        currentMonth = currentDate.get(Calendar.MONTH) + 1;
        currentYear = currentDate.get(Calendar.YEAR);

        // set default value for starting date
        dayInput.setText(String.valueOf(currentDayOfMonth));
        monthInput.setText(String.valueOf(currentMonth));
        yearInput.setText(String.valueOf(currentYear));

        createSubscriptionButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Called when the create buttom is clicked
             *
             * @param v the View
             */
            public void onClick(View v) {
                Subscription newSub = new Subscription();
                Intent returnIntent = new Intent(CreateSubscriptionActivity.this,
                        SubBookActivity.class);

                /*
                 * https://stackoverflow.com/questions/14400298/
                 * best-practice-for-displaying-error-messages
                 *
                 * Validate all inputs
                 */
                if (!validSubscriptionNameInput()
                        || !validDateInput()
                        || !validMonthlyChargeInput()
                        || !validCommentsInput()) {
                    setResult(Activity.RESULT_CANCELED, returnIntent);
                    finish();
                }

                //Assigns new values to new Subscription class
                newSub.setDateStartedDayOfMonth(Integer.parseInt(dayInput.getText().toString()));
                newSub.setDateStartedMonth(Integer.parseInt(monthInput.getText().toString()));
                newSub.setDateStartedYear(Integer.parseInt(yearInput.getText().toString()));
                newSub.setName(subscriptionNameInput.getText().toString());
                newSub.setMonthlyCharge(Double.parseDouble(monthlyChargeInput.getText().toString()));
                newSub.setComments(commentsInput.getText().toString());

                //return
                returnIntent.putExtra("newsub", newSub);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();

            }

            /**
             * Checks the validity of the name inputted
             *
             * @return boolean which is true if name is valid
             */
            private boolean validSubscriptionNameInput(){
                String input;

                input = subscriptionNameInput.getText().toString();
                if (!input.matches("^[ A-Za-z]+$")){
                    subscriptionNameInput.setError("Name should only contain letters in alphabet");
                    return false;                       // Name is not textual.
                } else if (input.length() <= 0) {
                    subscriptionNameInput.setError("Please fill this in");
                    return false;                       // No name entered.
                }
                return true;                            // Name is valid.
            }

            /**
             * Checks the validity of the date inputted
             *
             * @return boolean which is true if date is valid
             */
            private boolean validDateInput(){
                String input;
                Calendar inputDate;

                inputDate = Calendar.getInstance();
                inputDate.setLenient(false);
                try {
                    //set everything into Calender instance and see if error is thrown
                    input = dayInput.getText().toString();
                    inputDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(input));
                    input = monthInput.getText().toString();
                    inputDate.set(Calendar.MONTH, Integer.parseInt(input) - 1);
                    input = yearInput.getText().toString();
                    inputDate.set(Calendar.YEAR, Integer.parseInt(input));
                    inputDate.getTime();
                    return true;                        // Date is valid.
                } catch (Exception e) {
                    yearInput.setError("Invalid date");
                    return false;                       // Date is invalid.
                }
            }

            /**
             * Checks the validity of the monthly charge inputted
             *
             * @return boolean which is true if monthly charge is valid
             */
            private boolean validMonthlyChargeInput(){
                String input;
                float monthlyCharge;
                int decimalPlace;

                input = monthlyChargeInput.getText().toString();

                try {
                    monthlyCharge = Float.parseFloat(input);
                    decimalPlace = input.indexOf(".");
                    if (input.length() <= 0){
                        monthlyChargeInput.setError("Please fill this in");
                        return false;                   // Empty input.
                    } else if (input.length() - decimalPlace - 1 != 2){
                        monthlyChargeInput.setError("Please have two numbers after decimal point");
                        return false;                   // Sig Dig Issue.
                    } else if (monthlyCharge < 0){
                        monthlyChargeInput.setError("Cannot have negative charge");
                        return false;                   // Negative value.
                    }
                    return true;                        // Valid float number.
                }
                catch (Exception e) {
                    monthlyChargeInput.setError("Invalid float number");
                    return false;                       // Not valid float.
                }
            }

            /**
             * Checks the validity of the comments inputted
             *
             * @return boolean which is true if comments are valid
             */
            private  boolean validCommentsInput(){
                String input;

                input = commentsInput.getText().toString();
                if (input.length() == 0) {
                    return true;
                } else if (!input.matches("^[ A-Za-z]+$")){
                    commentsInput.setError("Name should only contain letters in alphabet");
                    return false;                       // Name is not textual.
                }

                return true;
            }

        });
    }
}
