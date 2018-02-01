package jtechteam.kynt.assignment.ui.fragment;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import jtechteam.kynt.assignment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabKhoanThuFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "TabKhoanThuFragment";
    private ImageView btnXoa, btnThem, btnLich;
    private TextInputEditText txtTenKhoanThu, txtSoTien, txtGhiChu;
    private TextView txtNgayGiaoDichTabKT, txtHTST, txtHTTKT, txtHTNGD, txtHTGC;
    private SimpleDateFormat timeFormat;
    private String homnay;


    public TabKhoanThuFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_khoan_thu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
        setupData();
        setupWidget();
        attachEvent();
        displayTransactionInformation();
    }

    private void setupView(View view) {
        btnLich = view.findViewById(R.id.btnLichLoaiThu);
        btnXoa = view.findViewById(R.id.btnXoa);
        btnThem = view.findViewById(R.id.btnThem);
        txtSoTien = view.findViewById(R.id.txtSoTien);
        txtTenKhoanThu = view.findViewById(R.id.txtTenKhoanThu);
        txtGhiChu = view.findViewById(R.id.txtGhiChu);
        txtNgayGiaoDichTabKT = view.findViewById(R.id.txtNgayGiaoDichTabKT);
        txtHTST = view.findViewById(R.id.txtHTST);
        txtHTTKT = view.findViewById(R.id.txtHTTKT);
        txtHTNGD = view.findViewById(R.id.txtHTNGD);
        txtHTGC = view.findViewById(R.id.txtHTGC);
    }

    private void setupData() {

    }

    @SuppressLint("SimpleDateFormat")
    private void setupWidget() {
        Date today = new Date(System.currentTimeMillis());
        timeFormat = new SimpleDateFormat("dd/MM/yyyy");
        homnay = timeFormat.format(today.getTime());
        txtNgayGiaoDichTabKT.setText(homnay);
        txtHTNGD.setText(homnay);
    }

    private void attachEvent() {
        btnLich.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
        btnThem.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLichLoaiThu:
                Calendar calendar = Calendar.getInstance();
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                int m = calendar.get(Calendar.MONTH);
                int y = calendar.get(Calendar.YEAR);

                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        validateDate(i, (i1+1), i2);
                    }
                }, y, m, d);

                datePicker.show();
                break;
            case R.id.btnThem:
                if (!txtSoTien.getText().toString().trim().isEmpty() && !txtTenKhoanThu.getText().toString().trim().isEmpty()){
                    //thêm số tiền, khoản thu, và giờ vào arraylist

                    Toast.makeText(getActivity(), "Giao dịch thành công", Toast.LENGTH_SHORT).show();
                    xoaTrang();
                }else {
                    Toast.makeText(getActivity(), "Không được để trống các trường", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnXoa:
                xoaTrang();
                break;
        }
    }

    private void xoaTrang(){
        txtSoTien.setText("");
        txtTenKhoanThu.setText("");
        txtNgayGiaoDichTabKT.setText(homnay);
        txtHTNGD.setText("");
        txtHTST.setText("");
        txtHTTKT.setText("");
        txtGhiChu.setText("");
        txtHTGC.setText("");
    }

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    private void validateDate(int i, int i1, int i2){
        Date today = new Date(System.currentTimeMillis());
        timeFormat = new SimpleDateFormat("dd/MM/yyyy");
        String homnay = timeFormat.format(today.getTime());
        Log.d(TAG, "today: Ngày hôm nay là: " + homnay);

        String ngayTrongLich = i2+"/"+i1+"/"+i;
        Log.d(TAG, "today: Ngày trong lịch là: " + ngayTrongLich);

        try {
            Date date1 = timeFormat.parse(ngayTrongLich);
            Date date2 = timeFormat.parse(homnay);
            Log.d(TAG, "today giá trị của các i:\ni là năm: " + i +"\ni1 là tháng: " + i1+"\ni2 là ngày: "+i2);
            Log.d(TAG, "today: ngày date1 là: " +date1);
            Log.d(TAG, "today: ngày date2 là: " +date2);

            if (date1.after(date2)){
                Toast.makeText(getActivity(), "Ngày giao dịch không được lớn hơn ngày hôm nay", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "today after: \nNgày hôm nay   : "+date1 +" /  \nNgày trong lịch: "+date2);
            }else if (date1.before(date2)){
                txtNgayGiaoDichTabKT.setText(i2+"/"+i1+"/"+i);
                txtHTNGD.setText(i2+"/"+i1+"/"+i);
                Log.d(TAG, "today before: \nNgày hôm nay   : "+date1 +" /  \nNgày trong lịch: "+date2);
            }else if (date1.equals(date2)){
                txtNgayGiaoDichTabKT.setText(i2+"/"+i1+"/"+i);
                txtHTNGD.setText(i2+"/"+i1+"/"+i);
                Log.d(TAG, "today equals: \nNgày hôm nay   : "+date1 +" /  \nNgày trong lịch: "+date2);
            }else {
                Toast.makeText(getActivity(), "Vào else", Toast.LENGTH_SHORT).show();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String price;
    private void displayTransactionInformation() {
        txtSoTien.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                price = txtSoTien.getText().toString().trim();
                Locale swedish = new Locale("vi", "VN");
                NumberFormat swedishFormat = NumberFormat.getCurrencyInstance(swedish);
                if (!price.equals("")) {
                    txtHTST.setText(swedishFormat.format(Long.parseLong(price)));
                }
            }

            public void afterTextChanged(Editable editable) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs

            }
        });

        txtTenKhoanThu.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtHTTKT.setText(txtTenKhoanThu.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
        });

        txtGhiChu.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtHTGC.setText(txtGhiChu.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
        });
    }

}
