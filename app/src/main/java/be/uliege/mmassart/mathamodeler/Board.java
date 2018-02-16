package be.uliege.mmassart.mathamodeler;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mmassart on 01/02/18.
 */

public class Board extends BaseAdapter {
    private Context mContext;
    // references to our images
    private Integer[] mThumbIds;
    private Integer[] selected;
    private int width;
    private int height;

    public Board(Context c, int width, int height) {
        mContext = c;
        int nbElements = width*height;
        this.width=width;
        this.height=height;
        mThumbIds = new Integer[nbElements];
        for(int i=0;i<nbElements;i++)
            mThumbIds[i]= R.drawable.black;
        selected = new Integer[nbElements];
        for (int i=0;i<nbElements;i++)
            selected[i] = 0;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public boolean hasWon() {

        for(int i=0;i<mThumbIds.length;i++)
            if (mThumbIds[i]==R.drawable.black)
                return false;
        return true;
    }

    public void selectItem(int position) {
        Log.d("SELECTITEM",this.mThumbIds[position].toString());
        Toast.makeText(this.mContext, "POSITION SELECT ITEM" + position,
                Toast.LENGTH_SHORT).show();
        selected[position] += 1;
        invertColor(position);
        if (position-1 >=0 && position%width != 0)
            invertColor(position-1);
        if (position+1 < (width*height)-1 && (position+1)%width != 0)
            invertColor(position+1);
        if(position-width >=0)
            invertColor(position-width);
        if (position+width < (width*height)-1)
            invertColor(position+width);

    }

    private void invertColor(int position) {
        if (mThumbIds[position] == R.drawable.black)
            mThumbIds[position]=R.drawable.white;
        else
            mThumbIds[position]=R.drawable.black;
    }

    // create a new ImageView for each item referenced by the Adapter
   /* public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            //imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);

        return imageView;
    }
    */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_simple, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(selected[position].toString());
            imageView.setImageResource(mThumbIds[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }


}