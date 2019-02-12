package com.example.administrator.laundry.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Window;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.activity.ChatActivity;
import com.example.administrator.laundry.activity.ChatListActivity;
import com.example.administrator.laundry.base.BaseFragment;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends BaseFragment {
    private static EMMessageListener emMessageListener;
    private EaseConversationListFragment conversationFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_list;
    }

    @Override
    protected void initView() {
        conversationFragment = new EaseConversationListFragment();
        getFragmentManager().beginTransaction().add(R.id.chat_list,conversationFragment).commit();
        conversationFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                startActivity(new Intent(getActivity(), ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID,conversation.conversationId()));
            }
        });
    }

    @Override
    protected void initData() {

    }
}
