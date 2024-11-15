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
