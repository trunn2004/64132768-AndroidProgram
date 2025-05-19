package quctrun.trunn2004.cuoiky;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.*;

public class AddTransactionActivity extends AppCompatActivity {

    private Spinner spinnerType;
    private EditText editAmount, editCategory, editNote;
    private TextView textDate;
    private Button btnPickDate, btnSave;

    private String selectedDate = "";

    private FirebaseFirestore db;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        spinnerType = findViewById(R.id.spinner_type);
        editAmount = findViewById(R.id.edit_amount);
        editCategory = findViewById(R.id.edit_category);
        editNote = findViewById(R.id.edit_note);
        textDate = findViewById(R.id.text_date);
        btnPickDate = findViewById(R.id.btn_pick_date);
        btnSave = findViewById(R.id.btn_save);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.transaction_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        btnPickDate.setOnClickListener(v -> showDatePicker());

        btnSave.setOnClickListener(v -> saveTransaction());
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                    textDate.setText("Ngày: " + selectedDate);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }

    private void saveTransaction() {
        String type = spinnerType.getSelectedItem().toString();
        String amountStr = editAmount.getText().toString().trim();
        String category = editCategory.getText().toString().trim();
        String note = editNote.getText().toString().trim();

        if (amountStr.isEmpty() || category.isEmpty() || selectedDate.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);
        String userId = auth.getCurrentUser().getUid();

        Map<String, Object> transaction = new HashMap<>();
        transaction.put("type", type);
        transaction.put("amount", amount);
        transaction.put("category", category);
        transaction.put("note", note);
        transaction.put("date", selectedDate);
        transaction.put("userId", userId);
        transaction.put("createdAt", new Date());

        db.collection("transactions")
                .add(transaction)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Đã lưu thành công", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
