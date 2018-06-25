package nl.jkoetsier.uva.dbbench.connector.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.HashMap;
import nl.jkoetsier.uva.dbbench.connector.DatabaseConnector;
import nl.jkoetsier.uva.dbbench.connector.mongodb.workload.MongoDbWorkloadVisitor;
import nl.jkoetsier.uva.dbbench.connector.util.exception.DatabaseException;
import nl.jkoetsier.uva.dbbench.internal.ExecutableQuery;
import nl.jkoetsier.uva.dbbench.internal.QueryResult;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoDbDatabaseConnector extends DatabaseConnector {

  private static Logger logger = LoggerFactory.getLogger(MongoDbDatabaseConnector.class);

  private MongoClient mongoClient;
  private MongoDatabase database;

  @Override
  public void connect() throws DatabaseException {
    mongoClient = new MongoClient(dbConfigProperties.getHost(), dbConfigProperties.getPort());
    database = mongoClient.getDatabase(dbConfigProperties.getDatabase());
  }

  @Override
  public void executeQuery(ExecutableQuery query) throws DatabaseException {

  }

  @Override
  public void closeConnection() {
    mongoClient.close();
  }

  @Override
  public String getSimpleName() {
    return "mongodb";
  }

  @Override
  public QueryResult getLastResults() throws DatabaseException {
    return null;
  }

  @Override
  public void importSchema(Schema schema) throws DatabaseException {
    // Schemaless, do nothing
  }

  @Override
  protected void importCsvFile(String tableName, String file) throws DatabaseException {
    throw new RuntimeException("Not implemented for MongoDB. Use bash script instead (see helpers repo)");
  }

  @Override
  public long getTableSize(String tableName) throws DatabaseException {
    MongoCollection<Document> collection = database.getCollection(tableName);
    return collection.count();
  }


  @Override
  public HashMap<String, ExecutableQuery> getWorkloadQueries(Workload workload) {
    MongoDbWorkloadVisitor visitor = new MongoDbWorkloadVisitor();
    workload.acceptVisitor(visitor);
    return visitor.getResult();
  }

  @Override
  public HashMap<String, ExecutableQuery> getCreateQueries(Schema schema) {
    // Schemaless, return empty HashMap
    return new HashMap<>();
  }


  @Override
  public void translateQueryResults(QueryResult queryResult, QueryResult expectedResult) {

  }
}
