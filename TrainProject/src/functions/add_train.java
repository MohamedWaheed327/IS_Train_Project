package functions;

public class add_train {
    /**
     * @param train_id
     * @param seats_number
     * @param start_station
     * @param end_station
     * @throws Exception
     */
    public static void add_train(String train_id, String seats_number, String start_station, String end_station)
            throws Exception {

        if (not_unique.not_unique("train_id", "train", train_id)) {
            return;
        }
        int N = Integer.valueOf(seats_number);

        train_id = train_id + ",";
        seats_number = seats_number + ",";
        start_station = "\"" + start_station + "\",";
        end_station = "\"" + end_station + "\",";
        query.query("insert into train values(" + train_id + seats_number + start_station + end_station + seats_number
                + "0" + ")");

        for (int i = 1; i <= N; i++) {
            query.query("insert into seat values(" + Integer.toString(i) + "," + train_id + "false" + ")");
        }
    }
}
