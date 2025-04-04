package quctrun.trunn2004.recyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    landScapeAddapter landScapeAdapter;
    ArrayList<LandScape> recyclerViewData;
    RecyclerView recyclerViewLandScape;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //3
        recyclerViewData = getDataForRecyclerView();
        //4
        recyclerViewLandScape = findViewById(R.id.rycyclerLand);
        //5
        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(this);
        recyclerViewLandScape.setLayoutManager(layoutLinear);
        //RecyclerView.LayoutManager layoutManagerHorizonal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //recyclerViewLandScape.setLayoutManager(layoutManagerHorizonal);
        //6
        landScapeAdapter = new landScapeAddapter(this, recyclerViewData);
        //7
        recyclerViewLandScape.setAdapter(landScapeAdapter);

    }
    ArrayList<LandScape> getDataForRecyclerView() {
        ArrayList<LandScape> dsDulieu = new ArrayList<LandScape>();
        LandScape landScape1 = new LandScape("shin", "Shin Ngại Ngùng");
        dsDulieu.add(landScape1);
        dsDulieu.add(new LandScape("shinvovong","Shin Tuyệt Vọng"));
        dsDulieu.add(new LandScape("shinngongtrong","Shin Tỏa Sáng"));
        dsDulieu.add(new LandScape("shintusuong","Shin Tự Sướng Cùng Hội Bạn"));
        return dsDulieu;
    }
}