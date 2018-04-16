package nl.jkoetsier.uva.dbbench.input.workload.sql;

import net.sf.jsqlparser.statement.select.*;
import nl.jkoetsier.uva.dbbench.input.exception.InvalidQueryException;
import nl.jkoetsier.uva.dbbench.schema.DataModel;
import nl.jkoetsier.uva.dbbench.schema.Entity;
import nl.jkoetsier.uva.dbbench.schema.fields.Field;
import nl.jkoetsier.uva.dbbench.workload.query.Projection;
import nl.jkoetsier.uva.dbbench.workload.query.Relation;
import nl.jkoetsier.uva.dbbench.workload.query.Selection;

import java.util.ArrayList;
import java.util.List;

public class SelectItemVisitor extends SelectItemVisitorAdapter {

    private Selection selection;
    private List<Field> fields;

    private DataModel dataModel = DataModel.getInstance();

    public SelectItemVisitor(Selection selection) {
        this.selection = selection;
        fields = new ArrayList<>();
    }

    public Relation getRelation() {
        if (fields.size() > 0) {
            Projection projection = new Projection(fields);
            projection.setInput(selection);

            return projection;
        }

        return selection;
    }

    @Override
    public void visit(AllColumns columns) {
        // Do nothing, no projection needed
        super.visit(columns);
    }

    @Override
    public void visit(AllTableColumns columns) {
        Entity entity = dataModel.getEntity(columns.getTable().getName());
        fields.addAll(entity.getFields().values());
    }

    @Override
    public void visit(SelectExpressionItem item) {
        List<Field> expressionFields = matchExpressionItem(item.toString());

        if (expressionFields != null) {
            fields.addAll(expressionFields);
        } else {
            // TODO handle different kind of expressions
        }

    }

    protected boolean isValidTableIdentifier(String string) {
        return string.matches("^[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)?$");
    }

    private List<Field> matchExpressionItem(String item) {
        if (isValidTableIdentifier(item)) {
            List<Field> fields = new ArrayList<>();
            String[] splitted = item.split("\\.");

            if (splitted.length > 1) {
                Entity entity = dataModel.getEntity(splitted[0]);

                if (entity == null) {
                    throw new InvalidQueryException(
                            String.format("Entity '%s' not found in DataModel", splitted[0]));
                }

                Field field = entity.getField(splitted[1]);

                if (field == null) {
                    throw new InvalidQueryException(
                            String.format("Field '%s' not found in entity '%s'", splitted[1],
                                    splitted[0])
                    );
                }
                fields.add(field);

            } else {
                if (!selection.producesField(splitted[0])) {
                    throw new InvalidQueryException(
                            String.format("Field '%s' not present", splitted[0])
                    );
                }

                fields.add(selection.getField(splitted[0]));
            }

            return fields;
        }

        return null;
    }


}
