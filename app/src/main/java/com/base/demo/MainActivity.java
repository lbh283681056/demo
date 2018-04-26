//package com.base.demo;
//
//import android.content.Context;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//
//
//
//import java.io.IOException;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        findViewById(R.id.sss).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                playMusicQues("file:///sdcard/sss.mp3");
//            }
//        });
//    }
//
//
//    private MediaPlayer mediaPlayer;
//    private int currentPlayVolume;
//    //初始化音乐播放
//    private void initMusic(){
//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//    }
//
//
//    //播放音乐
//    private void playMusicQues(String url){
//        if (mediaPlayer == null){
//            initMusic();
//        }
//        try {
//            mediaPlayer.setOnCompletionListener(onMusicCompleteListener);
//            mediaPlayer.reset();
//            mediaPlayer.setLooping(false);
//            mediaPlayer.setDataSource(this, Uri.parse(url));
//            mediaPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        mediaPlayer.start();
//        recordCurrentVolume();
//    }
//
//    //音乐播放结束监听
//    private MediaPlayer.OnCompletionListener onMusicCompleteListener = new MediaPlayer.OnCompletionListener() {
//        @Override
//        public void onCompletion(MediaPlayer mp) {
//
//        }
//    };
//
//    private void recordCurrentVolume() {
//        AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
//        currentPlayVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//
//    }
//}
