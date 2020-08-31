package com.nta.qltc.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.nta.qltc.R;
import com.nta.qltc.adapter.KhoanChi_ViewPagerAdapter;


public class KhoanChi_Fragment extends Fragment {
    public TabLayout tabLayout;
    View view;
    private ViewPager viewPager;

    public KhoanChi_Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_khoan_chi, container, false);
        viewPager = view.findViewById(R.id.viewpager_khoanchi);
        tabLayout = view.findViewById(R.id.tablayout_khoanchi);
//Set tên tablayout
        tabLayout.addTab(tabLayout.newTab().setText("Khoản chi"));
        tabLayout.addTab(tabLayout.newTab().setText("Loại chi"));

        KhoanChi_ViewPagerAdapter adapter = new KhoanChi_ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//Set icon tablayout
        tabLayout.getTabAt(0).setIcon(R.drawable.khoanchi);
        tabLayout.getTabAt(1).setIcon(R.drawable.loaichi);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        return view;
    }
}
