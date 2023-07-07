package android.tvz.hr.listaiskra.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ListItemDAO {

    @Insert
    void insertAll(ListItemDB... itemDBS);

    @Query("SELECT * FROM listitemdb")
    List<ListItemDB> getAllItems();
}
