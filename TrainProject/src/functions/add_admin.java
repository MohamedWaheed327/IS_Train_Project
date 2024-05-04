package functions;

public class add_admin {
    public static void fun(String user_name_, String user_password) throws Exception {
        if (not_unique.fun("user_name_", "admin_db", "\"" + user_name_ + "\"")) {
            System.out.println("this user name is already taken");
            return;
        }
        user_name_ = "\"" + user_name_ + "\",";
        user_password = "\"" + user_password + "\"";
        query.fun("insert into admin_db values(" + user_name_ + user_password + ")");
    }
}