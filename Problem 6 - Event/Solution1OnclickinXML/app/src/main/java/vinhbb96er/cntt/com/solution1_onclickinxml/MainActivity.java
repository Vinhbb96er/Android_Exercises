package vinhbb96er.cntt.com.solution1_onclickinxml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText numberA;
    private EditText numberB;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateSum(View v) {
        numberA = (EditText) findViewById(R.id.edt_numberA);
        numberB = (EditText) findViewById(R.id.edt_numberB);
        result = (TextView) findViewById(R.id.tv_result);
        if (numberA.getText().toString().isEmpty() || numberB.getText().toString().isEmpty()) {
            result.setText("Lỗi: Chưa nhập đủ số");
            return;
        }
        float numA = Float.parseFloat(numberA.getText().toString());
        float numB = Float.parseFloat(numberB.getText().toString());
        result.setText(String.valueOf(numA + numB));
    }
}
