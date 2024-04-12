package bee.corp.bvplayer;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class VideoUtils {
    private static MediaPlayer videoPlayer;

    @SuppressLint("Range")
    public static String getVideoFilePath(Context context, String videoTitle) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {
                MediaStore.Video.Media.DATA
        };

        String selection = MediaStore.Video.Media.TITLE + "=?";
        String[] selectionArgs = new String[]{videoTitle};

        Cursor cursor = contentResolver.query(
                uri,
                projection,
                selection,
                selectionArgs,
                null
        );

        String filePath = null;
        if (cursor != null && cursor.moveToFirst()) {
            filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
            cursor.close();
        }

        return filePath;
    }
    public static Bitmap getVideoPreviewImage(Context context, String videoPath) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(context, Uri.parse(videoPath));
            return retriever.getFrameAtTime();
        } catch (Exception e) {e.printStackTrace();return null;
        } finally {
            try {
                retriever.release();
            } catch (IOException e) {throw new RuntimeException(e);}
        }
    }

    public static void PlayVideo(String videoPath, android.widget.VideoView sh) {
        sh.setVideoURI(Uri.parse(videoPath));
        sh.start();
    }

}
