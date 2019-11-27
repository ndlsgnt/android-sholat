package id.christyzer.sholat.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class jenis {

    @SerializedName("jenis")
    private List<JenisItem> jenis;

    public void setJenis(List<JenisItem> jenis) {
        this.jenis = jenis;
    }

    public List<JenisItem> getJenis() {
        return jenis;
    }

    @Override
    public String toString() {
        return
                "jenis{" +
                        "jenis = '" + jenis + '\'' +
                        "}";
    }

    public class JenisItem {

        @SerializedName("pengertian")
        private String pengertian;

        @SerializedName("jenis")
        private String jenis;

        @SerializedName("id")
        private String id;

        @SerializedName("syarat")
        private String syarat;

        public void setPengertian(String pengertian) {
            this.pengertian = pengertian;
        }

        public String getPengertian() {
            return pengertian;
        }

        public void setJenis(String jenis) {
            this.jenis = jenis;
        }

        public String getJenis() {
            return jenis;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setSyarat(String syarat) {
            this.syarat = syarat;
        }

        public String getSyarat() {
            return syarat;
        }

    }
}