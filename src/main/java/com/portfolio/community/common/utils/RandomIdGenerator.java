package com.portfolio.community.common.utils;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

public class RandomIdGenerator {
    public static String generate(){
        return NanoIdUtils.randomNanoId(
                NanoIdUtils.DEFAULT_NUMBER_GENERATOR,
                NanoIdUtils.DEFAULT_ALPHABET,
                8);
    }
}
