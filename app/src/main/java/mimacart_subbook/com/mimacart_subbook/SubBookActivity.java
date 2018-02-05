/*
 * SubBookActivity
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
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * The main class for running SubBook
 */
public class SubBookActivity extends AppCompatActivity {
    private static final String FILENAME = "subscriptions.sav";
    Subscription clickedSubscription;
    private ListView subscriptionListView;
    private ArrayList<Subscription> subscriptionList;
    private Button addSubscriptionButton;
    private SubscriptionAdapter adapter;

    /**
     * Called when the activity related to this class created
     *
     * @param savedInstanceState the state from last opening
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subscriptionList = new ArrayList<Subscription>();

        // Get view object instances.
        addSubscriptionButton = findViewById(R.id.addSubscription);
        subscriptionListView = findViewById(R.id.subscriptionListView);

        addSubscriptionButton.setOnClickListener(new View.OnClickListener(){

            /**
             * Called when the modify button is clicked
             *
             * @param v the View
             */
            public void onClick(View v) {
                Intent createIntent = new Intent(SubBookActivity.this,
                        CreateSubscriptionActivity.class);
                startActivityForResult(createIntent, 1);
            }
        });

        subscriptionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * Called when an itme in ListView is clicked
             *
             * @param v the View
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //saveInFile();

                Intent detailIntent = new Intent(SubBookActivity.this,
                        SubscriptionDetailsActivity.class);
                clickedSubscription = adapter.getItem(position);
                detailIntent.putExtra("clickedSubscription", clickedSubscription);

                startActivityForResult(detailIntent, 2);
            }
        });
    }

    /**
     * Called when associated activity starts and sets subscription values
     */
    @Override
    protected void onStart(){
        super.onStart();

        loadFromFile();

        adapter = new SubscriptionAdapter(this, subscriptionList);
        subscriptionListView.setAdapter(adapter);

    }

    /**
     *  Called when call to another activity has come back
     * @param requestCode the number this class sent when switching activities
     * @param resultCode the number the other activity sends back
     * @param data the data the call to the other activity had brought
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Subscription newSubscription;
        String action;

        super.onActivityResult(requestCode, resultCode, data);

        // Request code == 1 mean that this class is expecting new subscription
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                newSubscription = (Subscription) data.getExtras().getSerializable("newSub");

                subscriptionList.add(newSubscription);
                adapter.notifyDataSetChanged();

                saveInFile();
            }

         // Request code == 2 means that this class is expecting a modification/deltion of a subscription
        } else if (requestCode == 2){
            if(resultCode == Activity.RESULT_OK){
                action = data.getStringExtra("action");
                if (action.equals("DELETE")){                       // delete clicked subscription
                    subscriptionList.remove(clickedSubscription);
                    adapter.notifyDataSetChanged();

                    saveInFile();
                } else if (action.equals("MODIFIED")) {             // update subscription values
                    newSubscription = (Subscription) data.getExtras().getSerializable("newsub");
                }
            }
        }
    }

    /**
     * Saves subscriptions into a file
     *
     * @throws RuntimeException the file is not found or somethings wrong with IO
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(subscriptionList, out);
            out.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * Loads the subscription from a file
     *
     * @throws RuntimeException the file is not found or somethings wrong with IO
     */
    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();
            subscriptionList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            subscriptionList = new ArrayList<Subscription>();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }
}
