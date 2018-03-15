package DispacherServlet;

public class User {
	    private String id;
	    private String password;    
	    
	 	public String getId() {
	        return id;
	    }
	    public void setId(String id) {
	        this.id = id;
	    }
	   
	    public String getPwd() {
	        return password;
	    }
	    public void setPwd(String pwd) {
	        this.password = pwd;
	    }
	    
	    public User() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	    public User(String id, String pwd) {
	        super();
	        this.id = id;
	        this.password = pwd;
	        
	    }
	    public User(String account, String pwd, String name,String id) {
	        super();
	        this.password = pwd;
	        this.id = id;
	    }
	    
	
}
