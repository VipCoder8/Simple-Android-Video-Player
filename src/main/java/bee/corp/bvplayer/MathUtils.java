package bee.corp.bvplayer;

public class MathUtils {
    public static int ConvertToHour(int milliseconds) {
        return milliseconds/(1000 * 60 * 60);
    }
    public static int ConvertToMinute(int milliseconds) {
        return milliseconds/(1000 * 60);
    }
    public static int ConvertToSeconds(int milliseconds) {
        return milliseconds/1000;
    }
}
