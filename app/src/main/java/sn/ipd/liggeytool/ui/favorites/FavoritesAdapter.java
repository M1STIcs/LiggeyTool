package sn.ipd.liggeytool.ui.favorites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sn.ipd.liggeytool.R;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class FavoritesAdapter extends
        RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView idTextView;
        public TextView titleTextView;
        private Context context;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(Context context, View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            this.context = context;
           idTextView = (TextView) itemView.findViewById(R.id.fav_id);
            titleTextView = (TextView) itemView.findViewById(R.id.fav_title);
            itemView.setOnClickListener(this);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {

                int position = getAdapterPosition(); // gets item position
                if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                    Favorite fv = mFavorites.get(position);
                    // We can access the data within the views
                    Toast.makeText(context, titleTextView.getText(), Toast.LENGTH_SHORT).show();

                }
        }
    }
    private List<Favorite> mFavorites;

    // Pass in the contact array into the constructor
    public FavoritesAdapter(List<Favorite> favorite) {
        mFavorites = favorite;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View favoriteView = inflater.inflate(R.layout.fragment_favorites, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(context,favoriteView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(FavoritesAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Favorite fav = mFavorites.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.titleTextView;
        textView.setText(fav.getName());

       viewHolder.idTextView.setText(String.valueOf(fav.getID()));

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mFavorites.size();
    }
}