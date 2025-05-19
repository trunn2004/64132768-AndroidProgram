package quctrun.trunn2004.cuoiky;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import quctrun.trunn2004.cuoiky.R;

public class StatsFragment extends Fragment {

    private Button btnStartDate, btnEndDate, btnSearch;
    private TextView txtTotalIncome, txtTotalExpense, txtRemaining;
    private BarChart barChart;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    private Calendar calendarStart = Calendar.getInstance();
    private Calendar calendarEnd = Calendar.getInstance();

    public StatsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        btnStartDate = view.findViewById(R.id.btnStartDate);
        btnEndDate = view.findViewById(R.id.btnEndDate);
        btnSearch = view.findViewById(R.id.btnSearch);
        txtTotalIncome = view.findViewById(R.id.txtTotalIncome);
        txtTotalExpense = view.findViewById(R.id.txtTotalExpense);
        txtRemaining = view.findViewById(R.id.txtRemaining);
        barChart = view.findViewById(R.id.barChart);

        btnStartDate.setOnClickListener(v -> showDatePicker(true));

        btnEndDate.setOnClickListener(v -> showDatePicker(false));


        btnSearch.setOnClickListener(v -> {
            if (auth.getCurrentUser() == null) {
                Toast.makeText(getContext(), "Chưa đăng nhập", Toast.LENGTH_SHORT).show();
                return;
            }

            String userId = auth.getCurrentUser().getUid();
            long start = calendarStart.getTimeInMillis();
            long end = calendarEnd.getTimeInMillis() + 86399999;

            db.collection("transactions")
                    .whereEqualTo("userId", userId)
                    .whereGreaterThanOrEqualTo("date", new Date(start))
                    .whereLessThanOrEqualTo("date", new Date(end))
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        int totalIncome = 0;
                        int totalExpense = 0;

                        for (var doc : queryDocumentSnapshots) {
                            long amount = doc.getLong("amount") != null ? doc.getLong("amount") : 0;
                            String type = doc.getString("type");

                            if ("Khoản Thu".equalsIgnoreCase(type)) {
                                totalIncome += amount;
                            } else if ("Khoản Chi".equalsIgnoreCase(type)) {
                                totalExpense += amount;
                            }
                        }

                        int remaining = totalIncome - totalExpense;

                        txtTotalIncome.setText(totalIncome + " VND");
                        txtTotalExpense.setText(totalExpense + " VND");
                        txtRemaining.setText(remaining + " VND");

                        showBarChart(totalIncome, totalExpense);
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Lỗi truy vấn: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });


        return view;
    }
    private void showDatePicker(boolean isStartDate) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, year, month, dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    if (isStartDate) {
                        calendarStart = calendar;
                        btnStartDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    } else {
                        calendarEnd = calendar;
                        btnEndDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }


    private void showBarChart(int income, int expense) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1, income));
        entries.add(new BarEntry(2, expense));

        BarDataSet dataSet = new BarDataSet(entries, "Thống kê");
        dataSet.setColors(Color.parseColor("#4CAF50"), Color.parseColor("#FF9800"));
        dataSet.setValueTextSize(14f);

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (Math.round(value) == 1) return "Thu nhập";
                else if (Math.round(value) == 2) return "Chi tiêu";
                else return "";
            }
        });


        barChart.getAxisRight().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.animateY(1000);
        barChart.invalidate();
    }
}
