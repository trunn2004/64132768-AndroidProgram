package quctrun.trunn2004.cuoiky;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoan_thu, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewKhoanThu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        adapter = new KhoanThuAdapter(list);
        recyclerView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String currentUserId = auth.getCurrentUser().getUid();

        db.collection("transactions")
                .whereEqualTo("type", "Khoản Thu")
                .whereEqualTo("userId", currentUserId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    list.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        KhoanThu thu = new KhoanThu(
                                doc.getLong("amount"),
                                doc.getString("note"),
                                doc.getString("date"),
                                doc.getString("category")
                        );
                        list.add(thu);
                    }
                    adapter.notifyDataSetChanged();
                });

        return view;
    }
}


