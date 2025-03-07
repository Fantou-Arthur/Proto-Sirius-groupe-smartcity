package edu.ezip.ing1.pds.business.server.capteur;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.capteur.Capteur;
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
        statement.setInt(1, capteur.getId());
        statement.setString(2, capteur.getName());
        statement.setBoolean(3, capteur.getState());
        statement.setInt(4, capteur.getId_lieu());
        statement.executeUpdate();

        final ResultSet resultSet = statement.executeQuery("select * from sensor");
        resultSet.next();
        return  new Response(request.getRequestId(), mapper.writeValueAsString(capteur));

    }


    public Response SelectAllCapteurs(final Request request, final Connection connection) throws SQLException, JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Statement stmt = connection.createStatement();
        final ResultSet res = stmt.executeQuery(CapteurQueries.SELECT_ALL_CAPTEURS.getQuery());
        Capteurs capteurs = new Capteurs();
        System.out.println("avant la boucle");
        while (res.next()) {
            Capteur capteur = new Capteur();
            capteur.setId(res.getInt(1));
            capteur.setName(res.getString(2));
            capteur.setState(res.getBoolean(3));
            capteur.setId_lieu(res.getInt(4));
            capteurs.add(capteur);
            System.out.println("Le capteur :" + capteur);
        }
        return new Response(request.getRequestId(), objectMapper.writeValueAsString(capteurs));
    }

}
