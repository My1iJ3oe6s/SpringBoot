package cn.joe.basetest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service  //標記service
public class UserService {

    @Autowired
    // 注入Spring容器中的bean对象
    private UserDAO userDAO;

    @Autowired
    SpringConfig springConfig;

    @Autowired
    @Qualifier("user1")
    User user;

    public List<User> queryUserList() {
        Integer age = user.getAge();

        System.out.println("###### age : " + age);

        // 调用userDAO中的方法进行查询
        return this.userDAO.queryUserList();
    }

}
