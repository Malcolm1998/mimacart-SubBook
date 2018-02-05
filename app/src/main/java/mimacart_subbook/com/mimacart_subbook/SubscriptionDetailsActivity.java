package mimacart_subbook.com.mimacart_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SubscriptionDetailsActivity extends AppCompatActivity {
    Subscription currentSubscription;
    private TextView subscriptionNameTextView;
    private TextView dateTextView;
    private TextView monthlyChargeTextView;
    private TextView commentsTextView;
    private Button deleteSubscriptionButton;
    private Button modifySubscriptionButtton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_details);

        Intent data = getIntent();
        currentSubscription = new Subscription();
        currentSubscription.setName(data.getStringExtra("name"));
        currentSubscription.setMonthlyCharge(Double.parseDouble(data.getStringExtra(
                "monthlyCharge")));
        currentSubscription.setComments(data.getStringExtra("comments"));
        currentSubscription.setDateStartedDayOfMonth(Integer.parseInt(data.getStringExtra(
                "day")));
        currentSubscription.setDateStartedMonth(Integer.parseInt(data.getStringExtra(
                "month")));
        currentSubscription.setDateStartedYear(Integer.parseInt(data.getStringExtra(
                "year")));

        subscriptionNameTextView = findViewById(R.id.nameDetail);
        monthlyChargeTextView = findViewById(R.id.chargeDetail);
        commentsTextView = findViewById(R.id.commentsDetail);
        dateTextView = findViewById(R.id.dateDetail);
        modifySubscriptionButtton = findViewById(R.id.modifySubscription);
        deleteSubscriptionButton = findViewById(R.id.deleteSubscription);

        subscriptionNameTextView.setText(currentSubscription.getName());
        monthlyChargeTextView.setText(String.valueOf(currentSubscription.getMonthlyCharge()));
        commentsTextView.setText(currentSubscription.getComments());
        dateTextView.setText(String.valueOf(currentSubscription.getDateStartedYear()) + "-" +
                String.valueOf(currentSubscription.getDateStartedMonth()) + "-" +
                String.valueOf(currentSubscription.getDateStartedDayOfMonth()));
    }
}
