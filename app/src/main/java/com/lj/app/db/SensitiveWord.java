package com.lj.app.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 13717 on 2017/12/5.
 */
@Entity
public class SensitiveWord {

    @Id
    private long id;
    private String name;
    @Generated(hash = 1686184670)
    public SensitiveWord(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 871140123)
    public SensitiveWord() {
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
