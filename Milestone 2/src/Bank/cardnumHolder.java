package Bank;



import java.sql.ResultSet;
import java.sql.SQLException;
import Singleton.*;
//current user card number
public class cardnumHolder {
    static singleton d = singleton.getInstance();
    public static int getCardNum(String username, String password) throws SQLException {
        d.connect();
        String givenum = "select cardnumber from users where username='"+username+"' and password='"+password+"';";
        ResultSet r = d.result(givenum);
        if(r.next()){
            return r.getInt("cardnumber");
        }
        else{
            return 0;
        }

    }
}