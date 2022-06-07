package net.fmjaeschke.serenitytest.pageobjects;

import net.serenitybdd.screenplay.targets.Target;

public class TodoList {
    public static final Target WHAT_NEEDS_TO_BE_DONE = Target.the("'What needs to be done?' field").locatedBy(".new-todo");
    public static final Target ITEMS = Target.the("List of todo items").locatedBy(".view label");
    public static final Target ITEMS_LEFT = Target.the("Count of items left").locatedBy(".todo-count strong");
    public static final Target CLEAR_COMPLETED = Target.the("Clear completed link").locatedBy(".clear-completed");
    public static final Target FILTER = Target.the("filter by {0}").locatedBy("//a[.='{0}']");
    public static final Target SELECTED_FILTER = Target.the("selected filter").locatedBy(".filters li .selected");
}
