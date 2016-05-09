package tys.com.airtasker3.mytask;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import tys.com.airtasker3.R;
import tys.com.airtasker3.model.task.MyTask;

/**
 * Created by deksen on 5/8/16 AD.
 */
public class MytaskAdapter extends RecyclerView.Adapter<MytaskViewHolder> {

    private List<MyTask> myTasks;
    private Context context;

    public MytaskAdapter(Context context, List<MyTask> myTasks) {
        this.context = context;
        this.myTasks = myTasks;
    }

    @Override
    public MytaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycle_row_mytask, parent, false);
        return new MytaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MytaskViewHolder holder, int position) {
        MyTask myTask = myTasks.get(position);

        holder.titleView.setText(myTask.getTitle());
        holder.totalBudgetView.setText(""+myTask.getTotalBudget());
        holder.locationView.setText(myTask.getLocationDetail());
        holder.commentNumView.setText(""+myTask.getNumComment());
        holder.offerNumView.setText(""+myTask.getNumOffer());

        RequestManager rm = Glide.with(context);
        DrawableTypeRequest dr = null;
        if (myTask.getClientPicture() != null) {
            dr = rm.load(myTask.getClientPicture());
        } else if (myTask.getClientImageUrl() != null) {
            dr = rm.load(myTask.getClientImageUrl());
        } else {
            dr = rm.load(R.mipmap.ic_default_profile);
        }
        dr.crossFade()
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_default_profile)
                .error(R.mipmap.ic_default_profile)
                .into(holder.profilePic);
    }

    @Override
    public int getItemCount() {
        return myTasks.size();
    }
}
