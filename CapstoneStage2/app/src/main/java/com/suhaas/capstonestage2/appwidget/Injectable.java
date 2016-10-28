package com.suhaas.capstonestage2.appwidget;


public interface Injectable {
    /**
     * Injects the members of given object, including injectable members
     * inherited from its supertypes.
     * @param object object with members to be injected
     */
    void inject(Object object);
}
