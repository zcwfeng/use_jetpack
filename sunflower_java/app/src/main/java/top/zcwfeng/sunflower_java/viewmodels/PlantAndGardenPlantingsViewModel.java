package top.zcwfeng.sunflower_java.viewmodels;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.google.common.base.Preconditions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import top.zcwfeng.sunflower_java.data.GardenPlanting;
import top.zcwfeng.sunflower_java.data.Plant;
import top.zcwfeng.sunflower_java.data.PlantAndGardenPlantings;

public class PlantAndGardenPlantingsViewModel extends ViewModel {
    // ObservableField 感应数据变化   ---->  LiveData 感应数据变化
    // 浇水时间 浇水日 信息数据成员
    public ObservableField<String> waterDateString;
    public ObservableInt wateringInterval;
    // 图片的URL
    public ObservableField<String> imageUrl;
    // 花的名称
    public ObservableField<String> plantName;
    // 应该是 种植于 什么 什么 时间 的信息
    public ObservableField<String> plantDateString;
    public PlantAndGardenPlantingsViewModel(@NonNull PlantAndGardenPlantings plantings) {
        // Preconditions.checkNotNull ----google guava utilities
        final Plant plant = Preconditions.checkNotNull(plantings.getPlant());
        final GardenPlanting gardenPlanting = plantings.getGardenPlantings().get(0);
        final DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);

        this.waterDateString = new ObservableField<>(dateFormat.format(gardenPlanting.getLastWateringDate().getTime()));
        this.wateringInterval = new ObservableInt(plant.getWateringInterval());
        this.imageUrl = new ObservableField<>(plant.getImageUrl());
        this.plantName = new ObservableField<>(plant.getName());
        this.plantDateString = new ObservableField<>(dateFormat.format(gardenPlanting.getPlantDate().getTime()));
    }
}
