package bee.corp.bvplayer;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    android.widget.VideoView videoView;
    MediaController videoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_layout);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setupVideoViews();
        VideoUtils.PlayVideo(getIntent().getStringExtra("Video"), videoView);
    }

    private void setupVideoViews() {
        videoView = findViewById(R.id.video_view);
        videoController = new MediaController(this);
        videoController.setAnchorView(videoView);
        videoController.setMediaPlayer(videoView);
        videoView.setMediaController(videoController);
    }

}
