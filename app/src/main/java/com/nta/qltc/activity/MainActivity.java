package com.nta.qltc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.nta.qltc.R;
import com.nta.qltc.fragment.ChangePass_Fragment;
import com.nta.qltc.fragment.GioiThieu_Fragment;
import com.nta.qltc.fragment.KhoanChi_Fragment;
import com.nta.qltc.fragment.KhoanThu_Fragment;
import com.nta.qltc.fragment.ThongKe_Fragment;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frame_layout);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupDrawerContent(navigationView);
        ThongKe_Fragment searchFragment = new ThongKe_Fragment();
        replaceFragment(searchFragment);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }

    private void selectedItemDrawer(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.khoanthu:
                setTitle("KHOẢN THU");
                KhoanThu_Fragment khoanThu_fragment = new KhoanThu_Fragment();
                replaceFragment(khoanThu_fragment);
                break;
            case R.id.khoanchi:
                setTitle("KHOẢN CHI");
                KhoanChi_Fragment khoanChi_fragment = new KhoanChi_Fragment();
                replaceFragment(khoanChi_fragment);
                break;
            case R.id.thongke:
                setTitle("THỐNG KÊ");
                ThongKe_Fragment searchFragment = new ThongKe_Fragment();
                replaceFragment(searchFragment);
                break;
            case R.id.gioithieu:
                setTitle("GIỚI THIỆU");
                GioiThieu_Fragment settingsFragment = new GioiThieu_Fragment();
                replaceFragment(settingsFragment);
                break;

            case R.id.doimatkhau:
                setTitle("ĐỔI MẬT KHẨU");
                ChangePass_Fragment changePassFragment = new ChangePass_Fragment();
                replaceFragment(changePassFragment);
                break;
            case R.id.logout:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;

        }

        item.setChecked(true);

        drawerLayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectedItemDrawer(item);
                return true;
            }
        });
    }

}
