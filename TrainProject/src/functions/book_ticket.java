package functions;

public class book_ticket {
    public static void book_ticket(String ticket_id, String user_name_) throws Exception {
        if (not_unique.not_unique("ticket_id", "booked_tickets", ticket_id)) {
            return;
        }
        ticket_id = ticket_id + ",";
        user_name_ = "\"" + user_name_ + "\"";
        query.query("insert into booked_tickets values(" + ticket_id + user_name_ + ")");
    }
}
