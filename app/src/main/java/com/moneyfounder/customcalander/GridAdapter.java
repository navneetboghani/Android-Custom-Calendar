package com.moneyfounder.customcalander;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GridAdapter extends ArrayAdapter {
    private static final String TAG = GridAdapter.class.getSimpleName();
    private LayoutInflater mInflater;
    private List<Date> monthlyDates;
    public List<Date> weekenddate=new ArrayList<>();
    public List<Date> weekoffday=new ArrayList<>();
    private Calendar currentDate;
    Activity activity;
    public GridAdapter(Context context, List<Date> monthlyDates, Calendar currentDate) {
        super(context, R.layout.single_cell_layout);
        this.monthlyDates = monthlyDates;
        this.currentDate = currentDate;
        this.activity= (Activity) context;
        mInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Date mDate = monthlyDates.get(position);
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);
        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) + 1;
        int displayYear = dateCal.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH) + 1;
        int currentYear = currentDate.get(Calendar.YEAR);
        View view = convertView;
        if(view == null){
            view = mInflater.inflate(R.layout.single_cell_layout, parent, false);
        }
        if(displayMonth == currentMonth && displayYear == currentYear){
            view.setBackgroundColor(ContextCompat.getColor(activity,R.color.lightgraery));
        }else{
            view.setBackgroundColor(ContextCompat.getColor(activity,R.color.dark_grery));
        }

        TextView cellNumber = (TextView)view.findViewById(R.id.calendar_date_id);
        cellNumber.setText(String.valueOf(dayValue));

        int getselecteddatetype=getdateselect(mDate);

        switch (getselecteddatetype)
        {
            case 0: cellNumber.setBackgroundResource(R.drawable.trans_rounded_tv);break;
            case 1: cellNumber.setBackgroundResource(R.drawable.blue_rounded_tv);break;
            case 2: cellNumber.setBackgroundResource(R.drawable.red_rounded_tv);break;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ondateclick(mDate,position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    private int getdateselect(Date mDate) {
        try {
            if(weekenddate.size()==0 && weekoffday.size()==0)
                return 0;
            Calendar dateCal = Calendar.getInstance();
            dateCal.setTime(mDate);
            int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
            int displayMonth = dateCal.get(Calendar.MONTH) + 1;
            int displayYear = dateCal.get(Calendar.YEAR);

            for(int i=0;i<weekenddate.size();i++)
            {
                Calendar tempdate = Calendar.getInstance();
                tempdate.setTime(weekenddate.get(i));
                int tempValue = tempdate.get(Calendar.DAY_OF_MONTH);
                int tempmonth = tempdate.get(Calendar.MONTH) + 1;
                int tempyear = tempdate.get(Calendar.YEAR);
                if(tempValue==dayValue && displayMonth==tempmonth && displayYear==tempyear)
                    return 1;
            }
            for(int i=0;i<weekoffday.size();i++)
            {
                Calendar tempdate = Calendar.getInstance();
                tempdate.setTime(weekoffday.get(i));
                int tempValue = tempdate.get(Calendar.DAY_OF_MONTH);
                int tempmonth = tempdate.get(Calendar.MONTH) + 1;
                int tempyear = tempdate.get(Calendar.YEAR);

                if(tempValue==dayValue && displayMonth==tempmonth && displayYear==tempyear)
                    return 2;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void ondateclick(Date mDate, int position)
    {
        try
        {
            Calendar dateCal = Calendar.getInstance();
            dateCal.setTime(mDate);
            int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
            int displayMonth = dateCal.get(Calendar.MONTH) + 1;
            int displayYear = dateCal.get(Calendar.YEAR);
            int currentMonth = currentDate.get(Calendar.MONTH) + 1;
            int currentYear = currentDate.get(Calendar.YEAR);
            int dayOfWeek = dateCal.get(Calendar.DAY_OF_WEEK);


            if (dayOfWeek==1)
            {
                weekenddate.clear();
                weekenddate.add(mDate);
                dateCal.add(Calendar.DATE, 1);
                weekenddate.add(dateCal.getTime());
                dateCal.add(Calendar.DATE, 1);
                weekenddate.add(dateCal.getTime());
                dateCal.add(Calendar.DATE, 1);
                weekenddate.add(dateCal.getTime());
                notifyDataSetChanged();
                return;

            }
            if (dayOfWeek==2)
            {
                weekenddate.clear();
                dateCal.add(Calendar.DATE, -1);
                weekenddate.add(dateCal.getTime());
                dateCal.add(Calendar.DATE, 1);
                weekenddate.add(dateCal.getTime());
                dateCal.add(Calendar.DATE, 1);
                weekenddate.add(dateCal.getTime());
                dateCal.add(Calendar.DATE, 1);
                weekenddate.add(dateCal.getTime());

                notifyDataSetChanged();
                return;
            }
            if (dayOfWeek==3)
            {
                weekenddate.clear();
                dateCal.add(Calendar.DATE, -2);
                weekenddate.add(dateCal.getTime());
                dateCal.add(Calendar.DATE, 1);
                weekenddate.add(dateCal.getTime());
                dateCal.add(Calendar.DATE, 1);
                weekenddate.add(dateCal.getTime());
                dateCal.add(Calendar.DATE, 1);
                weekenddate.add(dateCal.getTime());

                notifyDataSetChanged();
                return;
            }
            if (dayOfWeek==4)
            {
                weekenddate.clear();
                dateCal.add(Calendar.DATE, -3);
                weekenddate.add(dateCal.getTime());
                dateCal.add(Calendar.DATE, 1);
                weekenddate.add(dateCal.getTime());
                dateCal.add(Calendar.DATE, 1);
                weekenddate.add(dateCal.getTime());
                dateCal.add(Calendar.DATE, 1);
                weekenddate.add(dateCal.getTime());

                notifyDataSetChanged();
                return;
            }
            if (dayOfWeek==5)
            {
                weekoffday.clear();
                weekoffday.add(mDate);
                dateCal.add(Calendar.DATE, 1);
                weekoffday.add(dateCal.getTime());
                dateCal.add(Calendar.DATE, 1);
                weekoffday.add(dateCal.getTime());
                notifyDataSetChanged();
                return;
            }
            if (dayOfWeek==6)
            {
                weekoffday.clear();
                dateCal.add(Calendar.DATE, -1);
                weekoffday.add(dateCal.getTime());
                dateCal.add(Calendar.DATE, 1);
                weekoffday.add(dateCal.getTime());
                dateCal.add(Calendar.DATE, 1);
                weekoffday.add(dateCal.getTime());
                notifyDataSetChanged();
                return;
            }
            if (dayOfWeek==7)
            {
                weekoffday.clear();
                dateCal.add(Calendar.DATE, -2);
                weekoffday.add(dateCal.getTime());
                dateCal.add(Calendar.DATE, 1);
                weekoffday.add(dateCal.getTime());
                dateCal.add(Calendar.DATE, 1);
                weekoffday.add(dateCal.getTime());
                notifyDataSetChanged();
                return;
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getCount() {
        return monthlyDates.size();
    }
    @Nullable
    @Override
    public Object getItem(int position) {
        return monthlyDates.get(position);
    }
    @Override
    public int getPosition(Object item) {
        return monthlyDates.indexOf(item);
    }
}