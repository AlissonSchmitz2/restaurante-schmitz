package br.com.unesc.pdm.restauranteschmitz.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import br.com.unesc.pdm.restauranteschmitz.R;
import br.com.unesc.pdm.restauranteschmitz.lib.LoggerPreferences;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linear_table_1, linear_table_2, linear_table_3, linear_table_4,
    linear_table_5, linear_table_6, linear_table_7, linear_table_8, linear_table_9;
    private Button btn_reservate_1, btn_reservate_2, btn_reservate_3, btn_reservate_4,
    btn_reservate_5, btn_reservate_6, btn_reservate_7, btn_reservate_8, btn_reservate_9;
    private EditText txt_number_table;
    private Button btn_release_table, btn_save_operation, btn_reserve_all_tables;
    private TextView label_welcome;
    private LoggerPreferences loggerPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoggerPreferences.init(true, true);
        loggerPreferences = LoggerPreferences.get(MainActivity.this).with(MainActivity.this);

        Intent it = getIntent();
        label_welcome = findViewById(R.id.label_welcome);
        label_welcome.setText("Olá, " + it.getStringExtra("user"));

        linear_table_1 = findViewById(R.id.linear_table_1);
        btn_reservate_1 = findViewById(R.id.btn_reservate_1);
        createActionButton(linear_table_1, btn_reservate_1);

        linear_table_2 = findViewById(R.id.linear_table_2);
        btn_reservate_2 = findViewById(R.id.btn_reservate_2);
        createActionButton(linear_table_2, btn_reservate_2);

        linear_table_3 = findViewById(R.id.linear_table_3);
        btn_reservate_3 = findViewById(R.id.btn_reservate_3);
        createActionButton(linear_table_3, btn_reservate_3);

        linear_table_4 = findViewById(R.id.linear_table_4);
        btn_reservate_4 = findViewById(R.id.btn_reservate_4);
        createActionButton(linear_table_4, btn_reservate_4);

        linear_table_5 = findViewById(R.id.linear_table_5);
        btn_reservate_5 = findViewById(R.id.btn_reservate_5);
        createActionButton(linear_table_5, btn_reservate_5);

        linear_table_6 = findViewById(R.id.linear_table_6);
        btn_reservate_6 = findViewById(R.id.btn_reservate_6);
        createActionButton(linear_table_6, btn_reservate_6);

        linear_table_7 = findViewById(R.id.linear_table_7);
        btn_reservate_7 = findViewById(R.id.btn_reservate_7);
        createActionButton(linear_table_7, btn_reservate_7);

        linear_table_8 = findViewById(R.id.linear_table_8);
        btn_reservate_8 = findViewById(R.id.btn_reservate_8);
        createActionButton(linear_table_8, btn_reservate_8);

        linear_table_9 = findViewById(R.id.linear_table_9);
        btn_reservate_9 = findViewById(R.id.btn_reservate_9);
        createActionButton(linear_table_9, btn_reservate_9);

        txt_number_table = findViewById(R.id.txt_number_table);
        btn_release_table = findViewById(R.id.btn_release_table);
        btn_release_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number_table;
                try {
                    number_table = Integer.valueOf(
                            txt_number_table.getText().toString().isEmpty()
                                    ? "0"
                                    : txt_number_table.getText().toString());
                } catch (NumberFormatException e) {
                    number_table = 0;
                }


                if(number_table > 0 && number_table < 10) {
                    if(!getBtnReservateTable(number_table).isEnabled()) {
                        ColorDrawable colorDefaultTable = new ColorDrawable(getResources()
                                .getColor(R.color.table_default));
                        getLinearTable(number_table).setBackground(colorDefaultTable);
                        getBtnReservateTable(number_table).setEnabled(true);
                        loggerPreferences.edit().putBoolean("RESERVATE_TABLE_" + number_table, true);
                    } else {
                        Toast.makeText(MainActivity.this, "Mesa não reservada. A mesa " + number_table + " encontra-se habilitada para reserva.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    txt_number_table.setError("Mesa Inválida!");
                }
            }
        });

        btn_save_operation = findViewById(R.id.btn_save_operation);
        btn_save_operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 1; i < 10; i++) {
                    if(!getBtnReservateTable(i).isEnabled()) {
                        loggerPreferences.edit().putBoolean("RESERVATE_TABLE_" + i, true).apply();
                    } else {
                        loggerPreferences.edit().putBoolean("RESERVATE_TABLE_" + i, false).apply();
                    }
                }

                Toast.makeText(MainActivity.this, "Operações salvas com sucesso!", Toast.LENGTH_LONG).show();
            }
        });

        btn_reserve_all_tables = findViewById(R.id.btn_reserve_all_tables);
        btn_reserve_all_tables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable colorReservateTable = new ColorDrawable(getResources().getColor(R.color.table_reservate));
                boolean all_tables_reservate = true;

                for(int i = 1; i < 10; i++) {
                    if(getBtnReservateTable(i).isEnabled()) {
                        getLinearTable(i).setBackground(colorReservateTable);
                        getBtnReservateTable(i).setEnabled(false);
                        all_tables_reservate = false;
                    }
                }

                if(all_tables_reservate) {
                    Toast.makeText(MainActivity.this, "Operação inválida. Todas as mesas já possuem reserva.", Toast.LENGTH_LONG).show();
                }
            }
        });

        ColorDrawable colorReservateTable = new ColorDrawable(getResources().getColor(R.color.table_reservate));

        for(int i = 1; i < 10; i++) {
            if(loggerPreferences.getBoolean("RESERVATE_TABLE_" + i, false)) {
                getLinearTable(i).setBackground(colorReservateTable);
                getBtnReservateTable(i).setEnabled(false);
            }
        }
    }

    private void createActionButton(final LinearLayout linearLayout, final Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable colorReservateTable = new ColorDrawable(getResources().getColor(R.color.table_reservate));

                linearLayout.setBackground(colorReservateTable);
                button.setEnabled(false);
            }
        });
    }

    private LinearLayout getLinearTable(int id) {
        switch (id) {
            case 1:
                return linear_table_1;
            case 2:
                return linear_table_2;
            case 3:
                return linear_table_3;
            case 4:
                return linear_table_4;
            case 5:
                return linear_table_5;
            case 6:
                return linear_table_6;
            case 7:
                return linear_table_7;
            case 8:
                return linear_table_8;
            case 9:
                return linear_table_9;
        }

        return null;
    }

    private Button getBtnReservateTable(int id) {
        switch (id) {
            case 1:
                return btn_reservate_1;
            case 2:
                return btn_reservate_2;
            case 3:
                return btn_reservate_3;
            case 4:
                return btn_reservate_4;
            case 5:
                return btn_reservate_5;
            case 6:
                return btn_reservate_6;
            case 7:
                return btn_reservate_7;
            case 8:
                return btn_reservate_8;
            case 9:
                return btn_reservate_9;
        }

        return null;
    }

}
