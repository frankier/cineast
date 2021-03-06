package org.vitrivr.cineast.core.importer.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.vitrivr.cineast.core.features.OCRSearch;
import org.vitrivr.cineast.core.importer.PlainTextImporter;
import org.vitrivr.cineast.core.util.LogHelper;

import java.io.IOException;
import java.nio.file.Path;

public class OcrDataImportHandler extends DataImportHandler {

  /** */
  private static final Logger LOGGER = LogManager.getLogger();

  /**
   *
   * @param threads
   * @param batchsize
   */
  public OcrDataImportHandler(int threads, int batchsize) {
    super(threads, batchsize);
  }

  /**
   *
   * @param path
   */
  @Override
  public void doImport(Path path) {
    try {
      this.futures.add(this.service.submit(new DataImportRunner(new PlainTextImporter(path), OCRSearch.OCR_TABLE_NAME, "ocr")));
    } catch (IOException e) {
      LOGGER.error("Could not start data import process with path '{}' due to an IOException: {}. Aborting...", path.toString(), LogHelper.getStackTrace(e));
    }
  }
}
