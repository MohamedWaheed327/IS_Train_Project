package functions;

public class add_user {
    public static void fun(String national_id, String user_name_, String user_password, String user_email,
            String user_phone, String gender) throws Exception {
        if (not_unique.fun("user_name_", "user_db", "\"" + user_name_ + "\"")) {
            return;
        }
        national_id = "\"" + national_id + "\",";
        user_name_ = "\"" + user_name_ + "\",";
        user_password = "\"" + user_password + "\",";
        user_email = "\"" + user_email + "\",";
        user_phone = "\"" + user_phone + "\",";
        gender = "\"" + gender + "\"";
        query.fun("insert into user_db values(" + national_id + user_name_ + user_password + user_email + user_phone +
                gender + ")");
    }
}
