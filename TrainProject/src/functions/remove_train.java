package functions;

public class remove_train {
    public static void fun(String id) throws Exception {
        query.fun("delete from train where train_id = " + id);
        query.fun("delete from seat where train_id = " + id);
        query.fun("delete from ticket where train_id = " + id);
    }
}
