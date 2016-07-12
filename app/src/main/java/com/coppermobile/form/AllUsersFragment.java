package com.coppermobile.form;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllUsersFragment extends Fragment {

    @Bind(R.id.all_users_recycler_view)
    RecyclerView mAllUsersRecyclerView;
    @Bind(R.id.all_users_total_users)
    TextView mAllUsersTotalUsers;

    public AllUsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_users, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View v = getView();
        ButterKnife.bind(this, v);

        List<UserModel> users = new ArrayList<>();
        users.addAll(UserModel.listAll(UserModel.class));

        mAllUsersTotalUsers.append(String.valueOf(users.size()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        AllUsersFragmentArrayAdapter adapter = new AllUsersFragmentArrayAdapter(getActivity());
        adapter.setList(users);
        mAllUsersRecyclerView.setAdapter(adapter);
        mAllUsersRecyclerView.setLayoutManager(linearLayoutManager);

    }
}
