package com.maxyumgames.mstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private GridLayout catalog;
    private GridLayout favorite;
    private GridLayout bucket;
    private ScrollView product_card;
    private EditText find_line;
    float density;
    private List<Product> products = new ArrayList<Product>();
    private List<Product> favorite_products = new ArrayList<Product>();
    private List<Integer> counters = new ArrayList<Integer>();
    private List<Product> bucket_products = new ArrayList<Product>();
    private List<Integer> bucket_count = new ArrayList<Integer>();

    int last_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        density = getResources().getDisplayMetrics().density;

        catalog = findViewById(R.id.catalog);
        favorite = findViewById(R.id.favorite);
        bucket = findViewById(R.id.bucket);
        product_card = findViewById(R.id.product_card);
        find_line = findViewById(R.id.find_line);

        find_line.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                catalog.removeAllViews();
                String search = find_line.getText().toString();
                boolean use_filter = !search.trim().isEmpty();

                if(use_filter){
                    for (int i = 0; i < products.size(); i++){
                        if(products.get(i).product_name.toLowerCase().contains(search.toLowerCase()) ||
                                products.get(i).product_description.toLowerCase().contains(search.toLowerCase())) {
                            addItemToCatalog(i);
                        }
                    }
                }
                else{
                    for (int i = 0; i < products.size(); i++){
                        addItemToCatalog(i);
                    }
                }
            }
        });

        products.add(new Product(0, R.drawable._15918251b7360f3549dcaff8ebdc34a, "Патроны любых калибров", "Обычные патроны любых калибров ,цена для каждого калибра и типа разныя ,всё остальное по телефону 8-991-540-44-24",10 ));
        products.add(new Product(1, R.drawable._3393855b3d6c8ef23dfbf8e531e2af7__nice_designs_animals, "BFG-50 (BIG FUCKING GUN)", "BFG-50 или в простонародье BIG FUCKING GUN \n Убийственная штука ,прямой пример поговорки --В одно ухо влетело , через другое вылетело-- \n Если вы хотите испытать эту мощь ,то приходите в наш магазин и забирайте свой FUCK ", 2000));
        products.add(new Product(2, R.drawable._777167, "Дробовик\n\n Benelli M4 Super 90\n", "Benelli M4 Super 90 Курт кобейн оценит эту вещь ,позвоните ему и напишите прямо сейчас ,говорят он еще на связи, а на самом деле ,бьёт хорошо как в близи так и вдаль, Столярова подтвердит ", 1600));
        products.add(new Product(3, R.drawable._ef5ef91ba55bf8af0157d1406c59fe1, "ОСТОРОЖНО, МОЖЕТ УБИТЬ СВОИМ ВИДОМ \n K-POP исполнители", "ВНИМАНИЕ!!!\nК нам в руки попало очень опасное оружие: K-POP исполнители , говорят что никто не выживает в радиусе 15 км ,наша компания не хочет этого проверять из-за слишком большой ответственности за жизни других людей ,но вас особо тоже никто не просит ;)", 25000));
        products.add(new Product(4, R.drawable.barrett, "Снайперская винтовка Barret", "Снайперская винтовка Barret ,известная как моргенштерн у малолеток , слабее чем BFG ,но тоже может доставить уйму неприятностей)", 1900));
        products.add(new Product(5, R.drawable.brauning_m2_stankovyi_pulemiot, "Тяжелый пулёмет Brauning m2", "Если вы сможете поднять эту махину одни , то наш магазин готов сделать скидку 25% , может испепилить абсолютно любую машину в труху ,паша техник не даст соврать", 3100));
        products.add(new Product(6, R.drawable.maxresdefault, "Винтовка m-16", "Обычная винтовка m-16 ,которавя стоит на вооружении США ,но пендосов же можно гасить с их оружия тоже...", 1300));
        products.add(new Product(7, R.drawable.vohlfqllfacuvmhk5aqwq9gzjvm_1920, "Desert Eagle", "Малыш дигггг л ,утобьёт тебе руку и убьёт отдачей соседа по парте, не советуем с ней играться,данное оружие, любимец кинематографии, будь крутым гэнгста вместе с нами)", 900));

        for (int i = 0; i < products.size(); i++){
            addItemToCatalog(i);
        }
    }

    private void addItemToCatalog(int id) {
        Product p = products.get(id);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        LinearLayout gridItemView = (LinearLayout) inflater.inflate(R.layout.card_layout, null);

        TextView itemText = gridItemView.findViewById(R.id.content_title);
        TextView priceText = gridItemView.findViewById(R.id.content_price);
        ImageView itemImageView = gridItemView.findViewById(R.id.content_image);
        ImageButton itemButton = gridItemView.findViewById(R.id.add_to_favorite);
        Button show_product_card = gridItemView.findViewById(R.id.show_item_card);

        itemImageView.setImageResource(p.image_id);
        itemText.setText(p.product_name);
        priceText.setText(String.format("%.2f$", p.product_price));

        itemButton.setOnClickListener(v -> {
            if(!favorite_products.contains(p)) {
                favorite_products.add(p);
                counters.add(0);
            }
        });

        show_product_card.setOnClickListener(v -> {
            TextView title_view = product_card.findViewById(R.id.product_card_title);
            ImageView product_image = product_card.findViewById(R.id.product_card_image);
            TextView price_view = product_card.findViewById(R.id.product_card_price);
            TextView description_view = product_card.findViewById(R.id.product_card_description);

            title_view.setText(p.product_name);
            product_image.setImageResource(p.image_id);
            price_view.setText(String.format("%.2f$", p.product_price));
            description_view.setText(p.product_description);

            SetActiveWidget(3);
        });

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = dpToPx(160);
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.setMargins(dpToPx(10), dpToPx(10), dpToPx(10), dpToPx(10));

        catalog.addView(gridItemView, params);
    }

    private void addItemToFavorite(int id) {
        Product p = favorite_products.get(id);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        LinearLayout gridItemView = (LinearLayout) inflater.inflate(R.layout.favorite_card_layout, null);

        TextView itemText = gridItemView.findViewById(R.id.content_title);
        TextView priceText = gridItemView.findViewById(R.id.content_price);
        ImageView itemImageView = gridItemView.findViewById(R.id.content_image);
        Button minus_button = gridItemView.findViewById(R.id.minus_button);
        ImageButton itemButton = gridItemView.findViewById(R.id.add_to_bucket);
        Button plus_button = gridItemView.findViewById(R.id.plus_button);
        TextView product_counter = gridItemView.findViewById(R.id.product_counter);

        itemImageView.setImageResource(p.image_id);
        itemText.setText(p.product_name);
        priceText.setText(String.format("%.2f$", p.product_price));

        minus_button.setOnClickListener(v -> {
            if(counters.get(id) > 0) {
                counters.set(id, counters.get(id) - 1);
                product_counter.setText(counters.get(id).toString());
            }
            else{
                favorite_products.remove(id);
                counters.remove(id);

                favorite.removeAllViews();

                for (int i = 0; i < favorite_products.size(); i++){
                    addItemToFavorite(i);
                }
            }
        });

        itemButton.setOnClickListener(v -> {
            if(!bucket_products.contains(p)) {
                bucket_products.add(p);
                bucket_count.add(counters.get(id));
            }
            else{
                int index = bucket_products.indexOf(p);
                bucket_count.set(index, bucket_count.get(index) + counters.get(id));
            }

            counters.set(id, 0);
            product_counter.setText(counters.get(id).toString());
        });

        plus_button.setOnClickListener(v -> {
            counters.set(id, counters.get(id) + 1);
            product_counter.setText(counters.get(id).toString());
        });

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = dpToPx(160);
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.setMargins(dpToPx(10), dpToPx(10), dpToPx(10), dpToPx(10));

        favorite.addView(gridItemView, params);
    }

    private void addItemToBucket(int id) {
        Product p = bucket_products.get(id);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        LinearLayout gridItemView = (LinearLayout) inflater.inflate(R.layout.bucket_card_layout, null);

        TextView itemText = gridItemView.findViewById(R.id.content_title);
        TextView priceText = gridItemView.findViewById(R.id.content_price);
        ImageView itemImageView = gridItemView.findViewById(R.id.content_image);
        ImageButton itemButton = gridItemView.findViewById(R.id.delete_from_bucket);
        TextView product_counter = gridItemView.findViewById(R.id.product_counter);

        itemImageView.setImageResource(p.image_id);
        itemText.setText(p.product_name);
        priceText.setText(String.format("%.2f$", p.product_price));
        product_counter.setText(bucket_count.get(id).toString());

        itemButton.setOnClickListener(v -> {
            bucket_products.remove(id);
            bucket_count.remove(id);

            bucket.removeAllViews();

            for (int i = 0; i < bucket_products.size(); i++){
                addItemToBucket(i);
            }
        });

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = dpToPx(160);
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.setMargins(dpToPx(10), dpToPx(10), dpToPx(10), dpToPx(10));

        bucket.addView(gridItemView, params);
    }

    public int dpToPx(int dp) {
        return Math.round(dp * density);
    }

    public void go_to_bucket(View view) {
        ClearAllWidgets();

        for (int i = 0; i < bucket_products.size(); i++){
            addItemToBucket(i);
        }

        SetActiveWidget(2);
    }

    public void go_to_catalog(View view) {
        ClearAllWidgets();

        for (int i = 0; i < products.size(); i++){
            addItemToCatalog(i);
        }

        SetActiveWidget(0);
    }

    public void go_to_favorite(View view) {
        ClearAllWidgets();

        for (int i = 0; i < favorite_products.size(); i++){
            addItemToFavorite(i);
        }

        SetActiveWidget(1);
    }

    public void ClearAllWidgets(){
        catalog.removeAllViews();
        favorite.removeAllViews();
        bucket.removeAllViews();
    }

    public void SetActiveWidget(int id){
        catalog.setVisibility(id == 0 ? View.VISIBLE : View.INVISIBLE);
        favorite.setVisibility(id == 1 ? View.VISIBLE : View.INVISIBLE);
        bucket.setVisibility(id == 2 ? View.VISIBLE : View.INVISIBLE);
        product_card.setVisibility(id == 3 ? View.VISIBLE : View.INVISIBLE);

        if(id < 3)
            last_activity = id;
    }

    public void go_back(View view) {
        SetActiveWidget(last_activity);
    }
}