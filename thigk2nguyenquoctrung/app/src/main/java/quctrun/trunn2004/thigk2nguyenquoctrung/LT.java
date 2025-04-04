package quctrun.trunn2004.thigk2nguyenquoctrung;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LT extends AppCompatActivity {

    
    CardView cv1,cv2,cv3,cv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lt);

        TimDieuKhien();
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LT.this, "Bạn đã trả lời sai!!", Toast.LENGTH_SHORT).show();
            }
        });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LT.this, "Bạn đã trả lời sai!!", Toast.LENGTH_SHORT).show();
            }
        });
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LT.this, "Bạn đã trả lời đúng. Xin chúc mừng!!", Toast.LENGTH_SHORT).show();
            }
        });
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LT.this, "Bạn đã trả lời sai!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void TimDieuKhien(){
        cv1 = findViewById(R.id.card1);
        cv2 = findViewById(R.id.card2);
        cv3 = findViewById(R.id.card3);
        cv4 = findViewById(R.id.card4);
    }
}