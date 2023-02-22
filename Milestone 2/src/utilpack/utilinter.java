package utilpack;



import java.sql.SQLException;

public interface utilinter {
    void create(String name,String passw, int card) throws Exception;
    void myacc(int num) throws Exception;
    void signin(String name, String passw) throws Exception;
    void depo(int num,int sum) throws Exception;
    void withdraw(int num,int sum) throws SQLException;
    void transfer(int num,int num1,int sum) throws Exception;
    void delete(int num) throws Exception;
    void restore(int num) throws Exception;
    void change_username(int num, String name) throws Exception;
    void change_password(int num, String name) throws Exception;
    int cardnumgen() throws Exception;

}
