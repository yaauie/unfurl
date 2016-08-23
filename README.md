# UnfURL

[![Build Status](https://travis-ci.org/yaauie/unfurl.svg?branch=master)](https://travis-ci.org/yaauie/unfurl)
[![Release](https://jitpack.io/v/yaauie/unfurl.svg)](https://jitpack.io/#yaauie/unfurl)

`UnfURL` is a library offering straight-forward URL expansion in Java;
it is a thin wrapper of Apache's `HttpClient`.

## Usage:

First, initialize your `UrlExpander`:

``` java
final UrlExpander urlExpander = new UrlExpander();
```

Then, expand `String` links:
``` java
final String result = urlExpander.expand(shortUrlString);
```

Or expand `URI` links:
``` java
final URI result = urlExpander.expand(shortURI);
```

## Advanced Usage

Each `UrlExpander` can be initialized with `HttpClientConnectionManager`
in order to reuse http connections across requests; for example,
the following would create a connection pool that reused up to 10
connections per host, with up to 100 concurrently-alive connections
at any given time:

``` java
final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(10);  
        connectionManager.setMaxTotal(100);

final UrlExpander = new UrlExpander(connectionManager);
```
