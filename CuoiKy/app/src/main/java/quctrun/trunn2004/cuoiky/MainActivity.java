package quctrun.trunn2004.cuoiky;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNav;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        bottomNav = findViewById(R.id.bottom_nav);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        View headerView = navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.userName);
        TextView userEmailTextView = headerView.findViewById(R.id.userEmail);
        ImageView avatarImage = headerView.findViewById(R.id.avatarImage);

        userEmailTextView.setText(currentUser.getEmail());
        userNameTextView.setText(currentUser.getDisplayName() != null ?
                currentUser.getDisplayName() : "Người dùng");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (currentUser.getPhotoUrl() != null) {
            Glide.with(this)
                    .load(currentUser.getPhotoUrl())
                    .into(avatarImage);
        }
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selected = null;
            int id = item.getItemId();
            String toolbarTitle = "";
            if (id == R.id.nav_home) {
                selected = new HomeFragment();
                toolbarTitle = "Trang chủ";
            } else if (id == R.id.nav_account) {
                selected = new AccountFragment();
                toolbarTitle = "Tài khoản";
            } else if (id == R.id.nav_stats) {
                selected = new StatsFragment();
                toolbarTitle = "Thống kê";
            }
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(toolbarTitle);
            }
            if (selected != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selected)
                        .commit();
            }
            return true;
        });

        bottomNav.setSelectedItemId(R.id.nav_home);

        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            String toolbarTitle = "";
            int id = item.getItemId();

            if (id == R.id.nav_stats) {
                selectedFragment = new StatsFragment();
                toolbarTitle = "Thống kê";
            } else if (id == R.id.nav_income) {
                selectedFragment = new KhoanThuFragment();
                toolbarTitle = "Quản lý thu nhập";
            } else if (id == R.id.nav_expense) {
                selectedFragment = new KhoanChiFragment();
                toolbarTitle = "Quản lý chi tiêu";
            } else if (id == R.id.nav_account) {
                selectedFragment = new AccountFragment();
                toolbarTitle = "Thông tin người dùng";
            } else if (id == R.id.nav_logout) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                return true;
            }
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(toolbarTitle);
            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
