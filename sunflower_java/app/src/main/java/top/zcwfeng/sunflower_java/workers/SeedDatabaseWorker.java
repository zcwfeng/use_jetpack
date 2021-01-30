package top.zcwfeng.sunflower_java.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import top.zcwfeng.sunflower_java.data.AppDatabase;
import top.zcwfeng.sunflower_java.data.Plant;
import top.zcwfeng.sunflower_java.utilities.Constants;

public class SeedDatabaseWorker extends Worker {

    private static final String TAG = SeedDatabaseWorker.class.getSimpleName();

    /**
     * @param appContext   The application {@link Context}
     * @param workerParams Parameters to setup the internal state of this worker
     */
    public SeedDatabaseWorker(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            InputStream input = getApplicationContext().getAssets().open(Constants.PLANT_DATA_FILENAME);
            JsonReader reader = new JsonReader(new InputStreamReader(input));
            Type plantType = new TypeToken<List<Plant>>(){}.getType();
            List<Plant> plantList = new Gson().fromJson(reader, plantType);
            input.close();

            AppDatabase database = AppDatabase.getInstance(getApplicationContext());
            database.getPlantDao().insertAll(plantList);

            return Result.success();
        } catch (IOException e) {
            Log.e(TAG, "Error seeding database", e);
            return Result.failure();
        }
    }
}