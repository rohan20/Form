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

/**
 * Created by sagar on 07-Jul-16.
 */
public class AllUsersFragmentArrayAdapter extends RecyclerView.Adapter<AllUsersFragmentArrayAdapter.ViewHolder> {

    private Context mContext;
    private List<UserModel> mRegisteredUsersList;

    public AllUsersFragmentArrayAdapter(Context context) {
        mContext = context;
    }

    /**
     *
     * @param registeredUsersList
     */
    public void setList(List<UserModel> registeredUsersList) {
        mRegisteredUsersList = registeredUsersList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.all_users_user_name)
        TextView mAllUsersUserName;

        @Bind(R.id.all_users_user_email)
        TextView mAllUsersUserEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.all_users_fragment_item, parent, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(AllUsersFragmentArrayAdapter.ViewHolder holder, int position) {

        TextView nameText = holder.mAllUsersUserName;
        TextView emailText = holder.mAllUsersUserEmail;

        nameText.setText(mRegisteredUsersList.get(position).getmUserFirstName() + " " + mRegisteredUsersList.get(position).getmUserLastName());
        emailText.setText(mRegisteredUsersList.get(position).getmUserEmail());

    }

    @Override
    public int getItemCount() {
        return mRegisteredUsersList.size();
    }
}
