package vinhbb96er.cntt.com.mycalculator;

import android.icu.util.IslamicCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView computation;
    private TextView result;

    private Button btnClear;
    private Button btnAllClear;

    private Button btnNumber0;
    private Button btnNumber1;
    private Button btnNumber2;
    private Button btnNumber3;
    private Button btnNumber4;
    private Button btnNumber5;
    private Button btnNumber6;
    private Button btnNumber7;
    private Button btnNumber8;
    private Button btnNumber9;

    private Button btnPlus;
    private Button btnSubtract;
    private Button btnMulti;
    private Button btnDivide;
    private Button btnDot;
    private Button btnEqual;

    private ArrayList<Character> operatorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operatorList = new ArrayList<>();
        operatorList.add('+');
        operatorList.add('-');
        operatorList.add('*');
        operatorList.add('/');

        this.mapping();
        this.setEventClick();
    }

    private void mapping() {
        result = (TextView) findViewById(R.id.tv_result);
        computation = (TextView) findViewById(R.id.tv_computation);

        btnClear = (Button) findViewById(R.id.btn_clear);
        btnAllClear = (Button) findViewById(R.id.btn_all_clear);

        btnNumber0 = (Button) findViewById(R.id.btn0);
        btnNumber1 = (Button) findViewById(R.id.btn1);
        btnNumber2 = (Button) findViewById(R.id.btn2);
        btnNumber3 = (Button) findViewById(R.id.btn3);
        btnNumber4 = (Button) findViewById(R.id.btn4);
        btnNumber5 = (Button) findViewById(R.id.btn5);
        btnNumber6 = (Button) findViewById(R.id.btn6);
        btnNumber7 = (Button) findViewById(R.id.btn7);
        btnNumber8 = (Button) findViewById(R.id.btn8);
        btnNumber9 = (Button) findViewById(R.id.btn9);

        btnPlus = (Button) findViewById(R.id.btn_plus);
        btnSubtract = (Button) findViewById(R.id.btn_subtract);
        btnMulti = (Button) findViewById(R.id.btn_multi);
        btnDivide = (Button) findViewById(R.id.btn_divide);
        btnDot = (Button) findViewById(R.id.btn_dot);
        btnEqual = (Button) findViewById(R.id.btn_equal);
    }

    private void setEventClick() {
        btnClear.setOnClickListener(this);
        btnAllClear.setOnClickListener(this);

        btnNumber0.setOnClickListener(this);
        btnNumber1.setOnClickListener(this);
        btnNumber2.setOnClickListener(this);
        btnNumber3.setOnClickListener(this);
        btnNumber4.setOnClickListener(this);
        btnNumber5.setOnClickListener(this);
        btnNumber6.setOnClickListener(this);
        btnNumber7.setOnClickListener(this);
        btnNumber8.setOnClickListener(this);
        btnNumber9.setOnClickListener(this);

        btnPlus.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnMulti.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
    }

    private void checkOperator(String operator) {
        String computationText = computation.getText().toString();
        // if press operator first or last charator of computation is '.'
        if (computationText.isEmpty() || computationText.charAt(computationText.length() - 1) == '.') {
            computation.setText(computation.getText().toString() + "0" + operator);
        } else if (!operatorList.contains(computationText.charAt(computationText.length() - 1))) {
            // if last charator of computation is not operator
            computation.setText(computation.getText().toString() + operator);
        } else {
            // replace old operator by new operator
            String computationVal = computation.getText().toString();
            computationVal = computationVal.substring(0, computationVal.length() - 1);
            computation.setText(computationVal + operator);
        }
    }

    private void checkDecimalNumber() {
        String computationText = computation.getText().toString();
        // if press button '.' first
        if (computationText.isEmpty()) {
            computation.setText("0.");
        } else if (!operatorList.contains(computationText.charAt(computationText.length() - 1))) {
            int index = 0;
            // get last number
            for(int i = computationText.length() - 2; i >= 0; i--) {
                Character check = computationText.charAt(i);
                if (operatorList.contains(check)) {
                    index = i + 1;
                    break;
                }
            }
            String lastNumber = computationText.substring(index);
            // if lastNumber has not '.'
            if (lastNumber.indexOf('.') == -1) {
                computation.setText(computation.getText().toString() + ".");
            }
        }
    }

    private void calculate() {
        String computationText = computation.getText().toString();
        if (computationText.isEmpty()) {
            result.setText("0");
        } else {
            // if last character is '.' or operator
            if (computationText.charAt(computationText.length() - 1) == '.' || operatorList.contains(computationText.charAt(computationText.length() - 1))) {
                computationText = computation.getText().toString() + "0";
                computation.setText(computationText);
            }

            // if has not operator on computation
            if (computationText.indexOf('+') == -1 && computationText.indexOf('-') == -1 && computationText.indexOf('*') == -1 && computationText.indexOf('/') == -1) {
                result.setText(computation.getText().toString());
            } else {
                ArrayList<Double> numbers = splitNumbers(computationText);
                ArrayList<Character> operators = splitOperator(computationText);
                double resultVal = numbers.get(0);
                for (int i = 0; i < operators.size(); i++) {
                    switch (operators.get(i)) {
                        case '+':
                            resultVal += numbers.get(i + 1);
                            break;
                        case '-':
                            resultVal -= numbers.get(i + 1);
                            break;
                        case '*':
                            resultVal *= numbers.get(i + 1);
                            break;
                        case '/':
                            resultVal /= numbers.get(i + 1);
                            break;
                    }
                }
                DecimalFormat decimalFormat = new DecimalFormat("####.########");
                result.setText(String.valueOf(decimalFormat.format(resultVal)));
            }
        }
    }

    private ArrayList<Double> splitNumbers(String computationText) {
        ArrayList<Double> numbers = new ArrayList<>();
        int startNum = 0;
        for (int i = 0; i < computationText.length(); i++) {
            Character check = computationText.charAt(i);
            if (i == computationText.length() - 1) {
                String num = computationText.substring(startNum);
                numbers.add(Double.parseDouble(num));
            } else if (operatorList.contains(check)) {
                String num = computationText.substring(startNum, i);
                numbers.add(Double.parseDouble(num));
                startNum = i + 1;
            }
        }
        return numbers;
    }

    private ArrayList<Character> splitOperator(String computationText) {
        ArrayList<Character> operators = new ArrayList<>();
        for (int i = 0; i < computationText.length(); i++) {
            Character check = computationText.charAt(i);
            if (operatorList.contains(check)) {
                operators.add(check);
            }
        }
        return  operators;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_clear:
                String computationText = computation.getText().toString();
                if(!computationText.isEmpty()) {
                    computationText = computationText.substring(0, computationText.length() - 1);
                    computation.setText(computationText);
                }
                break;
            case R.id.btn_all_clear:
                computation.setText("");
                result.setText("0");
                break;
            case R.id.btn0:
                computation.setText(computation.getText().toString() + "0");
                break;
            case R.id.btn1:
                computation.setText(computation.getText().toString() + "1");
                break;
            case R.id.btn2:
                computation.setText(computation.getText().toString() + "2");
                break;
            case R.id.btn3:
                computation.setText(computation.getText().toString() + "3");
                break;
            case R.id.btn4:
                computation.setText(computation.getText().toString() + "4");
                break;
            case R.id.btn5:
                computation.setText(computation.getText().toString() + "5");
                break;
            case R.id.btn6:
                computation.setText(computation.getText().toString() + "6");
                break;
            case R.id.btn7:
                computation.setText(computation.getText().toString() + "7");
                break;
            case R.id.btn8:
                computation.setText(computation.getText().toString() + "8");
            case R.id.btn9:
                computation.setText(computation.getText().toString() + "9");
                break;
            case R.id.btn_plus:
                checkOperator("+");
                break;
            case R.id.btn_subtract:
                checkOperator("-");
                break;
            case R.id.btn_multi:
                checkOperator("*");
                break;
            case R.id.btn_divide:
                checkOperator("/");
                break;
            case R.id.btn_dot:
                checkDecimalNumber();
                break;
            case R.id.btn_equal:
                calculate();
                break;
        }
    }
}
