package com.nta.qltc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nta.qltc.R;
import com.nta.qltc.dao.DaoTaiKhoan;
import com.nta.qltc.model.TaikhoanMatKhau;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    EditText txtRegTk, txtRegMk, txtRegMkk;
    Button btDangKy, btNhapLai;
    TextView txtLogin;
    ArrayList<TaikhoanMatKhau> listTk = new ArrayList<>();
    DaoTaiKhoan tkDao;
    private RelativeLayout rlayout;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this, R.anim.dangnhap_dangky_animation);
        rlayout.setAnimation(animation);

        btDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tkDao = new DaoTaiKhoan(RegisterActivity.this);

                String tk = txtRegTk.getText().toString();
                String mk = txtRegMk.getText().toString();
                String mkk = txtRegMkk.getText().toString();
                boolean xetTk = true, xetMk = false;
                TaikhoanMatKhau tkmk = new TaikhoanMatKhau(tk, mk);

                listTk = tkDao.getALl();

                if (mk.matches(mkk)) {
                    xetMk = true;
                } else {
                    xetMk = false;
                }

                for (int i = 0; i < listTk.size(); i++) {
                    TaikhoanMatKhau tkx = listTk.get(i);
                    if (tkx.getTenTaiKhoan().matches(tk)) {
                        xetTk = false;
                        break;
                    }
                }

                if (tk.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Tên tài khoản không được để trống!", Toast.LENGTH_SHORT).show();
                } else {
                    if (mk.isEmpty() || mkk.isEmpty()) {
                        Toast.makeText(RegisterActivity.this, "Mật khẩu không được để trống!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (xetTk == true) {
                            if (xetMk == true) {
                                tkDao.Them(tkmk);
                                Toast.makeText(RegisterActivity.this, "Thêm tài khoản thành công!", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent();
                                i.putExtra("taikhoan", tk);
                                i.putExtra("matkhau", mk);
                                setResult(RESULT_OK, i);
                                finish();

                            } else {
                                Toast.makeText(RegisterActivity.this, "Mật khẩu không khớp nhau!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Tên tài khoản k được trùng!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        btNhapLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtRegTk.setText("");
                txtRegMk.setText("");
                txtRegMkk.setText("");
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {

        txtRegTk = findViewById(R.id.edtRegUser);
        txtRegMk = findViewById(R.id.edtRegPassword);
        txtRegMkk = findViewById(R.id.edtRePassword);
        btDangKy = findViewById(R.id.btnReg);
        btNhapLai = findViewById(R.id.btnRelay);
        txtLogin = findViewById(R.id.txtLogin);
    }
}
