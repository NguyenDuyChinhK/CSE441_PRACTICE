package com.example.usingrecyclerview;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Nation> nationList = new ArrayList<>();
        nationList.add(new Nation("India", "New Delhi", R.drawable.india_flag, 1393409038, 3287263, 424, 17.70));
        nationList.add(new Nation("China", "Beijing", R.drawable.china_flag, 1444216107, 9596961, 151, 18.47));
        nationList.add(new Nation("United States", "Washington D.C.", R.drawable.us_flag, 331002651, 9833517, 36, 4.24));
        nationList.add(new Nation("Indonesia", "Jakarta", R.drawable.indonesia_flag, 273523615, 1904569, 143, 3.60));
        nationList.add(new Nation("Pakistan", "Islamabad", R.drawable.pakistan_flag, 220892340, 881913, 250, 2.83));
        nationList.add(new Nation("Nigeria", "Abuja", R.drawable.nigeria_flag, 206139589, 923768, 223, 2.64));
        nationList.add(new Nation("Brazil", "Brasília", R.drawable.brazil_flag, 212559417, 8515767, 25, 2.66));
        nationList.add(new Nation("Bangladesh", "Dhaka", R.drawable.bangladesh_flag, 166303498, 148460, 1120, 2.17));
        nationList.add(new Nation("Vietnam", "Hanoi", R.drawable.vietnam_flag, 98168829, 331212, 296, 1.28));
        nationList.add(new Nation("Japan", "Tokyo", R.drawable.japan_flag, 126476461, 377975, 335, 3.10));
        nationList.add(new Nation("United Kingdom", "London", R.drawable.uk_flag, 67886011, 243610, 278, 0.88));
        nationList.add(new Nation("Australia", "Canberra", R.drawable.australia_flag, 25499884, 7692024, 3, 0.32));


        NationAdapter adapter = new NationAdapter(nationList, this);
        recyclerView.setAdapter(adapter);
    }
}