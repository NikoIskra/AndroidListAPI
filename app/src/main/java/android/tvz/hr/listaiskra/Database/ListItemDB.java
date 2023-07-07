package android.tvz.hr.listaiskra.Database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ListItemDB implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name="item_image")
    public int itemImage;

    @ColumnInfo(name = "text")
    public String text;

    @ColumnInfo(name = "description")
    public String description;

    public ListItemDB(int itemImage, String text, String description) {
        this.itemImage = itemImage;
        this.text = text;
        this.description = description;
    }

    protected ListItemDB(Parcel in) {
        uid = in.readInt();
        itemImage = in.readInt();
        text = in.readString();
        description = in.readString();
    }

    public static final Creator<ListItemDB> CREATOR = new Creator<ListItemDB>() {
        @Override
        public ListItemDB createFromParcel(Parcel in) {
            return new ListItemDB(in);
        }

        @Override
        public ListItemDB[] newArray(int size) {
            return new ListItemDB[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(uid);
        parcel.writeInt(itemImage);
        parcel.writeString(text);
        parcel.writeString(description);
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
