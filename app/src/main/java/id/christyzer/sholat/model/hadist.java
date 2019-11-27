package id.christyzer.sholat.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class hadist {

    @SerializedName("hadist")
    private List<HadistItem> hadist;

    public void setHadist(List<HadistItem> hadist) {
        this.hadist = hadist;
    }

    public List<HadistItem> getHadist() {
        return hadist;
    }

    @Override
    public String toString() {
        return
                "hadist{" +
                        "hadist = '" + hadist + '\'' +
                        "}";
    }

    public class HadistItem {

        @SerializedName("nama")
        private String nama;

        @SerializedName("id")
        private String id;

        @SerializedName("arti")
        private String arti;

        @SerializedName("isi")
        private String isi;

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getNama() {
            return nama;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setArti(String arti) {
            this.arti = arti;
        }

        public String getArti() {
            return arti;
        }

        public void setIsi(String isi) {
            this.isi = isi;
        }

        public String getIsi() {
            return isi;
        }

        @Override
        public String toString() {
            return
                    "HadistItem{" +
                            "nama = '" + nama + '\'' +
                            ",id = '" + id + '\'' +
                            ",arti = '" + arti + '\'' +
                            ",isi = '" + isi + '\'' +
                            "}";
        }
    }
}