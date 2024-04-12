package bee.corp.bvplayer;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.RelativeLayout;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.File;

public class VideoLoaderViewModel extends AndroidViewModel {

    private MutableLiveData<RelativeLayout> videoListLiveData = new MutableLiveData<>();

    private int tabsLayoutHeight = 0;
    private RelativeLayout tabsLayout;
    public VideoAdapter videoAdapter;

    public VideoLoaderViewModel(Application a) {
        super(a);
        videoAdapter = new VideoAdapter();
    }

    public void init(int width, Context c) {
        tabsLayout = new RelativeLayout(c);
        tabsLayout.setLayoutParams(new RelativeLayout.LayoutParams(width, tabsLayoutHeight));
    }

    @SuppressLint({"Range", "SetTextI18n"})
    public void loadMusic(Context c, int startY) {
        ContentResolver contentResolver = c.getContentResolver();
        String[] projection = {
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.DURATION};
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, projection,null,null,null);
        int titleIndex = cursor.getColumnIndex(projection[1]);
        int durationIndex = cursor.getColumnIndex(projection[2]);
        while(cursor.moveToNext()) {
            String filePath = VideoUtils.getVideoFilePath(c, cursor.getString(titleIndex));
            if(new File(filePath).exists()) {
                VideoView videoTab = new VideoView(c);
                videoTab.setTitle(cursor.getString(titleIndex));
                videoTab.setImagePreview(VideoUtils.getVideoPreviewImage(c, filePath));
                videoTab.setDuration(MathUtils.ConvertToHour(Integer.parseInt(cursor.getString(durationIndex))) + ":"
                + MathUtils.ConvertToMinute(Integer.parseInt(cursor.getString(durationIndex))) + ":"
                + MathUtils.ConvertToSeconds(Integer.parseInt(cursor.getString(durationIndex))));
                videoTab.setVideoPath(filePath);
                videoAdapter.addVideo(videoTab);
                tabsLayout.addView(videoTab);
                videoTab.setY(startY);
                startY += 140;
                tabsLayoutHeight += 150;
            }
        }
        tabsLayout.setLayoutParams(new RelativeLayout.LayoutParams(900, tabsLayoutHeight));
        videoListLiveData.setValue(tabsLayout);
    }
    public LiveData<RelativeLayout> getVideoListLiveData() {
        return videoListLiveData;
    }
}