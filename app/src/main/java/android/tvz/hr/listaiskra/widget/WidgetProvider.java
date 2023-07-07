package android.tvz.hr.listaiskra.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.tvz.hr.listaiskra.APII.Photos;
import android.tvz.hr.listaiskra.APII.PhotosAPI;
import android.tvz.hr.listaiskra.Fragment.PrviFragment;
import android.tvz.hr.listaiskra.MainActivity;
import android.tvz.hr.listaiskra.R;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

            Picasso.get().load("https://via.placeholder.com/600/24f355").into(views, R.id.image_widget, appWidgetIds);

            ComponentName componentName=new ComponentName(context, WidgetProvider.class);

            appWidgetManager.updateAppWidget(componentName, views);
        }
    }
}
