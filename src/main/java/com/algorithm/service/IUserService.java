package com.algorithm.service;

import com.algorithm.dto.MamaUser;

import java.util.List;

public interface IUserService {
    //显示所有用户
    public List<MamaUser> getUser() throws Exception;
    //根据id删除用户
    public void deleteUser(String id) throws Exception;
    //新增用户
    public void addUser(MamaUser mamaUser) throws Exception;
}
