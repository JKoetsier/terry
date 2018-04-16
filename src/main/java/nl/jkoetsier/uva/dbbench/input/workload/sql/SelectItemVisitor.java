package nl.jkoetsier.uva.dbbench.input.workload.sql;

import net.sf.jsqlparser.statement.select.*;
import nl.jkoetsier.uva.dbbench.input.exception.InvalidQueryException;
import nl.jkoetsier.uva.dbbench.schema.DataModel;
import nl.jkoetsier.uva.dbbench.schema.Entity;
import nl.jkoetsier.uva.dbbench.schema.fields.Field;
import nl.jkoetsier.uva.dbbench.workload.query.*;

import java.util.ArrayList;
import java.util.List;

public class SelectItemVisitor extends SelectItemVisitorAdapter {

    private Selection selection;
    private List<FieldRef> fieldRefs;

    private DataModel dataModel = DataModel.getInstance();

    public SelectItemVisitor(Selection selection) {
        this.selection = selection;
        this.fieldRefs = new ArrayList<>();
    }

    public Relation getRelation() {
        if (fieldRefs.size() > 0) {
            Projection projection = new Projection(new FieldRefs(fieldRefs));
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
        List<FieldRef> fieldRefList = selection.getFieldRefsForTable(columns.getTable().getName());
        fieldRefs.addAll(fieldRefList);
    }

    @Override
    public void visit(SelectExpressionItem item) {
        FieldRef fieldRef = matchExpressionItem(item.toString());


        if (fieldRef != null) {
            fieldRefs.add(fieldRef);
        } else {
            // TODO handle different kind of expressions
        }

    }

    protected boolean isValidTableIdentifier(String string) {
        return string.matches("^[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)?$");
    }

    private FieldRef matchExpressionItem(String item) {
        if (isValidTableIdentifier(item)) {
            FieldRef fieldRef = selection.getFieldRef(item);

            if (fieldRef == null) {
                throw new InvalidQueryException(
                        String.format("Field '%s' does not exist", item)
                );
            }

            return fieldRef;
        }

        return null;
    }


}
