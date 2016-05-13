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
import tys.com.airtasker3.model.task.TaskOffer;

/**
 * Created by chokechaic on 5/12/2016.
 */
public class TaskOfferAdapter extends RecyclerView.Adapter<TaskOfferViewHolder> {

    private List<TaskOffer> offers;
    private Context context;

    public TaskOfferAdapter(Context context, List<TaskOffer> offers) {
        this.context = context;
        this.offers = offers;
    }

    @Override
    public TaskOfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycle_row_offer, parent, false);
        return new TaskOfferViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskOfferViewHolder holder, int position) {
        TaskOffer offer = offers.get(position);

        holder.usernameView.setText(offer.getClient().getFirstname());
        holder.timeStamp.setText(offer.getDateDesc());
        holder.ratingBar.setRating(offer.getClient().getRating());

        RequestManager rm = Glide.with(context);
        DrawableTypeRequest dr = null;
        if (offer.getClient().getPicture() != null) {
            dr = rm.load(offer.getClient().getPicture());
        } else if (offer.getClient().getImageUrl() != null) {
            dr = rm.load(offer.getClient().getImageUrl());
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
        return offers.size();
    }
}
