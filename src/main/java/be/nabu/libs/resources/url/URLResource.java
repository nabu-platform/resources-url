package be.nabu.libs.resources.url;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import be.nabu.libs.resources.URIUtils;
import be.nabu.libs.resources.api.LocatableResource;
import be.nabu.libs.resources.api.Resource;
import be.nabu.libs.resources.api.ResourceContainer;

abstract public class URLResource implements Resource, Closeable, LocatableResource {

	private URL url;
	private URLResourceContainer parent;

	public URLResource(URLResourceContainer parent, URL url) {
		this.parent = parent;
		this.url = url;
	}
	
	@Override
	public URI getUri() {
		try {
			return url.toURI();
		}
		catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() throws IOException {
		// do nothing
	}

	@Override
	public String getName() {
		try {
			return URIUtils.getName(url.toURI());
		}
		catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ResourceContainer<?> getParent() {
		return parent;
	}
	
	URL getURL() {
		return url;
	}
}
