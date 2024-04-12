package bee.corp.bvplayer;

import androidx.databinding.BaseObservable;

import java.util.ArrayList;

public class VideoAdapter extends BaseObservable {
    ArrayList<VideoView> videos;

    public VideoAdapter() {
        videos = new ArrayList<>();
    }

    public void addVideo(VideoView video) {
        videos.add(video);
    }

    public void removeVideo(VideoView video) {
        videos.remove(video);
    }

    public ArrayList<VideoView> getVideos() {
        return videos;
    }
}
