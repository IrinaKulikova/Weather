package kulikova.weather.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        final TextView textViewTempMin;
        final TextView textViewTempMax;
        final TextView textViewPressure;
        final TextView textViewWind;
        final TextView textViewDate;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTempMin=itemView.findViewById(R.id.temp_min);
            textViewTempMax=itemView.findViewById(R.id.temp_max);
            textViewPressure=itemView.findViewById(R.id.pressure);
            textViewWind=itemView.findViewById(R.id.wind);
            textViewDate=itemView.findViewById(R.id.date);
        }

        public void bind(kulikova.weather.entities.List list) {
            SimpleDateFormat format= new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd HH:mm:ss");
            try {
                Date day=format.parse(list.getDtTxt());
                textViewDate.setText((day.getYear()+1900)+" - "+day.getMonth() + " - " +day.getDate() );
            } catch (ParseException e) {
                e.printStackTrace();
            }
            textViewTempMin.setText(list.getMain().getTempMin().toString());
            textViewTempMax.setText(list.getMain().getTempMax().toString());
            textViewPressure.setText(list.getMain().getPressure().toString());
            textViewWind.setText(list.getWind().getSpeed().toString());
        }
    }
}
