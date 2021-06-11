package com.example.learngerman;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NounActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_noun);

        final ArrayList<Word> nounList=new ArrayList<Word>();

        nounList.add(new Word("man","der Mann",R.drawable.man,R.raw.noun_man));
        nounList.add(new Word("tree","der Baum",R.drawable.tree,R.raw.noun_tree));
        nounList.add(new Word("river","der Fluss",R.drawable.river,R.raw.noun_river));
        nounList.add(new Word("ball","der Ball" , R.drawable.ball,R.raw.noun_ball));
        nounList.add(new Word("bird","der Vogel" , R.drawable.bird,R.raw.noun_bird));
        nounList.add(new Word("woman","die Frau" , R.drawable.woman,R.raw.noun_woman));
        nounList.add(new Word("duck","die Ente" , R.drawable.duck,R.raw.noun_duck));
        nounList.add(new Word("cloud","die Wolke" , R.drawable.cloud,R.raw.noun_clouds));
        nounList.add(new Word("clock","die Uhr" , R.drawable.clock,R.raw.noun_clock));
        nounList.add(new Word("street","die Straße" , R.drawable.street,R.raw.noun_street));
        nounList.add(new Word("car","das Auto" , R.drawable.car,R.raw.noun_car));
        nounList.add(new Word("bike","das Rad" , R.drawable.bike,R.raw.noun_bike));
        nounList.add(new Word("kids","die Kinder" , R.drawable.kids,R.raw.noun_kids));
        nounList.add(new Word("shoe"," die Schuhe" , R.drawable.shoes,R.raw.noun_shoe));
        nounList.add(new Word("flower","die Blumen" , R.drawable.flowers,R.raw.noun_flower));


        WordAdapter adapter5=new WordAdapter(this,nounList,R.color.category_noun);

        ListView listView = (ListView) findViewById(R.id.list_noun);

        listView.setAdapter(adapter5);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=nounList.get(position);

                //release the media player before playing a new song
                releaseMediaPlayer();

                mediaPlayer= MediaPlayer.create(NounActivity.this,word.getmAudioResource());
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(oncompletionAudio);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    //using memory efficiently
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
