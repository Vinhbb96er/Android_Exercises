package vinhbb96er.cntt.com.bai7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText aNumber;
    private EditText bNumber;
    private EditText cNumber;
    private TextView result;
    private Button btnSolve;
    private Button btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mapping();

        btnSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               float a = 0, b = 0, c = 0;
               try {
                   a = Float.parseFloat(aNumber.getText().toString());
                   b = Float.parseFloat(bNumber.getText().toString());
                   c = Float.parseFloat(cNumber.getText().toString());
               } catch (Exception e) {
                   result.setText("Lỗi: Nhập sai!");
                   return;
               }
               solveEquationLevel2(a, b, c);
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aNumber.setText("");
                bNumber.setText("");
                cNumber.setText("");
                result.setText("Kết quả");
            }
        });
    }

    private void mapping() {
        aNumber = findViewById(R.id.edt_a);
        bNumber = findViewById(R.id.edt_b);
        cNumber = findViewById(R.id.edt_c);
        result = findViewById(R.id.tv_result);
        btnSolve = findViewById(R.id.btn_solve);
        btnRemove = findViewById(R.id.btn_remove);
    }

    private void solveEquationLevel2(float a, float b, float c) {
        if (a == 0) {
            if (b == 0) {
                if ( c == 0) {
                    result.setText("Kết quả: Vô số nghiệm");
                    return;
                } else {
                    result.setText("Kết quả: Vô nghiệm");
                    return;
                }
            } else {
                result.setText("Kết quả: 1 nghiệm duy nhất\nx = " + (- c / b));
                return;
            }
        } else {
            float delta = b * b - 4 * a * c;
            if (delta > 0) {
                double x1 = (- b - Math.sqrt(delta)) / (2 * a);
                double x2 = (- b + Math.sqrt(delta)) / (2 * a);
                result.setText("Kết quả: 2 nghiệm phân biệt\nx1 = " + x1 + "\nx2 = " + x2);
                return;
            } else if (delta == 0) {
                double x = - b / (2 * a);
                result.setText("Kết quả: Nghiệm kép\nx = " + x);
                return;
            } else {
                result.setText("Kết quả: Vô nghiệm");
                return;
            }
        }
    }
}
