package DispacherServlet;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * ���ݿ������
 * @author wangle
 *
 */

public class BaseDao {

    //���ݿ��ַ��jdbc:mysql://����������:�˿ں�/���ݿ����ơ�
    private String url = "jdbc:mysql://localhost:3306/uavusers";
    //�û���
    private String user = "root";
    //�û�����
    private String pwd = "1234";
    //���ݿ����Ӷ���
    private java.sql.Connection conn;
    //���ݿ�����ִ�ж���
    private PreparedStatement pstmt;
    //���ݿⷵ�ؽ��
    private java.sql.ResultSet rs;
    
    //��̬�����
    static{
        //1����������
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        	System.out.println("Class Not Found!!!!!!!!!");
            e.printStackTrace();
        }
    }
    
    //2����������
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
    
    //ִ�ж���������
    public java.sql.ResultSet executeQuery(String query,
                    List<Object> params){
        getConnection();
        try {
            //3����������ִ�ж���
            pstmt = conn.prepareStatement(query);
            //4��ִ��
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
    
    //ִ��д��������
    public int executeUpdate(String query,
            List<Object> params){
        int result = 0;
        getConnection();
        try {
            //3����������ִ�ж���
            pstmt = conn.prepareStatement(query);
            //4��ִ��
            if(params!=null && params.size()>0){
                for(int i=0;i<params.size();i++){
                    pstmt.setObject(i+1, params.get(i));
                }
            }
            //5��������
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //6���ͷ���Դ
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
    
    //�ر���Դ
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