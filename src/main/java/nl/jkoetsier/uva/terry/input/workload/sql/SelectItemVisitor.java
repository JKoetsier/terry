package nl.jkoetsier.uva.terry.input.workload.sql;

import java.util.ArrayList;
import java.util.List;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitorAdapter;
import nl.jkoetsier.uva.terry.input.util.StringUtil;
import nl.jkoetsier.uva.terry.internal.workload.expression.SelectAllColumnsExpression;
import nl.jkoetsier.uva.terry.internal.workload.expression.SelectExpression;
import nl.jkoetsier.uva.terry.internal.workload.query.Projection;
import nl.jkoetsier.uva.terry.internal.workload.query.Selection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectItemVisitor extends SelectItemVisitorAdapter {

  private static Logger logger = LoggerFactory.getLogger(SelectItemVisitor.class);

  private Selection selection;
  private List<SelectExpression> selectExpressions = new ArrayList<>();
  private Projection returnProjection;

  public SelectItemVisitor(Selection selection) {
    this.selection = selection;
    this.returnProjection = new Projection();
    this.returnProjection.setInput(selection);
  }

  public Projection getRelation() {
    if (selectExpressions.size() > 0) {
      returnProjection.setSelectExpressions(selectExpressions);
    }

    return returnProjection;
  }

  @Override
  public void visit(AllColumns columns) {
    // Set nothing, project everything
    returnProjection.setInput(selection);
  }

  @Override
  public void visit(AllTableColumns columns) {
    SelectAllColumnsExpression selectAllColumnsExpression = new SelectAllColumnsExpression(
        StringUtil.unEscapeIdentifier(columns.getTable().getName()));
    SelectExpression selectExpression = new SelectExpression(selectAllColumnsExpression);

    selectExpressions.add(selectExpression);
  }

  @Override
  public void visit(SelectExpressionItem item) {
    ExpressionVisitor expressionVisitor = new ExpressionVisitor();
    item.getExpression().accept(expressionVisitor);

    SelectExpression selectExpression = new SelectExpression(expressionVisitor.getExpression());

    if (item.getAlias() != null) {
      selectExpression.setAlias(StringUtil.unEscapeIdentifier(item.getAlias().getName()));
    }

    selectExpressions.add(selectExpression);
  }
}