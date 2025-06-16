package net.fmjaeschke.serenitytest.features.record_todos;

import net.fmjaeschke.serenitytest.questions.TheItems;
import net.fmjaeschke.serenitytest.tasks.AddATodoItem;
import net.fmjaeschke.serenitytest.tasks.Start;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.annotations.WithTags;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

@SuppressWarnings("NewClassNamingConvention")
@ExtendWith(SerenityJUnit5Extension.class)
@WithTags({
    @WithTag("Screenplay pattern"),
    @WithTag("version:RELEASE-1"),
})
class AddNewTodos {

    private final Actor james = Actor.named("James");
        @Managed private WebDriver hisBrowser;
        @BeforeEach public void jamesCanBrowseTheWeb() {
            james.can(BrowseTheWeb.with(hisBrowser));
        }

        @Test
        void should_be_able_to_add_the_first_todo_item() {
            givenThat(james).wasAbleTo(Start.withAnEmptyTodoList());
            when(james).attemptsTo(AddATodoItem.called("Buy some milk"));
            then(james).should(seeThat(TheItems.displayed(), hasItem("Buy some milk")));
        }

        @Test
        void should_be_able_to_add_additional_todo_items() {
            givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));
            when(james).attemptsTo(AddATodoItem.called("Buy some milk"));
            then(james).should(seeThat(TheItems.displayed(),
                hasItems("Walk the dog", "Put out the garbage", "Buy some milk")));
        }
}
