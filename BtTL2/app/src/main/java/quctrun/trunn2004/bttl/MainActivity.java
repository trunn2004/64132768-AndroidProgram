package quctrun.trunn2004.bttl;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tvHienThi,tvSoA,tvSoB;
    Button nut1,nut2,nut3,nut4,nut5,nut6,nut7,nut8,nut9;
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
    }
    public void TimDieuKhien(){
        nut1 = (Button) findViewById(R.id.btn1);
        nut2 = (Button) findViewById(R.id.btn2);
        nut3 = (Button) findViewById(R.id.btn3);
        nut4 = (Button) findViewById(R.id.btn4);
        nut5 = (Button) findViewById(R.id.btn5);
        nut6 = (Button) findViewById(R.id.btn6);
        nut7 = (Button) findViewById(R.id.btn7);
        nut8 = (Button) findViewById(R.id.btn8);
        nut9 = (Button) findViewById(R.id.btn9);
        tvHienThi = (TextView) findViewById(R.id.tvKetQua);
        tvSoA = (TextView) findViewById(R.id.tvA);
        tvSoB = (TextView) findViewById(R.id.tvB);
        Random ramdom = new Random();
        int a = ramdom.nextInt(3);
        int b = ramdom.nextInt(3);
        tvSoA.setText(String.valueOf(a));
        tvSoB.setText(String.valueOf(b));
        int tong = a + b;
        nut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHienThi.setText("1");
                int soNhap = Integer.parseInt(nut1.getText().toString());
                if(soNhap == tong) {
                    Toast.makeText(MainActivity.this,"Ban da tra loi dung",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Ban da tra loi sai",Toast.LENGTH_SHORT).show();

                }
            }
        });
        nut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHienThi.setText("2");
                int soNhap = Integer.parseInt(nut2.getText().toString());
                if(soNhap == tong) {
                    Toast.makeText(MainActivity.this,"Ban da tra loi dung",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Ban da tra loi sai",Toast.LENGTH_SHORT).show();

                }
            }
        });
        nut3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHienThi.setText("3");
                int soNhap = Integer.parseInt(nut3.getText().toString());
                if(soNhap == tong) {
                    Toast.makeText(MainActivity.this,"Ban da tra loi dung",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Ban da tra loi sai",Toast.LENGTH_SHORT).show();

                }
            }
        });
        nut4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHienThi.setText("4");
                int soNhap = Integer.parseInt(nut4.getText().toString());
                if(soNhap == tong) {
                    Toast.makeText(MainActivity.this,"Ban da tra loi dung",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Ban da tra loi sai",Toast.LENGTH_SHORT).show();

                }
            }
        });
        nut5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHienThi.setText("5");
                int soNhap = Integer.parseInt(nut5.getText().toString());
                if(soNhap == tong) {
                    Toast.makeText(MainActivity.this,"Ban da tra loi dung",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Ban da tra loi sai",Toast.LENGTH_SHORT).show();

                }
            }
        });
        nut6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHienThi.setText("6");
                int soNhap = Integer.parseInt(nut6.getText().toString());
                if(soNhap == tong) {
                    Toast.makeText(MainActivity.this,"Ban da tra loi dung",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Ban da tra loi sai",Toast.LENGTH_SHORT).show();

                }
            }
        });
        nut7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHienThi.setText("7");
                int soNhap = Integer.parseInt(nut7.getText().toString());
                if(soNhap == tong) {
                    Toast.makeText(MainActivity.this,"Ban da tra loi dung",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Ban da tra loi sai",Toast.LENGTH_SHORT).show();

                }
            }
        });
        nut8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHienThi.setText("8");
                int soNhap = Integer.parseInt(nut8.getText().toString());
                if(soNhap == tong) {
                    Toast.makeText(MainActivity.this,"Ban da tra loi dung",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Ban da tra loi sai",Toast.LENGTH_SHORT).show();

                }
            }
        });
        nut9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHienThi.setText("9");
                int soNhap = Integer.parseInt(nut9.getText().toString());
                if(soNhap == tong) {
                    Toast.makeText(MainActivity.this,"Ban da tra loi dung",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Ban da tra loi sai",Toast.LENGTH_LONG).show();

                }
            }
        });


    }

}