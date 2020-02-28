package sn.ipd.liggeytool;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class User implements Parcelable {
    private final long id;
    private final String name;
    private final String type;
  //  private final double price;

    public User(JSONObject jObject) {
        this.id = jObject.optLong("user_id");
        this.name = jObject.optString("user_firstname");
        this.type = jObject.optString("user_lastname");
       // this.price = jObject.optDouble("price");
    }

    protected User(Parcel in) {
        id = in.readLong();
        name = in.readString();
        type = in.readString();
    //    price = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(type);
      //  dest.writeDouble(price);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public long id() { return id; }

    public String name() { return name; }

    public String type() { return type; }

   // public double price() { return price; }

    private List<User> parse(final String json) {
        try {
            final List<User> users = new ArrayList<>();
            final JSONArray jUserArray = new JSONArray(json);
            for (int i = 0; i < jUserArray.length(); i++) {
                users.add(new User(jUserArray.optJSONObject(i)));
            }
            return users;
        } catch (JSONException e) {
            Log.v("", "[JSONException] e : " + e.getMessage());
        }
        return null;
    }
}