package com.algorithm.service.impl;

import com.algorithm.dao.MamaUserMapper;
import com.algorithm.dto.MamaUser;
import com.algorithm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author renhao
 * @Date 2020/1/2 14:30
 **/
@Service
public class UserServiceImpl  implements IUserService {
    @Autowired
    private MamaUserMapper mamaUserMapper;

    @Override
    public List<MamaUser> getUser() throws Exception {
        return mamaUserMapper.selectAllUsers();
    }

    @Override
    public void deleteUser(String id) throws Exception {
        mamaUserMapper.deleteByPrimaryKey( id );
    }

    @Override
    public void addUser(MamaUser mamaUser) throws Exception {
        mamaUserMapper.insertSelective( mamaUser );
    }
}
