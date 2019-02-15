package com.example.administrator.laundry.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.fragment.GroupFragment;
import com.example.administrator.laundry.fragment.MapFragment;
import com.example.administrator.laundry.fragment.MeFragment;
import com.example.administrator.laundry.view.BottomBar;
import com.example.administrator.laundry.view.LoadDataView;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static android.content.ContentValues.TAG;

public class MainActivity extends BaseActivity {
    private MapFragment mapFragment = new MapFragment();
    private MeFragment meFragment = new MeFragment();
    private EaseContactListFragment easeContactListFragment = new EaseContactListFragment();
    private EaseConversationListFragment conversationFragment = new EaseConversationListFragment();
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void initView() {
        setContactData();
        setConversation();
        bottomBar.setContainer(R.id.fl_container)
                .setTitleSize(12)
                .setTitleBeforeAndAfterColor("#999999", "#1587FD")
                .addItem(mapFragment,
                        "首页",
                        R.mipmap.main_ziyuan,
                        R.mipmap.main_ziyuan_sel)
                .addItem(conversationFragment,
                        "消息",
                        R.mipmap.home_tab_msg_n,
                        R.mipmap.home_tab_msg_p)
                .addItem(easeContactListFragment,
                        "联系人",
                        R.mipmap.main_qunzu,
                        R.mipmap.main_qunzu_sel)
                .addItem(meFragment,
                        "我的",
                        R.mipmap.main_wode,
                        R.mipmap.main_wode_sel)
                .build();
    }

    /**
     * 回话列表界面
     */
    private void setConversation() {
        conversationFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                /**
                 * 跳转会话界面
                 */
                startActivity(new Intent(MainActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId()));
            }
        });
    }

    /**
     * 联系人界面
     */
    private void setContactData() {
        new Thread() {//需要在子线程中调用
            @Override
            public void run() {
                //需要设置联系人列表才能启动fragment
                easeContactListFragment.setContactsMap(getContact());
            }
        }.start();
        /**
         * 跳转会话界面
         */
        easeContactListFragment.setContactListItemClickListener(new EaseContactListFragment.EaseContactListItemClickListener() {
            @Override
            public void onListItemClicked(EaseUser user) {
                startActivity(new Intent(MainActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, user.getUsername()));
            }
        });
        /***
         * 显示联系人变化，及时更新
         */
        EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {


            @Override
            public void onContactInvited(String username, String reason) {
                //收到好友邀请
            }

            @Override
            public void onFriendRequestAccepted(String s) {

            }

            @Override
            public void onFriendRequestDeclined(String s) {

            }

            @Override
            public void onContactDeleted(String username) {
                //被删除时回调此方法
                new Thread() {//需要在子线程中调用
                    @Override
                    public void run() {
                        //需要设置联系人列表才能启动fragment
                        easeContactListFragment.setContactsMap(getContact());
                        easeContactListFragment.refresh();
                    }
                }.start();
            }


            @Override
            public void onContactAdded(String username) {
                //增加了联系人时回调此方法


                new Thread() {//需要在子线程中调用
                    @Override
                    public void run() {
                        //需要设置联系人列表才能启动fragment
                        easeContactListFragment.setContactsMap(getContact());
                        easeContactListFragment.refresh();
                    }
                }.start();

            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    protected ViewGroup loadDataViewLayout() {
        return null;
    }

    @Override
    protected void getLoadView(LoadDataView loadView) {

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        //重新让新的Fragment指向了原本未被销毁的fragment，它就是onAttach方法对应的Fragment对象
        if (mapFragment == null && fragment instanceof MapFragment) {
            mapFragment = (MapFragment) fragment;
        }
        if (conversationFragment == null && fragment instanceof GroupFragment) {
            conversationFragment = (EaseConversationListFragment) fragment;
        }
        if (meFragment == null && fragment instanceof MeFragment) {
            meFragment = (MeFragment) fragment;
        }
        if (easeContactListFragment == null && fragment instanceof MeFragment) {
            easeContactListFragment = (EaseContactListFragment) fragment;
        }
        super.onAttachFragment(fragment);
    }

    /**
     * 获取联系人
     *
     * @return
     */
    private Map<String, EaseUser> getContact() {
        Map<String, EaseUser> map = new HashMap<>();
        try {
            List<String> userNames = EMClient.getInstance().contactManager().getAllContactsFromServer();
            for (String userId : userNames) {
//                KLog.e("好友列表中有 : " + userId);
                map.put(userId, new EaseUser(userId));
            }
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        return map;
    }
}
