package com.example.favoritebuttoninroom;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.favoritebuttoninroom.adapters.DeleteItemClickListener;
import com.example.favoritebuttoninroom.adapters.FavoriteAdapter;
import com.example.favoritebuttoninroom.databinding.ActivityFavoriteBinding;
import com.example.favoritebuttoninroom.favoriteRoom.FavoriteDao;
import com.example.favoritebuttoninroom.favoriteRoom.FavoriteDatabase;
import com.example.favoritebuttoninroom.favoriteRoom.FavoriteModel;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    ActivityFavoriteBinding binding;
    private FavoriteDao favoriteDao;
    private FavoriteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setSupportActionBar(binding.materialToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Favorite");
        }
       // binding.materialToolbar.setNavigationIcon(R.drawable.ic_like);

        database = FavoriteDatabase.getDatabase(this);
        favoriteDao = database.getDao();

        setRecycler();
    }

    private void setRecycler() {
        List<FavoriteModel> list = favoriteDao.getAllData();

        binding.favRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.favRecycler.setAdapter(new FavoriteAdapter(this, list, favoriteDao, new DeleteItemClickListener() {
            @Override
            public void onItemDelete(int position, int id) {
                favoriteDao.deleteData(id);
                setRecycler();
            }
        }));

        if(list.isEmpty()){
            binding.textViewEmpty.setVisibility(View.VISIBLE);
        }
        else{
            binding.textViewEmpty.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRecycler();
    }
}