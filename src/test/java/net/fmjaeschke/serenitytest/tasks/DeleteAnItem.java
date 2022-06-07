package net.fmjaeschke.serenitytest.tasks;

import net.fmjaeschke.serenitytest.action.JSClick;
import net.fmjaeschke.serenitytest.pageobjects.TodoListItem;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class DeleteAnItem {
    public static Performable called(String itemName) {
        return Task.where("{0} deletes the item " + itemName,
            JSClick.on(TodoListItem.DELETE_ITEM.of(itemName))
        );
    }
}
