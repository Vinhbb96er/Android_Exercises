package vinhbb96er.cntt.com.problem8_timepickeranddatepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText workName;
    private EditText content;
    private TextView dateFinishTv;
    private TextView timeFinishTv;
    private Button btnDate;
    private Button btnTime;
    private Button btnAddWork;
    private ListView list;
    private ArrayList<WorkOnWeek> workLists;
    private ArrayAdapter<WorkOnWeek> adapter;
    private Calendar calendar;
    private Date dateFinish;
    private Date timeFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mapping();
        this.initDefaultValues();
        this.addEvent();
    }

    private void mapping() {
        workName = (EditText) findViewById(R.id.edt_work);
        content = (EditText) findViewById(R.id.edt_content);
        dateFinishTv = (TextView) findViewById(R.id.tv_date_finish);
        timeFinishTv = (TextView) findViewById(R.id.tv_time_finish);
        btnDate = (Button) findViewById(R.id.btn_date);
        btnTime = (Button) findViewById(R.id.btn_time);
        btnAddWork = (Button) findViewById(R.id.btn_add_work);
        list = (ListView) findViewById(R.id.lv_work);
        workLists = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workLists);
        list.setAdapter(adapter);
    }

    private void initDefaultValues() {
        calendar = Calendar.getInstance();
        dateFinish = calendar.getTime();
        timeFinish = calendar.getTime();
        String date = WorkOnWeek.formatDate(dateFinish);
        String time = WorkOnWeek.formatTime(timeFinish);
        dateFinishTv.setText(date);
        timeFinishTv.setText(time);
    }

    private void addEvent() {
        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnAddWork.setOnClickListener(this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ContentWorkActivity.class);
                WorkOnWeek work = workLists.get(i);
                Bundle bundle = new Bundle();
                bundle.putString("workName", work.getWorkName());
                bundle.putString("content", work.getContent());
                String dateTime = WorkOnWeek.formatTime(work.getTimeFinish()) + " - " + WorkOnWeek.formatDate(work.getDateFinish());
                bundle.putString("dateTime", dateTime);
                intent.putExtra("WorkPackage", bundle);
                startActivity(intent);
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                workLists.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    private void showDatePickerDialog()
    {
        String date = dateFinishTv.getText().toString();
        String arrDate[] = date.split("/");
        int day = Integer.parseInt(arrDate[0]);
        int month = Integer.parseInt(arrDate[1])-1;
        int year = Integer.parseInt(arrDate[2]);
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(year, month, day);
                dateFinish = calendar.getTime();
                dateFinishTv.setText(WorkOnWeek.formatDate(dateFinish));
            }
        }, year, month, day);
        datePickerDialog.setTitle("Chọn ngày hoàn thành");
        datePickerDialog.show();
    }

    private void showTimePickerDialog()
    {
        String time = timeFinishTv.getText().toString();
        time = (time.indexOf("AM") != -1) ? time.replace(" AM", "") : time.replace(" PM", "");
        String arrTime[] = time.split(":");
        int hour = Integer.parseInt(arrTime[0]);
        int minute = Integer.parseInt(arrTime[1]);
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                timeFinish = calendar.getTime();
                timeFinishTv.setText(WorkOnWeek.formatTime(timeFinish));
            }
        }, hour, minute, true);
        timePickerDialog.setTitle("Chọn giờ hoàn thành");
        timePickerDialog.show();
    }

    private void addNewWork() {
        String workNameText = workName.getText().toString();
        String contentText = content.getText().toString();
        if (workNameText.isEmpty() || contentText.isEmpty()) {
            Toast.makeText(MainActivity.this, "Bạn phải nhập đủ Công việc và Nội dung", Toast.LENGTH_LONG).show();
            return;
        }
        WorkOnWeek work = new WorkOnWeek(workNameText, contentText, dateFinish, timeFinish);
        workLists.add(work);
        adapter.notifyDataSetChanged();
        workName.setText("");
        content.setText("");
        workName.requestFocus();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_date:
                showDatePickerDialog();
                break;
            case R.id.btn_time:
                showTimePickerDialog();
                break;
            case R.id.btn_add_work:
                addNewWork();
                break;
        }
    }
}
