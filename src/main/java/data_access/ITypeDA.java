package data_access;

import model.Type;

import java.sql.SQLException;

public interface ITypeDA {

    /**
     * Returns a type by it's name
     *
     * @param typeName given type name
     * @return type by given name
     * @throws SQLException
     */
    Type getTypeByName(String typeName) throws SQLException;

}
