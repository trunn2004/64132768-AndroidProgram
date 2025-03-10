package quctrun.trunn2004.ex5_addsubmuldiv_anynomous;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        TimDieuKhien();
        //gan bo lang nghe su kien va code xu ly cho tung nut
        View.OnClickListener boLangNgheCong = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xu ly cong
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
        };
        nutCong.setOnClickListener(boLangNgheCong);
        nutTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String so1 = editTextSo1.getText().toString();
                String so2 = editTextSo1.getText().toString();
                float so01 = Float.parseFloat(so1);
                float so02 = Float.parseFloat(so1);
                float tong = so01 - so02;
                String chuoiKQ = String.valueOf(tong);
                editTextKQ.setText(chuoiKQ);

            }
        });
        nutNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String so1 = editTextSo1.getText().toString();
                String so2 = editTextSo1.getText().toString();
                float so01 = Float.parseFloat(so1);
                float so02 = Float.parseFloat(so1);
                float tong = so01 * so02;
                String chuoiKQ = String.valueOf(tong);
                editTextKQ.setText(chuoiKQ);
            }
        });
        nutChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
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