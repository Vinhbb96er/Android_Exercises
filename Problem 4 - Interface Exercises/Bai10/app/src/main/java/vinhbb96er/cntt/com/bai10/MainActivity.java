package vinhbb96er.cntt.com.bai10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText numberA;
    private EditText numberB;
    private Button btnPlus;
    private Button btnSuctract;
    private Button btnMulti;
    private Button btnDivide;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mapping();

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float a = 0, b = 0;
                try {
                    a = Float.parseFloat(numberA.getText().toString());
                    b = Float.parseFloat(numberB.getText().toString());
                } catch (Exception e) {
                    result.setText("Lỗi: Nhập sai");
                    return;
                }
                result.setText(a + " + " + b + " = " + (a + b));
            }
        });

        btnSuctract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float a = 0, b = 0;
                try {
                    a = Float.parseFloat(numberA.getText().toString());
                    b = Float.parseFloat(numberB.getText().toString());
                } catch (Exception e) {
                    result.setText("Lỗi: Nhập sai");
                    return;
                }
                result.setText(a + " - " + b + " = " + (a - b));
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float a = 0, b = 0;
                try {
                    a = Float.parseFloat(numberA.getText().toString());
                    b = Float.parseFloat(numberB.getText().toString());
                } catch (Exception e) {
                    result.setText("Lỗi: Nhập sai");
                    return;
                }
                result.setText(a + " x " + b + " = " + (a * b));
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float a = 0, b = 0;
                try {
                    a = Float.parseFloat(numberA.getText().toString());
                    b = Float.parseFloat(numberB.getText().toString());
                } catch (Exception e) {
                    result.setText("Lỗi: Nhập sai");
                    return;
                }
                result.setText(a + " / " + b + " = " + (a / b));
            }
        });
    }

    private void mapping() {
        numberA = findViewById(R.id.edt_numberA);
        numberB = findViewById(R.id.edt_numberB);
        btnPlus = findViewById(R.id.btn_plus);
        btnSuctract = findViewById(R.id.btn_subtract);
        btnMulti = findViewById(R.id.btn_multi);
        btnDivide = findViewById(R.id.btn_divide);
        result = findViewById(R.id.tv_result);
    }
}
