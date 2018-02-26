package com.iHome;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jasypt.util.password.StrongPasswordEncryptor;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
/**
 *
 * @author kskoul
 */
public class User {
    private   DB db;

    public User() {
        db = new DB();
    }

    public JsonObject checkUser(String email, String password) throws Exception {

        Connection con = null;
        String sqlquery = "SELECT * FROM user WHERE email = ?";

        JsonObjectBuilder object = Json.createObjectBuilder();

        try {
            db.open();
            con = db.getConnection();


            PreparedStatement stmt1 = con.prepareStatement(sqlquery);

            stmt1.setString(1, email);
            ResultSet rs = stmt1.executeQuery();
            System.out.println(rs.wasNull());
            if (!rs.wasNull()) {



//                if (passwordEncryptor.checkPassword(password, encryptedPassword)) {
                    // correct!
                    while (rs.next()) {

                        object.add("iduser", rs.getInt("iduser"))
                                .add("firstname", rs.getString("firstname"))
                                .add("lastname", rs.getString("lastname"))
                                .add("currentadress", rs.getString("currentadress"))
                                .add("email", rs.getString("email"))
                                .add("password", rs.getString("password"))
                                .add("ifowner", rs.getInt("ifowner"));

                    }
                JsonObject login = Json.createObjectBuilder()
                        .add("body", object.build()).build();

                StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
//                String encryptedPassword = passwordEncryptor.encryptPassword(login.getJsonObject("body").getString("password"));
                if (passwordEncryptor.checkPassword(password, login.getJsonObject("body").getString("password"))) {
                    rs.close(); //closing ResultSet
                    stmt1.close(); // closing PreparedStatement.

                    db.close(); // closing connection

//                    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("secret");
//                    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

                    String jwt = Jwts.builder()
                            .setSubject("users/TzMUocMF4p")
                            .setExpiration(new Date(1300819380))
                            .claim("name", "Robert Token Man")
                            .claim("scope", "self groups/admins")
                            .signWith(
                                    SignatureAlgorithm.HS512,
                                    "secret".getBytes("UTF-8")
                            )
                            .compact();
                    JsonObject log = Json.createObjectBuilder()
                            .add("body", login.getJsonObject("body"))
                            .add("token", jwt).build();
                    System.out.println(log);
                    return log;
                } else {
                    rs.close(); //closing ResultSet
                    stmt1.close(); // closing PreparedStatement.

                    db.close(); // closing connection


                    return object.build();
                }

            } else {
                rs.close(); //closing ResultSet
                stmt1.close(); // closing PreparedStatement.

                db.close(); // closing connection


                return object.build();
            }

        } catch (Exception e) {

            throw new Exception("Some error occured");

        } finally {
            try {
                db.close();
            } catch (Exception e) {
                //no need to do anything...
            }
        }
    }

    public boolean enrollUser(String firstName, String lastName, String currentadress, String email, String password) throws Exception {
        Connection con = null;
        String sqlquery = "INSERT INTO user (firstname, lastname, currentadress, email, password, ifowner) VALUES (?,?,?,?,?,0)";

        JsonObjectBuilder object = Json.createObjectBuilder();

        try {
            StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
            String encryptedPassword = passwordEncryptor.encryptPassword(password);
            System.out.println(encryptedPassword);
            db.open();
            con = db.getConnection();

            PreparedStatement stmt1 = con.prepareStatement(sqlquery);

            stmt1.setString(1, firstName);
            stmt1.setString(2, lastName);
            stmt1.setString(3, currentadress);
            stmt1.setString(4, email);
            stmt1.setString(5, encryptedPassword);
            stmt1.executeUpdate();


                 //closing ResultSet
                stmt1.close(); // closing PreparedStatement.

                db.close(); // closing connection

            return true;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            try {
                db.close();
            } catch (Exception e) {
                //no need to do anything...
            }
        }
    }
}
