package DispacherServlet;

import java.util.List;


public interface UserDao {
    public int addUser(User user);
    public List<User> findUsers();
    public boolean checkUser(String id,String psw);
}