package app.dotinfiny.Bdf;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomAppBar;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomAppBar = findViewById(R.id.bottom_navigation_menu);

        preferences = getApplicationContext().getSharedPreferences("Main", MODE_PRIVATE);

        //init();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomAppBar, navController);


        boolean newUser = preferences.getBoolean("IsNewUser", true);


        if (newUser) {
            navController.navigate(R.id.action_homeFragment_to_settingProfileFragment);
        } else {


        }
    }


}