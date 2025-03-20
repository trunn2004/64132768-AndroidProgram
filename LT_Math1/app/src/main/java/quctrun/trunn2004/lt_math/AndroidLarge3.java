package quctrun.trunn2004.lt_math;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AndroidLarge3 extends AppCompatActivity {
    CardView cv1,cv2,cv3,cv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_android_large3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TimDieuKhien();
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AndroidLarge3.this, "Bạn đã trả lời sai!!", Toast.LENGTH_SHORT).show();
            }
        });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AndroidLarge3.this, "Bạn đã trả lời sai!!", Toast.LENGTH_SHORT).show();
            }
        });
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AndroidLarge3.this, "Bạn đã trả lời đúng. Xin chúc mừng!!", Toast.LENGTH_SHORT).show();
            }
        });
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AndroidLarge3.this, "Bạn đã trả lời sai!!", Toast.LENGTH_SHORT).show();
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