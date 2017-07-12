package com.yisa.service;

import com.yisa.dao.Userdao;
import com.yisa.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yisa on 2017/7/12.
 */
@Service
public class UserService {

    @Autowired
    private Userdao userdao;

    @Autowired
    public void setUserdao(Userdao userdao) {
        this.userdao = userdao;
    }

    public List<User> getUser(){
        return userdao.getUserList();
    }


}
