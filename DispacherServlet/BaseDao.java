package DispacherServlet;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库操作类
 * @author wangle
 *
 */

public class BaseDao {

    //数据库地址“jdbc:mysql://服务器域名:端口号/数据库名称”
    private String url = "jdbc:mysql://localhost:3306/uavusers";
    //用户名
    private String user = "root";
    //用户密码
    private String pwd = "1234";
    //数据库链接对象
    private java.sql.Connection conn;
    //数据库命令执行对象
    private PreparedStatement pstmt;
    //数据库返回结果
    private java.sql.ResultSet rs;
    
    //静态代码块
    static{
        //1、加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        	System.out.println("Class Not Found!!!!!!!!!");
            e.printStackTrace();
        }
    }
    
    //2、创建连接
    private void getConnection(){
        if(conn == null){
            try {
                conn = DriverManager.getConnection(url, user, pwd);
            } catch (SQLException e) {
            	System.out.println("Connect Failed!!!!!!!!!");
                e.printStackTrace();
            }
        }
    }
    
    //执行读操作方法
    public java.sql.ResultSet executeQuery(String query,
                    List<Object> params){
        getConnection();
        try {
            //3、创建命令执行对象
            pstmt = conn.prepareStatement(query);
            //4、执行
            if(params!=null && params.size()>0){
                for(int i=0;i<params.size();i++){
                    pstmt.setObject(i+1, params.get(i));
                }
            }
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
        	System.out.println("Query Failed!!!!!!!!!");
            e.printStackTrace();
        }
        return rs;
    }
    
    //执行写操作方法
    public int executeUpdate(String query,
            List<Object> params){
        int result = 0;
        getConnection();
        try {
            //3、创建命令执行对象
            pstmt = conn.prepareStatement(query);
            //4、执行
            if(params!=null && params.size()>0){
                for(int i=0;i<params.size();i++){
                    pstmt.setObject(i+1, params.get(i));
                }
            }
            //5、处理结果
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //6、释放资源
            this.close();
        }
        return result;
    }
/*String sql="insert into t_user(username,pwd,regTime) values(?,?,?)";
PreparedStatement pst=conn.prepareStatement(sql);
pst.setString(1,"aa");
pst.setString(2,"123");
pst.setDate(3,new java.sql.Date(System.currentTimeMillis()));
pst.setObject(1,"aa");
pst.setObject(2,"123");
pst.setObject(3,new java.sql.Date(System.currentTimeMillis()));
*/
    
    //关闭资源
    public void close(){        
            try {
                if(rs!=null){
                    rs.close();
                    rs = null;
                }
                if(pstmt!=null){
                    pstmt.close();
                    pstmt = null;
                }
                if(conn!=null){
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
        }

}