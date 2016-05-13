package tys.com.airtasker3.task.mytask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tys.com.airtasker3.R;
import tys.com.airtasker3.createtask.CreateTaskActivity;
import tys.com.airtasker3.model.task.MyTask;
import tys.com.airtasker3.task.TaskActivity;
import tys.com.airtasker3.ui.recycleview.DividerItemDecoration;
import tys.com.airtasker3.ui.recycleview.RecycleClickListener;
import tys.com.airtasker3.ui.recycleview.RecyclerTouchListener;

/**
 * Created by deksen on 5/8/16 AD.
 */
public class MytaskFragment extends Fragment {

    private String TAG = MytaskFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private MytaskAdapter adapter;

    private List<MyTask> myTasks = new ArrayList<>();

    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mytask, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        adapter = new MytaskAdapter(rootView.getContext(), myTasks);

        progressDialog = new ProgressDialog(rootView.getContext());

        RecyclerView.LayoutManager lm = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(lm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(rootView.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(rootView.getContext(), recyclerView, listener));

        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            callWSMytask();
        }
    }

    private RecycleClickListener listener = new RecycleClickListener() {
        @Override
        public void onClick(View view, int position) {
            MyTask movie = myTasks.get(position);
            startActivityForResult(new Intent(getContext(), TaskActivity.class), 1);
        }
    };

    private void callWSMytask() {
        progressDialog.setMessage(getResources().getString(R.string.waiting));
        progressDialog.show();

        //TODO call WS
        myTasks.clear();
        myTasks.addAll(TempModel.getModel(getActivity()));

        adapter.notifyDataSetChanged();
        progressDialog.hide();
    }
}
