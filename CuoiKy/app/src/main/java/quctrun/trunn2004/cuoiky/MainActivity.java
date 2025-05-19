package quctrun.trunn2004.cuoiky;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selected = null;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selected = new HomeFragment();
                    break;
                case R.id.nav_stats:
                    selected = new StatsFragment();
                    break;
                case R.id.nav_note:
                    selected = new NoteFragment();
                    break;
                case R.id.nav_account:
                    selected = new AccountFragment();
                    break;
            }
            if (selected != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selected)
                        .commit();
            }
            return true;
        });

        bottomNav.setSelectedItemId(R.id.nav_home);
    }
}
