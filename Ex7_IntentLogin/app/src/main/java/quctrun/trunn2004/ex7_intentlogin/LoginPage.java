package quctrun.trunn2004.ex7_intentlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginPage extends AppCompatActivity {
    EditText edtTenDN, edtMatKhau;
    Button btnXacNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnXacNhan = (Button) findViewById(R.id.btnOK);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTenDN = (EditText) findViewById(R.id.edtUsername);
                edtMatKhau = (EditText) findViewById(R.id.edtPass);
                String tenDangNhap = edtTenDN.getText().toString();
                String matKhau = edtMatKhau.getText().toString();

                if (tenDangNhap.equals("nguyenquuoctrung") && matKhau.equals("123")){
                    Intent iQuiz = new Intent(LoginPage.this, HomeActivity.class);
                    iQuiz.putExtra("ten_dang_nhap",tenDangNhap);
                    iQuiz.putExtra("mat_khau",matKhau);
                    startActivity(iQuiz);
                }
                else {
                    Toast.makeText(LoginPage.this, "BẠN ĐĂNG NHẬP SAI THÔNG TIN RỒI", Toast.LENGTH_LONG);
                }
            }
        });
    }
}