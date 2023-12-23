package package1;

public class displayAll {
    public static void displayAll() throws Exception {
        query.query("select * from user_db");
        query.query("select * from admin_db");
        query.query("select * from train");
        query.query("select * from seat");
        query.query("select * from ticket");
        query.query("select * from booked_tickets");
    }
}
