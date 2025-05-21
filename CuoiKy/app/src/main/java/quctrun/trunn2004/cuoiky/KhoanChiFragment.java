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

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class KhoanChiFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<KhoanChi> list;
    private KhoanChiAdapter adapter;
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoan_chi, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewKhoanChi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        adapter = new KhoanChiAdapter(list);
        recyclerView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();

        db.collection("transactions")
                .whereEqualTo("type", "Khoản Chi")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    list.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        KhoanChi chi = new KhoanChi(
                                doc.getLong("amount"),
                                doc.getString("note"),
                                doc.getString("date"),
                                doc.getString("category")
                        );
                        list.add(chi);
                    }
                    adapter.notifyDataSetChanged();
                });

        return view;
    }
}


