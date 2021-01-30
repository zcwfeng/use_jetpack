package top.zcwfeng.sunflower_java.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Objects;

//依赖于你如何访问数据, 你也许想要在数据库中建立某些域的索引, 以加速查询速度. 要给实体添加索引,
//        需要在@Entity中引入indices属性, 并列出你想要在索引或者复合索引中引入的列的名字.
@Entity(tableName = "garden_planting",
        foreignKeys = {@ForeignKey(entity = Plant.class,
                parentColumns = {"id"},
                childColumns = {"plant_id"})},
        indices = {@Index("plant_id")})

public final class GardenPlanting {
    @ColumnInfo(name = "plant_id")
    private final String plantId;

    /**
     * Indicates when the [Plant] was planted. Used for showing notification when it's time
     * to harvest the plant.
     */
    @ColumnInfo(name = "plant_date")
    private final Calendar plantDate;

    /**
     * Indicates when the [Plant] was last watered. Used for showing notification when it's
     * time to water the plant.
     */
    @ColumnInfo(name = "last_watering_date")
    private final Calendar lastWateringDate;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long gardenPlantingId = 0L;

    public GardenPlanting(String plantId, Calendar plantDate, Calendar lastWateringDate) {
        this.plantId = plantId;
        this.plantDate = plantDate;
        this.lastWateringDate = lastWateringDate;
    }

    public String getPlantId() {
        return plantId;
    }

    public Calendar getPlantDate() {
        return plantDate;
    }

    public Calendar getLastWateringDate() {
        return lastWateringDate;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("GardenPlanting(plantId=%s, plantDate=%s, lastWateringDate=%s)",
                plantId, plantDate.toString(), lastWateringDate.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        GardenPlanting target = (GardenPlanting) obj;

        return this.plantId.equals(target.plantId)
                && this.plantDate.equals(this.plantDate)
                && this.lastWateringDate.equals(this.lastWateringDate);
    }

    /**
     * As [Plant.kt] is declared as [Data class], {@link Object#hashCode()} implicit implemented.
     * So we explicit implemented {@link Object#hashCode()} in [Plant.java]
     * see: https://kotlinlang.org/docs/reference/data-classes.html
     */
    @Override
    public int hashCode() {
        return Objects.hash(plantId);
    }

    /**
     * As [Plant.kt] is declared as [Data class], {copy()} implicit implemented.
     * So we explicit implemented {@link Object#clone()} in [Plant.java]
     * see: https://kotlinlang.org/docs/reference/data-classes.html
     */
    @Override
    protected Object clone() {
        return new GardenPlanting(plantId, plantDate, lastWateringDate);
    }
}
