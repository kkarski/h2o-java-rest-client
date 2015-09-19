package water.bindings;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import water.bindings.pojos.FrameKeyV3;
import water.bindings.pojos.ImportFilesV3;
import water.bindings.pojos.ParseSetupV3;
import water.bindings.pojos.ParseV3;

public class CreateFrameV3ITCase {

	private H2ORestClient client;
	private ImportFilesV3 files;
	private ParseSetupV3 setup;
	private ParseV3 parse;

	@BeforeClass
	public void setup() {
		client = new H2ORestClient("http://localhost:54321");
	}

	@Test
	public void importFiles() throws Exception {

		File f = new File(this.getClass().getResource("/allyears2k.csv")
				.toURI());
		Assert.assertTrue(f.exists());
		Assert.assertTrue(f.isFile());

		files = client.importFiles(f.getPath());

		Assert.assertNotNull(files);
		Assert.assertNotNull(files.destination_frames);

	}

	@Test(dependsOnMethods = { "importFiles" })
	public void parseFiles() throws Exception {

		Assert.assertNotNull(files);

		String paths = "\"" + files.destination_frames[0] + "\"";

		setup = client.guessSetup(new String[] { paths });

		Assert.assertNotNull(setup);
	}

	@Test(dependsOnMethods = { "parseFiles" })
	public void setupParse() throws Exception {

		Assert.assertNotNull(setup);

		ParseV3 p = new ParseV3();
		p.source_frames = setup.source_frames;
		p.chunk_size = setup.chunk_size;
		p._exclude_fields = setup._exclude_fields;
		p.blocking = false;
		p.check_header = setup.check_header;
		p.column_names = setup.column_names;
		p.column_types = setup.column_types;
		p.delete_on_done = true;
		p.destination_frame = new FrameKeyV3();
		p.destination_frame.name = setup.destination_frame;
		p.number_columns = setup.number_columns;
		p.parse_type = setup.parse_type;
		p.remove_frame = false;
		p.separator = setup.separator;
		p.single_quotes = setup.single_quotes;

		parse = client.parse(p);
		Assert.assertNotNull(parse);
	}
}
