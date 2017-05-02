package com.foot.tourpal.mine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foot.tourpal.base.BaseFragment;
import com.foot.tourpal.mine.dummy.DummyContent;
import com.foot.tourpal.mine.dummy.DummyContent.DummyItem;
import com.jess.arms.utils.LogUtils;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MineFragment extends BaseFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private static MineFragment fragment;

    public static MineFragment newInstance() {
        if(fragment == null) {
            synchronized (MineFragment.class) {
                fragment = new MineFragment();
            }
        }
        return fragment;
    }
    
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MineFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MineFragment newInstance(int columnCount) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        LogUtils.debugInfo(tag, new Exception().getStackTrace()[0].getMethodName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyMineRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        LogUtils.debugInfo(tag, new Exception().getStackTrace()[0].getMethodName());
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
        LogUtils.debugInfo(tag, new Exception().getStackTrace()[0].getMethodName());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        LogUtils.debugInfo(tag, new Exception().getStackTrace()[0].getMethodName());
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.debugInfo(tag, new Exception().getStackTrace()[0].getMethodName());
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.debugInfo(tag, new Exception().getStackTrace()[0].getMethodName());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if( isVisibleToUser && isVisible() ) {
            Intent intent = new Intent();
            intent.setData(Uri.parse("App://www.foot.com/LoginActivity"));
            startActivity(intent);
        }
    }
}
