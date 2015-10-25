package be.nabu.libs.resources.url;

import java.io.IOException;
import java.net.URL;

import be.nabu.libs.resources.api.ReadableResource;
import be.nabu.utils.io.ContentTypeMap;
import be.nabu.utils.io.IOUtils;
import be.nabu.utils.io.api.ByteBuffer;
import be.nabu.utils.io.api.ReadableContainer;

public class URLReadableResource extends URLResource implements ReadableResource {

	public URLReadableResource(URLResourceContainer parent, URL url) {
		super(parent, url);
	}

	@Override
	public String getContentType() {
		return ContentTypeMap.getInstance().getContentTypeFor(getURL().getPath());
	}

	@Override
	public ReadableContainer<ByteBuffer> getReadable() throws IOException {
		return IOUtils.wrap(getURL().openStream());
	}

}
