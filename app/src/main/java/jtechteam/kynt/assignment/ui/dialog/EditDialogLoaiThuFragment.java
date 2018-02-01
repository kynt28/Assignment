package jtechteam.kynt.assignment.ui.dialog;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import jtechteam.kynt.assignment.R;
import jtechteam.kynt.assignment.bean.KhoanThu;
import jtechteam.kynt.assignment.ui.adapter.TabLoaiThuAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDialogLoaiThuFragment extends DialogFragment implements View.OnClickListener{
    private static final String TAG = "EditLoaiThuFragment";
    private ImageView btnHuy, btnLuu, btnLich;
    private TextInputEditText txtTenKhoanThu, txtSoTien, txtGhiChu;
    private TextView txtNgayThangNam;
    private ArrayList<KhoanThu> datas;
    private int positions;
    private TabLoaiThuAdapter adapter;

    public EditDialogLoaiThuFragment() {}


    public static EditDialogLoaiThuFragment newInstance(ArrayList<KhoanThu> datas, int position) {
        EditDialogLoaiThuFragment fragment = new EditDialogLoaiThuFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putParcelableArrayList("data", datas);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_edit_loai_thu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
        setupData();
        setupWidget();
        attachEvent();
    }

    private void setupView(View view) {
        btnLich = view.findViewById(R.id.btnLichLoaiThu);
        btnLuu = view.findViewById(R.id.btnLuu);
        btnHuy = view.findViewById(R.id.btnHuy);
        txtSoTien = view.findViewById(R.id.txtSoTien);
        txtGhiChu = view.findViewById(R.id.txtGhiChu);
        txtTenKhoanThu = view.findViewById(R.id.txtTenKhoanThu);
        txtNgayThangNam = view.findViewById(R.id.txtNgayThangNam);
    }

    private void setupData() {
        datas = new ArrayList<>();
        datas =  getArguments().getParcelableArrayList("data");
        positions = getArguments().getInt("position");
    }

    @SuppressLint("SimpleDateFormat")
    private void setupWidget() {
        txtNgayThangNam.setText(datas.get(positions).getThoiGian());
        txtTenKhoanThu.setText(datas.get(positions).getTenKhoanThu());
        txtGhiChu.setText(datas.get(positions).getGhiChu());
        txtSoTien.setText(String.valueOf(datas.get(positions).getSoTien()));
        adapter = new TabLoaiThuAdapter(getActivity(), datas);
    }

    private void attachEvent() {
        btnLich.setOnClickListener(this);
        btnLuu.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
    }

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    private void validateDate(int i, int i1, int i2){
        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy");
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
                txtNgayThangNam.setText(i2+"/"+i1+"/"+i);
                Log.d(TAG, "today before: \nNgày hôm nay   : "+date1 +" /  \nNgày trong lịch: "+date2);
            }else if (date1.equals(date2)){
                txtNgayThangNam.setText(i2+"/"+i1+"/"+i);
                Log.d(TAG, "today equals: \nNgày hôm nay   : "+date1 +" /  \nNgày trong lịch: "+date2);
            }else {
                Toast.makeText(getActivity(), "Vào else", Toast.LENGTH_SHORT).show();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void onResume() {
        Window window = getDialog().getWindow();
        Point size = new Point();
        assert window != null;
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        window.setLayout((int) (size.x * 0.98), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnHuy:
                getDialog().dismiss();
                Toast.makeText(getActivity(), "Dữ liệu chưa được lưu", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnLuu:
                int stt = datas.get(positions).getStt();
                String textGhiChu = txtGhiChu.getText().toString();
                String textTenKhoanThu = txtTenKhoanThu.getText().toString();
                String textThoiGian = txtNgayThangNam.getText().toString();
                String textSoTien = txtSoTien.getText().toString();
                final KhoanThu khoanThu = new KhoanThu(stt, textTenKhoanThu, textGhiChu, Integer.parseInt(textSoTien), textThoiGian);
                datas.remove(positions);
                datas.add(positions, khoanThu);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyItemRemoved(positions);
                        adapter.notifyItemRangeChanged(positions, datas.size());
                        adapter.notifyItemChanged(positions, khoanThu);
                        adapter.notifyDataSetChanged();
                        adapter.closeAllItems();
                    }
                });

                Toast.makeText(getActivity(), "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                dismiss();
                break;

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
        }
    }
}
