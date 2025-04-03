package quctrun.trunn2004.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class landScapeAddapter extends RecyclerView.Adapter<landScapeAddapter.ItemLandHolder> {
    Context context;
    ArrayList<LandScape> listData;

    public landScapeAddapter(Context context, ArrayList<LandScape> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public ItemLandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater cai_bon = LayoutInflater.from(context);
        View vItem = cai_bon.inflate(R.layout.item_land, parent, false);
        ItemLandHolder viewholderCreated = new ItemLandHolder(vItem);
        return viewholderCreated;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLandHolder holder, int position) {
        //Lay doi tuong hien thi
        LandScape landScapeHienThi = listData.get(position);
        //Trich thong tin
        String caption = landScapeHienThi.getLandCaption();
        String tenFileAnh = landScapeHienThi.getLandImageFileName();
        //Dat vao cac truong thong tin cua holder
        holder.tvCaption.setText(caption);
        //Dat anh
        String packageName = holder.itemView.getContext().getPackageName();
        int imageID = holder.itemView.getResources().getIdentifier(tenFileAnh,"mipmap",packageName);
        holder.ivLandscape.setImageResource(imageID);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ItemLandHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvCaption;
        ImageView ivLandscape;

        public ItemLandHolder(@NonNull View itemView) {
            super(itemView);
            tvCaption = itemView.findViewById(R.id.textViewCation);
            ivLandscape = itemView.findViewById(R.id.imageViewLand);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // code o day
            int vitriDuocClick = getAdapterPosition();
            LandScape phantuDuocchon = listData.get(vitriDuocClick);
            //boc thong tin
            String ten = phantuDuocchon.getLandCaption();
            String tenFile = phantuDuocchon.getLandImageFileName();
            //Toast ten
            String chuoithongbao = "Ban vua clikc" + ten;
            Toast.makeText(v.getContext(), chuoithongbao, Toast.LENGTH_SHORT).show();
        }
    }
}
