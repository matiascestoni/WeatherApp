package weather.application.com.weatherapp.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import weather.application.com.weatherapp.R;
import weather.application.com.weatherapp.domain.Day;
import weather.application.com.weatherapp.domain.ForecastDay;

/**
 * @author Matias Cestoni.
 */
public class FutureDaysViewAdapter extends RecyclerView.Adapter<FutureDaysViewAdapter.FutureDayViewHolder> {

    private final ForecastDay forecastDay;

    public FutureDaysViewAdapter(ForecastDay forecastDay) {
        this.forecastDay = forecastDay;
    }

    @NonNull
    @Override
    public FutureDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_forecast, parent, false);
        return new FutureDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FutureDayViewHolder holder, int position) {
        Day day = forecastDay.getDay();
        holder.maxTemperature.setText(day.getMaxTemperature(holder.context));
        holder.minTemperature.setText(day.getMinTemperature(holder.context));
        holder.visibility.setText(day.getVisibility());
        holder.humidity.setText(day.getHumidity(holder.context));
        String imageUri = "http:" + day.getCondition().getIcon();
        Picasso.with(holder.context).load(imageUri).into(holder.weatherState);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class FutureDayViewHolder extends RecyclerView.ViewHolder {
        TextView maxTemperature;
        TextView minTemperature;
        TextView visibility;
        TextView humidity;
        ImageView weatherState;
        Context context;

        FutureDayViewHolder(View view) {
            super(view);
            context = view.getContext();
            maxTemperature = view.findViewById(R.id.max_temperature);
            minTemperature = view.findViewById(R.id.min_temperature);
            visibility = view.findViewById(R.id.visibility);
            humidity = view.findViewById(R.id.humidity);
            weatherState = view.findViewById(R.id.weather_state);
        }
    }
}
