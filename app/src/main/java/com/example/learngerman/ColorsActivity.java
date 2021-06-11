package com.example.learngerman;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;



import java.util.ArrayList;
//import android.support.v7.app.AppCompatActivity;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener  oncompletionAudio=  new MediaPlayer.OnCompletionListener()

    {
        @Override
        public void onCompletion (MediaPlayer mediaPlayer){
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        final ArrayList<Word> colorList=new ArrayList<Word>();

        colorList.add(new Word("red","rot",R.drawable.color_red,R.raw.germancolors_red));
        colorList.add(new Word("green","grün",R.drawable.color_green,R.raw.germancolors_green));
        colorList.add(new Word("brown","braun",R.drawable.color_brown,R.raw.germancolors_brown));
        colorList.add(new Word("blue","blau",R.drawable.color_gray,R.raw.germancolors_blue));
        colorList.add(new Word("black","schwarz",R.drawable.color_black,R.raw.germancolors_black));
        colorList.add(new Word("white","weiß",R.drawable.color_white,R.raw.germancolours_white));
        colorList.add(new Word("yellow","gelb",R.drawable.color_mustard_yellow,R.raw.germancolours_yellow));

        WordAdapter adapter2=new WordAdapter(this,colorList,R.color.category_colors);

        ListView listView2 = (ListView) findViewById(R.id.list_color);

        listView2.setAdapter(adapter2);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=colorList.get(position);

                //release the media player before playing a new song
                releaseMediaPlayer();

                mediaPlayer= MediaPlayer.create(ColorsActivity.this,word.getmAudioResource());
                mediaPlayer.start();
            }
        });


    }


    @Override
    protected void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();
        }

        // Set the media player back to null. For our code, we've decided that
        // setting the media player to null is an easy way to tell that the media player
        // is not configured to play an audio file at the moment.
        mediaPlayer = null;

    }
}

