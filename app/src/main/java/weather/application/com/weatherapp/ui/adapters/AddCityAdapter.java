package weather.application.com.weatherapp.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import weather.application.com.weatherapp.R;
import weather.application.com.weatherapp.domain.GeoCity;
import weather.application.com.weatherapp.ui.listeners.CityItemListener;

/**
 * @author Matias Cestoni.
 */
public class AddCityAdapter extends RecyclerView.Adapter<AddCityAdapter.AddCityViewHolder> {

    private final List<GeoCity> cities;
    private CityItemListener listener;

    public AddCityAdapter(List<GeoCity> cities, CityItemListener listener) {
        this.cities = cities;
        this.listener = listener;
    }

    public void addCities(List<GeoCity> cities) {
        clear();
        this.cities.addAll(this.cities.size(), cities);
        notifyDataSetChanged();
    }

    public void clear() {
        cities.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AddCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_city_adapter_item, parent, false);
        return new AddCityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddCityViewHolder holder, int position) {
        holder.container.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCitySelected(cities.get(holder.getAdapterPosition()));
            }
        });

        GeoCity geoCity = cities.get(position);
        holder.cityName.setText(geoCity.toString());
        String flagUri = holder.context.getString(R.string.flag_base_url, geoCity.getCountryCode().toLowerCase());
        Picasso.with(holder.context).load(flagUri).fit().centerCrop()
                .placeholder(R.drawable.flag_place_holder)
                .error(R.drawable.flag_place_holder)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return cities != null ? cities.size() : 0;
    }

    class AddCityViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container;
        TextView cityName;
        ImageView imageView;
        Context context;

        AddCityViewHolder(View view) {
            super(view);
            context = view.getContext();
            container = view.findViewById(R.id.city_item_layout);
            cityName = view.findViewById(R.id.city_name);
            imageView = view.findViewById(R.id.city_country_flag);
        }
    }
}
