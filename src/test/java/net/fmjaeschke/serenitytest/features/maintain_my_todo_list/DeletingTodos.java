package net.fmjaeschke.serenitytest.features.maintain_my_todo_list;

import net.fmjaeschke.serenitytest.questions.TheItems;
import net.fmjaeschke.serenitytest.tasks.DeleteAnItem;
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

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.*;

@SuppressWarnings("NewClassNamingConvention")
@ExtendWith(SerenityJUnit5Extension.class)
@WithTags({
    @WithTag("Screenplay pattern"),
    @WithTag("version:RELEASE-1"),
})
class DeletingTodos {
    @Managed
    private WebDriver hisBrowser;

    private final Actor james = Actor.named("James");

    @BeforeEach
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    void should_be_able_to_delete_todos() {
        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));
        when(james).attemptsTo(DeleteAnItem.called("Walk the dog"));
        then(james).should(seeThat(TheItems.displayed(), contains("Put out the garbage")));
    }

    @Test
    void should_see_deleting_a_todo_decreases_the_remaining_items_count() {
        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));
        when(james).attemptsTo(
            DeleteAnItem.called("Walk the dog")
        );
        then(james).should(seeThat(TheItems.leftCount(), is(1)));
    }
}
