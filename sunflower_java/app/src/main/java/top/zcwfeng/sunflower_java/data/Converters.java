package top.zcwfeng.sunflower_java.data;

import androidx.room.TypeConverter;

import java.util.Calendar;

/**
 * Type converters to allow Room to reference complex data types.
 */
public class Converters {

    @TypeConverter
    public long calendarToDatestamp(Calendar calendar) {
        return calendar.getTimeInMillis();
    }

    @TypeConverter
    public Calendar datestampToCalendar(long value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(value);
        return calendar;
    }
}