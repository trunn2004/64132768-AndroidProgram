package quctrun.trunn2004.ex5_addsubmuldiv_var;

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
        //gan bo lang nghe
        btnCong.setOnClickListener(boLangNghe_XuLyCong);
        btnCong.setOnClickListener(boLangNghe_XuLyTru);
        btnCong.setOnClickListener(boLangNghe_XuLyNhan);
        //btnCong.setOnClickListener(boLangNghe_XuLyChia);
        //bo lang nghe an danh
        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cdoe xu ly cong
                //1.lay so 1
                String strSo1= edtSoA.getText().toString();
                String strSo2= edtSoB.getText().toString();
                //2. chuyen thanh so de tinh tong
                double soA = Double.parseDouble(strSo1);
                double soB = Double.parseDouble(strSo2);
                //tinh toan
                if (soB != 0){
                    double tong = soA / soB;
                    String chuoiKQ = String.valueOf(tong);
                    tvKetQua.setText(chuoiKQ);}
                else System.out.println("Không thể tính toán");
            }
        });
    }

    View.OnClickListener boLangNghe_XuLyCong = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //cdoe xu ly cong
            //1.lay so 1
            String strSo1= edtSoA.getText().toString();
            String strSo2= edtSoB.getText().toString();
            //2. chuyen thanh so de tinh tong
            double soA = Double.parseDouble(strSo1);
            double soB = Double.parseDouble(strSo2);
            //tinh toan
            double tong = soA+soB;
            //xuat
            String strKQ = String.valueOf(tong);
            tvKetQua.setText(strKQ);
        }
    };
    View.OnClickListener boLangNghe_XuLyTru = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //cdoe xu ly tru
            //1.lay so 1
            String strSo1= edtSoA.getText().toString();
            String strSo2= edtSoB.getText().toString();
            //2. chuyen thanh so de tinh tong
            double soA = Double.parseDouble(strSo1);
            double soB = Double.parseDouble(strSo2);
            //tinh toan
            double tong = soA-soB;
            //xuat
            String strKQ = String.valueOf(tong);
            tvKetQua.setText(strKQ);
        }
    };
    View.OnClickListener boLangNghe_XuLyNhan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //cdoe xu ly Nhan
            //1.lay so 1
            String strSo1= edtSoA.getText().toString();
            String strSo2= edtSoB.getText().toString();
            //2. chuyen thanh so de tinh tong
            double soA = Double.parseDouble(strSo1);
            double soB = Double.parseDouble(strSo2);
            //tinh toan
            double tong = soA*soB;
            //xuat
            String strKQ = String.valueOf(tong);
            tvKetQua.setText(strKQ);
        }
    };

    void TimView(){
        edtSoA = (EditText) findViewById(R.id.edtsoA);
        edtSoB = (EditText) findViewById(R.id.edtSoB);
        btnCong =(Button) findViewById(R.id.btnCong);
        btnTru =(Button) findViewById(R.id.btnTru);
        btnNhan =(Button) findViewById(R.id.btnNhan);
        btnChia =(Button) findViewById(R.id.btnChia);
        tvKetQua = (TextView) findViewById(R.id.tvKetQua);
    }


}