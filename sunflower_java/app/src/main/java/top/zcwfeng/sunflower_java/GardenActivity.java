package top.zcwfeng.sunflower_java;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import top.zcwfeng.sunflower_java.databinding.ActivityGardenBinding;
// 查询 setupActionBarWithNavController：https://zhuanlan.zhihu.com/p/69653419?from_voters_page=true
public class GardenActivity extends AppCompatActivity {
    // androidx 的 抽屉布局
    private DrawerLayout drawerLayout;
    // androidx. Navigation 查询(AppBarConfiguration)：https://zhuanlan.zhihu.com/p/136479775
    private AppBarConfiguration appBarConfiguration;
    // androidx. Navigation 控制器
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //把整个布局交给了-->DataBinding去管理，那么就需要使用DataBinding初始化布局
        ActivityGardenBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_garden);

        // 拿到布局的drawer_layout 赋值 给成员drawerLayout
        drawerLayout = binding.drawerLayout;

        // 拿到布局的garden_nav_fragment(首页布局Fragment画面区域) 赋值 给成员navController
        navController = Navigation.findNavController(this, R.id.garden_nav_fragment);

        // NavigationUI使用AppBarConfiguration 对象来管理应用程序显示区域左上角的“导航”按钮的行为
        this.appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setDrawerLayout(drawerLayout)
//                .setOpenableLayout(drawerLayout)
                .build();

        setSupportActionBar(binding.toolbar);

        // 通过NavigationUI.setupActionBarWithNavController()方法，将App bar与NavController绑定
        NavigationUI.setupActionBarWithNavController(this, navController,appBarConfiguration);
        // Set up navigation menu
        // 通过NavigationUI.setupActionBarWithNavController()方法，将App bar与NavController绑定
        NavigationUI.setupWithNavController(binding.navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}