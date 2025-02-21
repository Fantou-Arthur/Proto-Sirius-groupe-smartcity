package edu.ezip.ing1.pds.business.server.affluence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.affluence.Affluence;
import edu.ezip.ing1.pds.business.dto.affluence.Affluences;
import edu.ezip.ing1.pds.business.server.affluence.AffluenceQueries;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;

public class AffluenceService {

    private final static String LoggingLabel = "A F F L U E N C E - S e r v i c e";
    private final Logger logger = LoggerFactory.getLogger(LoggingLabel);

    public AffluenceService() {}

    public Response InsertAffluence(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Affluence affluence = mapper.readValue(request.getRequestBody(), Affluence.class);
        final PreparedStatement statement = connection.prepareStatement(AffluenceQueries.INSERT_AFFLUENCE.getQuery());
        statement.setInt(1, affluence.getId());
        statement.setInt(2, affluence.getNbrPers());
        statement.setInt(3, affluence.getNbrDepart());
        statement.setInt(4, affluence.getNbrArriver());
        statement.executeUpdate();

        return  new Response(request.getRequestId(), mapper.writeValueAsString(affluence));

    }

    public Response DeleteAffluence(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Affluence affluence = mapper.readValue(request.getRequestBody(), Affluence.class);
        final PreparedStatement statement = connection.prepareStatement(AffluenceQueries.DELETE_AFFLUENCE.getQuery());
        statement.setInt(1, affluence.getId());
        statement.setInt(2, affluence.getIdPlace());
        statement.executeUpdate();

        return  new Response(request.getRequestId(), mapper.writeValueAsString(affluence));

    }

    public Response EditAffluence(final Request request, final Connection connection) throws SQLException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Affluence affluence = mapper.readValue(request.getRequestBody(), Affluence.class);
        final PreparedStatement statement = connection.prepareStatement(AffluenceQueries.EDIT_AFFLUENCE.getQuery());
        statement.setInt(1, affluence.getNbrPers());
        statement.setInt(2, affluence.getNbrDepart());
        statement.setInt(3, affluence.getNbrArriver());
        statement.setInt(4, affluence.getId());
        statement.setInt(5, affluence.getIdPlace());

        statement.executeUpdate();

        return  new Response(request.getRequestId(), mapper.writeValueAsString(affluence));

    }


    public Response SelectAllAffluence(final Request request, final Connection connection) throws SQLException, JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Statement stmt = connection.createStatement();
        final ResultSet res = stmt.executeQuery(AffluenceQueries.SELECT_ALL_AFFLUENCES.getQuery());
        Affluences affluences = new Affluences();
        while (res.next()) {
            Affluence affluence = new Affluence();
            affluence.setId(res.getInt(1));
            affluence.setNbrPers(res.getInt(2));
            affluence.setNbrDepart(res.getInt(3));
            affluence.setNbrArriver(res.getInt(4));
            affluence.setIdPlace(res.getInt(5));
            affluences.add(affluence);
        }
        return new Response(request.getRequestId(), objectMapper.writeValueAsString(affluences));
    }

}
