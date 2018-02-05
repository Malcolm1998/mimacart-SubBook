package mimacart_subbook.com.mimacart_subbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.util.Calendar;

public class CreateSubscriptionActivity extends AppCompatActivity {
    private EditText subscriptionNameInput;
    private EditText dayInput;
    private EditText monthInput;
    private EditText yearInput;
    private EditText monthlyChargeInput;
    private EditText commentsInput;
    private Button createSubscriptionButton;

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

            public void onClick(View v) {
                String input;
                Calendar inputDate = Calendar.getInstance();
                Intent returnIntent = new Intent(CreateSubscriptionActivity.this, SubBookActivity.class);;

                /*
                 * https://stackoverflow.com/questions/14400298/
                 * best-practice-for-displaying-error-messages
                 */
                if (!validSubscriptionNameInput()
                        || !validDateInput()
                        || !validMonthlyChargeInput()
                        || !validCommentsInput()) {
                    setResult(Activity.RESULT_CANCELED, returnIntent);
                    finish();
                }

                int intP;
                input = dayInput.getText().toString();
                intP = Integer.parseInt(input);
                returnIntent.putExtra("day", input);
                input = monthInput.getText().toString();
                intP = Integer.parseInt(input);
                returnIntent.putExtra("month", input);
                input = yearInput.getText().toString();
                intP = Integer.parseInt(input);
                returnIntent.putExtra("year", input);
                returnIntent.putExtra("name", subscriptionNameInput.getText().toString());
                input = monthlyChargeInput.getText().toString();
                returnIntent.putExtra("monthlyCharge", input);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();

            }

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

            private boolean validDateInput(){
                String input;
                Calendar inputDate;

                inputDate = Calendar.getInstance();
                inputDate.setLenient(false);
                try {
                    input = dayInput.getText().toString();
                    inputDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(input));
                    input = monthInput.getText().toString();
                    inputDate.set(Calendar.MONTH, Integer.parseInt(input) - 1);
                    input = yearInput.getText().toString();
                    inputDate.set(Calendar.YEAR, Integer.parseInt(input));
                    inputDate.getTime();
                    return true;                        // Date is valid.
                }
                catch (Exception e) {
                    yearInput.setError("Invalid date");
                    return false;                       // Date is invalid.
                }
            }

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
