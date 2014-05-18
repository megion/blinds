package com.megion.site.blinds.web.mapper;

import info.magnolia.module.blossom.annotation.VirtualURIMapper;

import javax.servlet.http.HttpServletRequest;

/**
 * URIMapper that maps /about to /sections/about
 */
@VirtualURIMapper
public class SampleURIMapper {

    public String mapper(String uri, HttpServletRequest request) {
        if (uri.equals("/")) {
            return "/home";
        }
        return null;
    }
}
