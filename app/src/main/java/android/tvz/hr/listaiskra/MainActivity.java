package android.tvz.hr.listaiskra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.tvz.hr.listaiskra.APII.Photos;
import android.tvz.hr.listaiskra.APII.PhotosAPI;
import android.tvz.hr.listaiskra.Database.ListItemDB;
import android.tvz.hr.listaiskra.Database.ListItemDatabase;
import android.tvz.hr.listaiskra.Fragment.PrviFragment;
import android.tvz.hr.listaiskra.Prosli.ListItem;
import android.tvz.hr.listaiskra.music.BackgroundMusicService;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Photos> photos=new ArrayList<>();

    private ArrayList<ListItem> mItemList;
    private boolean mTwoPane;

    public ArrayList<Photos> getPhotos() {
        return photos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        startService(new Intent(MainActivity.this, BackgroundMusicService.class));

        if (findViewById(R.id.frameLayout2)!=null) {
            mTwoPane=true;
        }

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            NotificationChannel channel= new NotificationChannel("kanal", "ime kanala", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager=(NotificationManager) (getSystemService(Context.NOTIFICATION_SERVICE));
            notificationManager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            System.out.println("fetching failed");
                            return;
                        }
                        String token=task.getResult();
                        System.out.println(token);
                        Toast.makeText(MainActivity.this, "token: " + token , Toast.LENGTH_SHORT).show();
                    }
                });

        Bundle bundle=new Bundle();


        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PhotosAPI photosAPI =retrofit.create(PhotosAPI.class);

        Call<List<Photos>> call= photosAPI.getPhotos();


        call.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                if(!response.isSuccessful()) {
                }
                List<Photos> photosRetro =response.body();
                bundle.putParcelable("photo1", photosRetro.get(0));
                bundle.putParcelable("photo2", photosRetro.get(1));
                bundle.putParcelable("photo3", photosRetro.get(2));
                photos.addAll(photosRetro);


                ListItemDatabase db = Room.databaseBuilder(getApplicationContext(),
                        ListItemDatabase.class, "listItem-database").allowMainThreadQueries().build();

                ListItemDB item1=new ListItemDB(R.drawable.necron, "Necrons", "The necron xenos species");
                ListItemDB item2=new ListItemDB(R.drawable.imperium, "Imperium of man", "The imperium");
                ListItemDB item3=new ListItemDB(R.drawable.chaos, "Chaos", "Chaos");

                db.listItemDAO().insertAll(item1, item2, item3);

                List<ListItemDB> itemDBList=db.listItemDAO().getAllItems();




                bundle.putParcelable("listItem1", itemDBList.get(0));
                bundle.putParcelable("listItem2", itemDBList.get(1));
                bundle.putParcelable("listItem3", itemDBList.get(2));


                if(mTwoPane==true)
                    bundle.putString("twopane", "true");
                PrviFragment fragment=new PrviFragment();

                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.frameLayout, fragment);
                fragmentTransaction.commit();
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {

            }
        });
    }

    public void createItemList() {
        mItemList=new ArrayList<>();
        mItemList.add(new ListItem(R.drawable.necron, "Necrons", "The necron xenos species", "https://warhammer40k.fandom.com/wiki/Necrons"));
        mItemList.add(new ListItem(R.drawable.imperium, "Imperium of man", "The imperium", "https://warhammer40k.fandom.com/wiki/Imperium_of_Man"));
        mItemList.add(new ListItem(R.drawable.chaos, "Chaos", "Chaos", "https://warhammer40k.fandom.com/wiki/Chaos"));
    }

}