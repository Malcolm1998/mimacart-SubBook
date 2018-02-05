/*
 * SubscriptionDetailsActivity
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
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * The class responsible for viewing/deleting existing subscriptions
 *
 * @author Malcolm MacArthu
 * @see Subscription
 * @see SubscriptionDetailsActivity
 */
public class SubscriptionDetailsActivity extends AppCompatActivity {
    Subscription clickedSubscription;
    private TextView subscriptionNameTextView;
    private TextView dateTextView;
    private TextView monthlyChargeTextView;
    private TextView commentsTextView;
    private Button deleteSubscriptionButton;
    private Button modifySubscriptionButtton;

    /**
     * Called when the activity related to this class created
     *
     * @param savedInstanceState the state from last opening
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_details);

        modifySubscriptionButtton = findViewById(R.id.addSubscription);
        deleteSubscriptionButton = findViewById(R.id.deleteSubscription);
        subscriptionNameTextView = findViewById(R.id.nameDetail);
        monthlyChargeTextView = findViewById(R.id.chargeDetail);
        commentsTextView = findViewById(R.id.commentsDetail);
        dateTextView = findViewById(R.id.dateDetail);

        deleteSubscriptionButton.setOnClickListener(new View.OnClickListener(){

            /**
             * Called when the delete button is clicked
             *
             * @param v the View
             */
            public void onClick(View v) {
                Intent deleteIntent = new Intent(SubscriptionDetailsActivity.this,
                        SubBookActivity.class);

                deleteIntent.putExtra("action", "DELETE");
                setResult(Activity.RESULT_OK, deleteIntent);
                finish();
            }
        });

        modifySubscriptionButtton.setOnClickListener(new View.OnClickListener() {

            /**
             * Called when the modify button is clicked
             *
             * @param v the View
             */
            @Override
            public void onClick(View v) {
                Intent modifyIntent = new Intent(SubscriptionDetailsActivity.this,
                        ModifySubscriptionActivity.class);
                modifyIntent.putExtra("clickedSubscription", clickedSubscription);
                startActivityForResult(modifyIntent, 1);
            }

        });
    }

    /**
     * Called when associated activity starts and fills information
     */
    @Override
    protected void onStart(){
        SimpleDateFormat dateFormat;
        String formatedDate;

        super.onStart();

        clickedSubscription = (Subscription)
                getIntent().getExtras().getSerializable("clickedSubscription");


        subscriptionNameTextView.setText(clickedSubscription.getName());
        monthlyChargeTextView.setText(String.valueOf(clickedSubscription.getMonthlyCharge()));
        commentsTextView.setText(clickedSubscription.getComments());

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        formatedDate = dateFormat.format(clickedSubscription.getDateStarted());
        dateTextView.setText(formatedDate);
    }

    /**
     *  Called when call to another activity has come back
     * @param requestCode the number this class sent when switching activities
     * @param resultCode the number the other activity sends back
     * @param data the data the call to the other activity had brought
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
