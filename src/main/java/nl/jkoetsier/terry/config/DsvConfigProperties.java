package nl.jkoetsier.terry.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties
@PropertySource("classpath:dsv_format.${dsv_format:csv}.properties")
public class DsvConfigProperties {

  private static Logger logger = LoggerFactory.getLogger(DsvConfigProperties.class);

  private String extension = "";
  private String fieldSeparator = "";
  private String quote = "";
  private int headerLines = 0;
  private String recordTerminator = "\\n";
  private String nullValue = "";

  public String getExtension() {
    return extension;
  }

  public void setDsvExtension(String extension) {
    this.extension = extension;
  }

  public String getFieldSeparator() {
    return fieldSeparator;
  }

  public void setDsvFieldSeparator(String fieldSeparator) {
    this.fieldSeparator = fieldSeparator;
  }

  public String getQuote() {
    return quote;
  }

  public void setDsvQuotes(String quotes) {
    this.quote = quotes;
  }

  public int getHeaderLines() {
    return headerLines;
  }

  public void setDsvHeaderLines(int headerLines) {
    this.headerLines = headerLines;
  }

  public String getRecordTerminator() {
    return recordTerminator;
  }

  public void setDsvRecordTerminator(String recordTerminator) {
    this.recordTerminator = recordTerminator;
  }

  public String getNullValue() {
    return nullValue;
  }

  public void setDsvNullValue(String nullValue) {
    this.nullValue = nullValue;
  }

  @Override
  public String toString() {
    return "DsvConfigProperties{" +
        "extension='" + extension + '\'' +
        ", fieldSeparator='" + fieldSeparator + '\'' +
        ", quote='" + quote + '\'' +
        ", headerLines=" + headerLines +
        ", recordTerminator='" + recordTerminator + '\'' +
        ", nullValue='" + nullValue + '\'' +
        '}';
  }
}
