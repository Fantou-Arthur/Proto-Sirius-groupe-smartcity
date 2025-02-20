package edu.ezip.ing1.pds.business.server.capteur;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.capteur.Capteur;
import edu.ezip.ing1.pds.business.dto.Student;
import edu.ezip.ing1.pds.business.dto.Students;
import edu.ezip.ing1.pds.business.dto.capteur.Capteurs;
import edu.ezip.ing1.pds.business.server.capteur.CapteurQueries;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;

public class CapteurService {

    private final static String LoggingLabel = "P l a c e - S e r v i c e";
    private final Logger logger = LoggerFactory.getLogger(LoggingLabel);

    public CapteurService() {}

    public Response InsertCapteur(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Capteur capteur = mapper.readValue(request.getRequestBody(), Capteur.class);
        final PreparedStatement statement = connection.prepareStatement(CapteurQueries.INSERT_CAPTEUR.getQuery());
        statement.setString(1, capteur.getName());
        statement.setString(2, capteur.getAddress());
        statement.setInt(3, capteur.getMaxCapacity());
        statement.executeUpdate();

        final ResultSet resultSet = statement.executeQuery("select * from Capteurs");
        resultSet.next();
        return  new Response(request.getRequestId(), mapper.writeValueAsString(capteur));

    }


    public Response SelectAllCapteurs(final Request request, final Connection connection) throws SQLException, JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Statement stmt = connection.createStatement();
        final ResultSet res = stmt.executeQuery(CapteurQueries.SELECT_ALL_CAPTEURS.getQuery());
        Capteurs capteurs = new Capteurs();
        while (res.next()) {
            Capteur capteur = new Capteur();
            capteur.setName(res.getString(1));
            capteur.setAddress(res.getString(2));
            capteur.setMaxCapacity(res.getInt(3));
            capteurs.add(capteur);
        }
        return new Response(request.getRequestId(), objectMapper.writeValueAsString(capteurs));
    }

}
