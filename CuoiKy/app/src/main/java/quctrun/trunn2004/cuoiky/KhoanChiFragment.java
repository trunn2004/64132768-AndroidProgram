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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class KhoanChiFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<KhoanChi> list;
    private KhoanChiAdapter adapter;
    private FirebaseFirestore db;
    private String currentUserId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoan_chi, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewKhoanChi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        adapter = new KhoanChiAdapter(list, new KhoanChiAdapter.OnItemClickListener() {
            @Override
            public void onEdit(KhoanChi khoanChi) {
                showEditDialog(khoanChi);
            }

            @Override
            public void onDelete(KhoanChi khoanChi) {
                db.collection("transactions").document(khoanChi.getId()).delete()
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
                .whereEqualTo("type", "Khoản Chi")
                .whereEqualTo("userId", currentUserId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    list.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        KhoanChi chi = new KhoanChi(
                                doc.getId(),
                                doc.getLong("amount"),
                                doc.getString("note"),
                                doc.getString("date"),
                                doc.getString("category")
                        );
                        list.add(chi);
                    }
                    adapter.notifyDataSetChanged();
                });
    }

    private void showEditDialog(KhoanChi khoanChi) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Sửa khoản chi");

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit_khoan_chi, null);
        EditText edtAmount = view.findViewById(R.id.edtSoTien);
        EditText edtNote = view.findViewById(R.id.edtGhiChu);
        EditText edtDate = view.findViewById(R.id.edtThoiGian);
        EditText edtCategory = view.findViewById(R.id.edtCategory);

        edtAmount.setText(String.valueOf(khoanChi.getSoTien()));
        edtNote.setText(khoanChi.getGhiChu());
        edtDate.setText(khoanChi.getThoiGian());
        edtCategory.setText(khoanChi.getCategory());

        builder.setView(view);
        builder.setPositiveButton("Lưu", (dialog, which) -> {
            long amount = Long.parseLong(edtAmount.getText().toString());
            String note = edtNote.getText().toString();
            String date = edtDate.getText().toString();
            String category = edtCategory.getText().toString();

            // Cập nhật
            db.collection("transactions").document(khoanChi.getId())
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


