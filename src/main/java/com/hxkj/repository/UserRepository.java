package com.hxkj.repository;


import com.hxkj.domain.User;

/**
 * @author dsd
 * @version 2018/6/21 11:36
 */
public interface UserRepository extends BaseRepository<User, Long> {

    User findFirstByUsernameEquals(String username);

}
