package android.tvz.hr.listaiskra.Prosli;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ListItem implements Parcelable {

    private int image;
    private String text;

    private String description;

    private String wikiURL;

    public ListItem(int image, String text, String description, String wikiURL) {
        this.image = image;
        this.text = text;
        this.description = description;
        this.wikiURL = wikiURL;
    }

    protected ListItem(Parcel in) {
        image = in.readInt();
        text = in.readString();
        description = in.readString();
        wikiURL = in.readString();
    }

    public static final Creator<ListItem> CREATOR = new Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel in) {
            return new ListItem(in);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    public String getWikiURL() {
        return wikiURL;
    }

    public void setWikiURL(String wikiURL) {
        this.wikiURL = wikiURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(image);
        parcel.writeString(text);
        parcel.writeString(description);
        parcel.writeString(wikiURL);
    }
}
