package com.foot.tourpal.mine;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.foot.tourpal.mine.dummy.DummyContent;
import com.foot.tourpal.mine.dummy.DummyContent.DummyItem;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.LogUtils;
import com.markmao.pullscrollview.ui.widget.PullScrollView;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MineFragment extends BaseFragment implements PullScrollView.OnTurnListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private static MineFragment fragment;

    private String tag = this.getClass().getSimpleName();

    private final int scroll_view = R.id.scroll_view;

    private PullScrollView mScrollView;
    private ImageView mHeadImg;

    private TableLayout mMainLayout;


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
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
        LogUtils.debugInfo(tag, new Exception().getStackTrace()[0].getMethodName());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        LogUtils.debugInfo(tag, new Exception().getStackTrace()[0].getMethodName());
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_mine_list, container, false);
    }

    @Override
    public void initData() {
        mScrollView = (PullScrollView) getActivity().findViewById(R.id.scroll_view);
        mHeadImg = (ImageView) getActivity().findViewById(R.id.background_img);

        mMainLayout = (TableLayout) getActivity().findViewById(R.id.table_layout);
        mScrollView.setHeader(mHeadImg);
        mScrollView.setOnTurnListener(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.leftMargin = 30;
        layoutParams.bottomMargin = 10;
        layoutParams.topMargin = 10;

        for (int i = 0; i < 30; i++) {
            TableRow tableRow = new TableRow(getActivity());
            TextView textView = new TextView(getActivity());
            textView.setText("Test pull down scroll view " + i);
            textView.setTextSize(20);
            textView.setPadding(15, 15, 15, 15);

            tableRow.addView(textView, layoutParams);
            if (i % 2 != 0) {
                tableRow.setBackgroundColor(Color.LTGRAY);
            } else {
                tableRow.setBackgroundColor(Color.WHITE);
            }

            final int n = i;
            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Click item " + n, Toast.LENGTH_SHORT).show();
                }
            });

            mMainLayout.addView(tableRow);
        }
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void onTurn() {

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
//            Intent intent = new Intent();
//            intent.setData(Uri.parse("App://www.foot.com/LoginActivity"));
//            startActivity(intent);
        }
    }
}
