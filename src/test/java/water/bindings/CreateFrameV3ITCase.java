package water.bindings;

import java.io.File;
import java.util.concurrent.Future;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.appx.h2o.H2ORestClient;

import water.bindings.pojos.FrameKeyV3;
import water.bindings.pojos.ImportFilesV3;
import water.bindings.pojos.ParseSetupV3;
import water.bindings.pojos.ParseV3;
import water.bindings.pojos.SplitFrameV3;

@Test(singleThreaded = true)
public class CreateFrameV3ITCase {

  private H2ORestClient client;
  private ParseSetupV3 setup;
  private ParseSetupV3 setup2;
  private ParseV3 parse;

  @BeforeClass
  public void setup() {
    client = new H2ORestClient("http://localhost:54321");
  }

  private ImportFilesV3 load() throws Exception {

    File f = new File(this.getClass().getResource("/allyears2k.csv").toURI());
    Assert.assertTrue(f.exists());
    Assert.assertTrue(f.isFile());

    ImportFilesV3 files = client.importFiles(f.getPath());

    Assert.assertNotNull(files);
    Assert.assertNotNull(files.destination_frames);

    return files;

  }

  @Test(priority = 1)
  public void guessSetup() throws Exception {
    ImportFilesV3 files = load();
    setup = client.guessSetup(files.destination_frames);
    Assert.assertNotNull(setup);
  }

  @Test(priority = 2)
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

    Future<ParseV3> parse = client.parse(p);
    Assert.assertNotNull(parse.get());
  }


  @Test(priority = 3)
  public void guessSetupAsObject() throws Exception {
    ImportFilesV3 files = load();
    setup2 = client.guessSetup(files);
    Assert.assertNotNull(setup2);
  }

  @Test(priority = 4)
  public void parseWithSetup() throws Exception {
    Assert.assertNotNull(setup2);
    Future<ParseV3> parsev3 = client.parse(setup2);
    Assert.assertNotNull(parsev3.get());
    parse = parsev3.get();
  }

  @Test(priority = 5)
  public void testSplitFrames() throws Exception {

    Assert.assertNotNull(parse);
    Future<SplitFrameV3> frames = client.splitFrame(parse, new Double[] {.25, .75});
    Assert.assertNotNull(frames.get());                

  }
}
