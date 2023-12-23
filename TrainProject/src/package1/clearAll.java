package package1;

public class clearAll {
    public static void clearAll() throws Exception {
        query.query("delete from user_db");
        query.query("delete from admin_db");
        query.query("delete from train");
        query.query("delete from seat");
        query.query("delete from ticket");
        query.query("delete from booked_tickets");
    }
}
