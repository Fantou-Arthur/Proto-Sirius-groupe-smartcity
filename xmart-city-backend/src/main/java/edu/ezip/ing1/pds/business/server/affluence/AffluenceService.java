package edu.ezip.ing1.pds.business.server.affluence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.affluence.Affluence;
import edu.ezip.ing1.pds.business.dto.affluence.Affluences; 
import edu.ezip.ing1.pds.business.dto.affluence.TreeData;
import edu.ezip.ing1.pds.business.dto.affluence.Pays;
import edu.ezip.ing1.pds.business.dto.affluence.PCode;
import edu.ezip.ing1.pds.business.dto.affluence.Ville;
import edu.ezip.ing1.pds.business.dto.affluence.Rue;
import edu.ezip.ing1.pds.business.dto.affluence.Lieu;
import edu.ezip.ing1.pds.business.dto.affluence.TreeViewData;
import edu.ezip.ing1.pds.business.dto.affluence.SensorInfos;
import edu.ezip.ing1.pds.business.dto.affluence.Sensor;
import edu.ezip.ing1.pds.business.server.queries.Queries;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

import java.io.IOException;
import java.sql.*;

public class AffluenceService {

    private final static String LoggingLabel = "A F F L U E N C E - S e r v i c e";
    private final Logger logger = LoggerFactory.getLogger(LoggingLabel);

    public AffluenceService() {}

    public Response InsertAffluence(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Affluence affluence = mapper.readValue(request.getRequestBody(), Affluence.class);
        final PreparedStatement statement = connection.prepareStatement(Queries.INSERT_AFFLUENCE.getQuery());
        statement.setInt(1, affluence.getIdPlace());
        statement.setInt(2, affluence.getNbrPers());
        statement.setInt(3, affluence.getNbrDepart());
        statement.setInt(4, affluence.getNbrArriver());
        logger.info("add statement : {}", statement);
        statement.executeUpdate();

        return  new Response(request.getRequestId(), mapper.writeValueAsString(statement));

    }

    public Response GetTreeView(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper mapper = new ObjectMapper();

        final Statement stmt = connection.createStatement();
        final ResultSet res = stmt.executeQuery(Queries.GET_TREE_VIEW.getQuery());

        //logger.info("res depuis la database de la demande de treeview data : {}", mapper.writeValueAsString(res));

        /*
        TreeData root = new TreeData("Root");

        while (res.next()) {
            
            Pays pays = new Pays(res.getString(1));

            root.addPays(pays);
            
            PCode pCode = new PCode(res.getString(4));

            root.getPays(pays.getName()).addPCode(pCode);

            Ville ville = new Ville(res.getString(2));

            root.getPays(pays.getName()).getPCode(pCode.getName()).addVille(ville);

            Rue rue = new Rue(res.getString(3));

            root.getPays(pays.getName()).getPCode(pCode.getName()).getVille(ville.getName()).addRue(rue);

            Lieu lieu = new Lieu(res.getString(7));

            root.getPays(pays.getName()).getPCode(pCode.getName()).getVille(ville.getName()).getRue(rue.getName()).addLieu(lieu);

            Sensor sensor = new Sensor(res.getInt(16), res.getString(13), res.getBoolean(15), res.getString(14), res.getInt(12) );

            root.getPays(pays.getName()).getPCode(pCode.getName()).getVille(ville.getName()).getRue(rue.getName()).getLieu(lieu.addSensor(sensor);
        }*/
        
        
        TreeViewData root = new TreeViewData();

        while (res.next()) {
            root.addSensor(res.getString(13), new SensorInfos(new ArrayList<String>(Arrays.asList(res.getString(1), res.getString(4), res.getString(2), res.getString(3), res.getString(7))), new Sensor(res.getInt(16), res.getString(13), res.getBoolean(15), res.getString(14), res.getInt(12) )));
        }
        
        logger.info("tree in backend before sending : {}", root);

        /*

        res : String, String, String, int, String, int, String, int, int

        query : SELECT a.country, a.city, a.streetName, a.postalCode, a.city, a.id, p.name, p.type, p.description, p.maxCapacity, p.peakHours, p.id, s.name, s.model, s.isActive, s.id FROM Adresses AS a, Places AS p, sensor AS s WHERE a.id=p.id AND p.id=s.id ORDER BY p.name
                        1           2           3               4       5       6       7       8           9               10          11       12     13      14      15          16      
        ex :

        */
        
        return  new Response(request.getRequestId(), mapper.writeValueAsString(root));
        
    }

    public Response DeleteAffluence(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Affluence affluence = mapper.readValue(request.getRequestBody(), Affluence.class);
        final PreparedStatement statement = connection.prepareStatement(Queries.DELETE_AFFLUENCE.getQuery());
        statement.setInt(1, affluence.getId());
        statement.setInt(2, affluence.getIdPlace());
        logger.info("delete statement : {}", statement);
        statement.executeUpdate();

        return  new Response(request.getRequestId(), mapper.writeValueAsString(statement));

    }

    public Response EditAffluence(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Affluence affluence = mapper.readValue(request.getRequestBody(), Affluence.class);
        logger.info("request body : {}", request.getRequestBody());
        final PreparedStatement statement = connection.prepareStatement(Queries.EDIT_AFFLUENCE.getQuery());
        statement.setInt(1, affluence.getNbrPers());
        statement.setInt(2, affluence.getNbrDepart());
        statement.setInt(3, affluence.getNbrArriver());
        statement.setInt(4, affluence.getId());
        statement.setInt(5, affluence.getIdPlace());
        logger.info("edit statement : {}", statement);
        statement.executeUpdate();

        return  new Response(request.getRequestId(), mapper.writeValueAsString(statement));

    }


    public Response SelectAllAffluence(final Request request, final Connection connection) throws SQLException, JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Statement stmt = connection.createStatement();
        final ResultSet res = stmt.executeQuery(Queries.SELECT_ALL_AFFLUENCES.getQuery());
        logger.info("ResultSet : {}", res);
        Affluences affluences = new Affluences();
        while (res.next()) {
            logger.info("res select all : {}", res);
            Affluence affluence = new Affluence();
            affluence.setId(res.getInt(1));
            affluence.setNbrPers(res.getInt(2));
            affluence.setNbrDepart(res.getInt(3));
            affluence.setNbrArriver(res.getInt(4));
            affluence.setIdPlace(res.getInt(5));
            logger.info("affluence : {}", affluence);
            affluences.add(affluence);
        }
        logger.info("affluences list (inaffluenceService): {}", affluences);
        return new Response(request.getRequestId(), objectMapper.writeValueAsString(affluences));
    }

}
