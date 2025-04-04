package quctrun.trunn2004.thigk2nguyenquoctrung;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity2 extends AppCompatActivity {
    EditText edtsoA,edtsob;
    Button btntong;
    EditText edtkq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText edtA = findViewById(R.id.edtgk);
        EditText edtB = findViewById(R.id.edtck);
        EditText edtKQ = findViewById(R.id.edtkq);
        Button btntong = findViewById(R.id.btnTB);
        btntong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strA = edtA.getText().toString();
                String strB = edtB.getText().toString();
                float soA = Float.parseFloat(strA);
                float soB = Float.parseFloat(strB);
                float tong = (soA + soB)/2 ;
                String strTong = String.valueOf(tong);
                edtKQ.setText(strTong);
            }
        });
    }
}