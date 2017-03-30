package com.mico.framework.mvpdagger.adapter;

import android.content.Context;

import com.mico.framework.baselib.base.adapter.SuperViewHolder;
import com.mico.framework.mvpdagger.R;
import com.mico.framework.mvpdagger.databinding.ListItemBookBinding;
import com.mico.framework.mvpdagger.model.Book;

import java.util.List;

/**
 * Created by LiuNana on 2017/3/28.
 * Book列表的Adapter
 */

public class BookAdapter extends BaseAdapter<Book> {

    public BookAdapter(Context context, List<Book> items) {
        super(context, items, R.layout.list_item_book);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, Book item) {
        ListItemBookBinding binding = (ListItemBookBinding) holder.getTag();
//        NhHouseListItemBinding binding = (NhHouseListItemBinding) holder.getTag();
//        binding.llTags.setSingleLine(true);
//        binding.llTags.setChildMargins(0, 0, 10, 0);
//        binding.llTags.setVisibility(View.VISIBLE);
        binding.setItem(item);
    }
}