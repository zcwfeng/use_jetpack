package top.zcwfeng.sunflower_java.viewmodels;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import top.zcwfeng.sunflower_java.data.GardenPlanting;
import top.zcwfeng.sunflower_java.data.GardenPlantingRepository;
import top.zcwfeng.sunflower_java.data.PlantAndGardenPlantings;

public class GardenPlantingListViewModel extends ViewModel {
    public LiveData<List<GardenPlanting>> gardenPlantings;
    public LiveData<List<PlantAndGardenPlantings>> plantAndGardenPlantings;

    GardenPlantingListViewModel(@NonNull GardenPlantingRepository repository) {
        this.gardenPlantings = repository.getGardenPlantings();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            plantAndGardenPlantings =
                    Transformations.map(repository.getPlantAndGardenPlantings(),
                            plantings -> plantings.stream()
                                    .filter(planting -> !planting.getGardenPlantings().isEmpty())
                                    .collect(Collectors.toList()));
        } else {
            this.plantAndGardenPlantings = Transformations.map(repository.getPlantAndGardenPlantings(), items -> {
                List<PlantAndGardenPlantings> removeItems = new ArrayList<>();
                for (PlantAndGardenPlantings item:items) {
                    if (item.getGardenPlantings().isEmpty()) {
                        removeItems.add(item);
                    }
                }
                return removeItems;
            });
        }

    }
}