package be.nabu.libs.resources.url;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.List;

import be.nabu.libs.resources.api.Resource;
import be.nabu.libs.resources.api.ResourceResolver;
import be.nabu.libs.resources.internal.VFSURLStreamHandlerFactory;

public class URLResourceResolver implements ResourceResolver {

	@Override
	public List<String> getDefaultSchemes() {
		return VFSURLStreamHandlerFactory.defaultSchemes;
	}

	@Override
	public Resource getResource(URI uri, Principal principal) throws IOException {
		if (VFSURLStreamHandlerFactory.defaultSchemes.contains(uri.getScheme())) {
			try {
				uri.toURL().openStream().close();
				return new URLReadableResource(null, uri.toURL());
			}
			catch (IOException e) {
				// do nothing
			}
		}
		return null;
	}
}
