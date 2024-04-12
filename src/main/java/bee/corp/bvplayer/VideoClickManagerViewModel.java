package bee.corp.bvplayer;
import android.annotation.SuppressLint;
import android.app.Application;
import android.view.SurfaceHolder;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
public class VideoClickManagerViewModel extends AndroidViewModel {
    private int lastSelectedIndex;
    private android.widget.VideoView videoView;
    private ArrayList<VideoView> videos;
    private MutableLiveData<Integer[]> layoutToDisplayLiveData;
    public VideoClickManagerViewModel(Application app) {
        super(app);
        this.layoutToDisplayLiveData = new MutableLiveData<>();
    }
    public void init(android.widget.VideoView videoH, ArrayList<VideoView> videos) {
        this.videoView = videoH;
        this.videos = videos;
        registerVideoClickListeners();
    }
    @SuppressLint("ClickableViewAccessibility")
    private void registerVideoClickListeners() {
        for(int i = 0; i < videos.size();i++) {
            final int selectedIndex = i;
            videos.get(i).setOnTouchListener((v, event) -> {
                layoutToDisplayLiveData.setValue(new Integer[]{R.layout.video_layout, selectedIndex});
                lastSelectedIndex = selectedIndex;
                return true;
            });
        }
    }
    public MutableLiveData<Integer[]> getLayoutToDisplay() {
        return layoutToDisplayLiveData;
    }
}
