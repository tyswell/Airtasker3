package tys.com.airtasker3.task;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import tys.com.airtasker3.R;

/**
 * Created by chokechaic on 5/12/2016.
 */
public class TaskOfferViewHolder extends RecyclerView.ViewHolder {

    public ImageView profilePic;
    public TextView usernameView;
    public TextView timeStamp;
    public RatingBar ratingBar;

    public TaskOfferViewHolder(View v) {
        super(v);
        profilePic = (ImageView) v.findViewById(R.id.profilePic);
        usernameView = (TextView) v.findViewById(R.id.usernameView);
        timeStamp = (TextView) v.findViewById(R.id.timeStamp);
        ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);
    }

}
