package com.example.heroku.repository.util;

import java.text.MessageFormat;

public final class RepositoryUtils {

    public static String getLikeTextValue(String searchText) {
        return MessageFormat.format("%{0}%", searchText).toLowerCase();
    }
}