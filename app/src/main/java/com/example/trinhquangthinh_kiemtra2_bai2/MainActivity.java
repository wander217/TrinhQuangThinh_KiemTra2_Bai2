package com.example.trinhquangthinh_kiemtra2_bai2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText id,name;
    private TextView total;
    private DatePicker date;
    private CheckBox vaxcin,arn,proteinS,proteinN;
    private Button add,update,remove,search;
    private RecyclerView itemView;
    private final List<Virus> virusList = new ArrayList<>();
    private VirusDAO virusDAO;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        this.id = findViewById(R.id.txtId);
        this.name= findViewById(R.id.txtName);
        this.date = findViewById(R.id.txtDate);
        this.vaxcin = findViewById(R.id.txtVacxin);
        this.arn = findViewById(R.id.txtArn);
        this.proteinN = findViewById(R.id.txtProteinN);
        this.proteinS = findViewById(R.id.txtProteinS);
        this.total = findViewById(R.id.txtTotal);
        this.itemView = findViewById(R.id.itemView);
       ItemAdapter itemAdapter = new ItemAdapter(virusList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this
                .getApplicationContext());
        this.itemView.setLayoutManager(linearLayoutManager);
        this.itemView.setAdapter(itemAdapter);
        this.virusDAO = new VirusDAO(getApplicationContext());
        total.setText("Tổng:"+virusDAO.getAll().size());
        this.add = findViewById(R.id.btnAdd);
        this.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Virus virus= new Virus();
                virus.setName(name.getText().toString());
                virus.setVaxcin(vaxcin.isChecked());
                virus.setArn(arn.isChecked());
                virus.setProteinS(proteinS.isChecked());
                virus.setProteinN(proteinN.isChecked());
                virus.setDate(Date.valueOf(date.getYear()+
                        "-"+date.getMonth()+"-"+date.getDayOfMonth()));
                boolean ans = virusDAO.add(virus);
                show(ans);
            }
        });
        this.update = findViewById(R.id.btnUpdate);
            this.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Virus virus= new Virus();
                    virus.setId(Integer.parseInt(id.getText().toString()));
                    virus.setName(name.getText().toString());
                    virus.setVaxcin(vaxcin.isChecked());
                    virus.setArn(arn.isChecked());
                    virus.setProteinS(proteinS.isChecked());
                    virus.setProteinN(proteinN.isChecked());
                    virus.setDate(Date.valueOf(date.getYear()+
                            "-"+date.getMonth()+"-"+date.getDayOfMonth()));
                    boolean ans = virusDAO.update(virus);
                    show(ans);
                }
            });
        this.remove = findViewById(R.id.btnDelete);
        this.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ans = virusDAO.remove(Integer
                        .parseInt(id.getText().toString()));
                show(ans);
            }
        });
        this.search = findViewById(R.id.btnSearch);
        this.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                virusList.clear();
                virusList.addAll(virusDAO.search(name.getText().toString()));
                itemAdapter.notifyDataSetChanged();
            }
        });
    }

    private void show(boolean ans){
        if(ans){
            Toast.makeText(getApplicationContext(),
                    "success",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(),
                    "fail",Toast.LENGTH_LONG).show();
        }
        total.setText("Tổng:"+virusDAO.getAll().size());
    }
}