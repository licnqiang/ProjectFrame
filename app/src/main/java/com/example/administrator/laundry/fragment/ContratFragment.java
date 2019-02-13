package com.example.administrator.laundry.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;

import com.baidu.platform.comapi.map.E;
import com.example.administrator.laundry.R;
import com.example.administrator.laundry.activity.ChatActivity;
import com.example.administrator.laundry.base.BaseFragment;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContratFragment extends BaseFragment {
    private static EMMessageListener emMessageListener;
    private EaseContactListFragment easeContactListFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_list;
    }

    @Override
    protected void initView() {
        easeContactListFragment = new EaseContactListFragment();
        getFragmentManager().beginTransaction().add(R.id.chat_list,easeContactListFragment).commit();
//        conversationFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
//            @Override
//            public void onListItemClicked(EMConversation conversation) {
//                startActivity(new Intent(getActivity(), ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID,conversation.conversationId()));
//            }
//        });
    }

    @Override
    protected void initData() {

    }
}
