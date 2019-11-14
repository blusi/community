package com.example.demo11.service;

import com.example.demo11.mapper.UserMapper;
import com.example.demo11.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void createOrUpdate(User user) {
      User dbUser = userMapper.findByAccountId(user.getAccountId());
      if(dbUser == null){
          //插入
          user.setGmtCreate(System.currentTimeMillis());
          user.setGmtModified(user.getGmtCreate());
          userMapper.insert(user);
      }else {
          //更新
          dbUser.setGmtCreate(System.currentTimeMillis());
          dbUser.setAvatarUrl(user.getAvatarUrl());
          dbUser.setToken(user.getToken());
          dbUser.setName(user.getName());
          userMapper.update(dbUser);
      }
    }
}
