package bee.corp.bvplayer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class VideoView extends ConstraintLayout {
    private final int VIDEO_PREVIEW_CHILD_INDEX = 0;
    private final int VIDEO_TITLE_CHILD_INDEX = 1;
    private final int VIDEO_DURATION_CHILD_INDEX = 2;
    public VideoView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.video_tab, this, true);
    }

    @SuppressLint("SetTextI18n")
    public void setTitle(String title) {
        ((TextView)((ConstraintLayout)getChildAt(0)).getChildAt(VIDEO_TITLE_CHILD_INDEX)).setText(title);
        if(((TextView)((ConstraintLayout)getChildAt(0)).getChildAt(VIDEO_TITLE_CHILD_INDEX)).getText().length() > 26) {
            ((TextView)((ConstraintLayout)getChildAt(0)).getChildAt(VIDEO_TITLE_CHILD_INDEX)).setText(
                    ((TextView)((ConstraintLayout)getChildAt(0)).getChildAt(VIDEO_TITLE_CHILD_INDEX)).
                            getText().
                            toString().
                            substring(0, 27) + "...");
        }
    }

    public void setVideoPath(String filePath) {
        ((ConstraintLayout)getChildAt(0)).getChildAt(VIDEO_TITLE_CHILD_INDEX).setTag(filePath);
    }

    public void setImagePreview(Bitmap preview) {
        ((ImageView)((ConstraintLayout)getChildAt(0)).getChildAt(VIDEO_PREVIEW_CHILD_INDEX)).setImageBitmap(preview);
    }

    public void setDuration(String duration) {
        ((TextView)((ConstraintLayout)getChildAt(0)).getChildAt(VIDEO_DURATION_CHILD_INDEX)).setText(duration);
    }

    public String getTitle() {
        return ((TextView)((ConstraintLayout)getChildAt(0)).getChildAt(VIDEO_TITLE_CHILD_INDEX)).getText().toString();
    }

    public String getVideoPath() {
        return (String) ((ConstraintLayout)getChildAt(0)).getChildAt(VIDEO_TITLE_CHILD_INDEX).getTag();
    }

}
