package top.zcwfeng.sunflower_java.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import top.zcwfeng.sunflower_java.data.Plant;
import top.zcwfeng.sunflower_java.data.PlantRepository;

public class PlantListViewModel extends ViewModel {
    private static final int NO_GROW_ZONE = -1;

    private PlantRepository plantRepository;

    private MutableLiveData<Integer> growZoneNumber;

    public LiveData<List<Plant>> plants;

    PlantListViewModel(@NonNull PlantRepository plantRepository) {
        super();
        this.plantRepository = plantRepository;
        this.growZoneNumber = new MutableLiveData<>(-1);
        this.plants = Transformations.switchMap(growZoneNumber, it -> {
            if (it == NO_GROW_ZONE) {
                return this.plantRepository.getPlants();
            } else {
                return this.plantRepository.getPlantsWithGrowZoneNumber(it);
            }
        });
    }

    public void setGrowZoneNumber(int num) {
        this.growZoneNumber.setValue(num);
    }

    public void cleanGrowZoneNumber() {
        this.growZoneNumber.setValue(NO_GROW_ZONE);
    }

    public boolean isFiltered() {
        return this.growZoneNumber.getValue() != NO_GROW_ZONE;
    }
}