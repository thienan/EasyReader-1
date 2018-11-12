package com.kroraina.easyreader.modules.main.store;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.LinearLayout;

import com.kroraina.easyreader.R;
import com.kroraina.easyreader.base.adapter.BaseViewHolder;
import com.kroraina.easyreader.base.annotations.LayoutId;
import com.kroraina.easyreader.modules.search.SearchActivity;
import com.kroraina.easyreader.base.adapter.BaseItem;

@LayoutId(R.layout.item_search_bar)
public class SearchBarItem extends BaseItem {

    public SearchBarItem(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType() {
        return 1000;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder) {
        LinearLayout mSearchBar = viewHolder.findViewById(R.id.ll_search_bar);
        mSearchBar.setOnClickListener(view -> {
            startActivity(SearchActivity.class);
        });
    }
}