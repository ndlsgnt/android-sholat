package id.christyzer.sholat.api;

import id.christyzer.sholat.model.hadist;
import id.christyzer.sholat.model.jenis;
import id.christyzer.sholat.model.sholat;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface api {

    @GET("getSholat.php")
    Call<jenis> getJenis(@Query("c") String keyword);

    @GET("getHadist.php")
    Call<hadist> getHadist();

    @GET("getMateriSholat.php")
    Call<sholat> getMateri(@Query("s") String jenis);

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("username") String username, @Field("password") String password);
}
