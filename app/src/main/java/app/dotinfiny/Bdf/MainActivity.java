package app.dotinfiny.Bdf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomAppBar;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomAppBar = findViewById(R.id.bottom_navigation_menu);


        // StorageReference rootRef = spaceRef.getRoot();

        preferences = getApplicationContext().getSharedPreferences(Constants.PREFNAME, MODE_PRIVATE);

        //init();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomAppBar, navController);


        boolean newUser = preferences.getBoolean(Constants.ISNEWUSER, false);


        if (newUser) {
            navController.navigate(R.id.action_homeFragment_to_settingProfileFragment);
        } else {


        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Log.d("Reques Code",""+requestCode);
//    }
}