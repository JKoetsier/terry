package nl.jkoetsier.uva.terry.intrep.workload.expression.operator;

import java.util.HashMap;

public class OperatorFactory {

  private static final HashMap<String, Class<? extends Operator>> mapping;

  static {
    mapping = new HashMap<>();

    mapping.put("and", AndOp.class);
    mapping.put("or", OrOp.class);

    mapping.put("+", PlusOp.class);
    mapping.put("-", MinusOp.class);
    mapping.put("/", DivideOp.class);
    mapping.put("*", MultiplyOp.class);
    mapping.put("%", ModuloOp.class);

    mapping.put("=", EqualsOp.class);
    mapping.put("<>", NeqOp.class);
    mapping.put("!=", NeqOp.class);
    mapping.put("<=", LteOp.class);
    mapping.put("<", LtOp.class);
    mapping.put(">=", GteOp.class);
    mapping.put(">", GtOp.class);
  }

  public static Operator create(String type) {
    Class<? extends Operator> opClass = mapping.get(type.toLowerCase());

    if (opClass == null) {
      throw new RuntimeException(String.format("Can't find class for operator [%s]", type));
    }

    try {
      return opClass.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}


