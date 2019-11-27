package id.christyzer.sholat.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class sholat {

    @SerializedName("sholat")
    private List<SholatItem> sholat;

    public void setSholat(List<SholatItem> sholat) {
        this.sholat = sholat;
    }

    public List<SholatItem> getSholat() {
        return sholat;
    }

    @Override
    public String toString() {
        return
                "sholat{" +
                        "sholat = '" + sholat + '\'' +
                        "}";
    }

    public class SholatItem {

        @SerializedName("pengetian")
        private String pengetian;

        @SerializedName("nama_sholat")
        private String namaSholat;

        @SerializedName("panduan")
        private String panduan;

        @SerializedName("jenis_id")
        private String jenisId;

        @SerializedName("id")
        private String id;

        @SerializedName("niat")
        private String niat;

        public void setPengetian(String pengetian) {
            this.pengetian = pengetian;
        }

        public String getPengetian() {
            return pengetian;
        }

        public void setNamaSholat(String namaSholat) {
            this.namaSholat = namaSholat;
        }

        public String getNamaSholat() {
            return namaSholat;
        }

        public void setPanduan(String panduan) {
            this.panduan = panduan;
        }

        public String getPanduan() {
            return panduan;
        }

        public void setJenisId(String jenisId) {
            this.jenisId = jenisId;
        }

        public String getJenisId() {
            return jenisId;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setNiat(String niat) {
            this.niat = niat;
        }

        public String getNiat() {
            return niat;
        }

        @Override
        public String toString() {
            return
                    "SholatItem{" +
                            "pengetian = '" + pengetian + '\'' +
                            ",nama_sholat = '" + namaSholat + '\'' +
                            ",panduan = '" + panduan + '\'' +
                            ",jenis_id = '" + jenisId + '\'' +
                            ",id = '" + id + '\'' +
                            ",niat = '" + niat + '\'' +
                            "}";
        }
    }
}