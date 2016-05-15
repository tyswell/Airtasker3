package tys.com.airtasker3.task;

import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import tys.com.airtasker3.R;
import tys.com.airtasker3.model.task.Task;
import tys.com.airtasker3.model.task.TaskComment;
import tys.com.airtasker3.model.task.TaskOffer;
import tys.com.airtasker3.task.mytask.TempModel;
import tys.com.airtasker3.ui.BaseActivity;
import tys.com.airtasker3.ui.recycleview.DividerItemDecoration;
import tys.com.airtasker3.ui.recycleview.RecycleClickListener;
import tys.com.airtasker3.ui.recycleview.RecyclerTouchListener;

/**
 * Created by chokechaic on 5/10/2016.
 */
public class TaskFragment extends Fragment {

    private TextView titleView;
    private ImageView profilePic;
    private TextView postByView;
    private TextView usernameView;
    private TextView timeStamp;
    private TextView totalBudgetView;
    private Button offerBtn;
    private TextView detailView;
    private GoogleMap mapFragment;
    private RecyclerView offerRecycle;
    private RecyclerView commentRecycle;

    private TaskOfferAdapter offerAdapter;
    private TaskCommentAdapter commentAdapter;

    private ProgressDialog progressDialog;

    private List<TaskOffer> offers = new ArrayList<>();
    private List<TaskComment> comments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_task, container, false);

        initView(rootView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        callWSMytask();
    }

    private void initView(View rootView) {
        titleView = (TextView) rootView.findViewById(R.id.titleView);
        profilePic = (ImageView) rootView.findViewById(R.id.profilePic);
        postByView = (TextView) rootView.findViewById(R.id.postByView);
        usernameView = (TextView) rootView.findViewById(R.id.usernameView);
        timeStamp = (TextView) rootView.findViewById(R.id.timeStamp);
        totalBudgetView = (TextView) rootView.findViewById(R.id.totalBudgetView);
        offerBtn = (Button) rootView.findViewById(R.id.offerBtn);
        detailView = (TextView) rootView.findViewById(R.id.detailView);
        mapFragment = ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.mapFragment)).getMap();
        offerRecycle = (RecyclerView) rootView.findViewById(R.id.offerRecycle);
        commentRecycle = (RecyclerView) rootView.findViewById(R.id.commentRecycle);

        offerAdapter = new TaskOfferAdapter(rootView.getContext(), offers);
        commentAdapter = new TaskCommentAdapter(rootView.getContext(), comments);

        progressDialog = new ProgressDialog(rootView.getContext());

        configRecyclerView(offerRecycle, offerAdapter, offerListener, rootView.getContext());
        configRecyclerView(commentRecycle, commentAdapter, commentListener, rootView.getContext());


    }

    private void configRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter adapter, RecycleClickListener listener, Context context) {
        recyclerView.setLayoutManager(new tys.com.airtasker3.ui.LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        commentRecycle.hasFixedSize();
        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(context, recyclerView, listener));
    }

    private RecycleClickListener offerListener = new RecycleClickListener() {
        @Override
        public void onClick(View view, int position) {
        }
    };


    private RecycleClickListener commentListener = new RecycleClickListener() {
        @Override
        public void onClick(View view, int position) {
        }
    };

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
//            callWSMytask();
        }
    }

    private void callWSMytask() {
        progressDialog.setMessage(getResources().getString(R.string.waiting));
        progressDialog.show();

        Task task = TempModel.getTask();
        setValue(task);

        //TODO call WS
        offers.clear();
        offers.addAll(task.getOffers());
        offerAdapter.notifyDataSetChanged();

        comments.clear();
        comments.addAll(task.getComments());
        commentAdapter.notifyDataSetChanged();

        progressDialog.hide();
    }

    public void setValue(Task value) {
        titleView.setText(value.getTitle());
        usernameView.setText(value.getClient().getFirstname());
        timeStamp.setText(value.getCreateDateDesc());
        totalBudgetView.setText(""+value.getTotalBudget());
        detailView.setText(value.getDetail());

        RequestManager rm = Glide.with(getContext());
        DrawableTypeRequest dr = null;
        if (value.getClient().getPicture() != null) {
            dr = rm.load(value.getClient().getPicture());
        } else if (value.getClient().getImageUrl() != null) {
            dr = rm.load(value.getClient().getImageUrl());
        } else {
            dr = rm.load(R.mipmap.ic_default_profile);
        }
        dr.crossFade()
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_default_profile)
                .error(R.mipmap.ic_default_profile)
                .into(profilePic);

        LatLng latlng = new LatLng(value.getLatitude() , value.getLongitude());
        Marker TP = mapFragment.addMarker(new MarkerOptions().
                position(latlng).title(value.getTitle()));
    }
}
