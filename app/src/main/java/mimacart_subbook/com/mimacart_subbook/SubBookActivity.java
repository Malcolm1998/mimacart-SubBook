package mimacart_subbook.com.mimacart_subbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SubBookActivity extends AppCompatActivity {
    private ListView subscriptionListView;
    private ArrayList<Subscription> subscriptionList;
    private Button addSubscriptionButton;
    private ArrayAdapter<Subscription> adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LifeCycle ---->", "onCreate is called");
        setContentView(R.layout.activity_main);

        subscriptionList = new ArrayList<Subscription>();

        addSubscriptionButton = findViewById(R.id.addSubscription);
        subscriptionListView = findViewById(R.id.subscriptionListView);

        adapter = new ArrayAdapter<Subscription>(this,
                R.layout.list_item, subscriptionList);
        subscriptionListView.setAdapter(adapter);

        addSubscriptionButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Intent intent = new Intent(SubBookActivity.this, CreateSubscriptionActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        subscriptionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                setResult(RESULT_OK);
                Intent intent = new Intent(view.getContext(), SubscriptionDetailsActivity.class);
                startActivity(intent);
            }

        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("LifeCycle ---->", "onStart is called");

        //loadFromFile();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Subscription newSubscription = new Subscription();

        super.onActivityResult(requestCode, resultCode, data);
        Intent mIntent = getIntent();

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Bundle bundle = data.getExtras();
                newSubscription.setName(data.getStringExtra("name"));
                newSubscription.setMonthlyCharge(Double.parseDouble(data.getStringExtra("monthlyCharge")));
                newSubscription.setDateStartedDayOfMonth(Integer.parseInt(data.getStringExtra("day")));
                newSubscription.setDateStartedMonth(Integer.parseInt(data.getStringExtra("month")));
                newSubscription.setDateStartedYear(Integer.parseInt(data.getStringExtra("year")));

                subscriptionList.add(newSubscription);
                adapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
