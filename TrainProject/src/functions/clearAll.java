package functions;

public class clearAll {
    public static void fun() throws Exception {
        // query.fun("delete from user_db");
        // query.fun("delete from admin_db");
        query.fun("delete from train");
        query.fun("delete from seat");
        query.fun("delete from ticket");
        query.fun("delete from booked_tickets");
    }
}
