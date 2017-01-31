package ru.startandroid.develop.p0541customadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Product> mProducts;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillProducts();

        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        mAdapter = new MyAdapter(this, mProducts);

        lvMain.setAdapter(mAdapter);

        Button btnBox = (Button) findViewById(R.id.btnBox);
        btnBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showResult();
            }
        });
    }

    private void fillProducts() {
        if (mProducts == null){
            mProducts = new ArrayList<>();
        }
        Random r = new Random();
        for (int i = 1; i < 11; i++){
            Product product = new Product("Product #" + i, r.nextInt(80), R.drawable.ic_launcher, false);
            mProducts.add(product);
        }
    }

    public void showResult(){
        StringBuilder box = new StringBuilder();
        for (Product product: mAdapter.getBox()){
            box.append(product.getName()).append("\n");
        }
        if (box.length() > 0){
            Toast.makeText(this, box.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
