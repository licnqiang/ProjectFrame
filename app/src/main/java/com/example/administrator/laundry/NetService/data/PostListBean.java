package com.example.administrator.laundry.NetService.data;

import java.util.List;

/**
 * @author lq
 * @fileName PostListBean
 * @data on  2019/2/26 15:52
 * @describe TODO
 */
public class PostListBean extends BaseReseponseInfo{

    private List<NoteBean> note;

    public List<NoteBean> getNote() {
        return note;
    }

    public void setNote(List<NoteBean> note) {
        this.note = note;
    }

    public static class NoteBean {
        /**
         * noteContent : 帖子内容
         * noteId : 1
         * noteTime : 时间戳
         * userImgNumber : 用户头像编号
         * userNickname : 用户昵称
         */

        public String noteContent;
        public int noteId;
        public String noteTime;
        public String userImgNumber;
        public String userNickname;

    }
}
