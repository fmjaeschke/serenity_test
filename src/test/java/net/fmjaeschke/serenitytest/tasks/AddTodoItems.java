package net.fmjaeschke.serenitytest.tasks;

import com.google.common.collect.ImmutableList;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import java.util.Collection;
import java.util.List;

public class AddTodoItems implements Task {

    private final List<String> todos;

    public AddTodoItems(Collection<String> items) { this.todos = ImmutableList.copyOf(items); }

    @Step("{0} adds the todo items called: #todos")
    public <T extends Actor> void performAs(T actor) {
        todos.forEach(
            todo -> actor.attemptsTo(AddATodoItem.called(todo))
        );
    }

    public static AddTodoItems called(Collection<String> items) {
        return new AddTodoItems(items);
    }
}
