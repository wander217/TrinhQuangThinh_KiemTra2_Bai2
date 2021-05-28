package com.example.trinhquangthinh_kiemtra2_bai2;

import java.sql.Date;

public class Virus {
    private int id;
    private String name;
    private boolean arn;
    private boolean proteinS;
    private boolean proteinN;
    private Date date;
    private boolean vaxcin;

    public Virus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isArn() {
        return arn;
    }

    public void setArn(boolean arn) {
        this.arn = arn;
    }

    public boolean isProteinS() {
        return proteinS;
    }

    public void setProteinS(boolean proteinS) {
        this.proteinS = proteinS;
    }

    public boolean isProteinN() {
        return proteinN;
    }

    public void setProteinN(boolean proteinN) {
        this.proteinN = proteinN;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isVaxcin() {
        return vaxcin;
    }

    public void setVaxcin(boolean vaxcin) {
        this.vaxcin = vaxcin;
    }
}
