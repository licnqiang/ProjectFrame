package com.example.administrator.laundry.NetService.data;

import java.util.List;

public class Detail extends BaseReseponseInfo{
    public String userImgNumber;
    public String userNickname;
    public int noteId;
    public String noteImgNumber;
    public String noteContent;
    public int noteType;
    public String noteAddress;
    public String noteTime;
    public String notePraise;
    public List<Comment> comment ;

    public class Comment {
        public String userImgNumber;
        public String userNickname;
        public int commentNumber;
        public String commentContent;
        public String commentTime;
        public String commentPraise;}

}
