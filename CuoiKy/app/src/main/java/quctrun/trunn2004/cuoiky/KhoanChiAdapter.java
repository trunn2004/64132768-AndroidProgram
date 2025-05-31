package quctrun.trunn2004.cuoiky;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KhoanChiAdapter extends RecyclerView.Adapter<KhoanChiAdapter.ChiViewHolder> {

    public interface OnItemClickListener {
        void onEdit(KhoanChi khoanChi);
        void onDelete(KhoanChi khoanChi);
    }

    private List<KhoanChi> list;
    private OnItemClickListener listener;

    public KhoanChiAdapter(List<KhoanChi> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoan_chi, parent, false);
        return new ChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiViewHolder holder, int position) {
        KhoanChi item = list.get(position);
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

    static class ChiViewHolder extends RecyclerView.ViewHolder {
        TextView soTien, ghiChu, thoiGian, category;
        ImageButton btnEdit, btnDelete;

        public ChiViewHolder(@NonNull View itemView) {
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



