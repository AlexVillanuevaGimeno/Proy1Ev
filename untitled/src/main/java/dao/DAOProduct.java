package dao;

import beans.Product;
import beans.User;
import connection.MotorSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DAOProduct {

    private MotorSQL motorSQL;

    private final String tableName = "producto";
    private final String SQL_INSERT = "INSERT INTO " + tableName + " (Id_Producto, Nombre_Producto, Precio_Producto, " +
            "Marca_Producto, Fecha_Subida_Producto, Descripcion_Producto, Imagen_Producto, Id_Usuario)  VALUES ";
    private final String SQL_FINDALL = "SELECT * FROM " + tableName + " WHERE 1=1";

    public DAOProduct(){
        motorSQL = MotorSQL.getMotorSQL();
    }

    public String add(Product bean){
        String sql = "";
        sql = SQL_INSERT;
        sql += "(" + bean.getIdProducto() + ", '" + bean.getNombreProducto() +
                "', '" + bean.getPrecioProducto() + "', '" + bean.getMarcaProducto() +
                "', '" + bean.getFechaSubidaProducto() + "', '" + bean.getDescripcionProducto() +
                "', '" + bean.getImagenProducto() +  "', " + bean.getIdUser() + ")";
        System.out.println(sql);
        motorSQL.connect();
        motorSQL.executeUpdate(sql);
        motorSQL.close();
        return "";
    }

    public ArrayList<Product> findAll(Product product){
        String sql = "";
        sql = SQL_FINDALL;
        System.out.println(product.getIdUser());
        if (product.getIdUser()>0){
           sql += " AND Id_Usuario = " + product.getIdUser();
        }
        System.out.println(sql);
        ArrayList<Product> lstProducts = new ArrayList<>();
        motorSQL.connect();
        ResultSet resultSet = motorSQL.executeQuery(sql);
        try {
            System.out.println("TRY findall usuario");
            while(resultSet.next()){
                Product productAux = new Product();
                productAux.setIdProducto(resultSet.getInt(1));
//                System.out.println("resultSet.getInt(1)");
//                System.out.println(resultSet.getInt(1));
                productAux.setNombreProducto(resultSet.getString(2));
//                System.out.println("resultSet.getString(2)");
//                System.out.println(resultSet.getString(2));
                productAux.setPrecioProducto((double) resultSet.getInt(3));
//                System.out.println("resultSet.getString(3)");
//                System.out.println(resultSet.getString(3));
                productAux.setMarcaProducto(resultSet.getString(4));
//                System.out.println("resultSet.getString(4)");
//                System.out.println(resultSet.getString(4));
                productAux.setFechaSubidaProducto(resultSet.getString(5));
//                System.out.println("resultSet.getString(5)");
//                System.out.println(resultSet.getString(5));
                productAux.setDescripcionProducto(resultSet.getString(6));
//                System.out.println("resultSet.getString(6)");
//                System.out.println(resultSet.getString(6));
                productAux.setImagenProducto(resultSet.getString(7));
//                System.out.println("resultSet.getString(7)");
//                System.out.println(resultSet.getString(7));
                lstProducts.add(productAux);
                System.out.println(productAux.toString());
//                usuarioAux.setId(resultSet.getInt(1));
//                usuarioAux.setUsername(resultSet.getString(2));
//                usuarioAux.setPassword(resultSet.getString(3));
//                System.out.println("im here 2.0");
//                listUsuario.add(usuarioAux);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        motorSQL.close();
        return lstProducts;
    }

}
