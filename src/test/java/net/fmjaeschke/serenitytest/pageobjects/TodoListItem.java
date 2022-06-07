package net.fmjaeschke.serenitytest.pageobjects;

import net.serenitybdd.screenplay.targets.Target;

public class TodoListItem {

    public static final Target
        COMPLETE_ITEM = Target.the("the complete item tick box")
        .locatedBy("//*[@class='view' and contains(.,'{0}')]//input[@type='checkbox']");

    public static final Target DELETE_ITEM   = Target.the("the delete item button")
        .locatedBy("//*[@class='view' and contains(.,'{0}')]//button[@class='destroy']");
}
