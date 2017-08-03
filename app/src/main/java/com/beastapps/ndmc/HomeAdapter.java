package com.beastapps.ndmc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.beastapps.ndmc.R;

/**
 * Created by ISHU on 6/27/2017.
 */

class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.RecyclerViewHolder> {
    private Context mContext;
    RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(225,225);

    static String[] buttonNames = {
            "NDMC", "News & Updates", "Important Information", "FAQ", "About", "eLibrary", "Fight Dengue/Chikungunya", "Sports Coaching",
            "Employee Corner", "E-Hospital", "OPD Registration", "Feedback", "Pensioner's Portal"

    };

    HomeAdapter(Context context) {
        mContext = context;
        layoutParams.setMargins(15,15,15,15);
    }

    @Override
    public int getItemCount() {
        return buttonNames.length;
    }



    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Button btn;

        btn = new Button(mContext);
        //btn.setLayoutParams(new GridView.LayoutParams(200, 200));
        //btn.setPadding(15, 15, 15, 15);
        btn.setLayoutParams(layoutParams);
        btn.setTextColor(Color.WHITE);

        btn.setBackgroundColor(Color.rgb(239, 108, 0));
        return new RecyclerViewHolder(btn);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.btn.setText(buttonNames[position]);

    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    public class myOnClickListener implements View.OnClickListener {
        int position;

        public myOnClickListener(int pos) {
            position = pos;
        }

        @Override
        public void onClick(View view) {


        }
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button btn;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            btn = (Button) itemView;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, NDMC.class);
            intent.putExtra("POS", getLayoutPosition());
            switch (getLayoutPosition()) {
                case 0:
                    intent.putExtra("URL", "http://www.ndmc.gov.in/");
                    mContext.startActivity(intent);
                    break;
                case 1:
                    intent.putExtra("URL", "http://www.ndmc.gov.in/publicnotice.aspx");
                    mContext.startActivity(intent);
                    break;
                case 2:
                    intent.putExtra("URL", "http://www.ndmc.gov.in/important_links.aspx");
                    mContext.startActivity(intent);
                    break;
                case 3:
                    intent.putExtra("URL", "http://www.ndmc.gov.in/faq/birth_death_faqs.aspx");
                    mContext.startActivity(intent);
                    break;
                case 4:
                    intent.putExtra("URL", "http://www.ndmc.gov.in/ndmc/history.aspx");
                    mContext.startActivity(intent);
                    break;
                case 5:
                    intent.putExtra("URL", "http://115.254.99.149/elibrary/");
                    mContext.startActivity(intent);
                    break;
                case 6:
                    intent.putExtra("URL", "http://online.ndmc.gov.in/dengue/");
                    mContext.startActivity(intent);
                    break;
                case 7:
                    intent.putExtra("URL", "http://103.25.172.148/ndmc/index.aspx");
                    mContext.startActivity(intent);
                    break;
                case 8:
                    intent.putExtra("URL", "http://online.ndmc.gov.in/employee/");
                    mContext.startActivity(intent);
                    break;
                case 9:
                    intent.putExtra("URL", "http://online.ndmc.gov.in/eHospital/");
                    mContext.startActivity(intent);
                    break;
                case 10:
                    intent.putExtra("URL", "http://ors.gov.in/index.html");
                    mContext.startActivity(intent);
                    break;

                case 11:
                    intent.putExtra("URL", "http://online.ndmc.gov.in/feedback/");
                    mContext.startActivity(intent);
                    break;
                case 12:
                    intent.putExtra("URL", "http://online.ndmc.gov.in/pension/");
                    mContext.startActivity(intent);
                    break;

            }
        }

    }
}