package con.java.con;

/**
 * Created by Yisa on 2017/2/19.
 */
public class OSService implements ListService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
