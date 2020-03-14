package com.kitsudev.indohistoryclient;

public class Result {
    String nm_kategori;
    String id_kategori;
    String nm_materi;
    String id_materi;
    String isi;
    String nama;
    String skor;
    String level;
    String gambar;
    String tanggal;
    String id_kuis;
    String id_tebakgambar;
    String pertanyaan;
    String jawaban;
    String jawaban1;
    String jawaban2;
    String jawaban3;
    String jawaban4;
    String skorMudah;
    String skorSedang;
    String skorSulit;
    String count;

    public Result() {
    }

    public Result(String count, String skorMudah, String skorSedang, String skorSulit, String nm_kategori, String id_kategori, String nama, String gambar, String tanggsl, String nm_materi, String id_materi, String isi,
                  String id_kuis, String level, String pertanyaan,  String jawaban, String skor, String jawaban1, String jawaban2, String jawaban3, String jawaban4, String id_tebakgambar) {
        this.nm_kategori=nm_kategori;
        this.id_kategori=id_kategori;
        this.nama=nama;
        this.skor=skor;
        this.level=level;
        this.gambar=gambar;
        this.tanggal=tanggal;
        this.id_materi=id_materi;
        this.nm_materi=nm_materi;
        this.id_kuis=id_kuis;
        this.pertanyaan=pertanyaan;
        this.jawaban=jawaban;
        this.jawaban1=jawaban1;
        this.jawaban2=jawaban2;
        this.jawaban3=jawaban3;
        this.jawaban4=jawaban4;
        this.id_tebakgambar=id_tebakgambar;
        this.skor=skorMudah;
        this.skor=skorSedang;
        this.skor=skorSulit;
        this.count=count;
    }

    public String getNama_kategori(){
        return nm_kategori;
    }
    public String getId_kategori() {   return id_kategori;}
    public String getNama_materi(){
        return nm_materi;
    }
    public String getId_materi() {   return id_materi;}
    public String getIsi() {   return isi;}
    public String getSkor() {   return skor;}
    public String getLevel() {   return level;}
    public String getId_kuis() {   return id_kuis;}
    public String getPertanyaan() {   return pertanyaan;}
    public String getJawaban() {   return jawaban;}
    public String getJawaban1() {   return jawaban1;}
    public String getJawaban2() {   return jawaban2;}
    public String getJawaban3() {   return jawaban3;}
    public String getJawaban4() {   return jawaban4;}
    public String getGambar() {   return gambar;}
    public String getTanggal() {   return tanggal;}
    public String getNama() {   return nama;}
    public String getId_tebakgambar() {   return id_tebakgambar;}
    public String setGambar() {   return gambar;}
    public String getSkorMudah() {   return skor;}
    public String getSkorSedang() {   return skor;}
    public String getSkorSulit() {   return skor;}
    public String getSoalMudah() {   return count;}
    public String getSoalSedang() {   return count;}
    public String getSoalSulit() {   return count;}
}
