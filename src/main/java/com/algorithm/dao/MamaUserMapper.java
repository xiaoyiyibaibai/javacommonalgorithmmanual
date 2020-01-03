package com.algorithm.dao;

import com.algorithm.dto.MamaUser;
import com.algorithm.dto.MamaUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MamaUserMapper {

    int deleteByPrimaryKey(String uid);

    int insert(MamaUser record);

    int insertSelective(MamaUser record);

    List<MamaUser> selectAllUsers();

    MamaUser selectByPrimaryKey(String uid);

    MamaUser selectByLoginName(@Param( "loginname" ) String loginname);

    List<MamaUser> selectByName(@Param( "name" ) String name);

    int updateByPrimaryKeySelective(MamaUser record);

    int updateByPrimaryKey(MamaUser record);
}