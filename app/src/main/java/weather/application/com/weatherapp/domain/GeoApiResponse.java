package weather.application.com.weatherapp.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Matias Cestoni.
 */
public class GeoApiResponse {

    @Nullable
    private Status status;

    @Nullable
    public Status getStatus() {
        return status;
    }

    public static class Status {

        private String message;
        private int value;

        public Status() {
        }

        public String getMessage() {
            return message;
        }

        public int getValue() {
            return value;
        }

        @NonNull
        @Override
        public String toString() {
            return message + ' ' + ", ErrorCode:" + value;
        }
    }
}
