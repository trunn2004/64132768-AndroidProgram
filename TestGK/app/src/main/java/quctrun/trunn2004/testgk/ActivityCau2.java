package quctrun.trunn2004.testgk;

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

public class ActivityCau2 extends AppCompatActivity {
    ListView lv;
    ArrayList<String> ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau2);
        lv = findViewById(R.id.listView);
        ds = new ArrayList<String>();
        ds.add("Kỹ thuật lập trình");
        ds.add("Kỹ thuật đồ họa");
        ds.add("Thiết kế Wed");
        ds.add("Phát triển ứng dụng Wed");
        ds.add("Hệ điều hành");
        ds.add("Lập trình thiết bị di động");
        ArrayAdapter<String> adapterNNLT;
        adapterNNLT = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, ds);
        lv.setAdapter(adapterNNLT);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //code xu ly o day
                String giaTriDuocChon = ds.get(position);
                //Toast.makeText(ActivityCau2.this, giaTriDuocChon, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActivityCau2.this, ItemActivity.class);
                intent.putExtra("selectedItem", giaTriDuocChon);
                startActivity(intent);
            }
        });

    }
}