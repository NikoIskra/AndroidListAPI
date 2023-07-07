package android.tvz.hr.listaiskra.Prosli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.tvz.hr.listaiskra.R;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        Intent intent=getIntent();
        ListItem listItem=intent.getParcelableExtra("List Item");

        int image= listItem.getImage();
        String description=listItem.getDescription();
        String URL=listItem.getWikiURL();

        TextView textView=findViewById(R.id.imageDetailTextView);
        textView.setText(description);

        ImageView imageView=(ImageView) findViewById(R.id.imageDetailImageView);
        imageView.setImageResource(image);

        Button button=findViewById(R.id.imageDetailButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(URL));
                startActivity(intent);
            }
        });

    }
}