package com.example.learngerman;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
//import android.support.v7.app.AppCompatActivity;

public class FamilyActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_family);

        final ArrayList<Word> familyList=new ArrayList<Word>();

        familyList.add(new Word("father","Vater",R.drawable.family_father,R.raw.germanfamilty_father));
        familyList.add(new Word("mother","Mutter",R.drawable.family_mother,R.raw.germanfamilty_mother));
        familyList.add(new Word("son","Sohn",R.drawable.family_son,R.raw.germanfamily_son));
        familyList.add(new Word("daughter","Tochter",R.drawable.family_daughter,R.raw.germanfamilty_daughter));
        familyList.add(new Word("brother","Bruder",R.drawable.family_older_brother,R.raw.germanfamilty_brother));
        familyList.add(new Word("younger brother","jüngere Bruder",R.drawable.family_younger_brother,R.raw.germanfamilty_youngerbrother));
        familyList.add(new Word("sister","Schwester",R.drawable.family_older_sister,R.raw.germanfamily_sister));
        familyList.add(new Word("grandmother","Großmutter",R.drawable.family_grandmother,R.raw.germanfamily_grandmother));
        familyList.add(new Word("grandfather","Großvater",R.drawable.family_grandfather,R.raw.germanfamily_grandfather));

        WordAdapter adapter=new WordAdapter(this,familyList,R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list_family);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=familyList.get(position);

                //release the media player before playing a new song
                releaseMediaPlayer();

                mediaPlayer= MediaPlayer.create(FamilyActivity.this,word.getmAudioResource());
                mediaPlayer.start();
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

