package com.cmcc.syw.dao;

import com.cmcc.syw.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
<<<<<<< f684d74ce6b4c0b92c0fa2cea989d8ceef4903e1
    int delete(String guid);

    int insert(User record);

    User get(String guid);

    User getByName(String name);
=======
    int insert(User user);

    int batchInsert(@Param("users") List<User> users);
>>>>>>> add thread learning code
}