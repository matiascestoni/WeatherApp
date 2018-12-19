package weather.application.com.weatherapp.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Matias Cestoni.
 */
public class ConditionWeatherUtil {

    public static Bitmap getImageFromAssetsFile(Context mContext, String fileName) {
        Bitmap image = null;
        AssetManager am = mContext.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static String getImageWithForecast(int code, boolean isDay) {
        switch (code) {
            case 1000:
                return isDay ? "01d.jpg" : "01n.jpg";
            case 1003:
                return isDay ? "02d.jpg" : "02n.jpg";
            case 1030:
            case 1006:
                return isDay ? "03d.jpg" : "03n.jpg";
            case 1098:
            case 1240:
            case 1189:
            case 1243:
                return isDay ? "05d.jpg" : "05n.jpg";
            case 1009:
                return isDay ? "04d.jpg" : "04n.jpg";
            case 1183:
            case 1086:
            case 1089:
            case 1201:
                return isDay ? "09d.jpg" : "09n.jpg";
            case 1095:
            case 1046:
                return isDay ? "10d.jpg" : "10n.jpg";
            case 1087:
            case 1273:
            case 1276:
            case 1279:
            case 1282:
                return isDay ? "11d.jpg" : "11n.jpg";
            case 1180:
            case 1066:
            case 1072:
            case 1114:
            case 1147:
            case 1150:
            case 1153:
            case 1168:
            case 1271:
            case 1210:
            case 1213:
            case 1216:
            case 1219:
            case 1222:
            case 1225:
            case 1255:
            case 1258:
                return isDay ? "13d.jpg" : "13n.jpg";
            case 1135:
                return isDay ? "50d.jpg" : "50n.jpg";
            case 1117:
                return "14d.jpg";
            case 1261:
            case 1264:
                return "15d.jpg";
            case 1237:
                return "16d.jpg";
            case 1204:
            case 1069:
            case 1207:
            case 1249:
            case 1252:
                return "17d.jpg";
            case 1063:
            case 1092:
                return isDay ? "18d.jpg" : "18n.jpg";
            default:
                return "unknnown.png";
        }
    }
}
