package top.zcwfeng.sunflower_java.viewmodels;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

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
}
