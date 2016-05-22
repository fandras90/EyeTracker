package com.eyetracker.mobile.ui.framelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.R;
import com.eyetracker.mobile.model.Frame;

import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * Created by fabia on 5/9/2016.
 */
public class FrameAdapter extends RecyclerView.Adapter<FrameAdapter.ViewHolder> {

    @Inject
    FrameListPresenter frameListPresenter;

    private Context context;
    private List<Frame> frameList;

    public FrameAdapter(Context context, List<Frame> frameList) {
        this.context = context;
        this.frameList = frameList;

        EyeTrackerApplication.injector.inject(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frame_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Frame frame = frameList.get(position);
        holder.id = frame.getId();
        Glide.with(context).load(frame.getImage().getUrl()).into(holder.ivImage);
        holder.tvTitle.setText(frame.getTitle());
        holder.tvFilterType.setText(frame.getFilterType().toString());
        holder.tvLeftCoord.setText(Float.toString(frame.getLeftCoordinates().getxCoord()) + Float.toString(frame.getLeftCoordinates().getyCoord()));
        holder.tvRightCoord.setText(Float.toString(frame.getRightCoordinates().getxCoord()) + Float.toString(frame.getRightCoordinates().getyCoord()));
    }

    @Override
    public int getItemCount() {
        return frameList.size();
    }

    public Frame getFrame(int position) {
        return frameList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView ivImage;
        public TextView tvTitle;
        public TextView tvFilterType;
        public TextView tvLeftCoord;
        public TextView tvRightCoord;
        public Long id;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvFilterType = (TextView) itemView.findViewById(R.id.tvFilterType);
            tvLeftCoord = (TextView) itemView.findViewById(R.id.tvLeftCoord);
            tvRightCoord = (TextView) itemView.findViewById(R.id.tvRightCoord);
        }

    }

}
