package nl.jkoetsier.uva.dbbench.input.workload.sql;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import nl.jkoetsier.uva.dbbench.input.exception.InvalidQueryException;
import nl.jkoetsier.uva.dbbench.schema.DataModel;
import nl.jkoetsier.uva.dbbench.schema.Entity;
import nl.jkoetsier.uva.dbbench.workload.query.InputRelation;

public class FromVisitor extends FromItemVisitorAdapter {

    private DataModel dataModel = DataModel.getInstance();
    private InputRelation inputRelation;

    public FromVisitor() {
    }

    public InputRelation getInputRelation() {
        return inputRelation;
    }

    @Override
    public void visit(Table table) {
        Entity entity = dataModel.getEntity(table.getName());

        if (entity == null) {
            throw new InvalidQueryException(String.format(
                    "Entity '%s' does not exist", table.getName()
            ));
        }

        InputRelation inputRelation;

        if (table.getAlias() == null) {
            inputRelation = new InputRelation(entity);
        } else {
            inputRelation = new InputRelation(entity, table.getAlias().getName());
        }

        this.inputRelation = inputRelation;
    }

    @Override
    public void visit(SubSelect subSelect) {
        super.visit(subSelect);
    }

    @Override
    public void visit(SubJoin subjoin) {
        super.visit(subjoin);
    }

    @Override
    public void visit(LateralSubSelect lateralSubSelect) {
        super.visit(lateralSubSelect);
    }

    @Override
    public void visit(ValuesList valuesList) {
        super.visit(valuesList);
    }

    @Override
    public void visit(TableFunction valuesList) {
        super.visit(valuesList);
    }

    @Override
    public void visit(ParenthesisFromItem aThis) {
        super.visit(aThis);
    }
}