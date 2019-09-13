package com.jamie.jamapp;

import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;

public final class JsonLoader extends JSONParser
{
    public JsonLoader(String source, Global global, boolean dualFields) {
        super(source, global, dualFields);
    }
}
