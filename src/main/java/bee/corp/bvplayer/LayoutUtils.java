package bee.corp.bvplayer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LayoutUtils {
    LayoutInflater layoutInflater;
    public LayoutUtils(Activity a) {
        layoutInflater = a.getLayoutInflater();
    }
    public View getLayout(int layout, ViewGroup mainLayout) {
        return layoutInflater.inflate(layout, mainLayout);
    }
}
