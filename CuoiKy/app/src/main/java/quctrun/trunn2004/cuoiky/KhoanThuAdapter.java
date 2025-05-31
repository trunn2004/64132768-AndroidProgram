package quctrun.trunn2004.cuoiky;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KhoanThuAdapter extends RecyclerView.Adapter<KhoanThuAdapter.ThuViewHolder> {

    public interface OnItemClickListener {
        void onEdit(KhoanThu khoanThu);
        void onDelete(KhoanThu khoanThu);
    }

    private List<KhoanThu> list;
    private OnItemClickListener listener;

    public KhoanThuAdapter(List<KhoanThu> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
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

        holder.btnEdit.setOnClickListener(v -> listener.onEdit(item));
        holder.btnDelete.setOnClickListener(v -> listener.onDelete(item));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ThuViewHolder extends RecyclerView.ViewHolder {
        TextView soTien, ghiChu, thoiGian, category;
        ImageButton btnEdit, btnDelete;

        public ThuViewHolder(@NonNull View itemView) {
            super(itemView);
            soTien = itemView.findViewById(R.id.txtSoTien);
            ghiChu = itemView.findViewById(R.id.txtGhiChu);
            thoiGian = itemView.findViewById(R.id.txtThoiGian);
            category = itemView.findViewById(R.id.txtCategory);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
