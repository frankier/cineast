package org.vitrivr.cineast.core.data.messages.components;

import java.util.function.Predicate;

import org.vitrivr.cineast.core.data.entities.MediaObjectMetadataDescriptor;

/**
 * Filter for metadata, based on the key.
 *
 * This class servers as filter descriptor and as actual filter.
 *
 * @author loris.sauter
 */
public class MetadataKeyFilter extends AbstractMetadataFilterDescriptor implements
    Predicate<MediaObjectMetadataDescriptor> {

  @Override
  public boolean test(MediaObjectMetadataDescriptor mediaObjectMetadataDescriptor) {
    return getKeywordsAsListLowercase()
        .contains(mediaObjectMetadataDescriptor.getKey().toLowerCase());
  }

  public static MetadataKeyFilter createForKeywords(String...keywords){
    MetadataKeyFilter filter = new MetadataKeyFilter();
    filter.setKeywords(keywords);
    return filter;
  }
}
