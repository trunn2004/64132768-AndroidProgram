package quctrun.trunn2004.ex4_addsubmuldiv_onclick;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editTextSo1;
    EditText editTextSo2;
    EditText editTextKQ;
    Button nutCong,nutTru,nutNhan,nutChia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        TimDieuKhien();
    }

    void TimDieuKhien(){
        editTextSo1 =(EditText)findViewById(R.id.edtSo1);
        editTextSo2 =(EditText)findViewById(R.id.edtSo2);
        editTextKQ = (EditText) findViewById(R.id.edtKQ);
        nutCong = (Button) findViewById(R.id.btnCong);
        nutTru = (Button) findViewById(R.id.btnTru);
        nutCong = (Button) findViewById(R.id.btnNhan);
        nutCong = (Button) findViewById(R.id.btnChia);
    }
    public void XuLyCong(View v){
        EditText editTextSo1 =(EditText)findViewById(R.id.edtSo1);
        EditText editTextSo2 =(EditText)findViewById(R.id.edtSo2);
        String so1 = editTextSo1.getText().toString();
        String so2 = editTextSo1.getText().toString();
        float so01 = Float.parseFloat(so1);
        float so02 = Float.parseFloat(so1);
        float tong = so01 + so02;
        String chuoiKQ = String.valueOf(tong);
        editTextKQ.setText(chuoiKQ);
    }
    public void XuLyTru(View v){
        String so1 = editTextSo1.getText().toString();
        String so2 = editTextSo1.getText().toString();
        float so01 = Float.parseFloat(so1);
        float so02 = Float.parseFloat(so1);
        float tong = so01 - so02;
        String chuoiKQ = String.valueOf(tong);
        editTextKQ.setText(chuoiKQ);
    }
    public void XuLyNhan(View v){
        String so1 = editTextSo1.getText().toString();
        String so2 = editTextSo1.getText().toString();
        float so01 = Float.parseFloat(so1);
        float so02 = Float.parseFloat(so1);
        float tong = so01 * so02;
        String chuoiKQ = String.valueOf(tong);
        editTextKQ.setText(chuoiKQ);
    }
    public void XuLyChia(View v){
        String so1 = editTextSo1.getText().toString();
        String so2 = editTextSo1.getText().toString();
        float so01 = Float.parseFloat(so1);
        float so02 = Float.parseFloat(so1);
        if (so02 != 0){
        float tong = so01 / so02;
        String chuoiKQ = String.valueOf(tong);
        editTextKQ.setText(chuoiKQ);}
        else System.out.println("Không thể tính toán");
    }

}