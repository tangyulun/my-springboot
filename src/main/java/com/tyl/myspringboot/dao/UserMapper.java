package com.tyl.myspringboot.dao;

import com.tyl.myspringboot.model.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {
    /**
     *
     * @mbggenerated 2018-09-24
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-09-24
     */
    int insert(User record);

    /**
     *
     * @mbggenerated 2018-09-24
     */
    int insertSelective(User record);

    /**
     *
     * @mbggenerated 2018-09-24
     */
    User selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-09-24
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *
     * @mbggenerated 2018-09-24
     */
    int updateByPrimaryKey(User record);
}