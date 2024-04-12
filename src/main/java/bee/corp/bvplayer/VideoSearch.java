package bee.corp.bvplayer;

import android.view.View;

import java.util.ArrayList;

public class VideoSearch {
    public static void Search(String toSearch, ArrayList<VideoView> videos) {
        float y = videos.get(0).getY();
        int height = videos.get(0).getHeight();
        if(toSearch.trim().length()>0) {
            for(int i = 0; i < videos.size();i++) {
                if(videos.get(i).getTitle().toLowerCase().contains(toSearch.toLowerCase())) {
                    videos.get(i).setY(y);
                    y+=height;
                    videos.get(i).setVisibility(View.VISIBLE);
                } else {
                    videos.get(i).setVisibility(View.INVISIBLE);
                }
            }
        } else {
            for(int i = 0; i < videos.size();i++) {
                videos.get(i).setY(y);
                y+=height;
                videos.get(i).setVisibility(View.VISIBLE);
            }
        }
    }
}
