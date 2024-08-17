package com.example.favoritebuttoninroom.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.favoritebuttoninroom.R;
import com.example.favoritebuttoninroom.databinding.RecyclerItemBinding;
import com.example.favoritebuttoninroom.favoriteRoom.FavoriteDao;
import com.example.favoritebuttoninroom.favoriteRoom.FavoriteModel;
import com.example.favoritebuttoninroom.model.MainModel;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {


    private Context context;
    private List<FavoriteModel> list;
    private FavoriteDao favoriteDao;
    private DeleteItemClickListener deleteItemClickListener;

    public FavoriteAdapter(Context context, List<FavoriteModel> list, FavoriteDao favoriteDao, DeleteItemClickListener deleteItemClickListener) {
        this.context = context;
        this.list = list;
        this.favoriteDao = favoriteDao;
        this.deleteItemClickListener = deleteItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerItemBinding binding = RecyclerItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavoriteModel favoriteModel = list.get(position);

        holder.binding.textName.setText(favoriteModel.name);
        holder.binding.textEmail.setText(favoriteModel.email);
        holder.binding.textGender.setText(favoriteModel.gender);
        holder.binding.textCity.setText(favoriteModel.city);
        holder.binding.textAge.setText(favoriteModel.age + " years");
        holder.binding.imgProfession.setImageResource(favoriteModel.work);

        Glide.with(context).load(favoriteModel.image).centerCrop().placeholder(R.drawable.placeholder).into(holder.binding.image);


        boolean isFavorite = favoriteDao.isFavorite(favoriteModel.id);
        if(isFavorite){
            holder.binding.imgIcon.setImageResource(R.drawable.ic_fill_like);
        }
        else{
            holder.binding.imgIcon.setImageResource(R.drawable.ic_like);
        }

        holder.binding.imgIcon.setOnClickListener(v -> deleteItemClickListener.onItemDelete(position, list.get(position).id));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        RecyclerItemBinding binding;

        public ViewHolder(@NonNull RecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
