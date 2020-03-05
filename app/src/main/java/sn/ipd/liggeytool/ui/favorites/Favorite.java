package sn.ipd.liggeytool.ui.favorites;

import java.util.ArrayList;

public class Favorite {

    private int mID;
    private String mName;

    public Favorite(int id, String name) {

        mID=id;
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public int getID() {
        return mID;
    }

    private static int lastFavoriteId = 0;

    public static ArrayList<Favorite> createFavoritesList(int numFavorites, String nameFavorite) {
        ArrayList<Favorite> favorites = new ArrayList<Favorite>();

        for (int i = 1; i <= numFavorites; i++) {
            favorites.add(new Favorite(i,"Favoris " + ++lastFavoriteId));
        }

        return favorites;
    }
}