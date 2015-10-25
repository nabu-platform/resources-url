package be.nabu.libs.resources.url;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import be.nabu.libs.resources.api.ResourceResolver;
import be.nabu.libs.resources.api.ResourceRoot;

public class URLResourceResolver implements ResourceResolver {

	private static List<String> defaultSchemes = Arrays.asList(new String [] { "http", "https", "ftp", "jar" });
	
	@Override
	public List<String> getDefaultSchemes() {
		return defaultSchemes;
	}

	@Override
	public ResourceRoot getResource(URI uri, Principal principal) throws IOException {
		if (defaultSchemes.contains(uri.getScheme())) {
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
