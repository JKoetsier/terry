package nl.jkoetsier.terry.connector.mongodb;

import com.mongodb.BasicDBObject;
import nl.jkoetsier.terry.intrep.ExecutableQuery;
import nl.jkoetsier.terry.intrep.workload.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoDbQuery extends ExecutableQuery {

  private static Logger logger = LoggerFactory.getLogger(MongoDbQuery.class);

  private String collection;
  private BasicDBObject find;
  private BasicDBObject projection;
  private Long limit;
  private Long offset;
  private BasicDBObject sort;

  public MongoDbQuery(Query queryObject) {
    super(queryObject);
  }


  public String getCollection() {
    return collection;
  }

  public void setCollection(String collection) {
    this.collection = collection;
  }

  public BasicDBObject getFind() {
    return find;
  }

  public void setFind(BasicDBObject find) {
    this.find = find;
  }

  public BasicDBObject getProjection() {
    return projection;
  }

  public void setProjection(BasicDBObject projection) {
    this.projection = projection;
  }

  public Long getLimit() {
    return limit;
  }

  public void setLimit(Long limit) {
    this.limit = limit;
  }

  public Long getOffset() {
    return offset;
  }

  public void setOffset(Long offset) {
    this.offset = offset;
  }

  public BasicDBObject getSort() {
    return sort;
  }

  public void setSort(BasicDBObject sort) {
    this.sort = sort;
  }
}
