package top.zcwfeng.sunflower_java.data;

import org.junit.Test;

import java.util.Calendar;
import static org.junit.Assert.assertEquals;

public class ConvertersTest {

    private final Calendar cal;

    public ConvertersTest() {
        this.cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1998);
        cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 4);
    }

    @Test
    public void calendarToDatestamp() {
        assertEquals(cal.getTimeInMillis(), new Converters().calendarToDatestamp(cal));
    }

    @Test
    public void datestampToCalendar() {
        assertEquals(new Converters().datestampToCalendar(cal.getTimeInMillis()), cal);
    }
}