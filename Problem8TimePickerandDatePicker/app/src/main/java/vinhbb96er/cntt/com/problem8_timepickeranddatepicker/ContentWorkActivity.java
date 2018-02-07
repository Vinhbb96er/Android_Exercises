package vinhbb96er.cntt.com.problem8_timepickeranddatepicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Vinh Nguyen on 2/7/2018.
 */

public class ContentWorkActivity extends AppCompatActivity {

    private TextView workName;
    private TextView content;
    private TextView dateTime;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_work_activity);

        this.mapping();

        Intent callerIntent = getIntent();
        Bundle packBundle = callerIntent.getBundleExtra("WorkPackage");
        workName.setText(packBundle.getString("workName"));
        content.setText(packBundle.getString("content"));
        dateTime.setText(packBundle.getString("dateTime"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void mapping() {
        workName = (TextView) findViewById(R.id.tv_work);
        content = (TextView) findViewById(R.id.tv_content);
        dateTime = (TextView) findViewById(R.id.tv_date_time);
        btnBack = (Button) findViewById(R.id.btn_back);
    }
}
