package com.example.administrator.laundry.NetService.data;

import java.util.List;

public class Detail extends BaseReseponseInfo {
    public String userImgNumber;
    public String userNickname;
    public int noteId;
    public String noteImgNumber;
    public String noteContent;
    public int noteType;
    public String noteAddress;
    public String noteTime;
    public String userId;
    public String notePraise;
    public int noteFlag;
    public int collectFlag;
    public List<Comment> comment;

    public class Comment {
        public String userImgNumber;
        public String userNickname;
        public int commentNumber;
        public int  commentFlag;

        public String commentContent;
        public String userId;
        public String commentTime;
        public String commentPraise;
    }

}
