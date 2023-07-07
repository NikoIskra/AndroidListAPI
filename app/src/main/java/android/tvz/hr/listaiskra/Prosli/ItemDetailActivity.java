package android.tvz.hr.listaiskra.Prosli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.tvz.hr.listaiskra.R;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetailActivity extends AppCompatActivity {

    BroadcastReceiver receiver=new BroadcastReceiver();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        IntentFilter filter=new IntentFilter("hr.android.broadcast.test");
        registerReceiver(receiver, filter);

        Intent intent=getIntent();
        ListItem listItem=intent.getParcelableExtra("List Item");

        int image= listItem.getImage();
        String text=listItem.getText();

        ImageView imageView=(ImageView) findViewById(R.id.image_detail_activity);
        imageView.setImageResource(image);

        Button button=findViewById(R.id.buttonBroadcast);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog();
                dialog.show(getSupportFragmentManager(), "dialog");
            }
        });

        TextView textView=findViewById(R.id.text_detail_activity);
        textView.setText(text);
        Animation animation= AnimationUtils.loadAnimation(this, R.anim.animacjiaprimjer);
        textView.startAnimation(animation);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.startAnimation(animation);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ItemDetailActivity.this, ImageDetailActivity.class);
                intent.putExtra("List Item", listItem);
                startActivity(intent);
            }
        });
    }
}