package functions;

public class add_ticket {
    /**
     * @param ticket_id
     * @param train_id
     * @param start_station
     * @param end_station
     * @param start_time
     * @param expected_end_time
     * @param cost
     * @param seat_id
     * @throws Exception
     */
    public static void fun(String ticket_id, String train_id, String start_station, String end_station,
            String start_time, String expected_end_time, String cost, String seat_id)
            throws Exception {

        if (not_unique.fun("ticket_id", "ticket", ticket_id)) {
            return;
        }

        ticket_id = ticket_id + ",";
        train_id = train_id + ",";
        start_station = "\"" + start_station + "\",";
        end_station = "\"" + end_station + "\",";
        start_time = start_time + ",";
        expected_end_time = expected_end_time + ",";
        cost = cost + ",";
        query.fun("insert into ticket values(" + ticket_id + train_id + start_station +
                end_station + start_time + expected_end_time + cost + seat_id + ")");
    }
}