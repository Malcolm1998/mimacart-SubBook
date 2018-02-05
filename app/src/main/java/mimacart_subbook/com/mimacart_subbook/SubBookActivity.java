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

public class SubBookActivity extends AppCompatActivity {
    private static final String FILENAME = "subscriptions.sav";
    private ListView subscriptionListView;
    private ArrayList<Subscription> subscriptionList;
    private Button addSubscriptionButton;
    private SubscriptionAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LifeCycle ---->", "onCreate is called");
        setContentView(R.layout.activity_main);

        subscriptionList = new ArrayList<Subscription>();

        addSubscriptionButton = findViewById(R.id.addSubscription);
        subscriptionListView = findViewById(R.id.subscriptionListView);

        //adapter = new SubscriptionAdapter(this, subscriptionList);
        //subscriptionListView.setAdapter(adapter);

        addSubscriptionButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                //saveInFile();

                Intent createIntent = new Intent(SubBookActivity.this,
                        CreateSubscriptionActivity.class);
                startActivityForResult(createIntent, 1);
            }
        });

        subscriptionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Subscription clickedSubscription;

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //saveInFile();

                Intent detailIntent = new Intent(SubBookActivity.this,
                        SubscriptionDetailsActivity.class);
                clickedSubscription = adapter.getItem(position);
                detailIntent.putExtra("name", clickedSubscription.getName());
                detailIntent.putExtra("monthlyCharge",
                        String.valueOf(clickedSubscription.getMonthlyCharge()));
                detailIntent.putExtra("comments",
                        clickedSubscription.getComments());
                detailIntent.putExtra("day",
                        String.valueOf(clickedSubscription.getDateStartedDayOfMonth()));
                detailIntent.putExtra("month",
                        String.valueOf(clickedSubscription.getDateStartedMonth()));
                detailIntent.putExtra("year",
                        String.valueOf(clickedSubscription.getDateStartedYear()));
                startActivityForResult(detailIntent, 2);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("LifeCycle ---->", "onStart is called");

        loadFromFile();

        adapter = new SubscriptionAdapter(this, subscriptionList);
        subscriptionListView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Subscription newSubscription = new Subscription();

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                newSubscription.setName(data.getStringExtra("name"));
                newSubscription.setMonthlyCharge(Double.parseDouble(data.getStringExtra(
                        "monthlyCharge")));
                newSubscription.setComments(data.getStringExtra("comments"));
                newSubscription.setDateStartedDayOfMonth(Integer.parseInt(data.getStringExtra(
                        "day")));
                newSubscription.setDateStartedMonth(Integer.parseInt(data.getStringExtra(
                        "month")));
                newSubscription.setDateStartedYear(Integer.parseInt(data.getStringExtra(
                        "year")));


                subscriptionList.add(newSubscription);
                adapter.notifyDataSetChanged();

                saveInFile();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
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
