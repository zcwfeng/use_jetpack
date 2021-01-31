package top.zcwfeng.sunflower_java.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Objects;

/**
 * 这个是 植物 的表实体
 * 数据表 plants == Plant实体类
 * （plants表：存储了所有植物的信息）
 *  这里数据的获取并不是来源于网络，而是来自于事先已经创建好了的assets目录下的json数组（plants.json），
 *  并在数据库创建时，通过WorkManger发送这个请求，把所有Plant的信息insert到plants表中
 */
@Entity(tableName = "plants")
public final class Plant {
    private static final int DEFAULT_WATERING_INTERVAL = 7;



    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private final String plantId;
    @NonNull
    private final String imageUrl;
    @NonNull
    private final String name;

    @NonNull
    private final String description; // 描述详情，很长很长的文字信息
    // 植物应该多久浇水一次，以天为单位
    // how often the plant should be watered, in days
    private final int wateringInterval;
    private final int growZoneNumber;

    public Plant(@NonNull String plantId, @NonNull String imageUrl, @NonNull String name,
                 @NonNull String description, int wateringInterval, int growZoneNumber) {
        this.plantId = plantId;
        this.imageUrl = imageUrl;
        this.name = name;
        this.description = description;
        this.wateringInterval = wateringInterval;
        this.growZoneNumber = growZoneNumber;
    }

    /**
     * Determines if the plant should be watered.  Returns true if [since]'s date > date of last
     * watering + watering Interval; false otherwise.
     */
    public boolean shouldBeWatered(Calendar since, Calendar lastWateringDate) {
        lastWateringDate.add(Calendar.DAY_OF_YEAR, wateringInterval);
        return since.compareTo(lastWateringDate) > 0;
    }



    @NonNull
    public String getPlantId() {
        return plantId;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public int getWateringInterval() {
        return wateringInterval;
    }

    public int getGrowZoneNumber() {
        return growZoneNumber;
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
        if (!(o instanceof Plant)) return false;
        Plant plant = (Plant) o;
        return plantId.equals(plant.plantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plantId);
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
