package quctrun.trunn2004.ex5_addsubmuldiv_var;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //khai bao cac doi tuong ung voi cac dieu khien(view) can thao tac
    EditText edtSoA,edtSoB;
    Button btnCong,btnTru,btnNhan,btnChia;
    TextView tvKetQua;

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
        TimView();
    }
    void TimView(){
        edtSoA = (EditText) findViewById(R.id.editTextText);
        edtSoB = (EditText) findViewById(R.id.editTextText2);
        btnCong =(Button) findViewById(R.id.button);
        btnTru =(Button) findViewById(R.id.button2);
        btnNhan =(Button) findViewById(R.id.button3);
        btnChia =(Button) findViewById(R.id.button4);
    }


}