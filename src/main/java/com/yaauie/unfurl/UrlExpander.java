package com.yaauie.unfurl;

/*
 * Copyright 2016 Ryan Biesemeyer (@yaauie)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by yaauie on 2016-08-15.
 */
public class UrlExpander {
    final CloseableHttpClient httpClient;

    public UrlExpander() {
        this(new BasicHttpClientConnectionManager());
    }

    public UrlExpander(final HttpClientConnectionManager connectionManager) {
        this.httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setRedirectStrategy(new DefaultRedirectStrategy())
                .build();
    }

    public String expand(String url) throws URISyntaxException, IOException {
        return expand(new URI(url)).toString();
    }

    public URI expand(URI uri) throws IOException {
        final HttpHead request = new HttpHead(uri);
        final HttpClientContext context = new HttpClientContext();

        httpClient.execute(request, context);

        final List<URI> redirectLocations = context.getRedirectLocations();
        if (redirectLocations == null || redirectLocations.isEmpty()) {
            return uri;
        } else {
            return redirectLocations.get(redirectLocations.size() -1);
        }
    }
}
