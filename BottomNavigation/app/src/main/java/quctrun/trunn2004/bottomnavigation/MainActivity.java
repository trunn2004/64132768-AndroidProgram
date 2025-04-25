package quctrun.trunn2004.bottomnavigation;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bot_nav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int mnuItemChon = item.getItemId();
                if(mnuItemChon == R.id.mnuHome){
                    Toast.makeText(MainActivity.this, "Thay Home", Toast.LENGTH_SHORT).show();
                }
                else if (mnuItemChon == R.id.mnuSearch) {
                    Toast.makeText(MainActivity.this, "Thay Search", Toast.LENGTH_SHORT).show();
                } else if (mnuItemChon == R.id.mnuProfile) {
                    Toast.makeText(MainActivity.this, "Thay Profile", Toast.LENGTH_SHORT).show();
                }
                else return false;
                return true;
            }
        });
    }
}