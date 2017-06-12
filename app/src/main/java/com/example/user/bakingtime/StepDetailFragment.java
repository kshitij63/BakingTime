package com.example.user.bakingtime;

import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * Created by user on 6/12/2017.
 */

public class StepDetailFragment extends Fragment implements ExoPlayer.EventListener {
    SimpleExoPlayerView simpleExoPlayerView;
    MediaSessionCompat compat;
    PlaybackStateCompat.Builder playbackStateCompat;
    int savedplayback;
    SimpleExoPlayer simpleExoPlayer;
    TextView textView,no_view;
    int position;
    String videoURL,deatil_description;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=LayoutInflater.from(getActivity()).inflate(R.layout.step_detail_fragment,container,false);

//        Toast.makeText(getActivity(), "trtrtr", Toast.LENGTH_SHORT).show();
        videoURL=getArguments().getString("videoURL");
        deatil_description=getArguments().getString("description");

       // Toast.makeText(getActivity(),videoURL +" " +deatil_description,Toast.LENGTH_SHORT).show();
        textView=(TextView) v.findViewById(R.id.detail);
        no_view=(TextView) v.findViewById(R.id.no_video);
        textView.setText(deatil_description);
        if(videoURL!=null){
            Uri videouri=Uri.parse(videoURL);

            no_view.setVisibility(View.INVISIBLE);
            simpleExoPlayerView=(SimpleExoPlayerView) v.findViewById(R.id.exoplayer_view);
            TrackSelector trackSelector=new DefaultTrackSelector();
            LoadControl loadControl=new DefaultLoadControl();
compat=new MediaSessionCompat(getActivity(),"mediasession_bake");
            playbackStateCompat= new PlaybackStateCompat.Builder().setActions(PlaybackStateCompat.ACTION_PLAY|PlaybackStateCompat.ACTION_PLAY_PAUSE|PlaybackStateCompat.ACTION_FAST_FORWARD|PlaybackStateCompat.ACTION_SEEK_TO|PlaybackStateCompat.STATE_STOPPED|PlaybackStateCompat.ACTION_REWIND|PlaybackStateCompat.ACTION_SKIP_TO_NEXT|PlaybackStateCompat.STATE_BUFFERING);
            compat.setPlaybackState(playbackStateCompat.build());
            compat.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS|MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
            compat.setMediaButtonReceiver(null);
            compat.setCallback(new mySessionCallback());
            compat.setActive(true);
            simpleExoPlayer= ExoPlayerFactory.newSimpleInstance(getActivity(),trackSelector,loadControl);

            simpleExoPlayerView.setPlayer(simpleExoPlayer);

            String useragent= Util.getUserAgent(getActivity(),"BakingTime");
            MediaSource mediaSource=new ExtractorMediaSource(videouri,new DefaultDataSourceFactory(getActivity(),useragent),new DefaultExtractorsFactory(),null,null);

            simpleExoPlayer.prepare(mediaSource);

            simpleExoPlayer.addListener(this);
            simpleExoPlayer.setPlayWhenReady(true);

        }
        else {
            no_view.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(),"no video",Toast.LENGTH_SHORT).show();
        }

        return v;
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        savedplayback=playbackState;
        if((playbackState == ExoPlayer.STATE_READY) && playWhenReady){
            playbackStateCompat.setState(PlaybackStateCompat.STATE_PLAYING,
                    simpleExoPlayer.getCurrentPosition(), 1f);
        } else if((playbackState == ExoPlayer.STATE_READY)){
            playbackStateCompat.setState(PlaybackStateCompat.STATE_PAUSED,
                    simpleExoPlayer.getCurrentPosition(), 1f);
        }
        if(playbackState== PlaybackState.STATE_PLAYING){
            //Toast.makeText(getActivity(),"playing",Toast.LENGTH_SHORT).show();
        }

        if(playbackState==PlaybackState.STATE_BUFFERING){
            //Toast.makeText(getActivity(),"buffering",Toast.LENGTH_SHORT).show();
        }

        if(playbackState==PlaybackState.STATE_CONNECTING){
            //Toast.makeText(getActivity(),"connecting",Toast.LENGTH_SHORT).show();
        }
        if(playbackState==PlaybackState.STATE_STOPPED){
            //Toast.makeText(getActivity(),"stopped",Toast.LENGTH_SHORT).show();

        }
        if(playbackState==PlaybackState.STATE_ERROR){
            //Toast.makeText(getActivity(),"error",Toast.LENGTH_SHORT).show();
        }

        compat.setPlaybackState(playbackStateCompat.build());


    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    private void stop_player(){
        simpleExoPlayer.stop();
        simpleExoPlayer.release();
        simpleExoPlayer=null;
    }

    //public void gettingPosition(int position){
      //  this.position=position;
    //}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    stop_player();
        compat.setActive(false);
    }


    private class mySessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            simpleExoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            simpleExoPlayer.setPlayWhenReady(true);

        }

        @Override
        public void onSeekTo(long pos) {
            simpleExoPlayer.seekTo(pos);

        }

        @Override
        public void onRewind() {
            simpleExoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onFastForward() {
            super.onFastForward();
        }

        @Override
        public void onStop() {
            super.onStop();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("svaedvideotate",savedplayback);
    }
}
