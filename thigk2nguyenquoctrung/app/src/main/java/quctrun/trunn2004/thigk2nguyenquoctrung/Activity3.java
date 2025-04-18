package quctrun.trunn2004.thigk2nguyenquoctrung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import quctrun.trunn2004.thigk2nguyenquoctrung.ItemActiviti3;
import quctrun.trunn2004.thigk2nguyenquoctrung.R;

public class Activity3 extends AppCompatActivity {
    ListView lv;
    ArrayList<String> ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_3);

        lv = findViewById(R.id.listView);

        ds = new ArrayList<>();
        ds.add("Kỹ thuật lập trình");
        ds.add("Kỹ thuật đồ họa");
        ds.add("Thiết kế Web");
        ds.add("Phát triển ứng dụng Web");
        ds.add("Hệ điều hành");
        ds.add("Lập trình thiết bị di động");

        ArrayAdapter<String> adapterNNLT = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ds);
        lv.setAdapter(adapterNNLT);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String giaTriDuocChon = ds.get(position);
                Toast.makeText(Activity3.this, giaTriDuocChon, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Activity3.this, ItemActiviti3.class);
                intent.putExtra("selectedItem", giaTriDuocChon);
                startActivity(intent);
            }
        });
    }
}
