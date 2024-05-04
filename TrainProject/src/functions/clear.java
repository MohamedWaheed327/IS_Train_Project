package functions;

import application.variables;
import javafx.stage.Stage;

public class clear {
    public static void clear() {
        for (Stage stage : variables.openStages) {
            stage.close();
        }
    }
}
