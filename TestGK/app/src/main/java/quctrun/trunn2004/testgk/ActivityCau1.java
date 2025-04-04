package quctrun.trunn2004.testgk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityCau1 extends AppCompatActivity {

    EditText edtsoA,edtsob;
    Button btntong;
    EditText edtkq;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText edtA = findViewById(R.id.edtSoA);
        EditText edtB = findViewById(R.id.edtSoB);
        EditText edtKQ = findViewById(R.id.edtKQ);
        Button btntong = findViewById(R.id.btnTong);
        btntong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strA = edtA.getText().toString();
                String strB = edtB.getText().toString();

                int soA = Integer.parseInt(strA);
                int soB = Integer.parseInt(strB);
                int tong = soA + soB ;
                String strTong = String.valueOf(tong);
                edtKQ.setText(strTong);
            }
        });
    }

}