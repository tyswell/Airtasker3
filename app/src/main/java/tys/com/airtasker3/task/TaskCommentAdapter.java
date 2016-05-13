package tys.com.airtasker3.task;

import android.content.Context;
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
import tys.com.airtasker3.model.task.Task;
import tys.com.airtasker3.model.task.TaskComment;
import tys.com.airtasker3.task.mytask.MytaskViewHolder;

/**
 * Created by chokechaic on 5/12/2016.
 */
public class TaskCommentAdapter extends RecyclerView.Adapter<TaskCommentViewHolder> {

    private List<TaskComment> comments;
    private Context context;

    public TaskCommentAdapter(Context context, List<TaskComment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @Override
    public TaskCommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycle_row_comment, parent, false);
        return new TaskCommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskCommentViewHolder holder, int position) {
        TaskComment comment = comments.get(position);

        holder.usernameView.setText(comment.getClient().getFirstname());
        holder.timeStamp.setText(comment.getCreatedDateDesc());
        holder.commentView.setText(comment.getComment());

        RequestManager rm = Glide.with(context);
        DrawableTypeRequest dr = null;
        if (comment.getClient().getPicture() != null) {
            dr = rm.load(comment.getClient().getPicture());
        } else if (comment.getClient().getImageUrl() != null) {
            dr = rm.load(comment.getClient().getImageUrl());
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
        return comments.size();
    }
}
