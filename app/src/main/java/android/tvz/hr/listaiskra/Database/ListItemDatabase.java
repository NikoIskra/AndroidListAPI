package android.tvz.hr.listaiskra.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ListItemDB.class}, version = 1)
public abstract class ListItemDatabase extends RoomDatabase {
    public abstract ListItemDAO listItemDAO();
}
