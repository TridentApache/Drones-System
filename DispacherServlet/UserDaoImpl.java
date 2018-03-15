package DispacherServlet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DispacherServlet.BaseDao;
import DispacherServlet.UserDao;
import DispacherServlet.User;

public class UserDaoImpl extends BaseDao implements UserDao {
    //添加一条用户信息
    @Override
    public int addUser(User user) {
        String update = "insert into users(ID,PASSWORD)values(?,?)";/////////////////////////uavusers
        List<Object> params = new ArrayList<Object>();
        params.add(user.getPwd());
        return this.executeUpdate(update, params);
    }

    @Override
    public List<User> findUsers() {
        List<User> result = new ArrayList<User>();
        String query = "select ID,PASSWORD from users";/////////////////////
        ResultSet rs = this.executeQuery(query, null);
        try {
            while(rs.next()){
            	String id = rs.getString("ID");
                String pwd = rs.getString("PASSWORD");
                User user = new User(id, pwd);
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
                this.close();
        }
        return result;
    }

  
    @Override
    public boolean checkUser(String id, String psw) {
        List<User> list = new ArrayList<User>();
        list = this.findUsers();
        for(User u:list){
            if(id.equals(u.getId())){
            	if(psw.equals(u.getPwd())){
                return true;}
            }
        }
        return false;
    }


}