package com.nta.qltc.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.clans.fab.FloatingActionButton;
import com.nta.qltc.R;
import com.nta.qltc.adapter.KhoanChiAdapter;
import com.nta.qltc.dao.DaoGiaoDich;
import com.nta.qltc.dao.DaoThuChi;
import com.nta.qltc.model.GiaoDich;
import com.nta.qltc.model.ThuChi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


public class Tab_KhoanChi_Fragment extends Fragment {

    View view;
    SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
    KhoanChiAdapter adapter;
    FloatingActionButton girdBtn, danhsachBtn, addBtn;
    private RecyclerView rcv;
    private ArrayList<GiaoDich> list = new ArrayList<>();
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            Collections.swap(list, fromPosition, toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };
    private DaoGiaoDich daoGiaoDich;
    private DaoThuChi daoThuChi;
//    private DatePickerDialog datePickerDialog;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private ArrayList<ThuChi> listTC = new ArrayList<>();

    public Tab_KhoanChi_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab_khoan_chi, container, false);
        rcv = view.findViewById(R.id.rcv_KhoanChi);
        addBtn = view.findViewById(R.id.addBtn);
        girdBtn = view.findViewById(R.id.girdBtn);
        danhsachBtn = view.findViewById(R.id.danhsachBtn);
        daoGiaoDich = new DaoGiaoDich(getActivity());

        list = daoGiaoDich.getGDtheoTC(1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(layoutManager);
        adapter = new KhoanChiAdapter(getActivity(), R.layout.item_list, list);
        rcv.setAdapter(adapter);


        girdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rcv.setLayoutManager(gridLayoutManager);
                adapter = new KhoanChiAdapter(getActivity(), R.layout.item_grid, list);
                rcv.setAdapter(adapter);
            }
        });
        danhsachBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                rcv.setLayoutManager(layoutManager);
                adapter = new KhoanChiAdapter(getActivity(), R.layout.item_list, list);
                rcv.setAdapter(adapter);
            }
        });

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(dividerItemDecoration);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rcv);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.them_khoan_thuchi);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (dialog != null && dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
                final TextView moTaGd = dialog.findViewById(R.id.them_mota_gd);
                final TextView ngayGd = dialog.findViewById(R.id.them_ngay_gd);
                final TextView tienGd = dialog.findViewById(R.id.them_tien_gd);
                final Spinner spLoaiGd = dialog.findViewById(R.id.spLoaiGd);
                final TextView title = dialog.findViewById(R.id.titleThemKhoan);
                final Button xoa = dialog.findViewById(R.id.xoaTextGD);
                final Button them = dialog.findViewById(R.id.btnThemGD);
                daoThuChi = new DaoThuChi(getActivity());
                listTC = daoThuChi.getThuChi(1);
                //Set tiêu đề
                title.setText("THÊM KHOẢN CHI");

                //Khi nhấn chọn ngày(lịch kiểu chọn):
//                ngayGd.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        final Calendar calendar = Calendar.getInstance();
//                        int d = calendar.get(Calendar.DAY_OF_MONTH);
//                        int m = calendar.get(Calendar.MONTH);
//                        int y = calendar.get(Calendar.YEAR);
//                        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                                final String NgayGD = dayOfMonth + "/" + (month + 1) + "/" + year;
//                                ngayGd.setText(NgayGD);
//                            }
//                        }, y, m, d);
//                        datePickerDialog.show();
//                    }
//                });

                //Khi nhấn chọn ngày (Lịch kiểu lướt):
                ngayGd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar calendar = Calendar.getInstance();
                        final int d = calendar.get(Calendar.DAY_OF_MONTH);
                        final int m = calendar.get(Calendar.MONTH);
                        final int y = calendar.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener, d, m, y);
                        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        datePickerDialog.show();
                    }
                });
                //Xử lý ngày chọn:
                mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        final String NgayGD = dayOfMonth + "/" + (month + 1) + "/" + year;
                        ngayGd.setText(NgayGD);
                    }
                };
                //Đổ dữ liệu vào spinner
                final ArrayAdapter sp = new ArrayAdapter(getActivity(), R.layout.spiner, listTC);
                spLoaiGd.setAdapter(sp);

                //Khi nhấn nút xóa
                xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                //Khi nhấn nút Thêm
                them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Thêm", Toast.LENGTH_SHORT).show();
                        String mota = moTaGd.getText().toString();
                        String ngay = ngayGd.getText().toString();
                        String tien = tienGd.getText().toString();
                        ThuChi tc = (ThuChi) spLoaiGd.getSelectedItem();
                        int ma = tc.getMaKhoan();
                        //Check lỗi

                        if (mota.isEmpty() && ngay.isEmpty() && tien.isEmpty()) {
                            Toast.makeText(getActivity(), "Các trường không được để trống!", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                GiaoDich gd = new GiaoDich(0, mota, dfm.parse(ngay), Integer.parseInt(tien), ma);

                                if (daoGiaoDich.themGD(gd) == true) {
                                    list.clear();
                                    list.addAll(daoGiaoDich.getGDtheoTC(1));
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(getActivity(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(getActivity(), "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });

                dialog.show();
            }
        });
        return view;
    }
}
