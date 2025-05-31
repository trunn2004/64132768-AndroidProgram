package quctrun.trunn2004.cuoiky;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class KhoanThuFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<KhoanThu> list;
    private KhoanThuAdapter adapter;
    private FirebaseFirestore db;
    private String currentUserId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoan_thu, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewKhoanThu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        adapter = new KhoanThuAdapter(list, new KhoanThuAdapter.OnItemClickListener() {
            @Override
            public void onEdit(KhoanThu khoanThu) {
                showEditDialog(khoanThu);
            }

            @Override
            public void onDelete(KhoanThu khoanThu) {
                db.collection("transactions").document(khoanThu.getId()).delete()
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(getContext(), "Đã xoá", Toast.LENGTH_SHORT).show();
                            loadData();
                        });
            }
        });

        recyclerView.setAdapter(adapter);
        loadData();
        return view;
    }

    private void loadData() {
        db.collection("transactions")
                .whereEqualTo("type", "Khoản Thu")
                .whereEqualTo("userId", currentUserId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    list.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        KhoanThu thu = new KhoanThu(
                                doc.getId(),
                                doc.getLong("amount"),
                                doc.getString("note"),
                                doc.getString("date"),
                                doc.getString("category")
                        );
                        list.add(thu);
                    }
                    adapter.notifyDataSetChanged();
                });
    }

    private void showEditDialog(KhoanThu khoanThu) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Sửa khoản thu");

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit_khoan_thu, null);
        EditText edtAmount = view.findViewById(R.id.edtSoTien);
        EditText edtNote = view.findViewById(R.id.edtGhiChu);
        EditText edtDate = view.findViewById(R.id.edtThoiGian);
        EditText edtCategory = view.findViewById(R.id.edtCategory);

        edtAmount.setText(String.valueOf(khoanThu.getSoTien()));
        edtNote.setText(khoanThu.getGhiChu());
        edtDate.setText(khoanThu.getThoiGian());
        edtCategory.setText(khoanThu.getCategory());

        builder.setView(view);
        builder.setPositiveButton("Lưu", (dialog, which) -> {
            long amount = Long.parseLong(edtAmount.getText().toString());
            String note = edtNote.getText().toString();
            String date = edtDate.getText().toString();
            String category = edtCategory.getText().toString();

            db.collection("transactions").document(khoanThu.getId())
                    .update("amount", amount,
                            "note", note,
                            "date", date,
                            "category", category)
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                    });
        });

        builder.setNegativeButton("Huỷ", null);
        builder.show();
    }
}
