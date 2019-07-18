package com.project.Kang_Lee.laundry.database.dbTab;

import com.project.Kang_Lee.laundry.database.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import java.io.Serializable;



@Table(database = AppDatabase.class)
public class UserInfo_Tab extends BaseModel implements Serializable {
    @PrimaryKey(autoincrement = true)//ID自增
    public long id;
    @Column
    public String userName; //姓名

}
