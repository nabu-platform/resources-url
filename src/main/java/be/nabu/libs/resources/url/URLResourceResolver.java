package be.nabu.libs.resources.url;

import java.io.IOException;
import java.net.MalformedURLException;
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
				if (URLResourceContainer.isFile(uri)) {
					return new URLReadableResource(null, uri.toURL());
				}
				else {
					return new URLResourceContainer(null, uri.toURL());
				}
			}
			catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
}
