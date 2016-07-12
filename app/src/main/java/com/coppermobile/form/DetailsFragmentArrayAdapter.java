package com.coppermobile.form;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsFragmentArrayAdapter extends RecyclerView.Adapter<DetailsFragmentArrayAdapter.ViewHolder> {

    private Context mContext;
    private List<DetailsItem> mDetailItems;

    public DetailsFragmentArrayAdapter(Context context, List<DetailsItem> detailsItems) {
        mContext = context;
        mDetailItems = detailsItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.detail_fragment_category_text_view)
        TextView mCategory;

        @Bind(R.id.detail_fragment_data_text_view)
        TextView mData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.details_fragment_item, parent, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(DetailsFragmentArrayAdapter.ViewHolder holder, int position) {

        TextView categoryText = holder.mCategory;
        TextView dataText = holder.mData;

        categoryText.setText(mDetailItems.get(position).getCategory());
        dataText.setText(mDetailItems.get(position).getData());
    }

    @Override
    public int getItemCount() {
        return mDetailItems.size();
    }
}
