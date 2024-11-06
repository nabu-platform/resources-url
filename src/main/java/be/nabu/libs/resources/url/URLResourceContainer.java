/*
* Copyright (C) 2015 Alexander Verbruggen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

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
		URI child = URIUtils.getChild(getUri(), name);
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
