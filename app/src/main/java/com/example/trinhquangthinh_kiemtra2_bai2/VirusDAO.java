package com.example.trinhquangthinh_kiemtra2_bai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class VirusDAO extends SQLiteOpenHelper {
    private static final String DBNAME="ThamHoa";
    private static final String ID="id";
    private static final String NAME="name";
    private static final String ARN ="arn";
    private static final String PROTEINS ="proteinS";
    private static final String PROTEINN ="proteinN";
    private static final String DATE = "findDate";
    private static final String VACXIN = "vacxin";
    private static final int version=1;

    public VirusDAO(@Nullable Context context) {
        super(context, DBNAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDB ="CREATE TABLE "+DBNAME+"(" +
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                NAME+" TEXT,"+ ARN+" BOOLEAN,"+ PROTEINN+" BOOLEAN,"
                + PROTEINS+" BOOLEAN,"+DATE+" INTEGER,"+ VACXIN+" BOOLEAN)";
        db.execSQL(createDB);
    }

    public boolean add(Virus virus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,virus.getName());
        values.put(ARN,virus.isArn());
        values.put(PROTEINN,virus.isProteinN());
        values.put(PROTEINS,virus.isProteinS());
        values.put(DATE,virus.getDate().getTime());
        values.put(VACXIN,virus.isVaxcin());
        long ans=db.insert(DBNAME,null,values);
        return ans>0;
    }

    public List<Virus> getAll(){
        SQLiteDatabase db= this.getReadableDatabase();
        String clause = VACXIN+"=?";
        Cursor cursor =db.query(DBNAME,null,clause
                ,new String[]{"1"},null,null,null);
        List<Virus> ans = new ArrayList<>();
        if(cursor!=null&&cursor.moveToNext()){
            do {
                Virus virus = new Virus();
                virus.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                virus.setVaxcin(cursor.getInt(cursor.getColumnIndex(VACXIN))==1);
                virus.setDate(new Date(cursor.getLong(cursor.getColumnIndex(DATE))));
                virus.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                virus.setArn(cursor.getInt(cursor.getColumnIndex(ARN))==1);
                virus.setProteinN(cursor.getInt(cursor.getColumnIndex(PROTEINN))==1);
                virus.setProteinS(cursor.getInt(cursor.getColumnIndex(PROTEINS))==1);
                ans.add(virus);
            }while (cursor.moveToNext());
        }
        return ans;
    }

    public boolean update(Virus virus){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,virus.getName());
        values.put(DATE,virus.getDate().getTime());
        values.put(VACXIN,virus.isVaxcin());
        values.put(ARN,virus.isArn());
        values.put(PROTEINS,virus.isProteinS());
        values.put(PROTEINN,virus.isProteinN());
        values.put(ID,virus.getId());
        String clase = ID+"=?";
        long ans = db.update(DBNAME,values,clase
                ,new String[]{String.valueOf(virus.getId())});
        return ans>0;
    }

    public boolean remove(int id){
        SQLiteDatabase db= this.getWritableDatabase();
        String clause = ID+" =? AND "+DATE+" <?";
        Date tmp = Date.valueOf("1960-1-1");
        long ans=db.delete(DBNAME,clause,new String[]{
                String.valueOf(id),String.valueOf(tmp.getTime())
        });
        return ans>0;
    }

    public List<Virus> search(String key){
        SQLiteDatabase db= this.getReadableDatabase();
        String clause = NAME+" LIKE ?";
        Cursor cursor =db.query(DBNAME,null,clause
                ,new String[]{"%"+key+"%"},null,null,null);
        List<Virus> ans = new ArrayList<>();
        if(cursor!=null&&cursor.moveToNext()){
            do {
                Virus virus = new Virus();
                virus.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                virus.setVaxcin(cursor.getInt(cursor.getColumnIndex(VACXIN))==1);
                virus.setDate(new Date(cursor.getLong(cursor.getColumnIndex(DATE))));
                virus.setName(new String(cursor.getBlob(cursor.getColumnIndex(NAME))));
                virus.setArn(cursor.getInt(cursor.getColumnIndex(ARN))==1);
                virus.setProteinN(cursor.getInt(cursor.getColumnIndex(PROTEINN))==1);
                virus.setProteinS(cursor.getInt(cursor.getColumnIndex(PROTEINS))==1);
                ans.add(virus);
            }while (cursor.moveToNext());
        }
        return ans;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropSQL = "DROP TABLE IF EXISTS "+DBNAME;
        db.execSQL(dropSQL);
    }
}
