package vinhbb96er.cntt.com.problem8_timepickeranddatepicker;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vinh Nguyen on 2/7/2018.
 */

public class WorkOnWeek {
    private String workName;
    private String content;
    private Date dateFinish;
    private Date timeFinish;

    public WorkOnWeek() {
    }

    public WorkOnWeek(String workName, String content, Date dateFinish, Date timeFinish) {
        this.workName = workName;
        this.content = content;
        this.dateFinish = dateFinish;
        this.timeFinish = timeFinish;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Date getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(Date timeFinish) {
        this.timeFinish = timeFinish;
    }

    public static String formatDate(Date d) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return  df.format(d);
    }

    public static String formatTime(Date d) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm a");
        return df.format(d);
    }

    @Override
    public String toString() {
        return this.workName + " - " + formatDate(this.dateFinish) + " - " + formatTime(this.timeFinish);
    }
}
