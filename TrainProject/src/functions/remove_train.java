package functions;

public class remove_train {
    public static void remove_train(String id) throws Exception {
        query.query("delete from train where train_id = " + id);
        query.query("delete from seat where train_id = " + id);
        query.query("delete from ticket where train_id = " + id);
    }
}
