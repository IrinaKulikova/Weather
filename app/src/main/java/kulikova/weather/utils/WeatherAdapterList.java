package kulikova.weather.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kulikova.weather.R;

public class WeatherAdapterList extends RecyclerView.Adapter<WeatherAdapterList.ViewHolder> {

    public void setWeathers(java.util.List<kulikova.weather.entities.List> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    java.util.List<kulikova.weather.entities.List> list = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapterList.ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView textViewTemp;
        final TextView textViewPressure;
        final TextView textViewWind;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTemp=itemView.findViewById(R.id.temp);
            textViewPressure=itemView.findViewById(R.id.pressure);
            textViewWind=itemView.findViewById(R.id.wind);
        }

        public void bind(kulikova.weather.entities.List list) {
            textViewTemp.setText(list.getMain().getTemp().toString());
            textViewPressure.setText(list.getMain().getPressure().toString());
            textViewWind.setText(list.getWind().getSpeed().toString());
        }
    }
}
