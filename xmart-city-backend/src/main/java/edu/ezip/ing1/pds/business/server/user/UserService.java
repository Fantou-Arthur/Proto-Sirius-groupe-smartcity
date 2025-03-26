package edu.ezip.ing1.pds.business.server.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.business.dto.place.Places;
import edu.ezip.ing1.pds.business.dto.user.User;
import edu.ezip.ing1.pds.business.server.place.PlaceQueries;
import edu.ezip.ing1.pds.business.server.queries.Queries;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserService {
    private final static String LoggingLabel = "U s e r - S e r v i c e";
    private final Logger logger = LoggerFactory.getLogger(LoggingLabel);

    public UserService() {}

    public Response InsertUser(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final User user = objectMapper.readValue(request.getRequestBody(), User.class);
        final PreparedStatement statement = connection.prepareStatement(UserQueries.INSERT_USER.getQuery());
        statement.setString(1, user.getUsername());
        statement.setString(2, encryptPassword(user.getPassword()));
        statement.setString(3, user.getEmail());
        statement.executeUpdate();

        return  new Response(request.getRequestId(), objectMapper.writeValueAsString(user));

    }
    
    public User SelectUserByEmail(String email, final Connection connection) throws SQLException, IOException, NoSuchAlgorithmException {
        final PreparedStatement statement = connection.prepareStatement(UserQueries.SELECT_USER_BY_EMAIL.getQuery());
        statement.setString(1,email);
        final ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            return resultSetToUser(resultSet);
        }
        return null;
    }

    public Response LoginUser(final Request request, final Connection connection) throws IOException, SQLException, NoSuchAlgorithmException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final User user = objectMapper.readValue(request.getRequestBody(), User.class);
        User userData = this.SelectUserByEmail(user.getEmail(),connection);
        logger.info("User data : {}", userData);
        if(userData != null) {
            if(isEqualPassword(user.getPassword(), userData.getPassword())) {
                return new Response(request.getRequestId(), objectMapper.writeValueAsString(userData));
            }else{
                return new Response(request.getRequestId(), objectMapper.writeValueAsString(null));
            }
        }
        return new Response(request.getRequestId(), objectMapper.writeValueAsString(null));
    }



    private User resultSetToUser(final ResultSet resultSet) throws SQLException, NoSuchAlgorithmException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setUsername(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setEmail(resultSet.getString(4));
        return user;
    }

    public String encryptPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public Boolean isEqualPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }
}
