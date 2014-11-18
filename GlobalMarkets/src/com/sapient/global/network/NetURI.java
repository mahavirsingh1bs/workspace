package com.sapient.global.network;

import java.net.URI;
import java.net.URISyntaxException;

public class NetURI {

	public static void main(String...args) throws URISyntaxException {
		URI voice = new URI("tel:+1-800-9988-9938");
		URI web   = new URI("http://www.xml.com/pub/a/2003/09/17/stax.html#id=_hbc");
		URI book  = new URI("urn:isbn:1-565-92870-9");
		System.out.println(voice + " " + web + " " + book);
		URI absolute = new URI("http", "//www.ibiblio.org" , null);
		URI relative = new URI(null, "/javafaq/index.shtml", "today");
		System.out.println(absolute + " " + relative);
		URI today= new URI("http", "www.ibiblio.org", "/javafaq/index.html", "today");
	}
}
