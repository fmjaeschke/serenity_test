package net.fmjaeschke.serenitytest.features.maintain_my_todo_list;

import net.fmjaeschke.serenitytest.questions.TheItems;
import net.fmjaeschke.serenitytest.tasks.Clear;
import net.fmjaeschke.serenitytest.tasks.CompleteItem;
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

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.contains;

@SuppressWarnings("NewClassNamingConvention")
@ExtendWith(SerenityJUnit5Extension.class)
@WithTags({
    @WithTag("Screenplay pattern"),
    @WithTag("version:RELEASE-3"),
})
class TodosBelongToAUser {

    private final Actor james = Actor.named("James");
    private final Actor jane = Actor.named("Jane");

    @Managed
    private WebDriver hisBrowser;
    @Managed private WebDriver herBrowser;

    @BeforeEach
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
        jane.can(BrowseTheWeb.with(herBrowser));
    }

    @Test
    void should_not_affect_todos_belonging_to_another_user() {
        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));
        andThat(jane).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Feed the cat"));
        when(james).attemptsTo(
            CompleteItem.called("Walk the dog"),
            Clear.completedItems()
        );
        then(jane).should(seeThat(TheItems.displayed(), contains("Walk the dog", "Feed the cat")));
    }
}
