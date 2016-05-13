package tys.com.airtasker3.task.mytask;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tys.com.airtasker3.R;

/**
 * Created by deksen on 5/8/16 AD.
 */
public class MytaskViewHolder extends RecyclerView.ViewHolder {

    public ImageView profilePic;
    public TextView titleView;
    public TextView totalBudgetView;
    public TextView locationView;
    public TextView commentNumView;
    public TextView offerNumView;

    public MytaskViewHolder(View v) {
        super(v);
        profilePic = (ImageView) v.findViewById(R.id.profilePic);
        titleView = (TextView) v.findViewById(R.id.titleView);
        totalBudgetView = (TextView) v.findViewById(R.id.totalBudgetView);
        locationView = (TextView) v.findViewById(R.id.locationView);
        commentNumView = (TextView) v.findViewById(R.id.commentNumView);
        offerNumView = (TextView) v.findViewById(R.id.offerNumView);
    }

}
