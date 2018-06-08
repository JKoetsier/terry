package nl.jkoetsier.uva.dbbench.internal.workload.visitor;

import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.schema.Table;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.BinExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Case;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Cast;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.ExpressionList;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FieldExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FunctionExpr;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.InExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.IsNullExpr;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.NullValue;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.RelationExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.SelectAllColumnsExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.SelectExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.DateConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.DoubleConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.LongConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.constant.StringConstant;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.AndOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.DivideOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.EqualsOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.GtOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.GteOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.LtOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.LteOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.MinusOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.ModuloOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.MultiplyOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.NeqOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.OrOp;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.operator.PlusOp;
import nl.jkoetsier.uva.dbbench.internal.workload.query.ExposedFields;
import nl.jkoetsier.uva.dbbench.internal.workload.query.FullJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.InnerJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.InputRelation;
import nl.jkoetsier.uva.dbbench.internal.workload.query.OuterJoin;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Projection;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Rename;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Selection;
import nl.jkoetsier.uva.dbbench.internal.workload.query.Union;
import nl.jkoetsier.uva.dbbench.internal.workload.util.StringFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkloadValidationVisitor extends WorkloadVisitor {

  private static Logger logger = LoggerFactory.getLogger(WorkloadValidationVisitor.class);

  private Schema schema;

  public WorkloadValidationVisitor(Schema schema) {
    this.schema = schema;
  }

  @Override
  public void visit(DateConstant dateConstant) {
    logger.debug("Validating DateConstant");

  }

  @Override
  public void visit(DoubleConstant doubleConstant) {
    logger.debug("Validating DoubleConstant");

  }

  @Override
  public void visit(LongConstant longConstant) {
    logger.debug("Validating LongConstant");

  }

  @Override
  public void visit(NullValue nullConstant) {
    logger.debug("Validating NullValue");
  }

  @Override
  public void visit(StringConstant stringConstant) {
    logger.debug("Validating StringConstant");
  }

  @Override
  public void visit(AndOp andOp) {
    logger.debug("Validating AndOp");
  }

  @Override
  public void visit(DivideOp divideOp) {
    logger.debug("Validating DivideOp");
  }

  @Override
  public void visit(EqualsOp equalsOp) {
    logger.debug("Validating EqualsOp");
  }

  @Override
  public void visit(GteOp gteOp) {
    logger.debug("Validating GteOp");
  }

  @Override
  public void visit(GtOp gtOp) {
    logger.debug("Validating GtOp");
  }

  @Override
  public void visit(LteOp lteOp) {
    logger.debug("Validating LteOp");
  }

  @Override
  public void visit(LtOp ltOp) {
    logger.debug("Validating LtOp");
  }

  @Override
  public void visit(MinusOp minusOp) {
    logger.debug("Validating MinusOp");
  }

  @Override
  public void visit(ModuloOp moduloOp) {
    logger.debug("Validating ModuloOp");
  }

  @Override
  public void visit(MultiplyOp multiplyOp) {
    logger.debug("Validating MultiplyOp");
  }

  @Override
  public void visit(NeqOp neqOp) {
    logger.debug("Validating NeqOp");
  }

  @Override
  public void visit(OrOp orOp) {
    logger.debug("Validating OrOp");
  }

  @Override
  public void visit(PlusOp plusOp) {
    logger.debug("Validating PlusOp");
  }

  @Override
  public void visit(BinExpression binExpression) {
    logger.debug("Validating BinExpression");
  }

  @Override
  public void visit(FieldExpression fieldExpression) {
    logger.debug("Validating FieldExpression");
  }

  @Override
  public void visit(FullJoin fullJoin) {
    logger.debug("Validating FullJoin");
    if (fullJoin.getOnExpression() != null) {
      fullJoin.getOnExpression().validate(fullJoin.getExposedFields());
    }
  }

  @Override
  public void visit(InnerJoin innerJoin) {
    logger.debug("Validating InnerJoin");
    if (innerJoin.getOnExpression() != null) {
      innerJoin.getOnExpression().validate(innerJoin.getExposedFields());
    }
  }

  @Override
  public void visit(InputRelation inputRelation) {
    logger.debug("Validating InputRelation {}", inputRelation.getTableName());
    Table table = schema.getEntity(inputRelation.getTableName());

    if (table == null) {
      throw new NotMatchingWorkloadException(String.format(
          "Table '%s' does not exist", inputRelation.getTableName()
      ));
    }

    inputRelation.setTable(table);

    if (inputRelation.getTableAlias() != null) {
      inputRelation.setExposedFields(ExposedFields.create(table, inputRelation.getTableAlias()));
    } else {
      inputRelation.setExposedFields(ExposedFields.create(table));
    }

    logger.debug("ExposedFields: {}", inputRelation.getExposedFields());
  }

  @Override
  public void visit(OuterJoin outerJoin) {
    logger.debug("Validating OuterJoin");
    if (outerJoin.getOnExpression() != null) {
      outerJoin.getOnExpression().validate(outerJoin.getExposedFields());
    }
  }

  @Override
  public void visit(Projection projection) {
    logger.debug("Validating Projection");
    logger.debug("Have input: {}", projection.getInput());

    ExposedFields inputExposedFields = projection.getInput().getExposedFields();

    // do some magic. check if exposedfields match with selectExpressions
    ExposedFields outputExposedFields = new ExposedFields();

    if (projection.getSelectExpressions() != null) {
      for (SelectExpression selectExpression : projection.getSelectExpressions()) {
        selectExpression.validate(inputExposedFields);

        outputExposedFields.addAll(selectExpression.getExposedFields());
      }
    }

    projection.setExposedFields(outputExposedFields);

    logger.debug("ExposedFields: {}", projection.getExposedFields());
  }

  @Override
  public void visit(Selection selection) {
    logger.debug("Validating Selection {}", selection);
    logger.debug("Have input: {}", selection.getInput());

    if (selection.getWhereExpression() != null) {
      // selection.getWhereExpression().validate(selection.getExposedFields());
    }

    logger.debug("Selection ExposedFields: {}", selection.getExposedFields());
  }

  @Override
  public void visit(Union union) {
    logger.debug("Validating Union");
  }

  @Override
  public void visit(Query query) {
    logger.debug("Validating Query\n{}", StringFormatter.format(query.toString()));
  }

  @Override
  public void visit(Workload workload) {
    logger.debug("Validating Workload");
  }

  @Override
  public void visit(FunctionExpr functionExpr) {
    logger.debug("Validating FunctionExpr");
  }

  @Override
  public void visit(ExpressionList expressionList) {
    logger.debug("Validating ExpressionList");
  }

  @Override
  public void visit(IsNullExpr isNullExpr) {
    logger.debug("Validating IsNullExpr");
  }

  @Override
  public void visit(Rename rename) {
    logger.debug("Validating Rename");
    rename.getInput().getExposedFields();
  }

  @Override
  public void visit(SelectExpression selectExpression) {
    logger.debug("Validating SelectExpression");
  }

  @Override
  public void visit(Case caseExpr) {
    logger.debug("Validating Case");
  }

  @Override
  public void visit(Cast cast) {
    logger.debug("Validating Cast");
  }

  @Override
  public void visit(RelationExpression relationExpression) {
    logger.debug("Validating RelationExpression");
  }

  @Override
  public void visit(InExpression inExpression) {
    logger.debug("Validating InExpression");
    logger.debug("ExposedFields: {}", inExpression.getExposedFields());
    logger.debug("Left: {}", inExpression.getLeftExpression());
    logger.debug("Right: {}", inExpression.getRightExpression());
  }

  @Override
  public void visit(SelectAllColumnsExpression selectAllColumnsExpression) {
    logger.debug("Validating SelectAllColumnsExpression");
  }
}
