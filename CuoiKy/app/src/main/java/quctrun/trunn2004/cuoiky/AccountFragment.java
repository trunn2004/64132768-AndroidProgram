package quctrun.trunn2004.cuoiky;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.UUID;

public class AccountFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private StorageReference storageRef;

    private TextView txtUserInfo, txtFullName;
    private ImageView imgAvatar;
    private Uri imageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        txtUserInfo = view.findViewById(R.id.txtUserInfo);
        txtFullName = view.findViewById(R.id.txtFullName);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        Button btnLogout = view.findViewById(R.id.btnLogout);
        Button btnChangePassword = view.findViewById(R.id.btnChangePassword);

        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            txtUserInfo.setText("Email: " + email);

            db.collection("users").document(user.getUid())
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String fullName = documentSnapshot.getString("fullName");
                            txtFullName.setText("Họ và tên: " + fullName);

                            String avatarUrl = documentSnapshot.getString("avatarUrl");
                            if (avatarUrl != null && !avatarUrl.isEmpty()) {
                                Glide.with(requireContext())
                                        .load(avatarUrl)
                                        .into(imgAvatar);
                            }
                        }
                    });
        }

        imgAvatar.setOnClickListener(v -> chooseImage());

        btnLogout.setOnClickListener(v -> {
            auth.signOut();
            startActivity(new Intent(getActivity(), LoginActivity.class));
            requireActivity().finish();
        });

        btnChangePassword.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ResetPasswordActivity.class));
        });

        return view;
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh đại diện"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {

            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), imageUri);
                imgAvatar.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

            uploadImage();
        }
    }

    private void uploadImage() {
        if (imageUri != null && auth.getCurrentUser() != null) {
            String uid = auth.getCurrentUser().getUid();
            StorageReference ref = storageRef.child("avatars/" + uid + "_" + UUID.randomUUID().toString());

            ref.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> ref.getDownloadUrl().addOnSuccessListener(uri -> {
                        db.collection("users").document(uid)
                                .update("avatarUrl", uri.toString())
                                .addOnSuccessListener(unused -> {
                                    Toast.makeText(getContext(), "Cập nhật ảnh đại diện thành công", Toast.LENGTH_SHORT).show();
                                });
                    }))
                    .addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Lỗi tải ảnh: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    });
        }
    }
}
