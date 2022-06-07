package net.fmjaeschke.serenitytest.features.completing_todos;

import net.fmjaeschke.serenitytest.questions.TheItemStatus;
import net.fmjaeschke.serenitytest.questions.TheItems;
import net.fmjaeschke.serenitytest.tasks.CompleteItem;
import net.fmjaeschke.serenitytest.tasks.Start;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static net.fmjaeschke.serenitytest.model.TodoStatus.Completed;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.CoreMatchers.is;

@SuppressWarnings("NewClassNamingConvention")
@ExtendWith(SerenityJUnit5Extension.class)
@WithTags({
    @WithTag("Screenplay pattern"),
    @WithTag("version:RELEASE-1"),
})
class CompleteATodo {

    private final Actor james = Actor.named("James");

    @Managed
    private WebDriver hisBrowser;

    @BeforeEach
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    void should_be_able_to_complete_a_todo() {
        givenThat(james).wasAbleTo(
            Start.withATodoListContaining("Walk the dog", "Put out the garbage")
        );
        when(james).attemptsTo(
            CompleteItem.called("Walk the dog")
        );
        then(james).should(
            seeThat(TheItemStatus.forTheItemCalled("Walk the dog"), is(Completed)),
            seeThat(TheItems.leftCount(), is(1))
        );
    }

    @Test
    void should_see_the_number_of_todos_decrease_when_an_item_is_completed() {
        givenThat(james).wasAbleTo(
            Start.withATodoListContaining("Walk the dog", "Put out the garbage")
        );
        when(james).attemptsTo(
            CompleteItem.called("Walk the dog")
        );
        then(james).should(seeThat(TheItems.leftCount(), is(1)));
    }
}