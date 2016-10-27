package com.suhaas.capstonestage2.views.fonts;


public enum FontType {

    // Be sure to sync this enum with attrs.xml -> CustomTextAppearance
    ROBOTO_LIGHT("fonts/Roboto-Light.ttf"),
    ROBOTO_BOLD("fonts/Roboto-Bold.ttf"),
    ROBOTO_REGULAR("fonts/Roboto-Regular.ttf"),
    ROBOTO_MEDIUM("fonts/Roboto-Medium.ttf");

    final String assetUrl;

    FontType(String assetUrl) {
        this.assetUrl = assetUrl;
    }

}
