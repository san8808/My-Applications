package com.example.learngerman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
//import android.support.v7.app.AppCompatActivity;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // pause playback
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // resume playback
                        mediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                    // Stop playback

                }
            };



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
        setContentView(R.layout.activity_numbers);

        mAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> numberList=new ArrayList<Word>();
        numberList.add(new Word("one","eins",R.drawable.number_one, R.raw.german_number_one));
        numberList.add(new Word("two","zwei",R.drawable.number_two,R.raw.german_number_two));
        numberList.add(new Word("three","drei",R.drawable.number_three,R.raw.german_number_three));
        numberList.add(new Word("four","vier",R.drawable.number_four,R.raw.german_number_four));
        numberList.add(new Word("five"," fünf",R.drawable.number_five,R.raw.german_number_five));
        numberList.add(new Word("six"," sechs",R.drawable.number_six,R.raw.german_number_six));
        numberList.add(new Word("seven","sieben",R.drawable.number_seven,R.raw.german_number_seven));
        numberList.add(new Word("eight","acht",R.drawable.number_eight,R.raw.german_number_eight));
        numberList.add(new Word("nine"," neun",R.drawable.number_nine,R.raw.german_number_nine));
        numberList.add(new Word("ten"," zehn",R.drawable.number_ten,R.raw.german_number_ten));

        WordAdapter adapter=new WordAdapter(this,numberList,R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = numberList.get(position);

                //release the media player before playing a new song
                releaseMediaPlayer();

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(onAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start
                    mAudioManager.registerMediaButtonEventReceiver(new ComponentName(getPackageName(), MediaPlayer.OnCompletionListener.class.getName()));


                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmAudioResource());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(oncompletionAudio);

                }
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
        mAudioManager.abandonAudioFocus(onAudioFocusChangeListener);
    }
}


