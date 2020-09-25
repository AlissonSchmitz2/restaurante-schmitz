package br.com.unesc.pdm.restauranteschmitz.main;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import br.com.unesc.pdm.restauranteschmitz.R;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linear_table_1, linear_table_2, linear_table_3, linear_table_4,
    linear_table_5, linear_table_6, linear_table_7, linear_table_8, linear_table_9;
    private Button btn_reservate_1, btn_reservate_2, btn_reservate_3, btn_reservate_4,
    btn_reservate_5, btn_reservate_6, btn_reservate_7, btn_reservate_8, btn_reservate_9;
    private EditText txt_number_table;
    private Button btn_release_table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linear_table_1 = findViewById(R.id.linear_table_1);
        btn_reservate_1 = findViewById(R.id.btn_reservate_1);
        createActionButton(linear_table_1, btn_reservate_1, 1);

        linear_table_2 = findViewById(R.id.linear_table_2);
        btn_reservate_2 = findViewById(R.id.btn_reservate_2);
        createActionButton(linear_table_2, btn_reservate_2, 2);

        linear_table_3 = findViewById(R.id.linear_table_3);
        btn_reservate_3 = findViewById(R.id.btn_reservate_3);
        createActionButton(linear_table_3, btn_reservate_3, 3);

        linear_table_4 = findViewById(R.id.linear_table_4);
        btn_reservate_4 = findViewById(R.id.btn_reservate_4);
        createActionButton(linear_table_4, btn_reservate_4, 4);

        linear_table_5 = findViewById(R.id.linear_table_5);
        btn_reservate_5 = findViewById(R.id.btn_reservate_5);
        createActionButton(linear_table_5, btn_reservate_5, 5);

        linear_table_6 = findViewById(R.id.linear_table_6);
        btn_reservate_6 = findViewById(R.id.btn_reservate_6);
        createActionButton(linear_table_6, btn_reservate_6, 6);

        linear_table_7 = findViewById(R.id.linear_table_7);
        btn_reservate_7 = findViewById(R.id.btn_reservate_7);
        createActionButton(linear_table_7, btn_reservate_7, 7);

        linear_table_8 = findViewById(R.id.linear_table_8);
        btn_reservate_8 = findViewById(R.id.btn_reservate_8);
        createActionButton(linear_table_8, btn_reservate_8, 8);

        linear_table_9 = findViewById(R.id.linear_table_9);
        btn_reservate_9 = findViewById(R.id.btn_reservate_9);
        createActionButton(linear_table_9, btn_reservate_9, 9);

        txt_number_table = findViewById(R.id.txt_number_table);
        btn_release_table = findViewById(R.id.btn_release_table);
        btn_release_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number_table = Integer.valueOf(
                        txt_number_table.getText().toString().isEmpty()
                                ? "0"
                                : txt_number_table.getText().toString());
                if(number_table > 0 && number_table < 10) {





                } else {
                    txt_number_table.setError("Mesa Inválida!");
                }
            }
        });
    }

    private void createActionButton(final LinearLayout linearLayout, final Button button, final int id) {
        Log.d("CREATELOG", "Teste" + id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable colorDefaultTable = new ColorDrawable(getResources().getColor(R.color.table_default));
                ColorDrawable colorReservateTable = new ColorDrawable(getResources().getColor(R.color.table_reservate));

                linearLayout.setBackground(colorReservateTable);
                button.setEnabled(false);
            }
        });
    }

}
