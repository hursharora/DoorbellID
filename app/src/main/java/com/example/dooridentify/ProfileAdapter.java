package com.example.dooridentify;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dooridentify.model.Profile;

import java.io.InputStream;
import java.util.List;

public class ProfileAdapter extends ArrayAdapter<Profile> {

    Context context;
    List<Profile> profilesList;

    public ProfileAdapter(@NonNull Context context, @NonNull List<Profile> objects) {
        super(context, 0, objects);
        this.context = context;
        this.profilesList = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);

        Profile curProfile = profilesList.get(position);

        ImageView profileImage = (ImageView) listItem.findViewById(R.id.profileImage);
        new DownloadImageTask(profileImage).execute(curProfile.getImgURL());


        TextView personName = (TextView) listItem.findViewById(R.id.personName);
        personName.setText(curProfile.getName());


        TextView personDesc = (TextView) listItem.findViewById(R.id.personDescription);
        personDesc.setText(curProfile.getDescription());


        return listItem;
    }



    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
