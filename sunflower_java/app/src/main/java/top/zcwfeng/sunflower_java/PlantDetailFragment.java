package top.zcwfeng.sunflower_java;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import top.zcwfeng.sunflower_java.databinding.FragmentPlantDetailBinding;
import top.zcwfeng.sunflower_java.utilities.InjectorUtils;
import top.zcwfeng.sunflower_java.viewmodels.PlantDetailViewModel;
import top.zcwfeng.sunflower_java.viewmodels.PlantDetailViewModelFactory;


public class PlantDetailFragment extends Fragment {

    private String shareText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPlantDetailBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_plant_detail, container, false);
        PlantDetailFragmentArgs args = PlantDetailFragmentArgs.fromBundle(requireArguments());
        PlantDetailViewModelFactory factory = InjectorUtils.providerPlantDetailViewModelFactory(
                requireContext(), args.getPlantId());
//        PlantDetailViewModel viewModel = ViewModelProviders.of(this, factory).get(PlantDetailViewModel.class);
        PlantDetailViewModel viewModel = new ViewModelProvider(this, factory).get(PlantDetailViewModel.class);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);
        binding.fab.setOnClickListener(v -> {
            viewModel.addPlantToGarden();
            Snackbar.make(v, R.string.added_plant_to_garden, Snackbar.LENGTH_LONG).show();
        });

        viewModel.plant.observe(this.getViewLifecycleOwner(), plant ->
                this.shareText = plant == null ? "" : getString(R.string.share_text_plant, plant.getName()));

        setHasOptionsMenu(true);

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_plant_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    // TODO: 2021/1/31 use desc
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
                        .setText(this.shareText)
                        .setType("text/plain")
                        .createChooserIntent();
                // https://android-developers.googleblog.com/2012/02/share-with-intents.html
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // If we're on Lollipop, we can open the intent as a document
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                } else {
                    // Else, we will use the old CLEAR_WHEN_TASK_RESET flag
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                }
                startActivity(shareIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}