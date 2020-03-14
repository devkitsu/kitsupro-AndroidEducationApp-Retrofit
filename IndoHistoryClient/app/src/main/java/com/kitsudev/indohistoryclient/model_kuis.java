package com.kitsudev.indohistoryclient;
import java.io.Serializable;

public class model_kuis implements Serializable{

    private String pertanyaan;
    private String pilihan1,pilihan2,pilihan3,pilihan4;
    private String jawaban;
    private int key;



    public Integer getKey() {
        return key;
    }

    public String getJawaban() {
        return jawaban;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public String getPilihan1() {
        return pilihan1;
    }

    public String getPilihan2() {
        return pilihan2;
    }

    public String getPilihan3() {
        return pilihan3;
    }

    public String getPilihan4() {
        return pilihan4;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public void setPilihan1(String pilihan1) {
        this.pilihan1 = pilihan1;
    }

    public void setPilihan2(String pilihan2) {
        this.pilihan2 = pilihan2;
    }

    public void setPilihan3(String pilihan3) {
        this.pilihan3 = pilihan3;
    }

    public void setPilihan4(String pilihan4) {
        this.pilihan4 = pilihan4;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public model_kuis(){
    }

    /*@Override
    public String toString() {
        return " "+pertanyaan+"\n" +
                " "+pilihan1 +"\n" +
                " "+pilihan2 +"\n" +
                " "+pilihan3 +"\n" +
                " "+pilihan4 +"\n" +
                " "+jawaban;
    }*/

    public model_kuis(String pertanyaan, String pilihan1, String pilihan2, String pilihan3, String pilihan4, String jawaban){
        this.pertanyaan = pertanyaan;
        this.pilihan1 = pilihan1;
        this.pilihan2 = pilihan2;
        this.pilihan3 = pilihan3;
        this.pilihan4 = pilihan4;
        this.jawaban = jawaban;
    }
}
