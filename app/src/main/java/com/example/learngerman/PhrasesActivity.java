package com.example.learngerman;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_phrases);

        final ArrayList<Word> phraseList=new ArrayList<Word>();

        phraseList.add(new Word("My name is...","Mein Name ist...",R.raw.phrase_my_name_is));
        phraseList.add(new Word("I come from Germany","Ich komme aus Deutschland",R.raw.phrase_icomefromgermany));
        phraseList.add(new Word("Good evening. My name is Anna","Guten Abend. Ich heiße Anna",R.raw.phrase_goodmorningiamanna));
        phraseList.add(new Word("I come from...","ich bin aus...",R.raw.phrase_i_come_from));
        phraseList.add(new Word("I speak german","ich spreche Deutsch",R.raw.phrase_ispeakgerman));
        phraseList.add(new Word("What is that?","was ist das",R.raw.phrase_what_is_that));
        phraseList.add(new Word("Is this food?","ist dieses Essen?",R.raw.phrase_isthis_food));
        phraseList.add(new Word("How old are you?","wie alt bist du?",R.raw.phrase_how_old_are_you));
        phraseList.add(new Word("I am hungry","Ich habe Hunger",R.raw.phrase_i_am_hungry));
        phraseList.add(new Word("Do you drink beer?","Du trinkst Bier",R.raw.phrase_doyou_drink_beer));

        WordAdapter adapter=new WordAdapter(this,phraseList,R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list_phrase);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=phraseList.get(position);

                //release the media player before playing a new song
                releaseMediaPlayer();

                mediaPlayer= MediaPlayer.create(PhrasesActivity.this,word.getmAudioResource());
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
