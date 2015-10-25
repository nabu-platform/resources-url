package be.nabu.libs.resources.url;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import be.nabu.libs.resources.URIUtils;
import be.nabu.libs.resources.api.Resource;
import be.nabu.libs.resources.api.ResourceContainer;

public class URLResourceContainer extends URLResource implements ResourceContainer<URLResource> {

	public URLResourceContainer(URLResourceContainer parent, URL url) {
		super(parent, url);
	}

	@Override
	public Iterator<URLResource> iterator() {
		return new ArrayList<URLResource>().iterator();
	}

	@Override
	public URLResource getChild(String name) {
		URI child = URIUtils.getChild(getURI(), name);
		try {
			if (isFile(child)) {
				return new URLReadableResource(this, child.toURL());
			}
			else {
				return new URLResourceContainer(this, child.toURL());
			}
		}
		catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getContentType() {
		return Resource.CONTENT_TYPE_DIRECTORY;
	}
	
	public static boolean isFile(URI uri) {
		// we assume if it has an extension that it's a file
		return uri.getPath().matches(".*\\.[\\w]+$");
	}
}
