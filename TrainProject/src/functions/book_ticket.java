package functions;

public class book_ticket {
    public static void fun(String ticket_id, String user_name_) throws Exception {
        if (not_unique.fun("ticket_id", "booked_tickets", ticket_id)) {
            return;
        }
        ticket_id = ticket_id + ",";
        user_name_ = "\"" + user_name_ + "\"";
        query.fun("insert into booked_tickets values(" + ticket_id + user_name_ + ")");
    }
}
