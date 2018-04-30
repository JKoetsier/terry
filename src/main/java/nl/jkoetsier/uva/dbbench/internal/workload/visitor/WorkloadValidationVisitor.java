package nl.jkoetsier.uva.dbbench.internal.workload.visitor;

import nl.jkoetsier.uva.dbbench.input.exception.NotMatchingWorkloadException;
import nl.jkoetsier.uva.dbbench.internal.schema.Entity;
import nl.jkoetsier.uva.dbbench.internal.schema.Schema;
import nl.jkoetsier.uva.dbbench.internal.workload.Query;
import nl.jkoetsier.uva.dbbench.internal.workload.Workload;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.BinExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.Cast;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.ExpressionList;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FieldExpression;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.FunctionExpr;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.IsNullExpr;
import nl.jkoetsier.uva.dbbench.internal.workload.expression.NullValue;
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

public class WorkloadValidationVisitor extends WorkloadVisitor {

  private Schema schema;

  public WorkloadValidationVisitor(Schema schema) {
    this.schema = schema;
  }

  @Override
  public void visit(DateConstant dateConstant) {

  }

  @Override
  public void visit(DoubleConstant doubleConstant) {

  }

  @Override
  public void visit(LongConstant longConstant) {

  }

  @Override
  public void visit(NullValue nullConstant) {

  }

  @Override
  public void visit(StringConstant stringConstant) {

  }

  @Override
  public void visit(AndOp andOp) {

  }

  @Override
  public void visit(DivideOp divideOp) {

  }

  @Override
  public void visit(EqualsOp equalsOp) {

  }

  @Override
  public void visit(GteOp gteOp) {

  }

  @Override
  public void visit(GtOp gtOp) {

  }

  @Override
  public void visit(LteOp lteOp) {

  }

  @Override
  public void visit(LtOp ltOp) {

  }

  @Override
  public void visit(MinusOp minusOp) {

  }

  @Override
  public void visit(ModuloOp moduloOp) {

  }

  @Override
  public void visit(MultiplyOp multiplyOp) {

  }

  @Override
  public void visit(NeqOp neqOp) {

  }

  @Override
  public void visit(OrOp orOp) {

  }

  @Override
  public void visit(PlusOp plusOp) {

  }

  @Override
  public void visit(BinExpression binExpression) {

  }

  @Override
  public void visit(FieldExpression fieldExpression) {

  }

  @Override
  public void visit(FullJoin fullJoin) {
    if (fullJoin.getOnExpression() != null) {
      fullJoin.getOnExpression().validate(fullJoin.getExposedFields());
    }
  }

  @Override
  public void visit(InnerJoin innerJoin) {
    if (innerJoin.getOnExpression() != null) {
      innerJoin.getOnExpression().validate(innerJoin.getExposedFields());
    }
  }

  @Override
  public void visit(InputRelation inputRelation) {
    Entity entity = schema.getEntity(inputRelation.getTableName());

    if (entity == null) {
      throw new NotMatchingWorkloadException(String.format(
          "Entity '%s' does not exist", inputRelation.getTableName()
      ));
    }

    inputRelation.setEntity(entity);

    if (inputRelation.getTableAlias() != null) {
      inputRelation.setExposedFields(ExposedFields.create(entity, inputRelation.getTableAlias()));
    } else {
      inputRelation.setExposedFields(ExposedFields.create(entity));
    }

    inputRelation.setValidated(true);
  }

  @Override
  public void visit(OuterJoin outerJoin) {
    if (outerJoin.getOnExpression() != null) {
      outerJoin.getOnExpression().validate(outerJoin.getExposedFields());
    }
  }

  @Override
  public void visit(Projection projection) {
    ExposedFields inputExposedFields = projection.getInput().getExposedFields();

    // do some magic. check if exposedfields match with selectExpressions

    if (projection.getSelectExpressions() != null) {
      for (SelectExpression selectExpression : projection.getSelectExpressions()) {
        selectExpression.validate(inputExposedFields);
      }
    }
    // setExposedFields on projection
  }

  @Override
  public void visit(Selection selection) {
    if (selection.getWhereExpression() != null) {
      selection.getWhereExpression().validate(selection.getExposedFields());
    }
  }

  @Override
  public void visit(Union union) {

  }

  @Override
  public void visit(Query query) {

  }

  @Override
  public void visit(Workload workload) {

  }

  @Override
  public void visit(FunctionExpr functionExpr) {

  }

  @Override
  public void visit(ExpressionList expressionList) {

  }

  @Override
  public void visit(IsNullExpr isNullExpr) {

  }

  @Override
  public void visit(Rename rename) {

  }

  @Override
  public void visit(SelectExpression selectExpression) {

  }

  @Override
  public void visit(Cast cast) {

  }
}
