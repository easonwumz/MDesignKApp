package com.wu.md.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wu.md.R
import com.wu.md.entity.NavInfo

class NavAdapter(list: MutableList<NavInfo>) :
    BaseMultiItemQuickAdapter<NavInfo, BaseViewHolder>(list) {

    init {
        addItemType(NavInfo.MSG_TYPE_VIP, R.layout.item_nav)
        addItemType(NavInfo.MSG_TYPE_CENTER, R.layout.item_nav)
    }

    override fun convert(holder: BaseViewHolder, item: NavInfo) {
        when (item.type) {
            NavInfo.MSG_TYPE_VIP -> {

            }
            NavInfo.MSG_TYPE_CENTER -> {
                holder.setText(R.id.tvTitle, item.title)
            }
        }
    }
}