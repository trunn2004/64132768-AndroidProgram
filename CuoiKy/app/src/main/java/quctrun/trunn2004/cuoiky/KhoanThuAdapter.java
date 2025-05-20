package quctrun.trunn2004.cuoiky;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KhoanThuAdapter extends RecyclerView.Adapter<KhoanThuAdapter.ThuViewHolder> {
    private List<KhoanThu> list;

    public KhoanThuAdapter(List<KhoanThu> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoan_thu, parent, false);
        return new ThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThuViewHolder holder, int position) {
        KhoanThu item = list.get(position);
        holder.soTien.setText("Số tiền: " + item.getSoTien());
        holder.ghiChu.setText("Ghi chú: " + item.getGhiChu());
        holder.thoiGian.setText("Ngày: " + item.getThoiGian());
        holder.category.setText("Loại: " + item.getCategory());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ThuViewHolder extends RecyclerView.ViewHolder {
        TextView soTien, ghiChu, thoiGian, category;

        public ThuViewHolder(@NonNull View itemView) {
            super(itemView);
            soTien = itemView.findViewById(R.id.txtSoTien);
            ghiChu = itemView.findViewById(R.id.txtGhiChu);
            thoiGian = itemView.findViewById(R.id.txtThoiGian);
            category = itemView.findViewById(R.id.txtCategory);
        }
    }
}

