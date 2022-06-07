package net.fmjaeschke.serenitytest.questions;

import net.fmjaeschke.serenitytest.pageobjects.TodoList;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import java.util.Collection;

public class TheItems {
    public static Question<Collection<String>> displayed() {
        return Text.ofEach(TodoList.ITEMS).describedAs("the items displayed");
    }

    public static Question<Integer> leftCount() {
        return Text.of(TodoList.ITEMS_LEFT)
            .describedAs("the number of items left")
            .asInteger();
    }
}
