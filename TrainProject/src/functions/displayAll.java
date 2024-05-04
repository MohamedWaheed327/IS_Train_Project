package functions;

public class displayAll {
    public static void fun() throws Exception {
        query.fun("select * from user_db");
        query.fun("select * from admin_db");
        query.fun("select * from train");
        query.fun("select * from seat");
        query.fun("select * from ticket");
        query.fun("select * from booked_tickets");
    }
}
