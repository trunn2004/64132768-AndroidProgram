package quctrun.trunn2004.cuoiky;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class StatsFragment extends Fragment {

    private BarChart barChart;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        barChart = view.findViewById(R.id.barChart);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        loadTransactionStats();

        return view;
    }

    private void loadTransactionStats() {
        String uid = auth.getCurrentUser().getUid();

        db.collection("transactions")
                .whereEqualTo("userId", uid)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    float totalIncome = 0f;
                    float totalExpense = 0f;

                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        String type = doc.getString("type");
                        Double amount = doc.getDouble("amount");

                        if (type != null && amount != null) {
                            if (type.equals("Thu")) {
                                totalIncome += amount;
                            } else if (type.equals("Chi")) {
                                totalExpense += amount;
                            }
                        }
                    }

                    showBarChart(totalIncome, totalExpense);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Lỗi tải dữ liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void showBarChart(float income, float expense) {
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, income));   // Vị trí cột 0 -> Thu
        entries.add(new BarEntry(1, expense));  // Vị trí cột 1 -> Chi

        BarDataSet dataSet = new BarDataSet(entries, "Thu / Chi");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(14f);

        BarData data = new BarData(dataSet);
        data.setBarWidth(0.5f);

        barChart.setData(data);

        // Trục X
        String[] labels = {"Thu", "Chi"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labels.length);
        xAxis.setTextSize(12f);

        // Trục Y
        barChart.getAxisLeft().setTextSize(12f);
        barChart.getAxisRight().setEnabled(false);

        barChart.getDescription().setEnabled(false);
        barChart.animateY(1000);
        barChart.invalidate(); // refresh
    }
}


