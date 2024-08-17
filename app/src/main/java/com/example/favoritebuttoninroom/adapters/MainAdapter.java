package com.example.favoritebuttoninroom.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.favoritebuttoninroom.favoriteRoom.FavoriteModel;
import com.example.favoritebuttoninroom.model.MainModel;
import com.example.favoritebuttoninroom.R;
import com.example.favoritebuttoninroom.databinding.RecyclerItemBinding;
import com.example.favoritebuttoninroom.favoriteRoom.FavoriteDao;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private List<MainModel> list;
    private FavoriteDao favoriteDao;

    public MainAdapter(Context context, List<MainModel> list, FavoriteDao favoriteDao) {
        this.context = context;
        this.list = list;
        this.favoriteDao = favoriteDao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        RecyclerItemBinding binding = RecyclerItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainModel model = list.get(position);
        holder.binding.textName.setText(model.name);
        holder.binding.textEmail.setText(model.email);
        holder.binding.textGender.setText(model.gender);
        holder.binding.textAge.setText( model.age + " years");
        holder.binding.textCity.setText(model.city);
        holder.binding.imgProfession.setImageResource(model.work);
  
        Glide.with(context).load(model.image).centerCrop().placeholder(R.drawable.placeholder).into(holder.binding.image);

        boolean isFavorite = favoriteDao.isFavorite(model.id);
        if(isFavorite){
            holder.binding.imgIcon.setImageResource(R.drawable.ic_fill_like);
        }
        else{
            holder.binding.imgIcon.setImageResource(R.drawable.ic_like);
        }

        holder.itemView.setOnClickListener(v -> {
            boolean isCurrentlyFavorite = favoriteDao.isFavorite(model.id);

            if(isCurrentlyFavorite){
                favoriteDao.deleteData(model.id);
                holder.binding.imgIcon.setImageResource(R.drawable.ic_like);
            }
            else{
                favoriteDao.insertData(new FavoriteModel(model.id, model.name, model.email, model.gender, model.city, model.age, model.image, model.work));
                holder.binding.imgIcon.setImageResource(R.drawable.ic_fill_like);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerItemBinding binding;

        public ViewHolder(@NonNull RecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
