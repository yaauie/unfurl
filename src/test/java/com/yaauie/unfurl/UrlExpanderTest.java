package com.yaauie.unfurl;

import org.junit.Assert;

/**
 * Created by yaauie on 2016-08-16.
 */
public class UrlExpanderTest {
    protected final UrlExpander urlExpander = new UrlExpander();

    @org.junit.Test
    public void testExpand() throws Exception {
        // http://ow.ly/3JB8303gXek ->
        // http://bit.ly/rules-for-10k ->
        // https://gist.github.com/yaauie/023e3c832b88175747bb
        Assert.assertEquals("https://gist.github.com/yaauie/023e3c832b88175747bb", urlExpander.expand("http://ow.ly/3JB8303gXek"));
    }
}