package net.fmjaeschke.serenitytest.features.maintain_my_todo_list;

import net.fmjaeschke.serenitytest.questions.ClearCompletedItems;
import net.fmjaeschke.serenitytest.questions.TheItems;
import net.fmjaeschke.serenitytest.tasks.Clear;
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

import static net.fmjaeschke.serenitytest.questions.ElementAvailability.Unavailable;
import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

@SuppressWarnings("NewClassNamingConvention")
@ExtendWith(SerenityJUnit5Extension.class)
@WithTags({
    @WithTag("Screenplay pattern"),
    @WithTag("version:RELEASE-2"),
})
class ClearCompletedTodos {
    @Managed
    private WebDriver hisBrowser;
    private final Actor james = Actor.named("James");

    @BeforeEach
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    void should_be_able_to_clear_completed_todos() {
        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));
        when(james).attemptsTo(
            CompleteItem.called("Walk the dog"),
            Clear.completedItems()
        );
        then(james).should(seeThat(TheItems.displayed(), contains("Put out the garbage")));
    }

    @Test
    void should_not_be_able_to_clear_completed_todos_if_none_are_complete() {
        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));
        then(james).should(seeThat(ClearCompletedItems.option(), is(Unavailable)));
    }
}
