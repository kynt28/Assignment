package jtechteam.kynt.assignment.ui.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import jtechteam.kynt.assignment.R;
import jtechteam.kynt.assignment.bean.KhoanThu;
import jtechteam.kynt.assignment.ui.activity.MainActivity;
import jtechteam.kynt.assignment.ui.dialog.EditDialogLoaiThuFragment;

public class TabLoaiThuAdapter extends RecyclerSwipeAdapter<TabLoaiThuAdapter.SimpleViewHolder> {
    private Context mContext;
    private ArrayList<KhoanThu> datas;

    private SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

    public TabLoaiThuAdapter(Context context, ArrayList<KhoanThu> datas) {
        this.mContext = context;
        this.datas = datas;
    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_items_loai_thu, parent, false);
        return new SimpleViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }




    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        // Xét dữ liệu cho các text trong item
        String price = String.valueOf(datas.get(position).getSoTien());
        Locale swedish = new Locale("vi", "VN");
        NumberFormat swedishFormat = NumberFormat.getCurrencyInstance(swedish);
        if (!price.equals("")) {
            viewHolder.txtSoTienItem.setText(swedishFormat.format(Long.parseLong(price)));
        }
        viewHolder.txtGhiChuItem.setText(datas.get(position).getGhiChu());
        viewHolder.txtNgayThangNamItem.setText(datas.get(position).getThoiGian());
        viewHolder.txtTenKhoanThuItem.setText(datas.get(position).getTenKhoanThu());
        viewHolder.txtSoThuTu.setText(String.valueOf(position + 1));


        mItemManger.bindView(viewHolder.itemView, position);
        attachEvent(viewHolder, position);
    }

    // Xử lý sự kiện khi trượt
    private void attachEvent(final SimpleViewHolder viewHolder, final int position) {
        // Lắng nghe sự kiện khi trượt item (thêm hiệu ứng cho các icon)
        viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.img1));
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.img2));
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.img3));
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.txtSoThuTu));
            }
        });

        //click 2 lần có thể vuốt được sang trái xem ngày tháng năm và stt
        viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                layout.setDragEdge(SwipeLayout.DragEdge.Left);
            }
        });


        // Click vào phím delete sẽ xóa item trong đó
        viewHolder.viewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                datas.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, datas.size());
                mItemManger.closeAllItems();
                Toast.makeText(view.getContext(), "Deleted " + viewHolder.txtTenKhoanThuItem.getText().toString() + "!", Toast.LENGTH_SHORT).show();
            }
        });


        // Click vào item Nổi bật thì sẽ xuất hiện biểu tượng ghim (Bug: ghim rồi nhưng vuốt lên mạnh sẽ bị mất ghim hoặc nhẩy ghim sang các cái khác)
        viewHolder.viewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNoiBat(viewHolder, position);
            }
        });


        // Click vào item Sửa thì sẽ xh cửa sổ diglogFragment để sửa
        viewHolder.viewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = ((MainActivity) mContext).getSupportFragmentManager();
                EditDialogLoaiThuFragment editNameDialogFragment = EditDialogLoaiThuFragment.newInstance(datas, position);
                editNameDialogFragment.show(fm, "editNameDialogFragment");
            }
        });
    }


    @SuppressLint("SetTextI18n")
    private void setNoiBat(SimpleViewHolder viewHolder, int position) {
        if (datas.get(position).isNoiBat()) {
            datas.get(position).setNoiBat(true);
            viewHolder.txtNoiBat.setText("Ghim");
            viewHolder.imgPin.setVisibility(View.GONE);
            mItemManger.closeAllItems();
            datas.get(position).setNoiBat(false);
        } else {
            datas.get(position).setNoiBat(false);
            viewHolder.txtNoiBat.setText("Gỡ");
            viewHolder.imgPin.setVisibility(View.VISIBLE);
            mItemManger.closeAllItems();
            datas.get(position).setNoiBat(true);

        }
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }


    class SimpleViewHolder extends RecyclerView.ViewHolder {
        private SwipeLayout swipeLayout;
        private TextView txtGhiChuItem, txtTenKhoanThuItem, txtNgayThangNamItem, txtSoThuTu, txtNoiBat, txtSoTienItem;
        private LinearLayout viewDelete, viewLike, viewEdit;
        private ImageView imgPin;

        private SimpleViewHolder(View itemView) {
            super(itemView);
            swipeLayout = itemView.findViewById(R.id.swipe);
            viewDelete = itemView.findViewById(R.id.viewDelete);
            viewLike = itemView.findViewById(R.id.viewLike);
            viewEdit = itemView.findViewById(R.id.viewEdit);
            txtGhiChuItem = itemView.findViewById(R.id.txtGhiChuItem);
            txtTenKhoanThuItem = itemView.findViewById(R.id.txtTenKhoanThuItem);
            txtNgayThangNamItem = itemView.findViewById(R.id.txtNgayThangNamItem);
            txtSoThuTu = itemView.findViewById(R.id.txtSoThuTu);
            txtNoiBat = itemView.findViewById(R.id.txtNoiBat);
            txtSoTienItem = itemView.findViewById(R.id.txtSoTienItem);
            imgPin = itemView.findViewById(R.id.imgPin);
        }
    }

}
