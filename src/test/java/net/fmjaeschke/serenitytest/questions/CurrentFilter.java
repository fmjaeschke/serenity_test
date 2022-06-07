package net.fmjaeschke.serenitytest.questions;

import net.fmjaeschke.serenitytest.model.TodoStatusFilter;
import net.fmjaeschke.serenitytest.pageobjects.TodoList;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class CurrentFilter {
    public static Question<TodoStatusFilter> selected() {
        return Text.of(TodoList.SELECTED_FILTER)
            .describedAs("the current filter")
            .asEnum(TodoStatusFilter.class);
    }
}
