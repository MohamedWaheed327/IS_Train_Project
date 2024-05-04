package functions;

import application.variables;
import javafx.stage.Stage;

public class clear {
    public static void fun() {
        for (Stage stage : variables.openStages) {
            stage.close();
        }
    }
}
