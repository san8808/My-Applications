package com.example.learngerman;

public class Word {

    private String defaultTranslation;
    private String miwokTranslation;
    private int imageResourceId=NO_IMAGE;
    private int mAudioResource;

    private static final int NO_IMAGE=-1;



    public int getImageResourceId() {
        return imageResourceId;
    }

    public Word(String defaultTranslation, String miwokTranslation,int imageResourceId,int AudioResource) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.imageResourceId=imageResourceId;
        this.mAudioResource=AudioResource;


    }
    public Word(String defaultTranslation, String miwokTranslation,int audioResource) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.mAudioResource=audioResource;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public int getmAudioResource() {
        return mAudioResource;
    }

    public boolean hasImage(){
        if(imageResourceId!= NO_IMAGE){
            return true;
        }
        else{
            return false;
        }

    }

}
