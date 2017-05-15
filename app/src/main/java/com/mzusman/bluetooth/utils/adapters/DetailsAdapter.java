package com.mzusman.bluetooth.utils.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mzusman.bluetooth.R;
import com.mzusman.bluetooth.enums.AvailableCommandNames;

import java.util.ArrayList;

/*
 * Class : DetailsAdapter.
 * Created by mzusman - morzusman@gmail.com on 4/11/16.
 */
public class DetailsAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    ArrayList<String> parametersList = new ArrayList<>();
    Context context;
    TextView name;
    TextView value;


    public DetailsAdapter(Context context, ImageView smileyImage1) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    public void setArray(ArrayList<String> array) {
        this.parametersList = array;
    }

    @Override
    public int getCount() {
        return parametersList.size();
    }

    @Override
    public Object getItem(int position) {
        return parametersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null) {
            vi = inflater.inflate(R.layout.detail_view, null);
        }

        String parameter = parametersList.get(position);
        String[] separated = parameter.split(",");
        name = (TextView) vi.findViewById(R.id.name);
        name.setText(separated[0]);
        value = (TextView) vi.findViewById(R.id.val);
        ImageView smileyImage = (ImageView) convertView.findViewById(R.id.smiley_image);

        if (separated.length > 2) {
            value.setText(separated[1] + ",\n" + separated[2]);
        } else
            value.setText(separated[1]);

        if (value.getText().equals("0.0,\n0.0") || value.getText().equals("-1")) {
            vi.setBackgroundColor(Color.argb(170, 180, 20, 20));
        } else {
            vi.setBackgroundColor(Color.argb(170, 20, 180, 20));
        }

        if (name.getText().equals(AvailableCommandNames.ENGINE_RPM.getValue()) && Integer.valueOf(value.getText().toString()) > 1000) {
            Toast.makeText(context, "ENGINE_RPM is too high", Toast.LENGTH_SHORT).show();
            smileyImage.setImageResource(R.drawable.red_smily);
        }
        else{
            smileyImage.setImageResource(R.drawable.green_smily);
        }

        if (name.getText().equals(AvailableCommandNames.SPEED.getValue()) && Integer.valueOf(value.getText().toString()) > 40) {
            Toast.makeText(context, "SPEED is too high", Toast.LENGTH_SHORT).show();

//            smileyImage.setImageResource(R.drawable.red_smily);
        }
//        else{
//            smileyImage.setImageResource(R.drawable.green_smily);
//        }

//        if(name.getText().equals(AvailableCommandNames.CALCULATED_ENGINE_LOAD.getValue()) && Integer.valueOf(value.getText().toString()) > 1000) {
//            Toast.makeText(context, "CALCULATED_ENGINE_LOAD is too high", Toast.LENGTH_LONG).show();
//        }else{
//            smileyImage.setImageResource(R.drawable.green_smily);
//        }
        return vi;

    }

}
