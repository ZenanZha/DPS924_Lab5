package com.example.zzha1.lab5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zzha1 on 6/9/2017.
 */

public class MyCustomAdapter extends BaseAdapter {
    String[] descriptions;
    int[] icons;
    Context context;
    String[] links;

    public MyCustomAdapter(MainActivity c, String[] d, int[] i, String[] l){
        context = c;
        descriptions = d;
        icons = i;
        links = l;
    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.favesite, null);
        TextView textView = (TextView) row.findViewById(R.id.textView);
        textView.setText(descriptions[position]);
        ImageView imageView = (ImageView) row.findViewById(R.id.imageView);
        imageView.setImageResource(icons[position]);
        row.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                URL url = new URL(links[position]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    readStream(in);
                } finally {
                    urlConnection.disconnect();
                }
            }
        });
        return row;
    }
}
