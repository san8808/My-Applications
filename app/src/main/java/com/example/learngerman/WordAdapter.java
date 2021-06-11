package com.example.learngerman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    int mcolorResourseId;


    public WordAdapter(Context context, ArrayList<Word> words,int colorResourseId) {

        super(context, 0, words);
        mcolorResourseId=colorResourseId;
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        View listitemView= convertView;
        if(listitemView==null){
            listitemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Word currentWord=getItem(position);

        TextView miwok_text_view=(TextView) listitemView.findViewById(R.id.miwok_text_view);

        miwok_text_view.setText(currentWord.getMiwokTranslation());

        TextView default_text_view=(TextView) listitemView.findViewById(R.id.default_text_view);

        default_text_view.setText(currentWord.getDefaultTranslation());


        //set the theme colur for the list item
        View textContainer=listitemView.findViewById(R.id.text_container);
        //set the desired colour with the color id resource
        int color= ContextCompat.getColor(getContext(),mcolorResourseId);
        // set the background color if the text container view
        textContainer.setBackgroundColor(color);


        ImageView imageView=(ImageView) listitemView.findViewById(R.id.image);


        if(currentWord.hasImage()){
            imageView.setImageResource(currentWord.getImageResourceId());

            imageView.setVisibility(View.VISIBLE);
        }
        else{
            imageView.setVisibility(View.GONE);

        }


        return listitemView;
    }
}
