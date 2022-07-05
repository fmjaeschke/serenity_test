package net.fmjaeschke.serenitytest.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class Start {

    public static Performable withAnEmptyTodoList() {
        return Task.where("{0} starts with an empty todo list",
            Open.browserOn().thePageNamed("home.page")
        );
    }

    public static Performable withATodoListContaining(String... items) {
        return Task.where("{0} starts with a todo list containing " + String.join(", ", items),
            Open.browserOn().thePageNamed("home.page"),
            AddTodoItems.called(items)
        );
    }
}
