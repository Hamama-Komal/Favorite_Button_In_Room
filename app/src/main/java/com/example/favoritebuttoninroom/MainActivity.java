package com.example.favoritebuttoninroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.favoritebuttoninroom.adapters.MainAdapter;
import com.example.favoritebuttoninroom.databinding.ActivityMainBinding;
import com.example.favoritebuttoninroom.favoriteRoom.FavoriteDao;
import com.example.favoritebuttoninroom.favoriteRoom.FavoriteDatabase;
import com.example.favoritebuttoninroom.model.MainModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private FavoriteDao favoriteDao;
    private FavoriteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setSupportActionBar(binding.materialToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Room Favorite Feature");
        }

        database = FavoriteDatabase.getDatabase(this);
        favoriteDao = database.getDao();

        getData();

    }

    private void getData() {

        List<MainModel> list = new ArrayList<>();

        list.add(new MainModel(1, "Eren", "eren2@gmail.com", "Male", "Canada", 2, "https://i.pinimg.com/474x/2d/75/78/2d7578c9b67e48b1a670affd99fe4b23.jpg", R.drawable.developer));
        list.add(new MainModel(2, "Armin", "armin33@gmail.com","Male", "New York", 21, "https://i.pinimg.com/474x/f1/20/a1/f120a1d86c008029b7f6863ee95c2209.jpg", R.drawable.doctor));
        list.add(new MainModel(3, "Levi", "levi44@gmail.com", "Male", "England", 31, "https://i.pinimg.com/474x/21/97/64/219764b3dae8bbe99f09223775b5870d.jpg", R.drawable.teacher));
        list.add(new MainModel(4, "Hange", "hange55@gmail.com", "Female", "Greenland", 20, "https://i.pinimg.com/474x/4a/24/41/4a2441ec0ee0b439c0f16647e9291490.jpg", R.drawable.engineer));
        list.add(new MainModel(5, "Isage", "isage66@gmail.com", "Male", "Malta", 40, "https://i.pinimg.com/474x/f7/88/2f/f7882f6c8e27568cd66843ee8bede6c4.jpg", R.drawable.doctor));
        list.add(new MainModel(6, "Barlotte", "barlotte77@gmail.com", "Male", "Dubai", 15, "https://i.pinimg.com/474x/43/05/d0/4305d055a43cbf50525aa384a61c021d.jpg", R.drawable.teacher));
        list.add(new MainModel(7, "Erwin", "ewrin88@gmail.com", "Male", "Korea", 23, "https://i.pinimg.com/474x/84/7e/45/847e45870107a9071775ee9e794f1a9f.jpg", R.drawable.scientist));
        list.add(new MainModel(8, "Goodwin", "goodwin99@gmail.com", "Female", "India", 56, "https://i.pinimg.com/474x/69/7d/e6/697de6aae0304a29187a4c9e93569538.jpg", R.drawable.developer));
        list.add(new MainModel(9, "Tanjiro", "tanjiro010r@gmail.com", "Male", "Pakistan", 22, "https://i.pinimg.com/474x/5f/37/f2/5f37f2dae69592b3108286873a66ec9c.jpg", R.drawable.scientist));
        list.add(new MainModel(10, "Zentius", "zentius1111@gmail.com", "Male", "London", 21, "https://i.pinimg.com/474x/1b/d5/7e/1bd57e70fd4973d6287e3bab2cdc44b8.jpg", R.drawable.doctor));
        list.add(new MainModel(11, "Denji", "denji1212@gmail.com", "Male", "China", 18, "https://i.pinimg.com/474x/02/0c/8f/020c8f30b5e4524ec9af6d8da2e60707.jpg", R.drawable.teacher));
        list.add(new MainModel(12, "Mikasa", "mikasa@gmail.com", "Female", "Japan", 24, "https://i.pinimg.com/474x/8c/67/bd/8c67bd7e8950a851a95f1fb17b76ad15.jpg", R.drawable.engineer));

        binding.mainRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.mainRecycler.setAdapter(new MainAdapter(this, list, favoriteDao));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_fav) {
            startActivity(new Intent(MainActivity.this, FavoriteActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}