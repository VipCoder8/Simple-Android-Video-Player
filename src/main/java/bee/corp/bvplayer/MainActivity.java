package bee.corp.bvplayer;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.Manifest;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity{

    VideoLoaderViewModel videoLoader;
    ScrollView videoTabs;
    EditText searchText;
    android.widget.VideoView videoView;
    MediaController videoController;
    ConstraintLayout videoLayout;
    LayoutUtils layoutUtils;
    VideoClickManagerViewModel videoClickManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBeforePermUtils();
        setupViews();
        requestPermissions(Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    private void setupViews() {
        videoTabs = findViewById(R.id.video_tabs);
        videoLayout = (ConstraintLayout) layoutUtils.getLayout(R.layout.video_layout, null);
        videoController = new MediaController(getApplicationContext());
        searchText = findViewById(R.id.search_field);
    }

    private void setupBeforePermUtils() {
        layoutUtils = new LayoutUtils(this);
    }

    private void setupAfterPermUtils() {
        videoLoader = new ViewModelProvider(this).get(VideoLoaderViewModel.class);
        videoLoader.init(videoTabs.getWidth(), getApplicationContext());
        videoLoader.loadMusic(getApplicationContext(), 0);
        videoClickManager = new ViewModelProvider(this).get(VideoClickManagerViewModel.class);
        videoClickManager.init(videoView, videoLoader.videoAdapter.getVideos());
        updateVideos();
        updateSearchText();
    }

    private void updateSearchText() {
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                VideoSearch.Search(s.toString(), videoLoader.videoAdapter.getVideos());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void updateVideos() {
        videoLoader.getVideoListLiveData().observe(this, relativeLayout -> videoTabs.addView(relativeLayout));
        videoClickManager.getLayoutToDisplay().observe(this, integer -> {
            Intent intent = new Intent(this, VideoActivity.class);
            intent.putExtra("Video", videoLoader.videoAdapter.getVideos().get(integer[1]).getVideoPath());
            MainActivity.this.startActivity(intent);
        });
    }

    private void requestPermissions(String perm) {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), perm)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{perm}, 0);
        } else {
            setupAfterPermUtils();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
            setupAfterPermUtils();
        }
    }
}