package package1;

public class add_ticket {
    public static void add_ticket(String ticket_id, String train_id, String start_station, String end_station,
            String start_time, String expected_end_time, String cost, String seat_id)
            throws Exception {

        if (not_unique.not_unique("ticket_id", "ticket", ticket_id)) {
            return;
        }

        ticket_id = ticket_id + ",";
        train_id = train_id + ",";
        start_station = "\"" + start_station + "\",";
        end_station = "\"" + end_station + "\",";
        start_time = "\"" + start_time + "\",";
        expected_end_time = "\"" + expected_end_time + "\",";
        cost = cost + ",";
        query.query("insert into ticket values(" + ticket_id + train_id + start_station +
                end_station + start_time + expected_end_time + cost + seat_id + ")");
    }
}
