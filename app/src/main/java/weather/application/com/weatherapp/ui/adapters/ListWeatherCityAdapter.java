package weather.application.com.weatherapp.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import weather.application.com.weatherapp.R;
import weather.application.com.weatherapp.domain.CityWeather;
import weather.application.com.weatherapp.ui.listeners.CityWeatherListener;
import weather.application.com.weatherapp.util.ConditionWeatherUtil;

/**
 * @author Matias Cestoni.
 */
public class ListWeatherCityAdapter extends RecyclerView.Adapter<ListWeatherCityAdapter.CityWeatherViewHolder> {

    private List<CityWeather> cityWeatherList;
    private CityWeatherListener listener;

    public ListWeatherCityAdapter(List<CityWeather> cityWeatherList, CityWeatherListener listener) {
        this.cityWeatherList = cityWeatherList;
        this.listener = listener;
    }

    public void setCityWeatherList(List<CityWeather> cities) {
        cityWeatherList.clear();
        cityWeatherList.addAll(cityWeatherList.size(), cities);
        notifyDataSetChanged();
    }

    public void removeCity(int location) {
        listener.onCityWeatherRemoved(cityWeatherList.get(location));
        cityWeatherList.remove(location);
        notifyItemRemoved(location);
    }

    public void clear() {
        cityWeatherList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CityWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_city_item, parent, false);
        return new CityWeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CityWeatherViewHolder holder, int position) {
        CityWeather cityWeather = cityWeatherList.get(position);

        holder.cityName.setText(cityWeather.getCity().getName());
        holder.temperature.setText(cityWeather.getTemperature(holder.context));
        holder.wind.setText(cityWeather.getWindText(holder.context));
        holder.humidity.setText(cityWeather.getHumidity(holder.context));
        holder.imageView.setImageBitmap(ConditionWeatherUtil.getImageFromAssetsFile(holder.context, cityWeather.getImageIcon()));
        holder.cardView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCityWeatherSelected(cityWeatherList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityWeatherList != null ? cityWeatherList.size() : 0;
    }

    class CityWeatherViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView cityName;
        TextView temperature;
        TextView wind;
        TextView humidity;
        ImageView imageView;
        Context context;

        CityWeatherViewHolder(View view) {
            super(view);
            context = view.getContext();
            cardView = view.findViewById(R.id.city_weather_card_view);
            cityName = view.findViewById(R.id.city_name);
            temperature = view.findViewById(R.id.temperature);
            wind = view.findViewById(R.id.wind);
            humidity = view.findViewById(R.id.humidity);
            imageView = view.findViewById(R.id.state_image);
        }
    }
}
