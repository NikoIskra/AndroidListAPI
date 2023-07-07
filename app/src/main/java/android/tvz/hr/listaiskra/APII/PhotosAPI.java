package android.tvz.hr.listaiskra.APII;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotosAPI {

    @GET("photos")
    Call<List<Photos>> getPhotos();
}
