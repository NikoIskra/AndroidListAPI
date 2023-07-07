package android.tvz.hr.listaiskra.Fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.tvz.hr.listaiskra.APII.Photos;
import android.tvz.hr.listaiskra.Database.ListItemDB;
import android.tvz.hr.listaiskra.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;


public class DrugiFragment extends Fragment {

    Photos photo;


    public DrugiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=this.getArguments();
        if (bundle != null) {
            photo=bundle.getParcelable("List item");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drugi, container, false);
        SimpleDraweeView simpleDraweeView =view.findViewById(R.id.image_detail_fragment);
        Uri uri=Uri.parse(photo.getPhotoUrl());
        simpleDraweeView.setImageURI(uri);
        TextView textView=view.findViewById(R.id.text_detail_fragment);
        textView.setText(photo.getTitle());
        TextView descriptionView=view.findViewById(R.id.description_detail_fragment);
        descriptionView.setText(photo.getPhotoUrl());
        return view;
    }
}