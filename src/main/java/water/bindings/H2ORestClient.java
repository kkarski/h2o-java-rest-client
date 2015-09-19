package water.bindings;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import water.bindings.pojos.ImportFilesV3;
import water.bindings.pojos.ParseSetupV3;
import water.bindings.pojos.ParseV3;
import water.bindings.proxies.jaxrs.ImportFiles;
import water.bindings.proxies.jaxrs.Parse;
import water.bindings.proxies.jaxrs.ParseSetup;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.common.collect.Lists;

public class H2ORestClient {

	private final String url;

	public H2ORestClient(final String url) {

		this.url = url;
	}

	ImportFilesV3 importFiles(String path) {

		ImportFiles store = JAXRSClientFactory.create(url, ImportFiles.class,
				Lists.newArrayList(JacksonJsonProvider.class));
		return store.importFiles(path);

	}

	ParseSetupV3 guessSetup(String[] source_frames) {

		ParseSetup store = JAXRSClientFactory.create(url, ParseSetup.class,
				Lists.newArrayList(JacksonJsonProvider.class));
		return store.guessSetup(source_frames);

	}

	ParseV3 parse(ParseV3 p) throws Exception {

		Parse store = JAXRSClientFactory.create(url, Parse.class,
				Lists.newArrayList(JacksonJsonProvider.class));

		MultivaluedHashMap<String, String> vals = new MultivaluedHashMap<String, String>();
		vals.add("source_frames", toArray(p.source_frames[0].name));
		vals.add("chunk_size", String.valueOf(p.chunk_size));
		vals.add("destination_frame", p.destination_frame.name);
		vals.add("number_columns", String.valueOf(p.number_columns));
		vals.addAll("column_names", toArray(p.column_names));
		vals.addAll("column_types", toArray(p.column_types));
		vals.add("separator", String.valueOf(p.separator));
		vals.add("check_header", String.valueOf(p.check_header));
		vals.add("delete_on_done", String.valueOf(p.delete_on_done));
		vals.add("parse_type", p.parse_type.name());
		vals.add("single_quotes", String.valueOf(p.single_quotes));
		return store.parse(vals);

	}

	private String toArray(String... strings) {
		StringBuffer buffer = new StringBuffer("[");

		int leng = strings.length;
		int i = 1;
		for (String s : strings) {
			buffer.append("\"").append(s).append("\"");
			if (i < leng) {
				buffer.append(",");
			}
			i++;

		}
		buffer.append("]");
		return buffer.toString();
	}
}
