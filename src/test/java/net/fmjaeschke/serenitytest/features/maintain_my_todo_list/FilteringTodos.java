package net.fmjaeschke.serenitytest.features.maintain_my_todo_list;

import net.fmjaeschke.serenitytest.questions.CurrentFilter;
import net.fmjaeschke.serenitytest.questions.TheItems;
import net.fmjaeschke.serenitytest.tasks.CompleteItem;
import net.fmjaeschke.serenitytest.tasks.FilterItems;
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

import static net.fmjaeschke.serenitytest.model.TodoStatusFilter.Active;
import static net.fmjaeschke.serenitytest.model.TodoStatusFilter.All;
import static net.fmjaeschke.serenitytest.model.TodoStatusFilter.Completed;
import static net.serenitybdd.screenplay.GivenWhenThen.and;
import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@SuppressWarnings("NewClassNamingConvention")
@ExtendWith(SerenityJUnit5Extension.class)
@WithTags({
    @WithTag("Screenplay pattern"),
    @WithTag("version:RELEASE-2"),
})
class FilteringTodos {
    @Managed
    private WebDriver hisBrowser;

    private final Actor james = Actor.named("James");

    @BeforeEach
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    void should_be_able_to_view_only_completed_todos() {
        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));
        when(james).attemptsTo(
            CompleteItem.called("Walk the dog"),
            FilterItems.toShow(Completed)
        );
        then(james).should(seeThat(TheItems.displayed(), contains("Walk the dog")));
        and(james).should(seeThat(TheItems.displayed(), not(contains("Put out the garbage"))));
        and(james).should(seeThat(CurrentFilter.selected(), is(Completed)));
    }

    @Test
    void should_be_able_to_view_only_incomplete_todos() {
        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));
        when(james).attemptsTo(
            CompleteItem.called("Walk the dog"),
            FilterItems.toShow(Active)
        );
        then(james).should(seeThat(TheItems.displayed(), contains("Put out the garbage")));
        and(james).should(seeThat(TheItems.displayed(), not(contains("Walk the dog"))));
        and(james).should(seeThat(CurrentFilter.selected(), is(Active)));
    }

    @Test
    void should_be_able_to_view_both_complete_and_incomplete_todos() {
        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));
        when(james).attemptsTo(
            CompleteItem.called("Walk the dog"),
            FilterItems.toShow(Active),
            FilterItems.toShow(All)
        );
        then(james).should(seeThat(TheItems.displayed(), contains("Walk the dog", "Put out the garbage")));
        and(james).should(seeThat(CurrentFilter.selected(), is(All)));
    }
}
