package org.vitrivr.cineast.core.data.messages.lookup;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import java.util.List;
import org.vitrivr.cineast.core.data.messages.components.AbstractMetadataFilterDescriptor;
import org.vitrivr.cineast.core.data.messages.interfaces.Message;
import org.vitrivr.cineast.core.data.messages.interfaces.MessageType;

/**
 * Ignores unkown json properties, e.g. may contain no filter at all.
 *
 * @author loris.sauter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OptionallyFilteredIdList implements Message {

  /*
  https://layer4.fr/blog/2013/08/19/how-to-map-unknown-json-properties-with-jackson/
  how to still realise that there are unkown properties
   */

  private AbstractMetadataFilterDescriptor[] filters;

  private String[] ids;

  /**
   * This default constructor is required for deserialization by fasterxml/jackson.
   */
  public OptionallyFilteredIdList() {
  }

  public String[] getIds(){
    return this.ids;
  }

  public List<String> getIdList(){ return Arrays.asList(this.ids);}

  @Override
  public MessageType getMessageType() {
    return null;
  }

  public AbstractMetadataFilterDescriptor[] getFilters() {
    return filters;
  }

  @JsonIgnore
  public boolean hasFilters(){
    return filters != null;
  }

  public void setFilters(
      AbstractMetadataFilterDescriptor[] filters) {
    this.filters = filters;
  }


  public List<AbstractMetadataFilterDescriptor> getFilterList(){
    return Arrays.asList(this.filters);
  }
}
