package vinhbb96er.cntt.com.problem7intent_equationlevel2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by Vinh Nguyen on 2/6/2018.
 */

public class ResultActivity extends AppCompatActivity {

    private TextView result;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = (TextView) findViewById(R.id.tv_result);
        btnBack = (Button) findViewById(R.id.btn_back);

        Intent callerIntent = getIntent();
        Bundle packBunle = callerIntent.getBundleExtra("MyPackage");
        float a = packBunle.getFloat("numberA");
        float b = packBunle.getFloat("numberB");
        float c = packBunle.getFloat("numberC");
        solveEquationLevel2(a, b, c);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void solveEquationLevel2(float a, float b, float c) {
        DecimalFormat df = new DecimalFormat("###.####");
        if (a == 0) {
            if (b == 0) {
                if ( c == 0) {
                    result.setText("Vô số nghiệm");
                    return;
                } else {
                    result.setText("Vô nghiệm");
                    return;
                }
            } else {
                result.setText("1 nghiệm duy nhất\nx = " + df.format(- c / b));
                return;
            }
        } else {
            float delta = b * b - 4 * a * c;
            if (delta > 0) {
                double x1 = (- b - Math.sqrt(delta)) / (2 * a);
                double x2 = (- b + Math.sqrt(delta)) / (2 * a);
                result.setText("2 nghiệm phân biệt\nx1 = " + df.format(x1) + "\nx2 = " + df.format(x2));
                return;
            } else if (delta == 0) {
                double x = - b / (2 * a);
                result.setText("Nghiệm kép\nx = " + df.format(x));
                return;
            } else {
                result.setText("Vô nghiệm");
                return;
            }
        }
    }
}
