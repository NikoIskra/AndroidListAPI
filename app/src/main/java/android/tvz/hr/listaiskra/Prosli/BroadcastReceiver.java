package android.tvz.hr.listaiskra.Prosli;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastReceiver extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("hr.android.broadcast.test".equals(intent.getAction())) {
            Toast.makeText(context, "Broadcast primljen", Toast.LENGTH_LONG).show();
        }
    }
}
