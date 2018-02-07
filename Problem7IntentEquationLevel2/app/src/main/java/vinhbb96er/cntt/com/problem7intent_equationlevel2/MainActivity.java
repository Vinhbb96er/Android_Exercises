package vinhbb96er.cntt.com.problem7intent_equationlevel2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText numberA;
    private EditText numberB;
    private EditText numberC;
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

                if (numberA.getText().toString().isEmpty() || numberB.getText().toString().isEmpty() || numberC.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Lỗi: Bạn chưa nhập đủ số", Toast.LENGTH_LONG).show();
                    return;
                }

                float a = 0, b = 0, c = 0;
                try {
                    a = Float.parseFloat(numberA.getText().toString());
                    b = Float.parseFloat(numberB.getText().toString());
                    c = Float.parseFloat(numberC.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Lỗi: Nhập sai", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putFloat("numberA", a);
                bundle.putFloat("numberB", b);
                bundle.putFloat("numberC", c);
                intent.putExtra("MyPackage", bundle);
                startActivityForResult(intent, 100);
            }
        });
    }

    private void mapping() {
        numberA = findViewById(R.id.edt_a);
        numberB = findViewById(R.id.edt_b);
        numberC = findViewById(R.id.edt_c);
        btnSolve = findViewById(R.id.btn_solve);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if(resultCode == RESULT_OK) {
                String a = numberA.getText().toString();
                String b = numberB.getText().toString();
                String c = numberC.getText().toString();
                Toast.makeText(MainActivity.this, "Wellcome back to MainActivity ! Your last edit text : a = " + a + ", b = " + b + ", c = " + c, Toast.LENGTH_LONG).show();
                numberA.setText("0");
                numberB.setText("0");
                numberC.setText("0");
            }
        }
    }
}

