package com.kroraina.easyreader.modules.community.discussion.topic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kroraina.easyreader.R;
import com.kroraina.easyreader.model.bean.BookCommentBean;
import com.kroraina.easyreader.modules.community.detail.DiscDetailActivity;
import com.kroraina.easyreader.modules.main.community.CommunityType;
import com.kroraina.easyreader.ui.widget.transform.CircleTransform;
import com.kroraina.easyreader.utils.Constant;
import com.kroraina.easyreader.utils.StringUtils;
import com.xincubate.lego.adapter.core.BaseItem;
import com.xincubate.lego.adapter.core.BaseViewHolder;
import com.xincubate.lego.annotation.LegoItem;

import java.util.ArrayList;
import java.util.List;

@LegoItem
public class DiscCommentItem extends BaseItem {

    public static List<DiscCommentItem> initFrom(Context context, List<BookCommentBean> beans, CommunityType communityType){
        List<DiscCommentItem> results = new ArrayList<>();
        for (BookCommentBean bean : beans){
            results.add(new DiscCommentItem(context,bean,communityType));
        }
        return results;
    }


    public CommunityType communityType;
    public BookCommentBean bean;
    public DiscCommentItem(Context context,BookCommentBean bean,CommunityType communityType) {
        super(context);
        this.bean = bean;
        this.communityType = communityType;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_disc_comment;
    }

    @Override
    public void onClick() {
        DiscDetailActivity.startActivity(context, communityType, bean.get_id());
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder , int position) {
        ImageView mIvPortrait = viewHolder.findViewById(R.id.disc_comment_iv_portrait);
        TextView mTvName = viewHolder.findViewById(R.id.disc_comment_tv_name);
        TextView mTvLv = viewHolder.findViewById(R.id.disc_comment_tv_lv);
        TextView mTvTime = viewHolder.findViewById(R.id.disc_comment_tv_time);
        TextView mTvBrief = viewHolder.findViewById(R.id.disc_comment_tv_brief);
        TextView mTvLabelDistillate = viewHolder.findViewById(R.id.disc_comment_tv_label_distillate);
        TextView mTvLabelHot = viewHolder.findViewById(R.id.disc_comment_tv_label_hot);
        TextView mTvResponseCount = viewHolder.findViewById(R.id.disc_comment_tv_response_count);
        TextView mTvLikeCount = viewHolder.findViewById(R.id.disc_comment_tv_like_count);
        //头像
        Glide.with(context)
                .load(Constant.IMG_BASE_URL+bean.getAuthor().getAvatar())
                .placeholder(R.drawable.ic_default_portrait)
                .error(R.drawable.ic_load_error)
                .transform(new CircleTransform(context))
                .into(mIvPortrait);
        //名字
        mTvName.setText(bean.getAuthor().getNickname());
        //等级
        mTvLv.setText(context.getResources().getString(R.string.nb_user_lv,
                bean.getAuthor().getLv()));
        //简介
        mTvBrief.setText(bean.getTitle());
        //label
        if (bean.getState().equals(Constant.BOOK_STATE_DISTILLATE)){
            mTvLabelDistillate.setVisibility(View.VISIBLE);
            mTvTime.setVisibility(View.VISIBLE);
        }
        else {
            mTvLabelDistillate.setVisibility(View.GONE);
            mTvTime.setVisibility(View.GONE);
        }
        //comment or vote
        String type = bean.getType();
        Drawable drawable = null;
        switch (type){
            case Constant.BOOK_TYPE_COMMENT:
                drawable = context.getResources().getDrawable(R.drawable.ic_notif_post);
                break;
            case Constant.BOOK_TYPE_VOTE:
                drawable = context.getResources().getDrawable(R.drawable.ic_notif_vote);
                break;
            default:
                drawable = context.getResources().getDrawable(R.mipmap.ic_launcher);
                break;
        }
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
        //time
        mTvTime.setText(StringUtils.dateConvert(bean.getUpdated(),Constant.FORMAT_BOOK_DATE));

        mTvResponseCount.setCompoundDrawables(drawable,null,null,null);
        //response count
        mTvResponseCount.setText(bean.getCommentCount()+"");
        //like count
        mTvLikeCount.setText(bean.getLikeCount()+"");
    }
}
