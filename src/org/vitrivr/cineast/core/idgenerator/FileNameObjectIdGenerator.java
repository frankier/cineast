package org.vitrivr.cineast.core.idgenerator;

import java.nio.file.Path;
import org.apache.commons.io.FilenameUtils;
import org.vitrivr.cineast.core.data.MediaType;

public class FileNameObjectIdGenerator implements ObjectIdGenerator {

  @Override
  public String next(Path path, MediaType type) {

    if (path == null) {
      return "null";
    }

    String filename = FilenameUtils.removeExtension(path.toFile().getName());
    return MediaType.generateId(type, filename);

  }
}
